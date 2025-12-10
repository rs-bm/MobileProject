package com.example.mobileproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class FourthActivity extends AppCompatActivity {

    private EditText typeWins;
    private EditText typePlays;
    private EditText typeBugs;
    private EditText typeAutomations;
    private EditText typeIlluminates;
    private EditText typeFriendly;
    private EditText typeFires;
    private EditText typeHits;
    private EditText typeDeaths;
    private EditText typeRevives;

    Button SaveButton;
    Button CancelButton;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    View.OnClickListener cancelListener = v -> {
        Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
        startActivity(intent);
        finish();
    };

    View.OnClickListener saveListener = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            ArrayList<EditText> fields = new ArrayList<>();
            fields.add(typeWins);
            fields.add(typePlays);
            fields.add(typeBugs);
            fields.add(typeAutomations);
            fields.add(typeIlluminates);
            fields.add(typeFriendly);
            fields.add(typeFires);
            fields.add(typeHits);
            fields.add(typeDeaths);
            fields.add(typeRevives);
            for (EditText field : fields)
            {
                if (field.getText().toString().trim().isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"All fields must be filled", Toast.LENGTH_SHORT).show();
                    return;
                }
            }



            int bulletsFired = Integer.parseInt(typeFires.getText().toString().trim());
            int bulletsHit = Integer.parseInt(typeHits.getText().toString().trim());
            if (bulletsHit > bulletsFired)
            {
                Toast.makeText(getApplicationContext(),"bullet hits cannot be greater than bullets fired", Toast.LENGTH_SHORT).show();
                return;
            }
            int wins = Integer.parseInt(typeWins.getText().toString().trim());
            int plays = Integer.parseInt(typePlays.getText().toString().trim());
            if (wins > plays)
            {
                Toast.makeText(getApplicationContext(),"wins cannot exceed the total missions played", Toast.LENGTH_SHORT).show();
                return;
            }
            double accuracy = ((double) bulletsHit/bulletsFired)*100;
            double missionSuccessRate = ((double) wins/plays)*100;

            editor.putString("wins", typeWins.getText().toString());
            editor.putString("plays", typePlays.getText().toString());
            editor.putString("bugs", typeBugs.getText().toString());
            editor.putString("automations", typeAutomations.getText().toString());
            editor.putString("illuminates", typeIlluminates.getText().toString());
            editor.putString("friendly", typeFriendly.getText().toString());
            editor.putString("fired", typeFires.getText().toString());
            editor.putString("hits", typeHits.getText().toString());
            editor.putString("deaths", typeDeaths.getText().toString());
            editor.putString("revives", typeRevives.getText().toString());

            editor.putString("accuracy", String.valueOf(accuracy));
            editor.putString("missionSuccessRate", String.valueOf(missionSuccessRate));
            editor.commit();
            Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
            startActivity(intent);
            finish();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_fourth);
        typeWins = findViewById(R.id.typeWins);
        typePlays = findViewById(R.id.typePlays);
        typeBugs = findViewById(R.id.typeBugs);
        typeAutomations = findViewById(R.id.typeAutomations);
        typeIlluminates = findViewById(R.id.typeIlluminates);
        typeFriendly = findViewById(R.id.typeFriendly);
        typeFires = findViewById(R.id.typeFires);
        typeHits = findViewById(R.id.typeHits);
        typeDeaths = findViewById(R.id.typeDeaths);
        typeRevives = findViewById(R.id.typeRevives);
        SaveButton = findViewById(R.id.saveButton);
        CancelButton = findViewById(R.id.cancelButton);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        prefs = getSharedPreferences("stats", MODE_PRIVATE);
        editor = prefs.edit();

        typeWins.setText(prefs.getString("wins", ""));
        typePlays.setText(prefs.getString("plays", ""));
        typeBugs.setText(prefs.getString("bugs", ""));
        typeAutomations.setText(prefs.getString("automations", ""));
        typeIlluminates.setText(prefs.getString("illuminates", ""));
        typeFriendly.setText(prefs.getString("friendly", ""));
        typeFires.setText(prefs.getString("fired", ""));
        typeHits.setText(prefs.getString("hits", ""));
        typeDeaths.setText(prefs.getString("deaths", ""));
        typeRevives.setText(prefs.getString("revives",""));

        SaveButton.setOnClickListener(saveListener);
        CancelButton.setOnClickListener(cancelListener);


    }
}