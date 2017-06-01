package com.example.yannbohoussou.projetandroid_new;

/**
 * Created by Yann BOHOUSSOU on 20/05/2016.
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class ChoixCategorieLayoutActivity extends Activity {
    private String pseudo ;
    private ArrayAdapter<String> myAdapter;
    private List<String> arraylist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choix_categorie_layout);
        Intent intent=getIntent();
        pseudo=intent.getStringExtra("pseudo");
        arraylist=new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.categories)));
        myAdapter= new ArrayAdapter<String>(this, R.layout.joueurs_liste, arraylist);
        ListView list=(ListView) findViewById(R.id.listCategories);
        list.setAdapter(myAdapter);
        list.setOnItemClickListener(
                new OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        // TODO Auto-generated method stub
                        String categorie=parent.getItemAtPosition(position).toString();
                        Intent intent = new Intent(ChoixCategorieLayoutActivity.this,Question3rActivity.class);
                        intent.putExtra("categorie",categorie);
                        intent.putExtra("pseudo", pseudo);
                        startActivity(intent);
                        finish();
                    }

                }
        );
    }
}
