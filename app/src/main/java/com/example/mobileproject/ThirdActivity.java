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
    }
}