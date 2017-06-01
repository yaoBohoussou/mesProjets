package com.example.yannbohoussou.projetandroid_new;

/**
 * Created by Yann BOHOUSSOU on 21/05/2016.
 */

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import mode_normal.*;

/**
 * Created by Yann BOHOUSSOU on 27/04/2016.
 */
public class Partie_niveau3 extends Activity
{
    private Partie partie;
    private TextView theme_Partie, niveau_Partie, enonce_Question, reponse1,reponse2,reponse3,reponse4;
    private ArrayList<Question> listeQuestions;
    private Random rand;
    private int nbr_maxi_questions, niveau =0, nbrMaxiReps;
    private long seed = 459865;
    private int indexQcour;
    private Chronometer chrono;
    private TextView chrono_txt;
    private int compt = 21;
    private long time = 20;

    private ImageView sphere1;
    private ImageView sphere2;
    private ImageView sphere3;
    private ImageView sphere4;
    private ImageView sphere5;


    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_4r);

        listeQuestions = new ArrayList<Question>();

        String theme = new String("no Theme");
        if(this.getIntent().getExtras()!=null)
        {
            theme = this.getIntent().getExtras().getString("theme");
        }
        partie = new Partie(theme,3);

        theme_Partie = (TextView)findViewById(R.id.theme);
        niveau_Partie = (TextView)findViewById(R.id.niveau);
        niveau_Partie.setVisibility(View.INVISIBLE);
        enonce_Question = (TextView)findViewById(R.id.question);
        reponse1 = (TextView)findViewById(R.id.reponse1);
        reponse2 = (TextView)findViewById(R.id.reponse2);
        reponse3 = (TextView)findViewById(R.id.reponse3);
        reponse4 = (TextView)findViewById(R.id.reponse4);

        sphere1 = (ImageView)findViewById(R.id.sphere1);
        sphere2 = (ImageView)findViewById(R.id.sphere2);
        sphere3 = (ImageView)findViewById(R.id.sphere3);
        sphere4 = (ImageView)findViewById(R.id.sphere4);
        sphere5 = (ImageView)findViewById(R.id.sphere5);

        sphere1.setImageResource(R.drawable.green_sphere);

        chrono = (Chronometer)findViewById(R.id.chrono1);
        chrono_txt = (TextView)findViewById(R.id.chrono);
        chrono.setVisibility(View.VISIBLE);
        chrono.setVisibility(View.INVISIBLE);



        this.niveau = partie.getAvancement();
        Toast.makeText(this, "niveau 3", Toast.LENGTH_SHORT).show();
        this.nbr_maxi_questions = 20;
        this.nbrMaxiReps = 4;

        theme_Partie.setText(partie.getTheme());
        niveau_Partie.setText(getResources().getString(R.string.niveau) + " " + new Integer(partie.getAvancement()).toString());
        chargerQuestions(partie.getTheme());

        Random ordre = new Random();
        ArrayList<Integer> pos = new ArrayList<Integer> ();
        int  nbr;
        for(;;)
        {
            nbr = ordre.nextInt(4);
            if(pos.size() == 4)
                break;
            else if(pos.contains(new Integer(nbr)));
            else
            {
                pos.add(nbr);
            }
        }


        enonce_Question.setText(this.listeQuestions.get(0).getEnonce());
        Integer p;
        int count = 1;
        for(int i = 0; i<pos.size(); i++)
        {
            p = pos.get(i);
            if(count == 1)
            {
                if(p == 0)
                {
                    reponse1.setText(this.listeQuestions.get(0).getReponse_fausse(0));
                    reponse1.setOnClickListener(clickListenerRbad);
                }
                else if(p ==1)
                {
                    reponse1.setText(this.listeQuestions.get(0).getReponse_fausse(1));
                    reponse1.setOnClickListener(clickListenerRbad);
                }
                else if(p ==2)
                {
                    reponse1.setText(this.listeQuestions.get(0).getReponse_fausse(2));
                    reponse1.setOnClickListener(clickListenerRbad);
                }
                else
                {
                    reponse1.setText(this.listeQuestions.get(0).getReponse_correcte());
                    reponse1.setOnClickListener(clickListenerRok);
                }

                count +=1;
            }
            else if(count ==2)
            {
                if(p == 0)
                {
                    reponse2.setText(this.listeQuestions.get(0).getReponse_fausse(0));
                    reponse2.setOnClickListener(clickListenerRbad);
                }
                else if(p ==1)
                {
                    reponse2.setText(this.listeQuestions.get(0).getReponse_fausse(1));
                    reponse2.setOnClickListener(clickListenerRbad);
                }
                else if(p ==2)
                {
                    reponse2.setText(this.listeQuestions.get(0).getReponse_fausse(2));
                    reponse2.setOnClickListener(clickListenerRbad);
                }
                else
                {
                    reponse2.setText(this.listeQuestions.get(0).getReponse_correcte());
                    reponse2.setOnClickListener(clickListenerRok);
                }

                count +=1;
            }
            else if(count ==3)
            {
                if(p == 0)
                {
                    reponse3.setText(this.listeQuestions.get(0).getReponse_fausse(0));
                    reponse3.setOnClickListener(clickListenerRbad);
                }
                else if(p ==1)
                {
                    reponse3.setText(this.listeQuestions.get(0).getReponse_fausse(1));
                    reponse3.setOnClickListener(clickListenerRbad);
                }
                else if(p ==2)
                {
                    reponse3.setText(this.listeQuestions.get(0).getReponse_fausse(2));
                    reponse3.setOnClickListener(clickListenerRbad);
                }
                else
                {
                    reponse3.setText(this.listeQuestions.get(0).getReponse_correcte());
                    reponse3.setOnClickListener(clickListenerRok);
                }

                count +=1;
            }
            else
            {
                if(p == 0)
                {
                    reponse4.setText(this.listeQuestions.get(0).getReponse_fausse(0));
                    reponse4.setOnClickListener(clickListenerRbad);
                }
                else if(p ==1)
                {
                    reponse4.setText(this.listeQuestions.get(0).getReponse_fausse(1));
                    reponse4.setOnClickListener(clickListenerRbad);
                }
                else if(p ==2)
                {
                    reponse4.setText(this.listeQuestions.get(0).getReponse_fausse(2));
                    reponse4.setOnClickListener(clickListenerRbad);
                }
                else
                {
                    reponse4.setText(this.listeQuestions.get(0).getReponse_correcte());
                    reponse4.setOnClickListener(clickListenerRok);
                }

                count +=1;
            }

        }


        chrono.setBase(SystemClock.elapsedRealtime());
        chrono.start();
        chrono.setOnChronometerTickListener(chronoListener);

        this.indexQcour = 0;


    }

    private Chronometer.OnChronometerTickListener chronoListener = new Chronometer.OnChronometerTickListener() {
        @Override
        public void onChronometerTick(Chronometer chronometer)
        {
            fonc(chronometer);
        }
    };
    private void fonc(Chronometer chronometer)
    {
        long elapsedMillis = (SystemClock.elapsedRealtime() - chronometer.getBase())/1000;
        if(elapsedMillis > this.time)
            reponseBad();
        else
        {
            String chr ;//= new String();
            compt -=1;
            if(compt < 10)
                chr = "00:0"+compt;
            else
                chr = "00:"+compt;
            chrono_txt.setText(chr);
        }
    }


    private View.OnClickListener clickListenerRok = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            {
                reponseOK();
            }
        }};

    private View.OnClickListener clickListenerRbad = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            {
                reponseBad();
            }
        }};

    private void reponseOK()
    {

        ImageView view = new ImageView(Partie_niveau3.this);
        view.setImageResource(R.drawable.ok);
        Toast toast = new Toast(Partie_niveau3.this);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.show();

        if(this.indexQcour == 1)
            sphere2.setImageResource(R.drawable.green_sphere);
        if(this.indexQcour == 2)
            sphere3.setImageResource(R.drawable.green_sphere);
        if(this.indexQcour == 3)
            sphere4.setImageResource(R.drawable.green_sphere);
        if(this.indexQcour == 4)
            sphere5.setImageResource(R.drawable.green_sphere);

        if(this.indexQcour < 4)
        {
            chrono.stop();
            this.indexQcour += 1;
            Random ordre = new Random();
            ArrayList<Integer> pos = new ArrayList<Integer> ();
            int  nbr;
            for(;;)
            {
                nbr = ordre.nextInt(4);
                if(pos.size() == 4)
                    break;
                else if(pos.contains(new Integer(nbr)));
                else
                {
                    pos.add(nbr);
                }
            }
            enonce_Question.setText(this.listeQuestions.get(this.indexQcour).getEnonce());
            Integer p;
            int count = 1;
            for(int i = 0; i<pos.size(); i++)
            {
                p = pos.get(i);
                if(count == 1)
                {
                    if(p == 0)
                    {
                        reponse1.setText(this.listeQuestions.get(this.indexQcour).getReponse_fausse(0));
                        reponse1.setOnClickListener(clickListenerRbad);
                    }
                    else if(p ==1)
                    {
                        reponse1.setText(this.listeQuestions.get(this.indexQcour).getReponse_fausse(1));
                        reponse1.setOnClickListener(clickListenerRbad);
                    }
                    else if(p ==2)
                    {
                        reponse1.setText(this.listeQuestions.get(this.indexQcour).getReponse_fausse(2));
                        reponse1.setOnClickListener(clickListenerRbad);
                    }
                    else
                    {
                        reponse1.setText(this.listeQuestions.get(this.indexQcour).getReponse_correcte());
                        reponse1.setOnClickListener(clickListenerRok);
                    }

                    count +=1;
                }
                else if(count ==2)
                {
                    if(p == 0)
                    {
                        reponse2.setText(this.listeQuestions.get(this.indexQcour).getReponse_fausse(0));
                        reponse2.setOnClickListener(clickListenerRbad);
                    }
                    else if(p ==1)
                    {
                        reponse2.setText(this.listeQuestions.get(this.indexQcour).getReponse_fausse(1));
                        reponse2.setOnClickListener(clickListenerRbad);
                    }
                    else if(p ==2)
                    {
                        reponse2.setText(this.listeQuestions.get(this.indexQcour).getReponse_fausse(2));
                        reponse2.setOnClickListener(clickListenerRbad);
                    }
                    else
                    {
                        reponse2.setText(this.listeQuestions.get(this.indexQcour).getReponse_correcte());
                        reponse2.setOnClickListener(clickListenerRok);
                    }

                    count +=1;
                }
                else if(count ==3)
                {
                    if(p == 0)
                    {
                        reponse3.setText(this.listeQuestions.get(this.indexQcour).getReponse_fausse(0));
                        reponse3.setOnClickListener(clickListenerRbad);
                    }
                    else if(p ==1)
                    {
                        reponse3.setText(this.listeQuestions.get(this.indexQcour).getReponse_fausse(1));
                        reponse3.setOnClickListener(clickListenerRbad);
                    }
                    else if(p ==2)
                    {
                        reponse3.setText(this.listeQuestions.get(this.indexQcour).getReponse_fausse(2));
                        reponse3.setOnClickListener(clickListenerRbad);
                    }
                    else
                    {
                        reponse3.setText(this.listeQuestions.get(this.indexQcour).getReponse_correcte());
                        reponse3.setOnClickListener(clickListenerRok);
                    }

                    count +=1;
                }
                else
                {
                    if(p == 0)
                    {
                        reponse4.setText(this.listeQuestions.get(this.indexQcour).getReponse_fausse(0));
                        reponse4.setOnClickListener(clickListenerRbad);
                    }
                    else if(p ==1)
                    {
                        reponse4.setText(this.listeQuestions.get(this.indexQcour).getReponse_fausse(1));
                        reponse4.setOnClickListener(clickListenerRbad);
                    }
                    else if(p ==2)
                    {
                        reponse4.setText(this.listeQuestions.get(this.indexQcour).getReponse_fausse(2));
                        reponse4.setOnClickListener(clickListenerRbad);
                    }
                    else
                    {
                        reponse4.setText(this.listeQuestions.get(this.indexQcour).getReponse_correcte());
                        reponse4.setOnClickListener(clickListenerRok);
                    }

                    count +=1;
                }

            }

            compt = 31;
            chrono.setBase(SystemClock.elapsedRealtime());
            chrono.start();
        }
        else
        {
            Intent goPartie= new Intent(Partie_niveau3.this, enigme_activity.class);
            goPartie.putExtra("theme",partie.getTheme());
            goPartie.putExtra("niveau",partie.getAvancement());
            this.startActivity(goPartie);
            this.finish();;
        }
    }

    private void reponseBad()
    {
        ImageView view = new ImageView(Partie_niveau3.this);
        view.setImageResource(R.drawable.game_over);
        Toast toast = new Toast(Partie_niveau3.this);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(view);
        toast.show();
        this.finish();
    }

    private void  chargerQuestions(String theme)
    {
        String suffixe = getSuffix(theme);
        //Toast.makeText(this, suffixe, Toast.LENGTH_LONG).show();
        int qID=0;
        String questionID, reponseOKid, reponseBADid;
        int ressourceQID, ressourceRokID, ressourceRbadID;
        String enonce,reponseOK,reponsesBad;
        String listFalseRpes[];
        ArrayList<String> reponses_fausses;
        this.rand = new Random();
        for (int i=0;i<5;i++)
        {

            do
            {
                qID = rand.nextInt(nbr_maxi_questions);
                //this.seed = qID;
            }while(qID==0);
            System.out.println(qID);
            //building de la questionID
            questionID = buildID(qID, suffixe, new String("_q_"));
            reponseOKid = buildID(qID, suffixe, new String("_r_"));
            reponseBADid = buildID(qID, suffixe, new String("_choix_"));
            //récupération de l'ID android de la question
            ressourceQID = getResources().getIdentifier(questionID, "string", getPackageName());
            ressourceRokID = getResources().getIdentifier(reponseOKid, "string", getPackageName());
            ressourceRbadID = getResources().getIdentifier(reponseBADid, "string", getPackageName());
            try
            {
                enonce = getResources().getString(ressourceQID);
                reponseOK = getResources().getString(ressourceRokID);
                reponsesBad = getResources().getString(ressourceRbadID);
                listFalseRpes= reponsesBad.split(",");
                reponses_fausses = new ArrayList<String>(Arrays.asList(listFalseRpes));
                this.listeQuestions.add(new Question(questionID,enonce,reponseOK,reponses_fausses));

                //Toast.makeText(this, questionID, Toast.LENGTH_LONG).show();
            }catch(Resources.NotFoundException e){Toast.makeText(this, "ressource inexistante", Toast.LENGTH_LONG).show();}

        }
    }

    private String buildID(int int_ID, String suffixe, String isQorR)
    {
        String q_ID = new String();
        //code build Question ID q_1_n1
        //q_ID = suffixe+"_q_"+ new Integer(int_ID).toString()+"_n"+new Integer(niveau).toString();
        q_ID = suffixe+isQorR+ new Integer(int_ID).toString()+"_n"+new Integer(this.niveau).toString();
        return q_ID;
    }


    private String getSuffix(String theme)//permet de récupérer le suffixe pour la constitution de l'id qu sera utilisé pour r&cupérer la question
    {
        if(theme.equals("Langues"))
            return "lang";
        else if(theme.equals("Sciences"))
            return "sc";
        else if(theme.equals("Cinema et TV"))
            return "ctv";
        else if(theme.equals("Histoire"))
            return "hist";
        else if(theme.equals("Musique"))
            return "mus";
        else if(theme.equals("Faune et flore"))
            return "ffl";
        else if(theme.equals("Sport"))
            return "spt";
        else
            return "myth";
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
        Partie_niveau3.this.finish();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
    }
}

