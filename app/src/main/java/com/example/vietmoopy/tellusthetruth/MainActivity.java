package com.example.vietmoopy.tellusthetruth;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        final Handler handler = new Handler();
        QuestionsBDD questionBdd = new QuestionsBDD(this);
        //f (questionBdd.IsEmpty()) {
            Question question = new Question("Suis-je une biche ?");
            Question question1 = new Question("Suis-je une chevre ?");
            Question question2 = new Question("Suis-je un bouc ?");
            questionBdd.open();
            questionBdd.insertQuestion(question);
            questionBdd.insertQuestion(question1);
            questionBdd.insertQuestion(question2);
            questionBdd.close();
      //  }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                Intent intent = new Intent(MainActivity.this,MenuActivity.class); //Création de l'intention (créé MenuActivity)
                startActivity(intent);  //Lancement de l'intention
            }
        }, 3000);
    }
}
;