package com.example.yannbohoussou.projetandroid_new;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button partieR_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        partieR_button = (Button) findViewById(R.id.mode3);
        partieR_button.setOnClickListener(partieR_clickListener);
    }

    private View.OnClickListener partieR_clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent choixTheme = new Intent(MainActivity.this, ModeNormal_choixTheme.class);
            lancerActivite(choixTheme);
        }
    };

    private void lancerActivite(Intent intent) {
        this.startActivity(intent);
    }

    public void campagne(View view){
        Intent intent= new Intent(this,ChoixJoueurModeSurvieActivity.class);
        intent.putExtra("mode", "campagne");
        startActivity(intent);
    }

    public void parameters(View view){
        Intent intent= new Intent(MainActivity.this,Parametre.class);
        startActivity(intent);
        this.finish();
    }

    public void survival(View view){
        Intent intent= new Intent(this,ChoixJoueurModeSurvieActivity.class);
        intent.putExtra("mode", "survival");
        startActivity(intent);
    }
    public void score(View view){
        Intent intent= new Intent(this,ScoresActivity.class);
        startActivity(intent);
    }

    public void exit(View view){
        new AlertDialog.Builder(MainActivity.this).setMessage("Are you sure you want to exit?")
                .setPositiveButton("yes", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {
                        MainActivity.this.finish();
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
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
