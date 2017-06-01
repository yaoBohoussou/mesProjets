package com.example.yannbohoussou.projetandroid_new;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by Yann BOHOUSSOU on 21/05/2016.
 */
public class enigme_activity extends Activity
{
    private Random rand;
    private HashMap<String, Integer> hash=new HashMap<String,Integer>();
    private String pseudo,categorie,reponse="";
    // private int niveau=0;
    private int niveau_cat=0;
    private GridAdapter gAdap;
    private int niveau;
    private String theme;
    private Button GO_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enigme_layout);

        this.rand = new Random();

        this.GO_button = (Button)findViewById(R.id.reponseEnigme_OK);
        this.GO_button.setOnClickListener(this.clickListenerRok);

        theme = new String("no Theme");
        if(this.getIntent().getExtras()!=null)
        {
            theme = this.getIntent().getExtras().getString("theme");
            niveau = this.getIntent().getExtras().getInt("niveau");
        }



        gAdap=new GridAdapter(this,"lykkhgvezqxw");
        GridView gview=(GridView) findViewById(R.id.gridview);
        gview.setAdapter(gAdap);
        gview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                EditText rep = (EditText) findViewById(R.id.reponseEnigme);
                reponse = rep.getText() + gAdap.getItem(position);
                rep.setText(reponse);
            }
        });
    }

    private View.OnClickListener clickListenerRok = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            {
                go(v);
            }
        }};

    private void go(View view)
    {
        Intent goPartie;//= new Intent(enigme_activity.this, enigme_activity.class);
        if(this.niveau == 1)
        {
            goPartie= new Intent(enigme_activity.this, Partie_niveau2.class);
            goPartie.putExtra("theme",this.theme);
            this.startActivity(goPartie);
        }
        else if(this.niveau == 2)
        {
            goPartie= new Intent(enigme_activity.this, Partie_niveau3.class);
            goPartie.putExtra("theme",this.theme);
            this.startActivity(goPartie);
        }

        else if(this.niveau == 3)
        {
            goPartie= new Intent(enigme_activity.this, Partie_niveau4.class);
            goPartie.putExtra("theme",this.theme);
            this.startActivity(goPartie);
        }
        else if(this.niveau == 4)
        {
            goPartie= new Intent(enigme_activity.this, Partie_niveau5.class);
            goPartie.putExtra("theme",this.theme);
            this.startActivity(goPartie);
        }
        else if(this.niveau == 5)
        {
            ImageView view_ = new ImageView(enigme_activity.this);
            view_.setImageResource(R.drawable.congrats);
            Toast toast = new Toast(enigme_activity.this);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(view);
            toast.show();
        }
        else
        {
            ImageView view_ = new ImageView(enigme_activity.this);
            view_.setImageResource(R.drawable.game_over);
            Toast toast = new Toast(enigme_activity.this);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(view);
            toast.show();
        }

        this.finish();
    }

    private void reponseBad()
    {
        ImageView view = new ImageView(enigme_activity.this);
        view.setImageResource(R.drawable.game_over);
        Toast toast = new Toast(enigme_activity.this);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(view);
        toast.show();
        this.finish();
    }

    private String buildID(int int_ID, String isQorR)
    {
        String q_ID = new String();
        q_ID = "enigme"+isQorR+ new Integer(int_ID).toString();
        return q_ID;
    }

    ArrayList<String> getEnigme(String qID, String rID)
    {
        ArrayList<String> enigme = new ArrayList<String>();
        int enigmeQID = getResources().getIdentifier(qID, "string", getPackageName());
        int enigmeRID = getResources().getIdentifier(rID, "string", getPackageName());

        enigme.add(getResources().getString(enigmeQID));
        enigme.add(getResources().getString(enigmeRID));
        return enigme;
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
        enigme_activity.this.finish();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
    }

}
