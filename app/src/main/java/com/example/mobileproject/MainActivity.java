package com.example.mobileproject;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.SimpleExpandableListAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FragmentManager fg;
    DatabaseReference dRef;
    ExpandableListView weaponsLV;
    ExpandableListAdapter adapter;
    List<String> weaponNames;
    HashMap<String, List<String>> weaponDetails;
    Button searchB;
    EditText searchET;
    ChipGroup chipGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchET = findViewById(R.id.searchET);
        searchB = findViewById(R.id.searchB);
        searchB.setOnClickListener(searchListener);
        chipGroup = findViewById(R.id.chipGroup);
        // Navigation fragment
        fg = getSupportFragmentManager();
        FragmentTransaction trans = fg.beginTransaction();
        NavFragment nf = new NavFragment();
        trans.add(R.id.navFragment, nf, "navFragment");
        trans.commit();
        // Populate stratagems listview
        weaponsLV = findViewById(R.id.weaponsLV);
        weaponDetails = new HashMap<>();
        getWeapons("");
        // Recolor status bar
        getWindow().setStatusBarColor(getResources().getColor(R.color.backgroundBlack));
    }

    /*
    Retrieves weapons data from firebase into listview with parameter to search by name
     */
    private void getWeapons(String search) {
        weaponDetails.clear();
        dRef = FirebaseDatabase.getInstance().getReference("stratagems");
        dRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot ds) {
                perStrat:
                for (DataSnapshot ds2 : ds.getChildren()) {
                    // Exclude strat if not matching search
                    if (!ds2.getKey().toLowerCase().contains(search.toLowerCase())) {
                        continue;
                    }
                    // Exclude strat if not matching filter
                    String stratType = ds2.child("type").getValue().toString();
                    for (int i = 0; i < chipGroup.getChildCount(); i++) {
                        Chip chip = (Chip) chipGroup.getChildAt(i);
                        String chipType = chip.getText().toString();
                        if (chipType.equalsIgnoreCase(stratType) && chip.isChecked() == false) {
                            continue perStrat;
                        }
                    }
                    // Populate list of details for each stratagem
                    List<String> detail = new ArrayList<>();
                    for (DataSnapshot ds3 : ds2.getChildren()) {
                        detail.add("-  " + ds3.getKey().toUpperCase() + ":  " + ds3.getValue());
                    }
                    // Add stratagem-details pairing
                    weaponDetails.put(ds2.getKey(), detail);
                }
                adapter = new MyAdapter(getApplicationContext(), new ArrayList<String>(weaponDetails.keySet()), weaponDetails);
                weaponsLV.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    View.OnClickListener searchListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String search = searchET.getText().toString();
            getWeapons(search);
        }
    };
}