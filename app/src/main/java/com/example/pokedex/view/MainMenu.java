package com.example.pokedex.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pokedex.R;
import com.example.pokedex.controller.API;
import com.example.pokedex.model.Pokemon;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class MainMenu extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_menu);

        API.gatherAllPokemon(this);

        Button myButton = (Button) findViewById(R.id.btnAccess);

        myButton.setOnClickListener(v -> {
            startActivity(new Intent(MainMenu.this, PokedexActivity.class));
            Collections.sort(API.myPokedex, new Comparator<Pokemon>() {
                @Override
                public int compare(Pokemon pokemon, Pokemon t1) {
                    return Integer.compare(pokemon.getNumber(), t1.getNumber());
                }
            });
            finish();
        });

    }


}