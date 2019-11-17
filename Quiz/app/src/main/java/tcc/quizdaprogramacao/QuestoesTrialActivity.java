package tcc.quizdaprogramacao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;


class QuestoesTrialActivity extends SQLiteOpenHelper {

    private Context context;
    private static final String DB_NAME = "QuizTrial.db";


    private static final int DB_VERSION = 15;
    private static final String TABLE_NAME = "TQ";
    private static final String UID = "_UID";
    private static final String QUESTION = "QUESTION";
    private static final String OPTA = "OPTA";
    private static final String OPTB = "OPTB";
    private static final String OPTC = "OPTC";


    private static final String ANSWER = "ANSWER";
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " + UID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + QUESTION + " VARCHAR(255), " + OPTA + " VARCHAR(255), " + OPTB + " VARCHAR(255), " + OPTC + " VARCHAR(255), " + ANSWER + " VARCHAR(255));";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    QuestoesTrialActivity(Context context) {
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

        /*1*/   arraylist.add(new QuestoesActivity("O que é uma variável?","Uma constante","Um espaço reservado na memória","Uma linguagem de programaçao","Um espaço reservado na memória"));
        /*2*/   arraylist.add(new QuestoesActivity("O que é hardware?","É o software mais importante do computador","É a parte física de um equipamento eletrônico","É uma parte dura do computador","É a parte física de um equipamento eletrônico"));

        /*3*/   arraylist.add(new QuestoesActivity("O que significa a sigla JDK?","Java Doctor Kit","Java Development Kit","Javão do Kit","Java Development Kit"));
        /*4*/   arraylist.add(new QuestoesActivity("O que significa a sigla JRE?","Java Runtime Ever","Java Run Ever","Java Runtime Environment","Java Runtime Environment"));

        /*5*/   arraylist.add(new QuestoesActivity("No Android Studio, o que é o 'manifest'?","é o manifesto com a história do Android","é o diretório onde está o arquivo com configuração do aplicativo","é um guia de como programar no Android Studio","é o diretório onde está o arquivo com configuração do aplicativo"));
        /*6*/   arraylist.add(new QuestoesActivity("No diretório res, estão armazenados...","o resto dos arquivos do app","os resultados do aplicativo","os arquivos referentes à interface do usuário","os arquivos referentes à interface do usuário"));

        /*7*/   arraylist.add(new QuestoesActivity("Para que serve a tag <head> em HTML?","É usada como cabeçalho","É usada para criar formulários","É usada para mudar a cor de fundo","É usada como cabeçalho"));
        /*8*/   arraylist.add(new QuestoesActivity("Para que serve a tag <div> em HTML?","É usada para criar tabelas","Define uma divisão em um documento HTML","É usada para mudar a fonte do texto","Define uma divisão em um documento HTML"));

        /*9*/   arraylist.add(new QuestoesActivity("O que significa a sigla UTP?","Unfinished Twister Pair (Par bagunçado e inacabado)","Unshield Twisted Pair (Par trançado sem blindagem)","Unmess Twisted Pair (Par trançado não bagunçado)","Unshield Twisted Pair (Par trançado sem blindagem)"));
        /*10*/   arraylist.add(new QuestoesActivity("O que significa a sigla STP?","Silenced Twisted Pair (Par trançado silenciado)","Security Twisted Pair (Par trançado e seguro)","Shielded Twisted Pair (Par trançado com blindagem)","Shielded Twisted Pair (Par trançado com blindagem)"));

        /*11*/   arraylist.add(new QuestoesActivity("O que significa, em português, a sigla SQL?", "Linguagem de Consulta Estruturada", "Linguagem de Consulta Sintética", "Linguagem de Consulta Padronizada", "Linguagem de Consulta Estruturada"));
        /*12*/   arraylist.add(new QuestoesActivity("O que o SQL te permite fazer?", "Programar páginas web em JavaScript", "Acessar, manipular e gerenciar Banco de Dados", "Criar interfaces gráficas para Consultas", "Acessar, manipular e gerenciar Banco de Dados"));


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
