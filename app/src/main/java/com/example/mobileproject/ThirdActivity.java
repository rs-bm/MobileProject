package com.example.mobileproject;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.ANRequest;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

public class ThirdActivity extends AppCompatActivity {
    FragmentManager fg;
    TableLayout table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidNetworking.initialize(getApplicationContext());
        setContentView(R.layout.activity_third);
        // Navigation fragment
        fg = getSupportFragmentManager();
        FragmentTransaction trans = fg.beginTransaction();
        NavFragment nf = new NavFragment();
        trans.add(R.id.navFragment, nf, "navFragment");
        trans.commit();
        // Recolor status bar
        getWindow().setStatusBarColor(getResources().getColor(R.color.backgroundBlack));

        setTable();
        setPropaganda();
    }

    /*
    Adds planet info from official API to the table
     */
    private void setTable() {
        table = findViewById(R.id.table);
        ANRequest req = AndroidNetworking.get("https://helldiverstrainingmanual.com/api/v1/war/campaign").setPriority(Priority.LOW).build();
        req.getAsJSONArray(new JSONArrayRequestListener() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                try {
                    Typeface font = ResourcesCompat.getFont(getApplicationContext(), R.font.fs_sinclair_medium);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject job = (JSONObject) jsonArray.get(i);

                        TableRow tr = new TableRow(getApplicationContext());
                        int c = (i % 2 == 1) ? R.color.backgroundBlack : R.color.backgroundGrey;
                        tr.setBackgroundColor(getResources().getColor(c));
                        tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                        tr.setGravity(Gravity.CENTER);
                        tr.setWeightSum(1);

                        TextView tv0 = new TextView(getApplicationContext());
                        tv0.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.30f));
                        tv0.setText(job.getString("name"));
                        tv0.setTextColor(getResources().getColor(R.color.white));
                        tv0.setGravity(Gravity.CENTER);
                        tv0.setTypeface(font);

                        TextView tv1 = new TextView(getApplicationContext());
                        tv1.setText(job.getString("faction"));
                        tv1.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.30f));
                        tv1.setTextColor(getResources().getColor(R.color.white));
                        tv1.setGravity(Gravity.CENTER);
                        tv1.setTypeface(font);

                        TextView tv2 = new TextView(getApplicationContext());
                        tv2.setText(String.valueOf(job.getInt("players")));
                        tv2.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.20f));
                        tv2.setTextColor(getResources().getColor(R.color.white));
                        tv2.setGravity(Gravity.CENTER);
                        tv2.setTypeface(font);

                        TextView tv3 = new TextView(getApplicationContext());
                        double percent = (double) (int) (job.getDouble("percentage") * 100) / 100;
                        tv3.setText(percent + "%");
                        tv3.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.20f));
                        tv3.setTextColor(getResources().getColor(R.color.white));
                        tv3.setGravity(Gravity.CENTER);
                        tv3.setTypeface(font);

                        tr.addView(tv0);
                        tr.addView(tv3);
                        tr.addView(tv2);
                        tr.addView(tv1);


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
    }

    /*
    Randomly chooses propaganda phrase to show upon loading the activity
     */
    public void setPropaganda() {
        TextView propaganda = findViewById(R.id.propaganda);
        Random random = new Random();
        ArrayList<String> phrases = new ArrayList<>();
        phrases.add("We're not a cult. We're a movement with merch.");
        phrases.add("Super Earth: One nation under debt.");
        phrases.add("Your pals are fighting; Why aren't you?");
        phrases.add("How 'bout a nice cup of LIBER-TEA");
        phrases.add("He just called his automaton friends. Time to call yours");
        String phrase = phrases.get(random.nextInt(phrases.size()));
        propaganda.setText(phrase);
    }
}
