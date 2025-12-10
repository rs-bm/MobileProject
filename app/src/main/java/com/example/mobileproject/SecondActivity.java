package com.example.mobileproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * Activity for viewing Stats.
 */
public class SecondActivity extends AppCompatActivity {


    TextView missionsWon;
    TextView missionsPlayed;
    TextView bugKills;
    TextView automationKills;
    TextView illuminateKills;
    TextView bulletsFired;
    TextView bulletsHit;

    TextView deaths;
    TextView revives;
    TextView friendlyKills;
    TextView missionSuccessRate;
    TextView accuracy;
    Button updateButton;

    View.OnClickListener updateListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), FourthActivity.class);
            startActivity(intent);
        }
    };
    FragmentManager fg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        fg = getSupportFragmentManager();
        //AndroidNetworking.initialize(getApplicationContext());
        FragmentTransaction trans = fg.beginTransaction();
        NavFragment nf = new NavFragment();

        missionsWon = findViewById(R.id.wins);
        missionsPlayed = findViewById(R.id.plays);
        bugKills = findViewById(R.id.bugKills);
        automationKills = findViewById(R.id.automationKills);
        illuminateKills = findViewById(R.id.illuminateKills);
        friendlyKills = findViewById(R.id.friendlyKills);
        bulletsFired = findViewById(R.id.bulletsFired);
        bulletsHit = findViewById(R.id.bulletsHit);
        deaths = findViewById(R.id.deaths);
        revives = findViewById(R.id.revives);
        missionSuccessRate = findViewById(R.id.missionSuccessRate);
        accuracy = findViewById(R.id.accuracy);
        updateButton = findViewById(R.id.updateButton);
        updateButton.setOnClickListener(updateListener);
        trans.add(R.id.navFragment, nf, "navFragment");
        trans.commit();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        SharedPreferences prefs = getSharedPreferences("stats", MODE_PRIVATE);

        missionsWon.setText(prefs.getString("wins", ""));
        missionsPlayed.setText(prefs.getString("plays", ""));
        bugKills.setText(prefs.getString("bugs", ""));
        automationKills.setText(prefs.getString("automations", ""));
        illuminateKills.setText(prefs.getString("illuminates", ""));
        friendlyKills.setText(prefs.getString("friendly", ""));
        bulletsFired.setText(prefs.getString("fired", ""));
        bulletsHit.setText(prefs.getString("hits", ""));
        deaths.setText(prefs.getString("deaths", ""));
        revives.setText(prefs.getString("revives",""));
        missionSuccessRate.setText(prefs.getString("missionSuccessRate", ""));
        accuracy.setText(prefs.getString("accuracy", ""));

        /*int idNum = 2;
        ANRequest req = AndroidNetworking.get("https://api-hellhub-collective.koyeb.app/api/statistics/" + idNum +"/").setPriority(Priority.LOW).build();
        req.getAsJSONObject(new JSONObjectRequestListener() {
            @Override
            public void onResponse(JSONObject jsonObject) {
               try {
                   id.setText(String.valueOf(jsonObject.getInt("id")));
                   missionsWon.setText(String.valueOf(jsonObject.getLong("missionsWon")));
                   missionsLost.setText(String.valueOf(jsonObject.getLong("missionsLost")));
                   missionTime.setText(String.valueOf(jsonObject.getLong("missionTime")));
                   bugKills.setText(String.valueOf(jsonObject.getLong("bugKills")));
                   automationKills.setText(String.valueOf(jsonObject.getLong("automationKills")));
                   illuminateKills.setText(String.valueOf(jsonObject.getLong("illuminateKills")));
                   bulletsFired.setText(String.valueOf(jsonObject.getLong("bulletsFired")));
                   bulletsHit.setText(String.valueOf(jsonObject.getLong("bulletsHit")));
                   timePlayed.setText(String.valueOf(jsonObject.getLong("timePlayed")));
                   deaths.setText(String.valueOf(jsonObject.getLong("deaths")));
                   revives.setText(String.valueOf(jsonObject.getLong("revives")));
                   friendlyKills.setText(String.valueOf(jsonObject.getLong("friendlyKills")));
                   missionSuccessRate.setText(String.valueOf(jsonObject.getInt("missionSuccessRate")));
                   accuracy.setText(String.valueOf(jsonObject.getInt("accuracy")));
               }catch (Exception e)
               {
                   Toast.makeText(getApplicationContext(), "Unable to show Stats", Toast.LENGTH_SHORT).show();
               }
            }

            @Override
            public void onError(ANError anError)
            {
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
    }
}