package tcc.quizdaprogramacao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


class QuestoesRedeActivity extends SQLiteOpenHelper {

    private Context context;
    private static final String DB_NAME = "QuizRede.db";


    private static final int DB_VERSION = 16;
    private static final String TABLE_NAME = "TQ";
    private static final String UID = "_UID";
    private static final String QUESTION = "QUESTION";
    private static final String OPTA = "OPTA";
    private static final String OPTB = "OPTB";
    private static final String OPTC = "OPTC";

    private static final String ANSWER = "ANSWER";
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " + UID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + QUESTION + " VARCHAR(255), " + OPTA + " VARCHAR(255), " + OPTB + " VARCHAR(255), " + OPTC + " VARCHAR(255), " + ANSWER + " VARCHAR(255));";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    QuestoesRedeActivity(Context context) {
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

        /*1*/   arraylist.add(new QuestoesActivity("Qual é a primeira camada do modelo OSI e TCP/IP?","Enlace","Física","Sessão","Física"));
        /*2*/   arraylist.add(new QuestoesActivity("Quais camadas que aparecem no modelo OSI e não aparecem no modelo TCP/IP?","Apresentação e Sessão","Física e Apresentação","Sessão e Aplicação","Apresentação e Sessão"));
        /*3*/   arraylist.add(new QuestoesActivity("O que significa a sigla UTP?","Unfinished Twister Pair (Par bagunçado e inacabado)","Unshield Twisted Pair (Par trançado sem blindagem)","Unmess Twisted Pair (Par trançado não bagunçado)","Unshield Twisted Pair (Par trançado sem blindagem)"));
        /*4*/   arraylist.add(new QuestoesActivity("O que significa a sigla STP?","Silenced Twisted Pair (Par trançado silenciado)","Security Twisted Pair (Par trançado e seguro)","Shielded Twisted Pair (Par trançado com blindagem)","Shielded Twisted Pair (Par trançado com blindagem)"));
        /*5*/   arraylist.add(new QuestoesActivity("O que define o formato e a ordem das mensagens trocadas entre duas ou mais entidades comunicantes?","A vontade do profissional","A entropia","O protocolo","O protocolo"));
        /*6*/   arraylist.add(new QuestoesActivity("São 3 topologias de rede comuns...","Barramento linear, estrela e anel","Roda, quadrado e piramide","Triângulo, circulo e árvore","Barramento linear, estrela e anel"));
        /*7*/   arraylist.add(new QuestoesActivity("DHCP é um protocolo utilizado em redes de computadores que...","bloqueia qualquer conexão suspeita","Permite que as máquinas obtenham conexão com a internet","Permite que as máquinas obtenham um endereço de IP automaticamente","Permite que as máquinas obtenham um endereço de IP automaticamente"));
        /*8*/   arraylist.add(new QuestoesActivity("Qual a principal função do protocolo FTP?","Garantir a segurança de uma transferência","Transferir arquivos de um computador para o outro","Verificar se a conexão é estável","Transferir arquivos de um computador para o outro"));
        /*9*/   arraylist.add(new QuestoesActivity("Quantas camadas existem no modelo OSI e TCP/IP, respectivamente?","Quatro e Sete","Cinco e Seis","Sete e Cinco","Sete e Cinco"));
        /*10*/  arraylist.add(new QuestoesActivity("Qual a função do comando ipconfig?","Identificar o endereço IP de uma máquina","Desligar o computador","Mostrar as configurações do protocolo IP","Mostrar as configurações do protocolo IP"));

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
