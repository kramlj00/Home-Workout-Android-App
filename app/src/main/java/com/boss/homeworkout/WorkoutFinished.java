package com.boss.homeworkout;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.boss.homeworkout.Database.WorkoutDB;

public class WorkoutFinished extends AppCompatActivity {

    Button btnStartNew;

    WorkoutDB workoutDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_finished);

        workoutDB = new WorkoutDB(this);

        btnStartNew = (Button) findViewById(R.id.btnStartNew);

        btnStartNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workoutDB.updateWorkoutDays();
                workoutDB.updateWorkoutHistory();
                Intent intent = new Intent(WorkoutFinished.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
