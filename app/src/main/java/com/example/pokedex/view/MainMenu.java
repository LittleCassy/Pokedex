package com.example.pokedex.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pokedex.R;
import com.example.pokedex.controller.API;
import com.example.pokedex.model.Pokemon;

import java.util.Collections;
import java.util.Comparator;

public class MainMenu extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_menu);

        API.gatherAllPokemonInfo(this);

        Button myButton = (Button) findViewById(R.id.btnAccess);

        myButton.setOnClickListener(v -> {

            ViewGroup main = (ViewGroup) myButton.getParent();
            main.removeView(myButton);


            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(MainMenu.this, PokedexActivity.class));
                    Collections.sort(API.getMyPokedex(), new Comparator<Pokemon>() {
                        @Override
                        public int compare(Pokemon pokemon, Pokemon t1) {
                            return Integer.compare(pokemon.getNumber(), t1.getNumber());
                        }
                    });
                    finish();
                }
            }, 6000);


        });

    }


}