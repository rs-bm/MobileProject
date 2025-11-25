package com.example.mobileproject;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FragmentManager fg;
    DatabaseReference dRef;
    ListView weaponsLV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fg = getSupportFragmentManager();
        FragmentTransaction trans = fg.beginTransaction();
        NavFragment nf = new NavFragment();
        trans.add(R.id.navFragment, nf, "navFragment");
        trans.commit();

        weaponsLV = findViewById(R.id.weaponsLV);
        getWeapons();
    }

    /*
        Populate the listview with stratagems from the database
     */
    private void getWeapons() {
        dRef = FirebaseDatabase.getInstance().getReference("stratagems");
        List<String> weapons = new ArrayList<>();
        dRef.addValueEventListener(new ValueEventListener() {
            List<HashMap<String, String>> data = new ArrayList<>();

            @Override
            public void onDataChange(@NonNull DataSnapshot ds) {
                for (DataSnapshot ds2 : ds.getChildren()) {
                    HashMap<String, String> current = new HashMap<>();
                    current.put("name", ds2.getKey());
                    current.put("desc", "Type: " + ds2.child("type").getValue() +
                            "\n - Code: " + ds2.child("code").getValue() +
                            "\n - Cooldown: " + ds2.child("cooldown").getValue() +
                            "\n - Damage: " + ds2.child("damage").getValue() +
                            "\n - Uses: " + ds2.child("uses").getValue() +
                            "\n - Spawntime: " + ds2.child("spawnTime").getValue());
                    data.add(current);
                }
                SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(), data,
                        android.R.layout.simple_list_item_2,
                        new String[]{"name", "desc"},
                        new int[]{android.R.id.text1, android.R.id.text2});
                weaponsLV.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}