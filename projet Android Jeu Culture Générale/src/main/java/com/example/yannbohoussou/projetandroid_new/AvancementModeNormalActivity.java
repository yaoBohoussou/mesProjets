package com.example.yannbohoussou.projetandroid_new;

/**
 * Created by Yann BOHOUSSOU on 20/05/2016.
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class AvancementModeNormalActivity extends Activity {
    private HashMap<String, Integer> hash=new HashMap<String,Integer>();
    private String[] list;
    private String pseudo;
    private int niveau=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avancement_mode_normal);
        hash.put("Sports", Integer.valueOf(1));
        hash.put("Cinéma & TV", Integer.valueOf(10));
        hash.put("Musique", Integer.valueOf(100));
        hash.put("Histoire", Integer.valueOf(1000));
        hash.put("Faune & flore", Integer.valueOf(10000));
        hash.put("Sciences", Integer.valueOf(100000));
        hash.put("Mythologie", Integer.valueOf(1000000));
        hash.put("Langues", Integer.valueOf(10000000));
        Intent intent=getIntent();
        pseudo=intent.getStringExtra("pseudo");
        SQLiteDatabase myDb = openOrCreateDatabase("joueursDB",MODE_PRIVATE,null);
        myDb.execSQL("CREATE TABLE IF NOT EXISTS joueurs(pseudo VARCHAR,niveau int,highScore int);");
        Cursor resultSet = myDb.rawQuery("Select * from joueurs where pseudo='"+pseudo+"'",null);
        if (resultSet.getCount()!=0){
            resultSet.moveToNext();
            niveau=resultSet.getInt(resultSet.getColumnIndex("niveau"));
        }
        list=getResources().getStringArray(R.array.categories);
        ArrayList<String> arrayList=new ArrayList<String>(Arrays.asList(list));
        MyAdapter myAdapter= new MyAdapter(this,R.layout.my_layout,arrayList ,niveau);
        ListView listView=(ListView) findViewById(R.id.listCategories);
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(
                new OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        // TODO Auto-generated method stub
                        int niveau_cat=niveau;
                        String categorie=parent.getItemAtPosition(position).toString();
                        niveau_cat=niveau_cat/hash.get(categorie);
                        niveau_cat=niveau_cat%10;
                        Intent intent ;
                        switch (niveau_cat) {
                            case 0:
                                intent = new Intent(AvancementModeNormalActivity.this,Question3rActivity.class);
                                break;
                            case 1:
                                intent = new Intent(AvancementModeNormalActivity.this,Question4rActivity.class);
                                break;
                            case 2:
                                intent = new Intent(AvancementModeNormalActivity.this,Question5rActivity.class);
                                break;
                            case 3:
                                intent = new Intent(AvancementModeNormalActivity.this,Question6rActivity.class);
                                break;
                            case 4:
                                intent = new Intent(AvancementModeNormalActivity.this,Question8rActivity.class);
                                break;

                            default:
                                intent = new Intent(AvancementModeNormalActivity.this,Question3rActivity.class);
                                break;
                        }
                        intent.putExtra("categorie",categorie);
                        intent.putExtra("pseudo", pseudo);
                        startActivity(intent);
                        finish();
                    }

                }
        );
    }
}
