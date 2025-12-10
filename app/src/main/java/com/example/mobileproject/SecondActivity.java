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

    View.OnClickListener updateListener = v -> {
        Intent intent = new Intent(getApplicationContext(), FourthActivity.class);
        startActivity(intent);
    };
    FragmentManager fg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        fg = getSupportFragmentManager();
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

        missionsWon.setText(prefs.getString("wins", "0"));
        missionsPlayed.setText(prefs.getString("plays", "0"));
        bugKills.setText(prefs.getString("bugs", "0"));
        automationKills.setText(prefs.getString("automations", "0"));
        illuminateKills.setText(prefs.getString("illuminates", "0"));
        friendlyKills.setText(prefs.getString("friendly", "0"));
        bulletsFired.setText(prefs.getString("fired", "0"));
        bulletsHit.setText(prefs.getString("hits", "0"));
        deaths.setText(prefs.getString("deaths", "0"));
        revives.setText(prefs.getString("revives","0"));
        missionSuccessRate.setText(prefs.getString("missionSuccessRate", "0"));
        accuracy.setText(prefs.getString("accuracy", "0"));
    }
}