package tcc.quizdaprogramacao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


class QuestoesMobile extends SQLiteOpenHelper {

    private Context context;
    private static final String DB_NAME = "TQuiz.db";

    //If you want to add more questions or wanna update table values
    //or any kind of modification in db just increment version no.
    private static final int DB_VERSION = 7;
    //Table name
    private static final String TABLE_NAME = "TQ";
    //Id of question
    private static final String UID = "_UID";
    //Question
    private static final String QUESTION = "QUESTION";
    //Option A
    private static final String OPTA = "OPTA";
    //Option B
    private static final String OPTB = "OPTB";
    //Option C
    private static final String OPTC = "OPTC";

    //Answer
    private static final String ANSWER = "ANSWER";
    //So basically we are now creating table with first column-id , sec column-question , third column -option A, fourth column -option B , Fifth column -option C , sixth column -option D , seventh column - answer(i.e ans of  question)
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " + UID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + QUESTION + " VARCHAR(255), " + OPTA + " VARCHAR(255), " + OPTB + " VARCHAR(255), " + OPTC + " VARCHAR(255), " + ANSWER + " VARCHAR(255));";
    //Drop table query
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    QuestoesMobile(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //OnCreate is called only once
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //OnUpgrade is called when ever we upgrade or increment our database version no
        sqLiteDatabase.execSQL(DROP_TABLE);
        onCreate(sqLiteDatabase);
    }

    void allQuestion() {
        ArrayList<Questoes> arraylist = new ArrayList<>();

        arraylist.add(new Questoes("Qual a IDE usada para programar para Android?", "AndroidBeans", "AndroidStudio", "VisualAndroid", "AndroidStudio"));

        arraylist.add(new Questoes("Quando a Computação Móvel nasceu?", "Anos 90", "Anos 2010", "Anos 2000", "Anos 90"));

        arraylist.add(new Questoes("Android, iOS, Symbian, BlackBerryOS são...","Marcas de smartphones","Sistemas Operacionais","Operadoras de telefonia","Sistemas Operacionais"));

        arraylist.add(new Questoes("O banco de dados nativo do AndroidStudio é...","SQL Server","SQLite","AndroiDB","SQLite"));

        arraylist.add(new Questoes("O que significa NFC?","Next Field Common","Near Field Communication","Near Friend Community","Near Field Communication"));

        arraylist.add(new Questoes("O sistema Android foi desenvolvido com o conceito de plataforma...","...pré-fabricada, pois é feito pelo Google e repassada aos fabricantes de smartphones","...aberta, onde os fabricantes podem alterar o código-fonte","...fechada. Os fabricantes não podem alterar o código-fonte","...aberta, onde os fabricantes podem alterar o código-fonte"));

        arraylist.add(new Questoes("Em quantas camadas é desenvolvido o Android?","4","6","3","4"));

        arraylist.add(new Questoes("Para desenvolver aplicativos para Android, é necessária a utilização do...","Android HDK - Hardware Development Kit","Android SDK - Software Development Kit","Android VDK - Version Development Kit","Android SDK - Software Development Kit"));

        arraylist.add(new Questoes("A linguagem de programação para o iOS é...","Swift","AppleTools","Suifite","Swift"));

        arraylist.add(new Questoes("São linguagens de programação para o Android...","Python e JavaScript","Java e Kotlin","Java e R","Java e Kotlin"));

        arraylist.add(new Questoes("No Android Studio, o 'Debug'...","roda o aplicativo no modo de depuração","roda o aplicativo","recompila o projeto" ,"roda o aplicativo no modo de depuração"));

        arraylist.add(new Questoes("No Android Studio, o que é o 'manifest'?","é o manifesto com a história do Android","é o diretório onde está o arquivo com configuração do aplicativo","é um guia de como programar no Android Studio","é o diretório onde está o arquivo com configuração do aplicativo"));

        arraylist.add(new Questoes("No diretório res, estão armazenados...","o resto dos arquivos do app","os resultados do aplicativo","os arquivos referentes à interface do usuário","os arquivos referentes à interface do usuário"));

        arraylist.add(new Questoes("No diretório Gradle Script, estão armazenados...","os testes realizados","os registros do banco de dados","os scripts responsáveis pela compilação do aplicativo","os scripts responsáveis pela compilação do aplicativo"));

        arraylist.add(new Questoes("No Android Studio, o AVD Manager é","uma opção que gerencia emuladores","um ambiente de interface gráfica","uma opção que gerencia todo o Android","uma opção que gerencia emuladores"));

        arraylist.add(new Questoes("No Android Studio, é possível testar a aplicação por meio de:","um smartphone conectado via bluetooth","Emulador ou dispositivo conectado via USB","outro software","Emulador ou dispositivo conectado via USB"));


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
