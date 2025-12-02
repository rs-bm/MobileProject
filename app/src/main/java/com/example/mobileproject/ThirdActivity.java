package com.example.mobileproject;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.ANRequest;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

public class ThirdActivity extends AppCompatActivity {
    FragmentManager fg;
    TextView statusTV;
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
        statusTV = findViewById(R.id.status);
        ANRequest req = AndroidNetworking.get("https://helldiverstrainingmanual.com/api/v1/war/status").setPriority(Priority.LOW).build();
        req.getAsJSONObject(new JSONObjectRequestListener() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    ((TextView) findViewById(R.id.status)).setText(jsonObject.getInt("warId") + "");
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