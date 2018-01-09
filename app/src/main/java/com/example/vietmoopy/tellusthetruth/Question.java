package com.example.vietmoopy.tellusthetruth;

import java.util.List;

/**
 * Created by NicoRi on 30/12/2017.
 */

public class Question {
    private int id;
    private String question;

    public Question(){}

    public Question(String question){
        this.question = question;
    }

    public int getId(){
            return id;
        }
    public void setId(int id){
            this.id = id;
        }


    public String getQuestion(){
            return question;
        }
    public void setQuestion(String question){
        this.question = question;
    }

    public String toString(){
        return question;
    }
}
