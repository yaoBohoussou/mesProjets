package com.example.yannbohoussou.projetandroid_new;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.RadioGroup;

import java.util.Locale;

/**
 * Created by Yann BOHOUSSOU on 24/05/2016.
 */
public class Parametre extends Activity
{
    RadioGroup unite = null;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parametrelayout);
        unite = (RadioGroup)findViewById(R.id.radioGroup);
    }

    public void valider(View view)
    {
        int radioCheked = unite.getCheckedRadioButtonId();
        if(radioCheked == R.id.radioEng)
        {
            String languageToLoad  = "en"; // your language
            Locale locale = new Locale(languageToLoad);
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config,getBaseContext().getResources().getDisplayMetrics());
        }
        else if(radioCheked == R.id.radioFr )
        {
            String languageToLoad  = "fr"; // your language
            Locale locale = new Locale(languageToLoad);
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config,getBaseContext().getResources().getDisplayMetrics());
        }

        Intent intent= new Intent(Parametre.this,MainActivity.class);
        startActivity(intent);

        this.finish();

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
    }

}
