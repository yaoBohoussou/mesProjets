package com.example.yannbohoussou.projetandroid_new;

/**
 * Created by Yann BOHOUSSOU on 20/05/2016.
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi") public class Question3rActivity extends Activity {
    private HashMap<String, Integer> hash=new HashMap<String,Integer>();
    private String reponse,categorie,pseudo;
    private TextView questionText,reponse1Text,reponse2Text,reponse3Text;
    private int errors=0,questions=0,reponses=0;
    private ArrayList<Integer> list;
    private ImageView[] spheres,hearts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_3r);
        hash.put("Sports", Integer.valueOf(1));
        hash.put("Cinéma & TV", Integer.valueOf(10));
        hash.put("Musique", Integer.valueOf(100));
        hash.put("Histoire", Integer.valueOf(1000));
        hash.put("Faune & flore", Integer.valueOf(10000));
        hash.put("Sciences", Integer.valueOf(100000));
        hash.put("Mythologie", Integer.valueOf(1000000));
        hash.put("Langues", Integer.valueOf(10000000));
        Intent intent = getIntent();
        pseudo = intent.getStringExtra("pseudo");
        categorie= intent.getStringExtra("categorie");
        TextView theme=(TextView) findViewById(R.id.theme);
        theme.setText(categorie);
        Integer lis[]={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
        list=new ArrayList<Integer>(Arrays.asList(lis));
        Collections.shuffle(list);
        questionText=(TextView) findViewById(R.id.question);
        reponse1Text=(TextView) findViewById(R.id.reponse1);
        reponse2Text=(TextView) findViewById(R.id.reponse2);
        reponse3Text=(TextView) findViewById(R.id.reponse3);
        setTexts(categorie);
        reponse1Text.setOnClickListener(myListenner);
        reponse2Text.setOnClickListener(myListenner);
        reponse3Text.setOnClickListener(myListenner);
        spheres=new ImageView[5];
        spheres[0]=(ImageView) findViewById(R.id.sphere1);
        spheres[1]=(ImageView) findViewById(R.id.sphere2);
        spheres[2]=(ImageView) findViewById(R.id.sphere3);
        spheres[3]=(ImageView) findViewById(R.id.sphere4);
        spheres[4]=(ImageView) findViewById(R.id.sphere5);
        hearts=new ImageView[3];
        hearts[0]=(ImageView) findViewById(R.id.heart1);
        hearts[1]=(ImageView) findViewById(R.id.heart2);
        hearts[2]=(ImageView) findViewById(R.id.heart3);


    }
    private void setTexts(String categorie){
        String suffix=getSuffix(categorie);
        String enonce;
        ArrayList<String> choix;
        enonce=getResources().getString(getResources().getIdentifier(buildID(list.get(questions), suffix, "q"),"string",getPackageName()));
        questionText.setText(enonce);
        reponse=getResources().getString(getResources().getIdentifier(buildID(list.get(questions), suffix, "r"),"string",getPackageName()));
        choix=new ArrayList<String>(Arrays.asList(getResources().getString(getResources().getIdentifier(buildID(list.get(questions), suffix, "choix"),"string",getPackageName())).split(",")));
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
    }
    private String getSuffix(String theme)//permet de récupérer le suffixe pour la constitution de l'id qu sera utilisé pour r&cupérer la question
    {
        if(theme.equals("Langues"))
            return "lang";
        else if(theme.equals("Sciences"))
            return "sc";
        else if(theme.equals("Cinema & TV"))
            return "ctv";
        else if(theme.equals("Histoire"))
            return "hist";
        else if(theme.equals("Musique"))
            return "mus";
        else if(theme.equals("Faune & flore"))
            return "ffl";
        else if(theme.equals("Sport"))
            return "spt";
        else
            return "myth";
    }
    private String buildID(int int_ID, String suffixe, String isQorR)
    {
        String q_ID = new String();
        q_ID = suffixe+"_"+isQorR+"_"+int_ID+"_n1";
        return q_ID;
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
                Toast toast = new Toast(Question3rActivity.this);
                ImageView view = new ImageView(Question3rActivity.this);
                toast.setDuration(Toast.LENGTH_SHORT);
                if(rp.equals(reponse)){
                    spheres[4-reponses].setImageResource(R.drawable.green_sphere);
                    view.setImageResource(R.drawable.ok);
                    toast.setView(view);
                    toast.show();
                    if(reponses<4){
                        new CountDownTimer(2000,2000) {				// 2000 ms is the duration of length_short

                            @Override
                            public void onTick(long millisUntilFinished) {
                                // TODO Auto-generated method stub

                            }

                            @Override
                            public void onFinish() {
                                // TODO Auto-generated method stub

                                questions++;
                                reponses++;
                                setTexts(categorie);

                            }
                        }.start();

                    }
                    else{
                        new CountDownTimer(2000,2000) {				// 2000 ms is the duration of length_short

                            @Override
                            public void onTick(long millisUntilFinished) {
                                // TODO Auto-generated method stub

                            }

                            @Override
                            public void onFinish() {
                                // TODO Auto-generated method stub
                                Intent intent = new Intent(Question3rActivity.this,EnigmeLayoutActivity.class);
                                intent.putExtra("pseudo", pseudo);
                                intent.putExtra("niveau", 1);
                                intent.putExtra("categorie",categorie);
                                startActivity(intent);
                                finish();

                            }
                        }.start();

                    }
                }
                else {
                    hearts[2-errors].setVisibility(View.INVISIBLE);
                    if(errors<2){
                        view.setImageResource(R.drawable.no);
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
                                errors++;
                                setTexts(categorie);

                            }
                        }.start();

                    }
                    else{
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
                                Question3rActivity.this.finish();

                            }
                        }.start();
                    }
                }
            }
        }};
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(Question3rActivity.this).setMessage("Are you sure you want to exit?")
                .setPositiveButton("yes", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {
                        Question3rActivity.this.finish();
                        System.exit(0);
                    }
                })
                .setNegativeButton("no", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {
                    }
                })
                .show();
    }
    @Override
    public void onPause(){
        Question3rActivity.this.finish();
        super.onPause();
    }

}
