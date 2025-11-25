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
    FragmentManager fg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        fg = getSupportFragmentManager();
        FragmentTransaction trans = fg.beginTransaction();
        NavFragment nf = new NavFragment();
        trans.add(R.id.navFragment, nf, "navFragment");
        trans.commit();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ANRequest req = AndroidNetworking.get("https://api-hellhub-collective.koyeb.app/api/statistics").setPriority(Priority.LOW).build();
        req.getAsObjectList(Quote.class, new ParsedRequestListener<List<Quote>>() {
            @Override
            public void onResponse(List<Quote> quotes)
            {
                String TAG = "STATS";
                Log.i(TAG, "list size : " + quotes.size());
                for (Quote quote : quotes)
                {
                    Log.i(TAG, "id : " + quote.getId());
                    Log.i(TAG, "missionsWon : " + quote.getMissionsWon());
                    Log.i(TAG, "missionsLost : " + quote.getMissionsLost());
                    Log.i(TAG, "missionTime : " + quote.getMissionTime());
                    Log.i(TAG, "bugKills : " + quote.getBugKills());
                    Log.i(TAG, "automationKills : " + quote.getAutomationKills());
                    Log.i(TAG, "illuminateKills : " + quote.getIlluminateKills());
                    Log.i(TAG, "bulletsFired : " + quote.getBulletsFired());
                    Log.i(TAG, "bulletsHit : " + quote.getBulletsHit());
                    Log.i(TAG, "timePlayed : " + quote.getTimePlayed());
                    Log.i(TAG, "deaths : " + quote.getDeaths());
                    Log.i(TAG, "revives : " + quote.getRevives());
                    Log.i(TAG, "friendlyKills : " + quote.getFriendlyKills());
                    Log.i(TAG, "missionSuccessRate : " + quote.getMissionSuccessRate());
                    Log.i(TAG, "accuracy : " + quote.getAccuracy());

                    ((TextView) findViewById(R.id.ID)).setText(quote.getId());
                    ((TextView) findViewById(R.id.wins)).setText(String.valueOf(quote.getMissionsWon()));
                    ((TextView) findViewById(R.id.losses)).setText(String.valueOf(quote.getMissionsLost()));
                    ((TextView) findViewById(R.id.missionTime)).setText(String.valueOf(quote.getMissionTime()));
                    ((TextView) findViewById(R.id.bugKills)).setText(String.valueOf(quote.getBugKills()));
                    ((TextView) findViewById(R.id.automationKills)).setText(String.valueOf(quote.getAutomationKills()));
                    ((TextView) findViewById(R.id.illuminateKills)).setText(String.valueOf(quote.getIlluminateKills()));
                    ((TextView) findViewById(R.id.bulletsFired)).setText(String.valueOf(quote.getBulletsFired()));
                    ((TextView) findViewById(R.id.bulletsHit)).setText(String.valueOf(quote.getBulletsHit()));
                    ((TextView) findViewById(R.id.timePlayed)).setText(String.valueOf(quote.getTimePlayed()));
                    ((TextView) findViewById(R.id.deaths)).setText(String.valueOf(quote.getDeaths()));
                    ((TextView) findViewById(R.id.revives)).setText(String.valueOf(quote.getRevives()));
                    ((TextView) findViewById(R.id.friendlyKills)).setText(String.valueOf(quote.getFriendlyKills()));
                    ((TextView) findViewById(R.id.successRate)).setText(quote.getMissionSuccessRate());
                    ((TextView) findViewById(R.id.accuracy)).setText(quote.getAccuracy());


                }
            }

            @Override
            public void onError(ANError anError)
            {
                Log.i("ERR", req.getUrl());
                Log.i("ERR",anError.getErrorDetail());
                Log.i("ERR", anError.getErrorBody());
                Log.i("ERR", anError.getErrorCode()+"");
                Log.i("ERR", anError.getResponse().toString());
                Toast.makeText(getApplicationContext(), anError.getErrorDetail(), Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), req.getUrl(), Toast.LENGTH_LONG).show();
            }
        });



    }

}