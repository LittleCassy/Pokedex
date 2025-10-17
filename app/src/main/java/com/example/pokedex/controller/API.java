package com.example.pokedex.controller;

import android.content.Context;
import android.os.StrictMode;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pokedex.model.Pokemon;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class API {


    static ArrayList<Pokemon> myPokedex = new ArrayList<>();
    private static String[] fullPokeList = new String[1028];
    private static String[] fullURLList = new String[1028];

    public static void obtainAllPokemon(Context ct){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        RequestQueue queue = Volley.newRequestQueue(ct);
        String url = "https://pokeapi.co/api/v2/pokemon?limit=1028&offset=0";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jObj = new JSONObject(response);
                            for (int i = 0; i < jObj.getJSONArray("results").length(); i++) {
                                fullPokeList[i] = jObj.getJSONArray("results").getJSONObject(i).getString("name");
                                fullURLList[i] = jObj.getJSONArray("results").getJSONObject(i).getString("url");


                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(stringRequest);
    }

    public static void gatherAllPokemonInfo(Context ct){

        RequestQueue queue = Volley.newRequestQueue(ct);
        queue.stop();

        for (int i = 0; i < fullPokeList.length; i++) {
            queue.add(gatherIndividualPokemonInfo(fullURLList[i]));
        }

        queue.start();
    }

    static StringRequest gatherIndividualPokemonInfo(String URL){

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jObj = new JSONObject(response);
                            myPokedex.add(new Pokemon(jObj.getString("name"), jObj.getJSONObject("sprites").getString("front_default"), jObj.getInt("id")));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        return stringRequest;
    }

    public static ArrayList<Pokemon> getMyPokedex() {
        return myPokedex;
    }

}