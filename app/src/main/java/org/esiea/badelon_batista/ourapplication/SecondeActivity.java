package org.esiea.badelon_batista.ourapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class SecondeActivity extends AppCompatActivity {

    public static final String BIERS_UPDATE = "com.octip.cours.inf4042_12.BIERS_UPDATE";
    private RecyclerView rv;
    private RecyclerView.Adapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seconde);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        GetBiersServices.startActionBiers(this);
        IntentFilter intentFilter = new IntentFilter(BIERS_UPDATE);
        LocalBroadcastManager.getInstance(this).registerReceiver(new BierUpdate(), intentFilter);
        rv = (RecyclerView) findViewById(R.id.rv_bier);
        rv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mAdapter = new BiersAdapter(getBiersFromFile());
        rv.setAdapter(mAdapter);




    }

    @Override
    protected void onResume(){
        super.onResume();




    }

    public JSONArray getBiersFromFile(){
        try {
            InputStream is = new FileInputStream(getCacheDir() + "/" + "bieres.json");
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
            return new JSONArray(new String(buffer, "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
            return new JSONArray();
        } catch (JSONException e) {
            e.printStackTrace();
            return new JSONArray();
        }
    }

    public class BierUpdate extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("Test", "BroadCast BierUpdate receive");
            ((BiersAdapter)rv.getAdapter()).setNewBiere(getBiersFromFile());
        }
    }


}
