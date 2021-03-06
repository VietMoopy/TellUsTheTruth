package com.example.vietmoopy.tellusthetruth;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by vietmoopy on 28/12/17.
 */

public class PlayerAdapter extends ArrayAdapter<Player> {

    private ArrayAdapter<TextView> arrayTV;

    public PlayerAdapter(Context context, ArrayList<Player> players) {
        super(context, 0, players);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Player player = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_player_item, parent, false);
        }

        // Lookup view for data population
        TextView tvPlayer = (TextView) convertView.findViewById(R.id.tvPlayer);
        EditText etPlayer = (EditText) convertView.findViewById(R.id.etPlayer);
        // Populate the data into the template view using the data object
        player.setName(String.valueOf(etPlayer.getText())); // Met le nom du joueur dans l'objet joueur pour pouvoir la récuperér
        tvPlayer.setText(player.playerNumber); // Affiche le joueur nombre..
        etPlayer.setText(player.name); // Affiche le nom du joueur
        // Return the completed view to render on screen
        return convertView;
    }



}
