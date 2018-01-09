package com.example.vietmoopy.tellusthetruth;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by NicoRi on 27/12/2017.
 */

public class RouletteActivity  extends AppCompatActivity implements View.OnClickListener {

    Roulette roulettePlayer;
    TextView namePlayer;
    Button b_showQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.roulette_joueur);   //AFFICHAGE ROULETTE JOUEUR
        MyApplication mApp = (MyApplication)getApplicationContext();
        roulettePlayer = new Roulette("player");
        namePlayer = (TextView) findViewById(R.id.displayName);
        namePlayer.setText(roulettePlayer.getRandomPlayer(mApp.getPlayersList())); //NOM D'UN JOUEUR AU HASARD

        b_showQuestion = findViewById(R.id.b_show_question);
        b_showQuestion.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(RouletteActivity.this, QuestionActivity.class);
        startActivity(intent);
    }
}
