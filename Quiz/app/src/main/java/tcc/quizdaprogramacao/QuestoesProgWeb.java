package tcc.quizdaprogramacao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


class QuestoesProgWeb extends SQLiteOpenHelper {

    private Context context;
    private static final String DB_NAME = "QuizProgWeb.db";


    private static final int DB_VERSION = 9;
    private static final String TABLE_NAME = "TQ";
    private static final String UID = "_UID";
    private static final String QUESTION = "QUESTION";
    private static final String OPTA = "OPTA";
    private static final String OPTB = "OPTB";
    private static final String OPTC = "OPTC";

    private static final String ANSWER = "ANSWER";
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " + UID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + QUESTION + " VARCHAR(255), " + OPTA + " VARCHAR(255), " + OPTB + " VARCHAR(255), " + OPTC + " VARCHAR(255), " + ANSWER + " VARCHAR(255));";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    QuestoesProgWeb(Context context) {
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
        ArrayList<Questoes> arraylist = new ArrayList<>();

        arraylist.add(new Questoes("Como inserir um comentario no código em HTML?","<Isto é um comentário>","<!--Isto é um comentário-->","!Isto é um comentário!","<!--Isto é um comentário-->"));

        arraylist.add(new Questoes("HTML é uma linguagem de..." ,"HTML não é uma linguagem","Formatação","Marcação","Marcação"));

        arraylist.add(new Questoes("CSS é uma linguagem de..." ,"Marcação","Formatação","CSS não é uma linguagem","Formatação"));

        arraylist.add(new Questoes("Para que serve a tag <head> em HTML?","É usada como cabeçalho","É usada para criar formulários","É usada para mudar a cor de fundo","É usada como cabeçalho"));

        arraylist.add(new Questoes("Quais tags são necessárias para se criar uma tabela?","<table>, <tr> e <td>","Apenas <table>","<form>, <tr> e <td>","<table>, <tr> e <td>"));

        arraylist.add(new Questoes("Para que serve a tag <div> em HTML?","É usada para criar tabelas","Define uma divisão em um documento HTML","É usada para mudar a fonte do texto","Define uma divisão em um documento HTML"));

        arraylist.add(new Questoes("Para que serve o comando echo em PHP?","É usada como cabeçalho","É utilizado em scripts para exibir mensagens na tela","Não serve para nada","É utilizado em scripts para exibir mensagens na tela"));

        arraylist.add(new Questoes("Para que serve o comando GET em PHP?","O método $_GET é usado para receber variáveis através do URL","É usada para mudar a cor de fundo","É usada para criar formulários","O método $_GET é usado para receber variáveis através do URL"));

        arraylist.add(new Questoes("Como acrescentar sobra em um texto?","Usando Color","Usando box-shadow","Usando text-shadow","Usando text-shadow"));

        arraylist.add(new Questoes("Qual unidade de medida NÃO se refere ao tamanho da fonte?(CSS)","em","length","ex","length"));

        arraylist.add(new Questoes("Para que serve a tag <br>","Para mudar a fonte da cor","Não serve para nada","Para realizar quebra de linha no texto","Para realizar quebra de linha no texto"));

        arraylist.add(new Questoes("Quais são os tipos de variáveis escalares em PHP?","Boolean, NULL e Array","Integer, Float e String","Float, String e Object","Integer, Float e String"));

        arraylist.add(new Questoes("Quais são os tipos de variáveis Super Globais da linguagem PHP?","$_REQUEST, $_SESSION, $_SERVER","$_SERVER, $_GET, $_GLOBALS","$_FILE, $_POST, $_GET","$_FILE, $_POST, $_GET"));


        this.addAllQuestions(arraylist);

    }


    private void addAllQuestions(ArrayList<Questoes> allQuestions) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            for (Questoes question : allQuestions) {
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


    List<Questoes> getAllOfTheQuestions() {

        List<Questoes> questionsList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        String coloumn[] = {UID, QUESTION, OPTA, OPTB, OPTC, ANSWER};
        Cursor cursor = db.query(TABLE_NAME, coloumn, null, null, null, null, null);


        while (cursor.moveToNext()) {
            Questoes question = new Questoes();
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
