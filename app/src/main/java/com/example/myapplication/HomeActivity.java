package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    View add_button;

    MyDatabaseHelper myDB;
    ArrayList<String> racer_id,racer,races_won,fastest_lap,points,nationality,team;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = findViewById(R.id.recyclerView);

        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        myDB = new MyDatabaseHelper(HomeActivity.this);
        racer_id = new ArrayList<>();
        racer = new ArrayList<>();
        races_won = new ArrayList<>();
        fastest_lap = new ArrayList<>();
        points = new ArrayList<>();
        nationality = new ArrayList<>();
        team = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(HomeActivity.this, this, racer_id,racer,races_won,fastest_lap,points,nationality,team);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(HomeActivity.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            recreate();
        }
    }

    void storeDataInArrays() {
        Cursor cursor = myDB.readAllData();


            while (cursor.moveToNext()) {
                racer_id.add(cursor.getString(0));
                racer.add(cursor.getString(1));
                races_won.add(cursor.getString(2));
                fastest_lap.add(cursor.getString(3));
                points.add(cursor.getString(4));
                nationality.add(cursor.getString(5));
                team.add(cursor.getString(6));
            }

    }

}