package com.boss.homeworkout;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.boss.homeworkout.Custom.WorkoutDoneDecorator;
import com.boss.homeworkout.Database.WorkoutDB;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

public class Calendar extends AppCompatActivity {

    MaterialCalendarView materialCalendarView;

    WorkoutDB workoutDB;
    Button btnHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        workoutDB = new WorkoutDB(this);
        btnHistory = (Button)findViewById(R.id.btnHistory);

        materialCalendarView = (MaterialCalendarView)findViewById(R.id.calendar);
        List<String> workoutDay = workoutDB.getWorkoutDays();

        HashSet<CalendarDay> convertedList = new HashSet<>();
        for(String value:workoutDay) {
            convertedList.add(CalendarDay.from(new Date(Long.parseLong(value))));
        }
        materialCalendarView.addDecorator(new WorkoutDoneDecorator(convertedList));


        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Calendar.this, ActivityHistory.class);
                startActivity(intent);
            }
        });
    }

}
