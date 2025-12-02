package com.example.mobileproject;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.ANRequest;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.ParsedRequestListener;

import org.json.JSONObject;

import java.util.List;

public class SecondActivity extends AppCompatActivity {

    private TextView id;
    private TextView missionsWon;
    private TextView missionsLost;
    private TextView missionTime;
    private TextView bugKills;
    private TextView automationKills;
    private TextView illuminateKills;
    private TextView bulletsFired;
    private TextView bulletsHit;
    private TextView timePlayed;
    private TextView deaths;
    private TextView revives;
    private TextView friendlyKills;
    private TextView missionSuccessRate;
    private TextView accuracy;
    FragmentManager fg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        fg = getSupportFragmentManager();
        AndroidNetworking.initialize(getApplicationContext());
        FragmentTransaction trans = fg.beginTransaction();
        NavFragment nf = new NavFragment();
        id = findViewById(R.id.ID);
        missionsWon = findViewById(R.id.wins);
        missionsLost = findViewById(R.id.losses);
        missionTime = findViewById(R.id.missionTime);
        bugKills = findViewById(R.id.bugKills);
        automationKills = findViewById(R.id.automationKills);
        illuminateKills = findViewById(R.id.illuminateKills);
        bulletsFired = findViewById(R.id.bulletsFired);
        bulletsHit = findViewById(R.id.bulletsHit);
        timePlayed = findViewById(R.id.timePlayed);
        deaths = findViewById(R.id.deaths);
        revives = findViewById(R.id.revives);
        friendlyKills = findViewById(R.id.friendlyKills);
        missionSuccessRate = findViewById(R.id.successRate);
        accuracy = findViewById(R.id.accuracy);


        trans.add(R.id.navFragment, nf, "navFragment");
        trans.commit();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        int idnum = 1;
        ANRequest req = AndroidNetworking.get("https://api-hellhub-collective.koyeb.app/api/statistics/" + idnum +"/").setPriority(Priority.LOW).build();
        req.getAsJSONObject(new JSONObjectRequestListener() {
            @Override
            public void onResponse(JSONObject jsonObject) {
               try {
                   id.setText(jsonObject.getInt("id"));
                   missionsWon.setText(jsonObject.getInt("missionsWon"));
                   missionsLost.setText(jsonObject.getInt("missionsLost"));
                   missionTime.setText(jsonObject.getInt("missionTime"));
                   bugKills.setText(jsonObject.getInt("bugKills"));
                   automationKills.setText(jsonObject.getInt("automationKills"));
                   illuminateKills.setText(jsonObject.getInt("illuminateKills"));
                   bulletsFired.setText(jsonObject.getInt("bulletsFired"));
                   bulletsHit.setText(jsonObject.getInt("bulletsHit"));
                   timePlayed.setText(jsonObject.getInt("timePlayed"));
                   deaths.setText(jsonObject.getInt("deaths"));
                   revives.setText(jsonObject.getInt("revives"));
                   friendlyKills.setText("friendlyKills");
                   missionSuccessRate.setText("missionSucccessRate");
                   accuracy.setText("accuracy");
               }catch (Exception e)
               {

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



    }

}