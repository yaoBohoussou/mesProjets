package com.example.yannbohoussou.projetandroid_new;

/**
 * Created by Yann BOHOUSSOU on 20/05/2016.
 */
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ChoixJoueurModeSurvieActivity extends Activity {
    private ArrayAdapter<String> myAdapter;
    private List<String> arraylist;
    private String mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choix_joueur_mode_survie);
        Intent intent=getIntent();
        mode=intent.getStringExtra("mode");
        SQLiteDatabase myDb = openOrCreateDatabase("joueursDB",MODE_PRIVATE,null);
        myDb.execSQL("CREATE TABLE IF NOT EXISTS joueurs(pseudo VARCHAR,niveau int,highScore int);");
        Cursor resultSet = myDb.rawQuery("Select pseudo from joueurs",null);
        arraylist = new ArrayList<String>();
        while(resultSet.moveToNext()) {
            arraylist.add(resultSet.getString(0));
        }
        resultSet.close();
        myDb.close();
        myAdapter= new ArrayAdapter<String>(this, R.layout.joueurs_liste, arraylist);
        ListView list=(ListView) findViewById(R.id.listScores);
        list.setAdapter(myAdapter);
        list.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent,
                                            View view, int position, long id) {
                        // TODO Auto-generated method stub
                        String pseudo=parent.getItemAtPosition(position).toString();
                        if(mode.equals("campagne")){
                            Intent intent = new Intent(ChoixJoueurModeSurvieActivity.this,AvancementModeNormalActivity.class);
                            intent.putExtra("pseudo", pseudo);
                            startActivity(intent);
                            finish();}
                        else{
                            if(mode.equals("survival")){
                                Intent intent = new Intent(ChoixJoueurModeSurvieActivity.this,SurvivalActivityActivity.class);
                                intent.putExtra("pseudo", pseudo);
                                startActivity(intent);
                                finish();
                            }
                        }
                    }
                });
        list.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {

                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent,
                                                   View view, int position, long id) {
                        // TODO Auto-generated method stub
                        final String pseudoToDelete=parent.getItemAtPosition(position).toString();
                        new AlertDialog.Builder(ChoixJoueurModeSurvieActivity.this).setMessage("Are you sure you want to delete this pseudo?")
                                .setPositiveButton("yes", new DialogInterface.OnClickListener()
                                {
                                    public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {
                                        SQLiteDatabase db = openOrCreateDatabase("joueursDB",MODE_PRIVATE,null);
                                        db.execSQL("CREATE TABLE IF NOT EXISTS joueurs(pseudo VARCHAR,niveau int,highScore int);");
                                        db.execSQL("DELETE FROM joueurs  WHERE pseudo='"+pseudoToDelete+"';");
                                        db.close();
                                        arraylist.remove(arraylist.indexOf(pseudoToDelete));
                                        myAdapter.notifyDataSetChanged();

                                    }
                                })
                                .setNegativeButton("no", new DialogInterface.OnClickListener()
                                {
                                    public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {
                                    }
                                })
                                .show();
                        return true;
                    }
                });

    }


    public void nouveauJoueur(View view){
        TextView editPseudo = (TextView) findViewById(R.id.editPseudo);
        String pseudo= editPseudo.getText().toString();
        if (pseudo.equals("")  || pseudo==null)
            Toast.makeText(this, "Entrer un pseudo !!", Toast.LENGTH_LONG).show();
        else{
            SQLiteDatabase myDb = openOrCreateDatabase("joueursDB",MODE_PRIVATE,null);
            myDb.execSQL("CREATE TABLE IF NOT EXISTS joueurs(pseudo VARCHAR,niveau int,highScore int);");
            Cursor resultSet = myDb.rawQuery("Select * from joueurs where pseudo='"+pseudo+"'",null);
            if (resultSet.getCount()!=0)
                Toast.makeText(this, "Joueur existant deja !!!", Toast.LENGTH_LONG).show();
            else{
                myDb.execSQL("INSERT INTO joueurs VALUES('"+pseudo+"','0','0');");
                Intent intent;
                if(mode.equals("campagne"))
                    intent = new Intent(this,ChoixCategorieLayoutActivity.class);
                else
                    intent = new Intent(this,SurvivalActivityActivity.class);
                intent.putExtra("pseudo", pseudo);
                startActivity(intent);
                finish();
            }
            resultSet.close();
            myDb.close();
        }
        arraylist.add(pseudo);
        myAdapter.notifyDataSetChanged();

    }
}
