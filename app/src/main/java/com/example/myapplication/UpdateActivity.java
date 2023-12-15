package com.example.myapplication;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    private EditText racer,races_won,fastest_lap,points,nationality,team;
    private View updateButton, deleteButton;

    String id, racerName, racerWon, fastestLap,Points,Nationality,Team;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        racer=findViewById(R.id.Racer_txt);
        races_won=findViewById(R.id.races_won_txt);
        fastest_lap=findViewById(R.id.fastest_lap_txt);
        points=findViewById(R.id.Points_txt);
        nationality=findViewById(R.id.nationality_txt);
        team=findViewById(R.id.team_txt);
        updateButton=findViewById(R.id.add_button);
        deleteButton=findViewById(R.id.delete);

        // First we call this
        getAndSetIntentData();



        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // And only then we call this
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                racerName = racer.getText().toString().trim();
                racerWon= races_won.getText().toString().trim();
                fastestLap = fastest_lap.getText().toString().trim();
                Points = points.getText().toString().trim();
                Nationality = nationality.getText().toString().trim();
                Team = team.getText().toString().trim();
                myDB.updateData(id, racerName, racerWon, fastestLap,Points,Nationality,Team);
                Intent intent = new Intent(UpdateActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });
    }

    void getAndSetIntentData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("racer") &&
                getIntent().hasExtra("racesWon") && getIntent().hasExtra("fastesLap")) {
            // Getting Data from Intent
            id = getIntent().getStringExtra("id");
            racerName = getIntent().getStringExtra("racer");
            racerWon = getIntent().getStringExtra("racesWon");
            fastestLap = getIntent().getStringExtra("fastestLap");
            Points=getIntent().getStringExtra("points");
            Nationality=getIntent().getStringExtra("nationality");
            Team=getIntent().getStringExtra("team");
            // Setting Intent Data
            racer.setText(racerName);
            races_won.setText(racerWon);
            fastest_lap.setText(fastestLap);
            points.setText(Points);
            nationality.setText(Nationality);
            team.setText(Team);
            Log.d("stev", racer + " " + races_won + " " + fastest_lap + " " + points + " " + nationality + " " + team );
        } else {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + racer + " ?");
        builder.setMessage("Are you sure you want to delete " + racer + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.deleteOneRow(id);
                Intent intent = new Intent(UpdateActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}