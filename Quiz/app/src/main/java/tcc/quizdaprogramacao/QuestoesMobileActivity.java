package tcc.quizdaprogramacao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


class QuestoesMobileActivity extends SQLiteOpenHelper {

    private Context context;
    private static final String DB_NAME = "QuizMobile.db";

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

    QuestoesMobileActivity(Context context) {
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

        /*1*/   arraylist.add(new QuestoesActivity("Qual a IDE usada para programar para Android?", "AndroidBeans", "AndroidStudio", "VisualAndroid", "AndroidStudio"));
        /*2*/   arraylist.add(new QuestoesActivity("O banco de dados nativo do AndroidStudio é...","SQL Server","SQLite","AndroiDB","SQLite"));
        /*3*/   arraylist.add(new QuestoesActivity("O sistema Android foi desenvolvido com o conceito de plataforma...","...pré-fabricada, pois é feito pelo Google e repassada aos fabricantes de smartphones","...aberta, onde os fabricantes podem alterar o código-fonte","...fechada. Os fabricantes não podem alterar o código-fonte","...aberta, onde os fabricantes podem alterar o código-fonte"));
        /*4*/   arraylist.add(new QuestoesActivity("São linguagens de programação para o Android...","Python e JavaScript","Java e Kotlin","Java e R","Java e Kotlin"));
        /*5*/   arraylist.add(new QuestoesActivity("No Android Studio, o que é o 'manifest'?","é o manifesto com a história do Android","é o diretório onde está o arquivo com configuração do aplicativo","é um guia de como programar no Android Studio","é o diretório onde está o arquivo com configuração do aplicativo"));
        /*6*/   arraylist.add(new QuestoesActivity("No diretório res, estão armazenados...","o resto dos arquivos do app","os resultados do aplicativo","os arquivos referentes à interface do usuário","os arquivos referentes à interface do usuário"));
        /*7*/   arraylist.add(new QuestoesActivity("No Android Studio, o AVD Manager é...","uma opção que gerencia emuladores","um ambiente de interface gráfica","uma opção que gerencia todo o Android","uma opção que gerencia emuladores"));
        /*8*/   arraylist.add(new QuestoesActivity("No Android Studio, é possível testar a aplicação por meio de...","um smartphone conectado via bluetooth","Emulador ou dispositivo conectado via USB","outro software","Emulador ou dispositivo conectado via USB"));
        /*9*/   arraylist.add(new QuestoesActivity("No Android Studio, o 'Debug'...","roda o aplicativo no modo de depuração","roda o aplicativo","recompila o projeto" ,"roda o aplicativo no modo de depuração"));
        /*10*/   arraylist.add(new QuestoesActivity("No diretório Gradle Script, estão armazenados...","os testes realizados","os registros do banco de dados","os scripts responsáveis pela compilação do aplicativo","os scripts responsáveis pela compilação do aplicativo"));


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
