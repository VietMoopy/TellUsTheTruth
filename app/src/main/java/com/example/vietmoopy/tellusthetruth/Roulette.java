package com.example.nicori.test_jeu_piece;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by NicoRi on 27/12/2017.
 */

public class Roulette {

    private String type;

    Roulette(String type){
        this.type = type;
    }



    public String getRandomPlayer(ArrayList<Player> playerArrayList){
        Random rand = new Random();
        return playerArrayList.get(rand.nextInt(playerArrayList.size())).getName();
    }
}


