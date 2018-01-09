package com.example.nicori.test_jeu_piece;

import android.text.Editable;

/**
 * Created by vietmoopy on 28/12/17.
 */

public class Player {
    public String name;
    public String playerNumber;

    Player(String name, String playerNumber){
        this.name = name;
        this.playerNumber = playerNumber;
    }

    public String getName(){
        return name;
    }

    public String getPlayerNumber(){
        return playerNumber;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPlayerNumber(String playerNumber) {
        this.playerNumber = playerNumber;
    }

}
