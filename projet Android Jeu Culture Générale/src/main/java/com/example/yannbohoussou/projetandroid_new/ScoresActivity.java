package com.example.yannbohoussou.projetandroid_new;

/**
 * Created by Yann BOHOUSSOU on 20/05/2016.
 */
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ScoresActivity extends Activity {
    private List<String> arraylist;
    private ArrayAdapter<String> myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scores);
        SQLiteDatabase myDb = openOrCreateDatabase("joueursDB",MODE_PRIVATE,null);
        myDb.execSQL("CREATE TABLE IF NOT EXISTS joueurs(pseudo VARCHAR,niveau int,highScore int);");
        Cursor resultSet = myDb.rawQuery("Select pseudo,highscore from joueurs",null);
        arraylist = new ArrayList<String>();
        while(resultSet.moveToNext()) {
            arraylist.add(resultSet.getString(0)+"====>"+resultSet.getString(1));
        }
        resultSet.close();
        myDb.close();
        myAdapter= new ArrayAdapter<String>(this, R.layout.joueurs_liste, arraylist);
        ListView list=(ListView) findViewById(R.id.highScores);
        list.setAdapter(myAdapter);
    }
}
