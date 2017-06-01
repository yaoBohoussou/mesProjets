package com.example.yannbohoussou.projetandroid_new;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Yann BOHOUSSOU on 25/05/2016.
 */
@SuppressLint("NewApi") public class SurvivalActivityActivity extends ActionBarActivity {
    private HashMap<Integer, String> hash_cat=new HashMap<Integer,String>();
    private String reponse,pseudo;
    private TextView questionText,reponse1Text,reponse2Text,reponse3Text;
    private int questions=0,score=0;
    private ArrayList<Integer> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.survival_activity);
        Intent intent = getIntent();
        pseudo = intent.getStringExtra("pseudo");
        hash_cat.put(Integer.valueOf(1),"lang");
        hash_cat.put(Integer.valueOf(2),"sc");
        hash_cat.put(Integer.valueOf(3),"ctv");
        hash_cat.put(Integer.valueOf(4),"spt");
        hash_cat.put(Integer.valueOf(5),"ffl");
        hash_cat.put(Integer.valueOf(6),"mus");
        hash_cat.put(Integer.valueOf(7),"hist");
        hash_cat.put(Integer.valueOf(8),"myth");
        Integer [] lis = new Integer[1975];
        for(int i=0;i<=1974;i++)
            lis[i]=i;
        list=new ArrayList<Integer>(Arrays.asList(lis));
        for(int i=0;i<list.size();i++){
            while(list.get(i)%10>4 || list.get(i)%100>74){
                list.remove(i);
            }
        }
        Collections.shuffle(list);
        Log.i("shit",list.get(1)+"");
        questionText=(TextView) findViewById(R.id.question);
        reponse1Text=(TextView) findViewById(R.id.reponse1);
        reponse2Text=(TextView) findViewById(R.id.reponse2);
        reponse3Text=(TextView) findViewById(R.id.reponse3);
        setTexts(list.get(questions));
        reponse1Text.setOnClickListener(myListenner);
        reponse2Text.setOnClickListener(myListenner);
        reponse3Text.setOnClickListener(myListenner);
    }
    private void setTexts(int nbr){
        String q_num;
        int indice;
        String question_tag="_n"+(nbr%10+1);
        String reponse_tag=question_tag;
        String choix_tag=question_tag;
        nbr=nbr/10;
        indice=nbr%10+1;
        q_num=""+(nbr/10+1);
        question_tag=hash_cat.get(indice)+"_q_"+q_num+question_tag;
        reponse_tag=hash_cat.get(indice)+"_r_"+q_num+reponse_tag;
        choix_tag=hash_cat.get(indice)+"_choix_"+q_num+choix_tag;
        String enonce;
        ArrayList<String> choix;
        enonce=getResources().getString(getResources().getIdentifier(question_tag,"string",getPackageName()));
        questionText.setText(enonce);
        reponse=getResources().getString(getResources().getIdentifier(reponse_tag,"string",getPackageName()));
        choix=new ArrayList<String>(Arrays.asList(getResources().getString(getResources().getIdentifier(choix_tag,"string",getPackageName())).split(",")));
        choix.remove(2);
        choix.add(reponse);
        Collections.shuffle(choix);
        reponse1Text.setBackground(getResources().getDrawable(R.drawable.answer));
        reponse2Text.setBackground(getResources().getDrawable(R.drawable.answer));
        reponse3Text.setBackground(getResources().getDrawable(R.drawable.answer));
        reponse1Text.setClickable(true);
        reponse2Text.setClickable(true);
        reponse3Text.setClickable(true);
        reponse1Text.setText(choix.get(0));
        reponse2Text.setText(choix.get(1));
        reponse3Text.setText(choix.get(2));
        TextView theme=(TextView) findViewById(R.id.theme);
        switch(indice){
            case 1: theme.setText("Langues");break;
            case 2: theme.setText("Sciences");break;
            case 3: theme.setText("Cinema & TV");break;
            case 4: theme.setText("Sport");break;
            case 5: theme.setText("Faune & flore");break;
            case 6: theme.setText("Musique");break;
            case 7: theme.setText("Histoire");break;
            case 8: theme.setText("Mythologie");break;
        }
        TextView points=(TextView) findViewById(R.id.niveau);
        points.setText(score+" pts");
    }

    private View.OnClickListener myListenner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            {
                int id=v.getId();
                TextView responseText=(TextView) findViewById(id);
                responseText.setBackground(getResources().getDrawable(R.drawable.answer_click));
                reponse1Text.setClickable(false);
                reponse2Text.setClickable(false);
                reponse3Text.setClickable(false);
                String rp=responseText.getText().toString();
                Toast toast = new Toast(SurvivalActivityActivity.this);
                ImageView view = new ImageView(SurvivalActivityActivity.this);
                toast.setDuration(Toast.LENGTH_SHORT);
                if(rp.equals(reponse)){
                    score++;
                    view.setImageResource(R.drawable.ok);
                    toast.setView(view);
                    toast.show();
                    new CountDownTimer(2000,2000) {				// 2000 ms is the duration of length_short

                        @Override
                        public void onTick(long millisUntilFinished) {
                            // TODO Auto-generated method stub

                        }

                        @Override
                        public void onFinish() {
                            // TODO Auto-generated method stub

                            questions++;
                            setTexts(list.get(questions));

                        }
                    }.start();


                }
                else {
                    SQLiteDatabase myDb = openOrCreateDatabase("joueursDB",MODE_PRIVATE,null);
                    myDb.execSQL("CREATE TABLE IF NOT EXISTS joueurs(pseudo VARCHAR,niveau int,highScore int);");
                    Cursor resultSet = myDb.rawQuery("Select highscore from joueurs where pseudo= '"+pseudo+"'",null);
                    resultSet.moveToNext();
                    if(resultSet.getInt(0)<score)
                        myDb.execSQL("UPDATE joueurs SET highScore='"+score+"' WHERE  pseudo='"+pseudo+"';");
                    resultSet.close();
                    myDb.close();
                    view.setImageResource(R.drawable.game_over);
                    toast.setView(view);
                    toast.show();
                    new CountDownTimer(2000,2000) {				// 2000 ms is the duration of length_short

                        @Override
                        public void onTick(long millisUntilFinished) {
                            // TODO Auto-generated method stub

                        }

                        @Override
                        public void onFinish() {
                            // TODO Auto-generated method stub
                            SurvivalActivityActivity.this.finish();

                        }
                    }.start();

                }
            }
        }};
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.survival_activity, menu);
        return true;
    }

    @Override


    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
