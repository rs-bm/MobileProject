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
    Button ResetButton;
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

    View.OnClickListener resetListener = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            editor.putString("wins", "0");
            editor.putString("plays", "0");
            editor.putString("bugs", "0");
            editor.putString("automations", "0");
            editor.putString("illuminates", "0");
            editor.putString("friendly", "0");
            editor.putString("fired", "0");
            editor.putString("hits", "0");
            editor.putString("deaths", "0");
            editor.putString("revives","0");
            editor.putString("accuracy", "0");
            editor.putString("missionSuccessRate", "0");

            editor.commit();

            typeWins.setText("0");
            typePlays.setText("0");
            typeBugs.setText("0");
            typeAutomations.setText("0");
            typeIlluminates.setText("0");
            typeFriendly.setText("0");
            typeFires.setText("0");
            typeHits.setText("0");
            typeDeaths.setText("0");
            typeRevives.setText("0");
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
        ResetButton = findViewById(R.id.resetButton);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        prefs = getSharedPreferences("stats", MODE_PRIVATE);
        editor = prefs.edit();

        typeWins.setText(prefs.getString("wins", "0"));
        typePlays.setText(prefs.getString("plays", "0"));
        typeBugs.setText(prefs.getString("bugs", "0"));
        typeAutomations.setText(prefs.getString("automations", "0"));
        typeIlluminates.setText(prefs.getString("illuminates", "0"));
        typeFriendly.setText(prefs.getString("friendly", "0"));
        typeFires.setText(prefs.getString("fired", "0"));
        typeHits.setText(prefs.getString("hits", "0"));
        typeDeaths.setText(prefs.getString("deaths", "0"));
        typeRevives.setText(prefs.getString("revives","0"));

        SaveButton.setOnClickListener(saveListener);
        CancelButton.setOnClickListener(cancelListener);
        ResetButton.setOnClickListener(resetListener);


    }
}