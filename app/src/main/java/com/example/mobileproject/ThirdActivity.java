package com.example.mobileproject;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.ANRequest;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ThirdActivity extends AppCompatActivity {
    FragmentManager fg;
    ListView statusLV;
    List<String> status;
    TableLayout table;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidNetworking.initialize(getApplicationContext());
        setContentView(R.layout.activity_third);
        fg = getSupportFragmentManager();
        FragmentTransaction trans = fg.beginTransaction();
        NavFragment nf = new NavFragment();
        trans.add(R.id.navFragment, nf, "navFragment");
        trans.commit();
//        status = new ArrayList<>();
//        statusLV = findViewById(R.id.statusLV);
        /*
        to-do:
        1. reformat list as table, e.g.,
            planet | faction | players | percent
            -------+---------+---------+--------
              ...  |   ...   |   ...   |   ...
        2. only load the top few planets, but give a button to show more
         */

        table = findViewById(R.id.table);
        ANRequest req = AndroidNetworking.get("https://helldiverstrainingmanual.com/api/v1/war/campaign").setPriority(Priority.LOW).build();
        req.getAsJSONArray(new JSONArrayRequestListener() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                try {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject job = (JSONObject) jsonArray.get(i);

                        TableRow tr = new TableRow(getApplicationContext());
                        tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                        tr.setGravity(Gravity.CENTER);
                        tr.setWeightSum(1);

                        TableRow.LayoutParams params = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.25f);

                        TextView tv0 = new TextView(getApplicationContext());
                        tv0.setLayoutParams(params);
                        tv0.setText(job.getString("name"));
                        tv0.setTextColor(getResources().getColor(R.color.yellow));

                        TextView tv1 = new TextView(getApplicationContext());
                        tv1.setText(job.getString("faction"));
                        tv1.setLayoutParams(params);
                        tv1.setTextColor(getResources().getColor(R.color.yellow));

                        TextView tv2 = new TextView(getApplicationContext());
                        tv2.setText(String.valueOf(job.getInt("players")));
                        tv2.setLayoutParams(params);
                        tv2.setTextColor(getResources().getColor(R.color.yellow));


                        TextView tv3 = new TextView(getApplicationContext());
                        tv3.setText(String.valueOf(job.getDouble("percentage")));
                        tv3.setLayoutParams(params);
                        tv3.setTextColor(getResources().getColor(R.color.yellow));


                        tr.addView(tv0);
                        tr.addView(tv1);
                        tr.addView(tv2);
                        tr.addView(tv3);
                        table.addView(tr);
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }

            @Override
            public void onError(ANError anError) {
                Log.i("ERR", req.getUrl());
                Log.i("ERR", anError.getErrorDetail());
                Log.i("ERR", anError.getErrorBody());
                Log.i("ERR", anError.getErrorCode() + "");
                Log.i("ERR", anError.getResponse().toString());
                Toast.makeText(getApplicationContext(), anError.getErrorDetail(), Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), req.getUrl(), Toast.LENGTH_LONG).show();
            }
        });
        setPropaganda();
    }
    public void setPropaganda()
    {
        TextView propaganda = findViewById(R.id.propaganda);
        Random random = new Random();
        ArrayList<String> phrases = new ArrayList<>();
        phrases.add("We're not a cult. We're a movement with merch.");
        phrases.add("Super Earth: One nation under debt.");
        phrases.add("Your pals are fighting; Why aren't you?");
        phrases.add("Return, refit, and redeploy to purge the stain of this failure with the peroxide of victory.");
        phrases.add("How 'bout a nice cup of LIBER-TEA");
        phrases.add("He just called his automation friends. Time to call yours");
        String phrase = phrases.get(random.nextInt(phrases.size()));
        propaganda.setText(phrase);
    }
}

/*
ANRequest req = AndroidNetworking.get("https://helldiverstrainingmanual.com/api/v1/war/campaign").setPriority(Priority.LOW).build();
        req.getAsJSONArray(new JSONArrayRequestListener() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                try {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject job = (JSONObject) jsonArray.get(i);
                        status.add("Planet: " + job.getString("name") +
                                "\nFaction: " + job.getString("faction") +
                                "\nPlayers: " + job.getInt("players") +
                                "\nLiberation percentage: " + job.getDouble("percentage"));
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                statusLV.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, status));
            }

            @Override
            public void onError(ANError anError) {
                Log.i("ERR", req.getUrl());
                Log.i("ERR", anError.getErrorDetail());
                Log.i("ERR", anError.getErrorBody());
                Log.i("ERR", anError.getErrorCode() + "");
                Log.i("ERR", anError.getResponse().toString());
                Toast.makeText(getApplicationContext(), anError.getErrorDetail(), Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), req.getUrl(), Toast.LENGTH_LONG).show();
            }
        });
 */