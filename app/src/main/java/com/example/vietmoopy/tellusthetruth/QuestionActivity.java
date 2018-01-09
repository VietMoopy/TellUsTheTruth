package com.example.nicori.test_jeu_piece;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by NicoRi on 27/12/2017.
 */
public class QuestionActivity extends AppCompatActivity {

    TextView tv_display_question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_activity);
        tv_display_question = findViewById(R.id.displayQuestion);
        QuestionsBDD questionBdd = new QuestionsBDD(this);

        questionBdd.open();
        Question questionFromBdd = questionBdd.getRandomQuestion();
        if(questionFromBdd != null) {
            tv_display_question.setText(questionFromBdd.toString());
        }
    }



}
