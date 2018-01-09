package com.example.vietmoopy.tellusthetruth;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NicoRi on 27/12/2017.
 */

public class MyApplication extends Application{

    private ArrayList<Player> players_name;

    public ArrayList<Player> getPlayersList() {
        return players_name;
    }

    public void setPlayersList(ArrayList<Player> playersList) {
        players_name = playersList;
    }
}
