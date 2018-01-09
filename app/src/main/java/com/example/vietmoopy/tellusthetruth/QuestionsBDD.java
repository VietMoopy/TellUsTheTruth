package com.example.nicori.test_jeu_piece;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Random;

/**
 * Created by NicoRi on 28/12/2017.
 */

public class QuestionsBDD {

    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "Questions.db";

    private static final String TABLE_QUESTION = "table_questions";
    private static final String COL_ID = "ID";
    private static final int NUM_COL_ID = 0;
    private static final String COL_QUESTION = "Question";
    private static final int NUM_COL_QUESTION = 1;

    private SQLiteDatabase SQLBdd;

    private BDD myBdd;

    public QuestionsBDD(Context context){
        //On créer la BDD et sa table
        myBdd = new BDD(context, NOM_BDD, null, VERSION_BDD);
    }

    public boolean IsEmpty(){
        String countQuery = "SELECT  * FROM " + TABLE_QUESTION;
        Cursor c = SQLBdd.rawQuery(countQuery, null);
        if ((c.getCount())>0) return false;
        else return true;
    }


    public void open(){
        //on ouvre la BDD en écriture
        SQLBdd = myBdd.getWritableDatabase();
    }

    public void close(){
        //on ferme l'accès à la BDD
        SQLBdd.close();
    }

    public SQLiteDatabase getBDD(){
        return SQLBdd;
    }

    public long insertQuestion(Question question){
        long nullLong = -1;
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)

        if (!rechercheQuestion(question)){
            values.put(COL_QUESTION, question.getQuestion());
            //on insère l'objet dans la BDD via le ContentValues
            return SQLBdd.insert(TABLE_QUESTION, null, values);
        }
        else{
            return nullLong;
        }
    }

    public Question getQuestionWithID(int ID){
        //Récupère dans un Cursor les valeur correspondant à un question contenu dans la BDD
        Cursor c = SQLBdd.query(TABLE_QUESTION, new String[] {COL_ID, COL_QUESTION}, COL_ID + " LIKE \"" + ID +"\"", null, null, null, null);
        return cursorToQuestion(c);
    }

    public Question getRandomQuestion(){
        Random rand = new Random();
        String countQuery = "SELECT  * FROM " + TABLE_QUESTION;
        Cursor c = SQLBdd.rawQuery(countQuery, null);
        int randomID = rand.nextInt(c.getCount());
        return getQuestionWithID(randomID+1);
    }

    //Cette méthode permet de convertir un cursor en un livre
    private Question cursorToQuestion(Cursor c){
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;

        //Sinon on se place sur le premier élément
        c.moveToFirst();
        //On créé un livre
        Question question = new Question();
        //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
        question.setId(c.getInt(NUM_COL_ID));
        question.setQuestion(c.getString(NUM_COL_QUESTION));
        //On ferme le cursor
        c.close();

        //On retourne le livre
        return question;
    }

    public boolean rechercheQuestion(Question question){
        String countQuery = "SELECT  * FROM " + TABLE_QUESTION;
        Cursor c = SQLBdd.rawQuery(countQuery, null);
        for(int i = 1; i < c.getCount(); i++){
            Question questionPresente = getQuestionWithID(i);
            if (questionPresente.toString().equals(question.toString())){
                return true;
            }
        }
        return false;
    }

    /*public int updateQuestion(int id, Questions questions){
        //La mise à jour d'une question dans la BDD fonctionne plus ou moins comme une insertion
        //il faut simplement préciser quelle question on doit mettre à jour grâce à l'ID
        ContentValues values = new ContentValues();
        values.put(COL_QUESTION, questions.getQuestion());
        return bdd.update(TABLE_QUESTION, values, COL_ID + " = " +id, null);
    }

    public int removeQuestionWithID(int id){
        //Suppression d'un livre de la BDD grâce à l'ID
        return bdd.delete(TABLE_QUESTION, COL_ID + " = " +id, null);
    }*/

}