package com.example.nicori.test_jeu_piece;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by NicoRi on 28/12/2017.
 */

public class BDD extends SQLiteOpenHelper{

    private static final String TABLE_QUESTIONS = "table_questions";
    private static final String COL_ID = "ID";
    private static final String COL_QUESTION = "Question";

    private static final String CREATE_BDD_QUESTIONS = "CREATE TABLE " + TABLE_QUESTIONS + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_QUESTION + " TEXT NO NULL);";

    public BDD(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BDD_QUESTIONS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //On peut fait ce qu'on veut ici moi j'ai décidé de supprimer la table et de la recréer
        //comme ça lorsque je change la version les id repartent de 0
        db.execSQL("DROP TABLE " + TABLE_QUESTIONS + ";");
        onCreate(db);

    }
}