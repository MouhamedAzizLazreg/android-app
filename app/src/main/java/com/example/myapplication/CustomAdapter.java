package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList<String> racer_id, racer, races_won, fastest_lap, points,nationality,team;

    CustomAdapter(Activity activity, Context context, ArrayList<String> racer_id, ArrayList<String> racer,
                  ArrayList<String> races_won, ArrayList<String> fastest_lap, ArrayList<String> points, ArrayList<String> nationality, ArrayList<String> team) {
        this.activity = activity;
        this.context = context;
        this.racer_id = racer_id;
        this.racer = racer;
        this.races_won = races_won;
        this.fastest_lap = fastest_lap;
        this.points = points;
        this.nationality = nationality;
        this.team = team;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.racer_txt.setText(racer.get(position));
        holder.races_won_txt.setText(races_won.get(position));
        holder.fastest_lap_txt.setText(fastest_lap.get(position));
        holder.points_txt.setText(points.get(position));
        holder.nationality_txt.setText(nationality.get(position));
        holder.team_txt.setText(team.get(position));

        // RecyclerView onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the click event
                // You can open a new activity or perform any other action
                // based on the selected item
                String selectedRacerId = racer_id.get(position);
                String selectedRacer = racer.get(position);
                String selectedRacesWon = races_won.get(position);
                String selectedFastestLap = fastest_lap.get(position);
                String selectedPoints = points.get(position);
                String selectedNationality = nationality.get(position);
                String selectedTeam = team.get(position);

                // Example: Opening a detail activity for the selected food
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", selectedRacerId);
                intent.putExtra("racer", selectedRacer);
                intent.putExtra("racesWon", selectedRacesWon);
                intent.putExtra("fastesLap", selectedFastestLap);
                intent.putExtra("points", selectedPoints);
                intent.putExtra("nationality", selectedNationality);
                intent.putExtra("team", selectedTeam);
                activity.startActivity(intent);
            }

        });
    }

    @Override
    public int getItemCount() {
        return racer_id.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView racer_txt, races_won_txt, fastest_lap_txt, points_txt,nationality_txt,team_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            racer_txt = itemView.findViewById(R.id.racer_txt);
            races_won_txt = itemView.findViewById(R.id.races_won_txt);
            fastest_lap_txt = itemView.findViewById(R.id.fastest_lap_txt);
            points_txt = itemView.findViewById(R.id.points_txt);
            nationality_txt = itemView.findViewById(R.id.nationality_txt);
            team_txt = itemView.findViewById(R.id.team_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}