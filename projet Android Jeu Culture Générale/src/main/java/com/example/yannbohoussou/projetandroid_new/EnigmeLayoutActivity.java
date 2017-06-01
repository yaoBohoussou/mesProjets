package com.example.yannbohoussou.projetandroid_new;

/**
 * Created by Yann BOHOUSSOU on 20/05/2016.
 */

import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;

public class EnigmeLayoutActivity extends Activity {
    private HashMap<String, Integer> hash=new HashMap<String,Integer>();
    private String pseudo,categorie,reponse="";
    private int niveau=0;
    private int niveau_cat=0;
    private GridAdapter gAdap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enigme_layout);
        hash.put("Sports", Integer.valueOf(1));
        hash.put("Cinéma & TV", Integer.valueOf(10));
        hash.put("Musique", Integer.valueOf(100));
        hash.put("Histoire", Integer.valueOf(1000));
        hash.put("Faune & flore", Integer.valueOf(10000));
        hash.put("Sciences", Integer.valueOf(100000));
        hash.put("Mythologie", Integer.valueOf(1000000));
        hash.put("Langues", Integer.valueOf(10000000));
        Intent intent=getIntent();
        pseudo = intent.getStringExtra("pseudo");
        categorie=intent.getStringExtra("categorie");
        niveau_cat=intent.getIntExtra("niveau", 1);
        gAdap=new GridAdapter(this,"lykkhgvezqxw");
        GridView gview=(GridView) findViewById(R.id.gridview);
        gview.setAdapter(gAdap);
        gview.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                EditText rep=(EditText) findViewById(R.id.reponseEnigme);
                reponse=rep.getText()+gAdap.getItem(position);
                rep.setText(reponse);
            }
        });



    }

    public void go(View view){
        SQLiteDatabase myDb = openOrCreateDatabase("joueursDB",MODE_PRIVATE,null);
        myDb.execSQL("CREATE TABLE IF NOT EXISTS joueurs(pseudo VARCHAR,niveau int,highScore int);");
        Cursor resultSet = myDb.rawQuery("Select * from joueurs where pseudo='"+pseudo+"'",null);
        if (resultSet.getCount()!=0){
            resultSet.moveToNext();
            niveau=resultSet.getInt(resultSet.getColumnIndex("niveau"));
        }
        int i=hash.get(categorie);
        niveau=niveau+i;
        myDb.execSQL("UPDATE joueurs SET niveau='"+niveau+"' WHERE pseudo='"+pseudo+"';");
        resultSet.close();
        myDb.close();
        Intent intent;
        switch (niveau_cat) {
            case 1:
                intent=new Intent(this,Question4rActivity.class);
                break;
            case 2:
                intent=new Intent(this,Question5rActivity.class);
                break;
            case 3:
                intent=new Intent(this,Question6rActivity.class);
                break;
            case 4:
                intent=new Intent(this,Question8rActivity.class);
                break;
            default:
                intent=new Intent(this,AvancementModeNormalActivity.class);
                break;
        }
        intent.putExtra("pseudo", pseudo);
        intent.putExtra("categorie",categorie);
        startActivity(intent);
        finish();
    }
}
