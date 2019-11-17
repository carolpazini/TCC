package tcc.quizdaprogramacao.BD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;


public class DatabaseHelper extends SQLiteOpenHelper {

    public static class FeedEntry implements BaseColumns {

        private static final String TABLE_NAME = "contacts";
        private static final String COLUMN_ID = "Id";
        private static final String COLUMN_EMAIL = "Email";
        private static final String COLUMN_USERNAME = "UserName";
        private static final String COLUMN_PASSWORD = "Password";
    }
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "contacts.db";
    SQLiteDatabase db;

    private static final String SQL_CREATE_ENTRIES=
                    "CREATE TABLE "+ FeedEntry.TABLE_NAME +" ("+
                    FeedEntry.COLUMN_ID + "  INTEGER PRIMARY KEY," +
                    FeedEntry.COLUMN_EMAIL + " TEXT," +
                    FeedEntry.COLUMN_USERNAME + " TEXT,"+
                    FeedEntry.COLUMN_PASSWORD +" TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;


    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }


    public void InsertContacts(Contact contact){
        db=getWritableDatabase();

        String query="SELECT * FROM "+ FeedEntry.TABLE_NAME;
        Cursor cursor=db.rawQuery(query,null);
        int count=cursor.getCount();

        ContentValues contentvalues=new ContentValues();
        contentvalues.put(FeedEntry.COLUMN_ID,count+1);
        contentvalues.put(FeedEntry.COLUMN_EMAIL, contact.GetEmail());
        contentvalues.put(FeedEntry.COLUMN_USERNAME, contact.GetUserName());
        contentvalues.put(FeedEntry.COLUMN_PASSWORD, contact.GetPassword());
        db.insert(FeedEntry.TABLE_NAME,null,contentvalues);
        db.close();
    }

    //usado na classe LoginActivity
    public String LoginIn(String Username){
    db=this.getReadableDatabase();
        String query="SELECT UserName,Password FROM  "+ FeedEntry.TABLE_NAME;
        Cursor corsor=db.rawQuery(query,null);
        String username,password;
        password="Não Encontrado";
        if(corsor.moveToFirst()){
            do{
                username=corsor.getString(0);
                if(username.contentEquals(Username)){
                    password=corsor.getString(1);
                    break;
                }
            }while (corsor.moveToNext());
        }
        return  password;
    }

    //usado na classe SenhaActivity
    public String Recupera(String Email){
        db=this.getReadableDatabase();
        String query="SELECT Email FROM  "+ FeedEntry.TABLE_NAME;
        Cursor corsor=db.rawQuery(query,null);
        String email;
        email="Não Encontrado";
        if(corsor.moveToFirst()){
            do{
                email=corsor.getString(0);
                if(email.contentEquals(Email)){
                    break;
                }
            }while (corsor.moveToNext());
        }
        return  email;
    }

    //usado na classe RecuperaSenhaActivity
    public String MostraSenha(String Password){
        db=this.getReadableDatabase();
        String query="SELECT Password FROM  "+ FeedEntry.TABLE_NAME;
        Cursor cursor=db.rawQuery(query,null);
        String password;
        password="Não Encontrado";
        if(cursor.moveToFirst()){
            do{
                password=cursor.getString(0);
                if(password.contentEquals(Password)){
                    password=cursor.getString(cursor.getColumnIndex(Password));
                    break;
                }
            }while (cursor.moveToNext());
        }
        return  password;
    }

}
