package tcc.quizdaprogramacao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


class QuestoesBD extends SQLiteOpenHelper {

    private Context context;
    private static final String DB_NAME = "QuizBD.db";

    //Para atualizar o banco de dados, é só atualizar o numero da versão do banco
    private static final int DB_VERSION = 12;
    //Nome da tabela
    private static final String TABLE_NAME = "TQ";
    //Id da questão
    private static final String UID = "_UID";
    //Questão
    private static final String QUESTION = "QUESTION";
    //Opção A
    private static final String OPTA = "OPTA";
    //Opção B
    private static final String OPTB = "OPTB";
    //Opção C
    private static final String OPTC = "OPTC";

    //Resposta
    private static final String ANSWER = "ANSWER";
    //Criando a tabela e suas colunas
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " + UID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + QUESTION + " VARCHAR(255), " + OPTA + " VARCHAR(255), " + OPTB + " VARCHAR(255), " + OPTC + " VARCHAR(255), " + ANSWER + " VARCHAR(255));";
    //Para dropar a tabela
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    QuestoesBD(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //OnCreate é chamado aqui
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //OnUpgrade é chamado quando atualizamos a versao do banco de dados
        sqLiteDatabase.execSQL(DROP_TABLE);
        onCreate(sqLiteDatabase);
    }
    //Questões do banco
    void allQuestion() {
        ArrayList<Questoes> arraylist = new ArrayList<>();

        /*1*/   arraylist.add(new Questoes("O que significa, em português, a sigla SQL?", "Linguagem de Consulta Estruturada", "Linguagem de Consulta Sintética", "Linguagem de Consulta Padronizada", "Linguagem de Consulta Estruturada"));
        /*2*/   arraylist.add(new Questoes("O que o SQL te permite fazer?", "Programar páginas web em JavaScript", "Acessar, manipular e gerenciar Banco de Dados", "Criar interfaces gráficas para Consultas", "Acessar, manipular e gerenciar Banco de Dados"));
        /*3*/   arraylist.add(new Questoes("O que significa, em português, a sigla DML?", "Dados Manipulados em Linux", "Linguagem de Manipulação de Dados", "Linguagem Massiva de Dados", "Linguagem de Manipulação de Dados"));
        /*4*/   arraylist.add(new Questoes("Qual é o comando para criar uma Base de Dados?", "ALTER DATABASE NomeDaBaseDeDados;", "CREATE DATABASE NomeDaBaseDeDados;", "DATABASE CREATE NomeDaBaseDeDados", "CREATE DATABASE NomeDaBaseDeDados;"));
        /*5*/   arraylist.add(new Questoes("Os comandos DELETE e TRUNCATE removem linhas de uma tabela, mas uma diferença entre eles é que...", "Não existe ROLLBACK para o TRUNCATE mas para o DELETE sim", "O DELETE é permamente e o TRUNCATE é temporário", "Não há diferença entre esses comandos", "Não existe ROLLBACK para o TRUNCATE mas para o DELETE sim"));
        /*6*/   arraylist.add(new Questoes("O que é uma entidade?", "É um espírito que é baixado na tabela", "São abstrações que contêm informações interrelacionadas e coerentes", "É o mesmo que atributo", "São abstrações que contêm informações interrelacionadas e coerentes"));
        /*7*/   arraylist.add(new Questoes("O que é chave primária?", "É como se chama a senha do banco de dados", "É o que indentifica e individualiza um registro", "É o comando que cria o banco de dados", "É o que indentifica e individualiza um registro"));
        /*8*/   arraylist.add(new Questoes("O que é chave estrangeira?", "É o atributo que implementa o relacionamento entre entidades", "É um código em língua estrangeira", "É o que faz a conexão do banco de dados com um aplicativo", "É o atributo que implementa o relacionamento entre entidades"));
        /*9*/   arraylist.add(new Questoes("O que é cardinalidade?", "É o que define o tipo de relacionamento entre as entidades", "São os cards de um banco de dados", "É o que norteia o assunto do banco de dados", "É o que define o tipo de relacionamento entre as entidades"));
        /*10*/  arraylist.add(new Questoes("O que significa MER?", "Modelo Estrangeiro de Relacionamento", "Modelo Entidade Relacionamento", "Mapeamento de Entidades Relacionadas", "Modelo Entidade Relacionamento"));

        // arraylist.add(new Questoes("O que significa, em português, a sigla DQL?", "Linguagem de Consulta de Dados", "Dados Quantitativos em Linguagens", "Linguagem Quotidiana de Dados", "Linguagem de Consulta de Dados"));
        // arraylist.add(new Questoes("O que significa, em português, a sigla DDL?", "Linguagem Definitiva de Dados", "Dados Definidos em Ligações", "Linguagem de Definição de Dados", "Linguagem de Definição de Dados"));
        // arraylist.add(new Questoes("O que significa, em português, a sigla DDL?", "Linguagem Definitiva de Dados", "Dados Definidos em Ligações", "Linguagem de Definição de Dados", "Linguagem de Definição de Dados"));
        // arraylist.add(new Questoes("O que significa, em português, a sigla DDL?", "Linguagem Definitiva de Dados", "Dados Definidos em Ligações", "Linguagem de Definição de Dados", "Linguagem de Definição de Dados"));
        // arraylist.add(new Questoes("O que significa, em português, a sigla DTL?", "Dados Transformados em Linguagem", "Linguagem de Troca de Dados", "Linguagem de Transação de Dados", "Linguagem de Transação de Dados"));
        // arraylist.add(new Questoes("Qual o nome da linguagem SQL da Microsoft?", "MySQL", "T-SQL", "MariaDB", "T-SQL"));
        // arraylist.add(new Questoes("Qual o termo utilizado para Banco de Dados Não Relacionais", "NoSQL", "T-SQL", "Oracle", "NoSQL"));
        // arraylist.add(new Questoes("Em qual década surgiu o SQL?", "1970", "1990", "1960", "1970"));
        // arraylist.add(new Questoes("Qual é a base de dados nativa do Android Studio?", "SQLite", "MySQL", "PostgreSQL", "SQLite"));
        // arraylist.add(new Questoes("O que é um atributo?", "É a informação que compõe uma entidade", "É uma das qualidades do banco de dados", "É o tributo pago ao servidor do SQL", "É a informação que compõe uma entidade"));
        // arraylist.add(new Questoes("Em banco de dados, o que é um relacionamento?", "É quando as colunas estão relacionadas dentro das tabelas", "É o elemento responsável por definir as características das ligações entre as entidades", "É quando as tabelas são amigáveis", "É o elemento responsável por definir as características das ligações entre as entidades"));


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
