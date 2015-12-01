package org.esiea.badelon_batista.ourapplication;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateUtils;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    TextView tv_hw;
    Intent inten;
    //DatePickerDialog dpd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        tv_hw = (TextView)findViewById(R.id.tv_hello_world);
        Button btn_hw = (Button)findViewById(R.id.btn_hello_world);

       // dpd = new DatePickerDialog(this,onDateSet(),)
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
//sandboxer chaque processus acceder a ses données de base
    //intent c'est le systeme qui gere, par exemple partage de photo la fenetre qui demande quelle appli choisir donc il regarde tute les appli qui peuve faire ça en leur envoyant un intent
    @Override
    protected void onResume(){
        super.onResume();
        String now = DateUtils.formatDateTime(getApplicationContext(),(new Date()).getTime(), DateFormat.SHORT);
        tv_hw.setText(now);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //Faire un toast avec msg initialise dans string.xml et android:onclick dans le content_main
    public void onClickMethod(View v){

        Toast.makeText(getApplicationContext(), getString(R.string.msg), Toast.LENGTH_LONG).show();

    }
    //toute activity doit etre declarer dans lemanfeifest
    public void Intent(View v){
        inten= new Intent(this,SecondeActivity.class);
        startActivity(inten);
        //startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=Londres")));
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
