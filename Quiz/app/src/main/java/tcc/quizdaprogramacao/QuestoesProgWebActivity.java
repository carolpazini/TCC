package tcc.quizdaprogramacao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


class QuestoesProgWebActivity extends SQLiteOpenHelper {

    private Context context;
    private static final String DB_NAME = "QuizProgWeb.db";


    private static final int DB_VERSION = 13;
    private static final String TABLE_NAME = "TQ";
    private static final String UID = "_UID";
    private static final String QUESTION = "QUESTION";
    private static final String OPTA = "OPTA";
    private static final String OPTB = "OPTB";
    private static final String OPTC = "OPTC";

    private static final String ANSWER = "ANSWER";
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " + UID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + QUESTION + " VARCHAR(255), " + OPTA + " VARCHAR(255), " + OPTB + " VARCHAR(255), " + OPTC + " VARCHAR(255), " + ANSWER + " VARCHAR(255));";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    QuestoesProgWebActivity(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_TABLE);
        onCreate(sqLiteDatabase);
    }

    void allQuestion() {
        ArrayList<QuestoesActivity> arraylist = new ArrayList<>();

        /*1*/   arraylist.add(new QuestoesActivity("Como inserir um comentario no código em HTML?","<Isto é um comentário>","<!--Isto é um comentário-->","!Isto é um comentário!","<!--Isto é um comentário-->"));
        /*2*/   arraylist.add(new QuestoesActivity("HTML é uma linguagem de..." ,"HTML não é uma linguagem","Formatação","Marcação","Marcação"));
        /*3*/   arraylist.add(new QuestoesActivity("CSS é uma linguagem de..." ,"Marcação","Formatação","CSS não é uma linguagem","Formatação"));
        /*4*/   arraylist.add(new QuestoesActivity("Para que serve a tag <head> em HTML?","É usada como cabeçalho","É usada para criar formulários","É usada para mudar a cor de fundo","É usada como cabeçalho"));
        /*5*/   arraylist.add(new QuestoesActivity("Para que serve a tag <div> em HTML?","É usada para criar tabelas","Define uma divisão em um documento HTML","É usada para mudar a fonte do texto","Define uma divisão em um documento HTML"));
        /*6*/   arraylist.add(new QuestoesActivity("Para que serve o comando echo em PHP?","É usada como cabeçalho","É utilizado em scripts para exibir mensagens na tela","Não serve para nada","É utilizado em scripts para exibir mensagens na tela"));
        /*7*/   arraylist.add(new QuestoesActivity("Para que serve o comando GET em PHP?","O método $_GET é usado para receber variáveis através do URL","É usada para mudar a cor de fundo","É usada para criar formulários","O método $_GET é usado para receber variáveis através do URL"));
        /*8*/   arraylist.add(new QuestoesActivity("No CSS, qual unidade de medida NÃO se refere ao tamanho da fonte?","em","length","ex","length"));
        /*9*/   arraylist.add(new QuestoesActivity("Quais são os tipos de variáveis escalares em PHP?","Boolean, NULL e Array","Integer, Float e String","Float, String e Object","Integer, Float e String"));
        /*10*/   arraylist.add(new QuestoesActivity("Quais são os tipos de variáveis Super Globais da linguagem PHP?","$_REQUEST, $_SESSION, $_SERVER","$_SERVER, $_GET, $_GLOBALS","$_FILE, $_POST, $_GET","$_FILE, $_POST, $_GET"));


        this.addAllQuestions(arraylist);

    }


    private void addAllQuestions(ArrayList<QuestoesActivity> allQuestions) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            for (QuestoesActivity question : allQuestions) {
                values.put(QUESTION, question.getQuestion());
                values.put(OPTA, question.getOptA());
                values.put(OPTB, question.getOptB());
                values.put(OPTC, question.getOptC());

                values.put(ANSWER, question.getAnswer());
                db.insert(TABLE_NAME, null, values);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }
    }


    List<QuestoesActivity> getAllOfTheQuestions() {

        List<QuestoesActivity> questionsList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        String coloumn[] = {UID, QUESTION, OPTA, OPTB, OPTC, ANSWER};
        Cursor cursor = db.query(TABLE_NAME, coloumn, null, null, null, null, null);


        while (cursor.moveToNext()) {
            QuestoesActivity question = new QuestoesActivity();
            question.setId(cursor.getInt(0));
            question.setQuestion(cursor.getString(1));
            question.setOptA(cursor.getString(2));
            question.setOptB(cursor.getString(3));
            question.setOptC(cursor.getString(4));

            question.setAnswer(cursor.getString(5));
            questionsList.add(question);
        }

        db.setTransactionSuccessful();
        db.endTransaction();
        cursor.close();
        db.close();
        return questionsList;
    }
}
