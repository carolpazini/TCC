package tcc.quizdaprogramacao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


class QuestoesFundamentosTI extends SQLiteOpenHelper {

    private Context context;
    private static final String DB_NAME = "QuizFundamentosTI.db";


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

    QuestoesFundamentosTI(Context context) {
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

        /*1*/   arraylist.add(new Questoes("O que é uma variável?","Uma constante","Um espaço reservado na memória","Uma linguagem de programaçao","Um espaço reservado na memória"));
        /*2*/   arraylist.add(new Questoes("O que é hardware?","É o software mais importante do computador","É a parte física de um equipamento eletrônico","É uma parte dura do computador","É a parte física de um equipamento eletrônico"));
        /*3*/   arraylist.add(new Questoes("O que é software?","A parte lógica de um equipamento eletrônico","São as peças macias do computador","É uma parte macia do computador","A parte lógica de um equipamento eletrônico"));
        /*4*/   arraylist.add(new Questoes("O que é o CMD?", "É a sigla para Computador Mega Desenvolvedor","É um interpretador de linha de comando","É a sigla para Command Media Disk","É um interpretador de linha de comando"));
        /*5*/   arraylist.add(new Questoes("Quem inventou o algoritmo?","Ada Lovelace","Mary Sommerville","Ada Smith","Ada Lovelace"));
        /*6*/   arraylist.add(new Questoes("São sistemas operacionais...","Penguin, Door e Mc","Linux, Windows e Mac","Office, Safari, Opera","Linux, Windows e Mac"));
        /*7*/   arraylist.add(new Questoes("A melhor alternativa para evitar perda de dados é...","Fazer backups regularmente","Usar senhas com mais de 4 digitos","Não usar computadores públicos","Fazer backups regularmente"));
        /*8*/   arraylist.add(new Questoes("São operadores booleanos...","IN, OUT, OFF","NOT, OR, AND","YES, NO","NOT, OR, AND"));
        /*9*/   arraylist.add(new Questoes("Um computador de grande porte, usado para o processamento de muitos dados é o...","Mainframe","Supercomputador","Computação investível","Mainframe"));
        /*10*/   arraylist.add(new Questoes("Como é chamando o sistema de numeração com base 2?","Sistema decimal","Sistema binário","Sistema duodecimal","Sistema binário"));

        // arraylist.add(new Questoes("O que é Máquina Virtual?","É qualquer equipamento de informática","É uma outra palavra para computador","É um arquivo de computador que se comporta como um computador de verdade","É um arquivo de computador que se comporta como um computador de verdade"));
        // arraylist.add(new Questoes("Quanto vale um byte?","10 bits","2 bits","8 bits","8 bits"));
        // arraylist.add(new Questoes("Como é chamando o sistema de numeração com base 16","Sistema pentadecimal","Sistema hexadecimal","Sistema enneadecimal","Sistema hexadecimal"));
        // arraylist.add(new Questoes("O que significa CPU","Computer Power Unit","Central Processing Unit","Computador Pequeno e Útil","Central Processing Unit"));
        // arraylist.add(new Questoes("Como é chamando o sistema de numeração com base 8","Sistema octal","Sistema ternary","Sistema octadecimal","Sistema octal"));
        // arraylist.add(new Questoes("A primeira geração dos computadores foi marcada pela utilização de","Circuitos integrados","Válvulas","Transistores","Válvulas"));
        // arraylist.add(new Questoes("A menor unidade de armazenamento utilizada pelos computadores é o","Bit","Byte","Megabite","Byte"));


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
