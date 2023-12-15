package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {
    private EditText racer,races_won,fastest_lap,points,nationality,team;
    private View add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        racer=findViewById(R.id.racer);
        races_won=findViewById(R.id.races_won);
        fastest_lap=findViewById(R.id.fastest_lap);
        points=findViewById(R.id.points);
        nationality=findViewById(R.id.nationality);
        team=findViewById(R.id.team);
        add_button=findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                myDB.addRacer(racer.getText().toString().trim(),
                        races_won.getText().toString().trim(),
                        fastest_lap.getText().toString().trim(),
                        points.getText().toString().trim(),
                        nationality.getText().toString().trim(),
                        team.getText().toString().trim());
                Intent intent = new Intent(AddActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

    }
}