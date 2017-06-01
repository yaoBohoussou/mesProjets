package com.example.yannbohoussou.projetandroid_new;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Yann BOHOUSSOU on 20/05/2016.
 */
public class ModeNormal_choixTheme extends Activity
{
    ListView vue = null;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choix_categorie_layout);

        ArrayList<String> themes = new ArrayList<String>();
        vue = (ListView)findViewById(R.id.listCategories);

        String sport = getResources().getString(R.string.sport);
        String cineEttv = getResources().getString(R.string.cineEttv);
        String histoire = getResources().getString(R.string.histoire);
        String musique = getResources().getString(R.string.musique);
        String fauneFlore = getResources().getString(R.string.fauneFlore);
        String science = getResources().getString(R.string.science);
        String mytho = getResources().getString(R.string.mytho);
        String langues = getResources().getString(R.string.langues);

        themes.add(sport);
        themes.add(cineEttv);
        themes.add(histoire);
        themes.add(musique);
        themes.add(fauneFlore);
        themes.add(science);
        themes.add(mytho);
        themes.add(langues);

       // ArrayAdapter<String> adapter =  new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,themes);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.joueurs_liste, themes);
        vue.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id)
            {
                String item = (String)vue.getAdapter().getItem(position);
                affmessage(item);
                commencerPartie(item);
            }
        });

        vue.setAdapter(adapter);
    }
    private void affmessage(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private void commencerPartie(String theme_choisi)
    {
        Intent goPartie= new Intent(ModeNormal_choixTheme.this, Partie_niveau1.class);
        goPartie.putExtra("theme",theme_choisi);
        this.startActivity(goPartie);
    }

    protected void onStart()
    {
        super.onStart();
    }
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }

    @Override
    protected void onStop()
    {
        super.onStop();
    }

    @Override
    protected void onRestart()
    {
        super.onRestart();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
    }}
