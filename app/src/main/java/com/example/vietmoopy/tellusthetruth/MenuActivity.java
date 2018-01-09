package com.example.vietmoopy.tellusthetruth;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import static android.app.PendingIntent.getActivity;


public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    /******CREATION DES VARIABLES******/
    private EditText nom_J1;
    private EditText nom_J2;
    private EditText nom_J3;
    private Button b_roulette;
    private ImageButton b_ajouterPlayer;
    private ListView listView;
    private PlayerAdapter adapter;
    private ArrayList<Player> playersList;
    private AlertDialog warning;

    private int compteur = 3;
    private int compteurVraiJoueurs; // Compte le nombre de vrai joueur (là ou le formulaire n'est pas vide)


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);

        /******AFFECTATION DES VARIABLES******/

        /** Création d'une alerte **/
        AlertDialog.Builder builder = new AlertDialog.Builder(MenuActivity.this);

// Add the buttons
        builder.setPositiveButton(R.string.Ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
            }
        });

        builder.setMessage(R.string.alert_joueur)
                .setTitle(R.string.alert_joueur_titre);
        warning = builder.create(); // Création du message d'alerte à afficher

        /** Création d'une alerte **/

        b_roulette = findViewById(R.id.b_roulette);
        b_ajouterPlayer = findViewById(R.id.b_ajouterPlayer);

        b_roulette.setOnClickListener(this);
        b_ajouterPlayer.setOnClickListener(this);
        playersList = new ArrayList<Player>();

        // Construct the data source
        ArrayList<Player> arrayOfPlayers = new ArrayList<Player>();
        // Create the adapter to convert the array to views
        adapter = new PlayerAdapter(this, arrayOfPlayers);
        initPlayers();
        // Attach the adapter to a ListView
        listView = (ListView) findViewById(R.id.playerlist);
        listView.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        if(v == b_roulette) {
            compteurVraiJoueurs = 0;
            MyApplication mApp = (MyApplication)getApplicationContext(); // Récupère les données globales de l'application
            setPlayersList();
            if(compteurVraiJoueurs >= 3) { // Si le nombre de joueur est supérieur à 3, le jeu se lance
                mApp.setPlayersList(playersList); // Affecte la liste des joueurs à la variable globale afin d'y avoir tout le temps accès
                Intent intent = new Intent(MenuActivity.this, RouletteActivity.class);
                startActivity(intent);
            }
            else { // Pas assez de joueur, on affiche le warning
                warning.show();
            }
        }
        else if(v == b_ajouterPlayer){
            addPlayer();
        }
    }

    public void addPlayer(){ // Ajoute un player dans la liste
        // Add item to adapter
        compteur++;
        String Joueur = "Joueur ";
        Joueur = Joueur + compteur + " :";
        Player player = new Player("", Joueur);
        adapter.add(player);

    }
    public void initPlayers(){ // Initialise 3 champs de joueur (minimum)
        for(int i = 1; i <= compteur; i++){
            String Joueur = "Joueur ";
            Joueur = Joueur + i + " : ";
            Player player = new Player("", Joueur);
            adapter.add(player);
        }
    }

    public void setPlayersList(){ // Set la liste des players avec leur nom pour la roulette
        Player tempPlayer = new Player("A","99"); // Pour actualiser l'affichage la listview
        adapter.add(tempPlayer);
        adapter.remove(tempPlayer);
        playersList = new ArrayList<Player>();
        for(int i = 0; i < adapter.getCount(); i++) { // Parcours la liste des playersadapters pour compter le nombre de vrais joueurs
            System.out.println(adapter.getItem(i).getName() + "print");
            if(!adapter.getItem(i).getName().equals("")) { // Si le nom du joueur n'est pas vide alors
                compteurVraiJoueurs++;
                playersList.add(adapter.getItem(i));
            }
        }
    }


}
