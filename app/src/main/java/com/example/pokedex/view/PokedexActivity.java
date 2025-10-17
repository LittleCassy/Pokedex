package com.example.pokedex.view;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.gridlayout.widget.GridLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.pokedex.R;
import com.example.pokedex.controller.API;
import com.example.pokedex.model.Pokemon;

public class PokedexActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pokedex);

        GridLayout myGrid = findViewById(R.id.myGrid);

        for(Pokemon p : API.getMyPokedex()){
            ImageButton myTestButton = new ImageButton(this);

            Glide
                    .with(this)
                    .load(p.getImage())
                    .sizeMultiplier(0.1f)
                    .into(myTestButton);

            myGrid.addView(myTestButton);
        }

    }
}