package tcc.quizdaprogramacao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


class QuestoesJava extends SQLiteOpenHelper {

    private Context context;
    private static final String DB_NAME = "QuizJava.db";


    private static final int DB_VERSION = 12;
    private static final String TABLE_NAME = "TQ";
    private static final String UID = "_UID";
    private static final String QUESTION = "QUESTION";
    private static final String OPTA = "OPTA";
    private static final String OPTB = "OPTB";
    private static final String OPTC = "OPTC";

    private static final String ANSWER = "ANSWER";
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " + UID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + QUESTION + " VARCHAR(255), " + OPTA + " VARCHAR(255), " + OPTB + " VARCHAR(255), " + OPTC + " VARCHAR(255), " + ANSWER + " VARCHAR(255));";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    QuestoesJava(Context context) {
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

        arraylist.add(new Questoes("Quem foi considerado o pai do Java?","James Gosling","Isaac Newton","Blaise Pascal","James Gosling"));

        /*1*/   arraylist.add(new Questoes("A linguagem Java e considerada uma linguagem de programação","Orientada a evento","Orientada a Objeto","Estraturada","Orientada a Objeto"));
        /*2*/   arraylist.add(new Questoes("O que significa a sigla JVM?","Java Virtual Machine","Java Vosso Mestre","Java Viewer Machine","Java Virtual Machine"));
        /*3*/   arraylist.add(new Questoes("O que significa a sigla JDK?","Java Doctor Kit","Java Development Kit","Javão do Kit","Java Development Kit"));
        /*4*/   arraylist.add(new Questoes("O que significa a sigla JRE?","Java Runtime Ever","Java Run Ever","Java Runtime Environment","Java Runtime Environment"));
        /*5*/   arraylist.add(new Questoes("O que é  a JDK?","É uma biblioteca de imagens da linguagem Java","É um conjunto de utilitários que permitem criar sistemas de software para a plataforma Java","É um conjunto de utilitários que permitem sistemas de software para a plataforma C++","É um conjunto de utilitários que permitem criar sistemas de software para a plataforma Java"));
        /*6*/   arraylist.add(new Questoes("O que é o JRE?","É uma IDE ultilizada para programar códigos em Javascript","É um plug-in necessário para a execução de programas Java","É uma palavra reservada da linguagem Java","É um plug-in necessário para a execução de programas Java"));
        /*7*/   arraylist.add(new Questoes("Quais dessas palavras sao palavras reservadas do Java?","public, static, void","Java, cup, water","chiporinfula, python, coffee","public, static, void"));
        /*8*/   arraylist.add(new Questoes("O que é a JVM?","É um programa que carrega e executa os aplicativos Java","É uma biblioteca do Java","É uma classe do Java","É um programa que carrega e executa os aplicativos Java"));
        /*9*/   arraylist.add(new Questoes("Quais desses comandos em Java Declara uma variável do tipo Real?","float NomeDaVariavel","string NomeDaVariavel","add NomeDaVariavel","float NomeDaVariavel"));
        /*10*/  arraylist.add(new Questoes("Qual das linhas de codigo converte o dado do tipo String para Inteiro?","private int NomeDaVariavel;","int NomeDaVariavel = Interger.parsenInt","public static void main(String[] args) {};","int NomeDaVariavel = Interger.parsenInt"));

        // arraylist.add(new Questoes("Java é uma linguagem considerada de que nível?","Alto","Médio","Baixo","Alto"));
        // arraylist.add(new Questoes("Quais desses comandos em Java Declara uma variável do tipo Inteiro?","double NomeDaVariavel","int NomeDaVariavel","inteiro NomeDaVariavel","int NomeDaVariavel"));
        // arraylist.add(new Questoes("Quais desses comandos em Java Declara uma variável do tipo String?","string NomeDaVariavel","varchar NomeDaVariavel","eax, NomeDaVariavel","string NomeDaVariavel"));
        // arraylist.add(new Questoes("Quando foi criado o Java?","1960","1990","1970","1990"));
        // arraylist.add(new Questoes("Quais dessas IDEs aceita Java?" ,"NetBeans, Eclipse e VisualCode","DevC++, PyCharm, Eclipse","Mars, NotePad++ e Sublime Text","NetBeans, Eclipse e VisualCode"));
        // arraylist.add(new Questoes("Qual das linhas de codigo importa uma biblioteca do Java?","#Include <NomeDaBliblioteca.h>","import NomeDaBiblioteca","import.NomeDaBiblioteca;","import.NomeDaBiblioteca;"));




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
