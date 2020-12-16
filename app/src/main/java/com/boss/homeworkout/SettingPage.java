package com.boss.homeworkout;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.boss.homeworkout.Database.WorkoutDB;

import java.util.Calendar;
import java.util.Date;

public class SettingPage extends AppCompatActivity {

    Button btnSave;
    RadioButton rdiEasy, rdiMedium, rdiHard;
    RadioGroup rdiGroup;
    WorkoutDB workoutDB;
    ToggleButton switchAlarm;
    TimePicker timePicker;
    TextView timeTextView;

    int mHour, mMin;

    Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_page);

        myDialog = new Dialog(this);

        btnSave = (Button) findViewById(R.id.btnSave);
        rdiEasy = (RadioButton) findViewById(R.id.rdiEasy);
        rdiMedium = (RadioButton) findViewById(R.id.rdiMedium);
        rdiHard = (RadioButton) findViewById(R.id.rdiHard);
        rdiGroup = (RadioGroup) findViewById(R.id.rdiGroup);
        switchAlarm = (ToggleButton) findViewById(R.id.switchAlarm);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        timeTextView = (TextView)findViewById(R.id.timeTextView);

        workoutDB = new WorkoutDB(this);
        int hour = workoutDB.getAlarmHour();
        int minute = workoutDB.getAlarmMinute();

        if(hour == 0 && minute == 0) {
            timeTextView.setText("No alarm set");
            switchAlarm.setChecked(false);
        }
        else
            timeTextView.setText("Alarm set for time:" + " " + hour + ":" + minute);

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hourOfDay, int minute) {
                mHour = hourOfDay;
                mMin = minute;
            }
        });

        //Get data from DB and set radio button base on data
        int mode = workoutDB.getSettingMode();
        setRadioButton(mode);

        int alarmChecked = workoutDB.getAlarmChecked();
        if(alarmChecked == 1)
            switchAlarm.setChecked(true);
        else {
            switchAlarm.setChecked(false);
            timeTextView.setText("No alarm set");
        }

        //Event
        btnSave.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                saveWorkoutMode();
                saveAlarm(switchAlarm.isChecked());
                Toast.makeText(SettingPage.this, "SAVED!!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void saveAlarm(boolean checked) {
        if(checked){
            workoutDB.saveAlarmChecked(1);

            AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);

            Date date = new Date();

            Calendar call_alarm = Calendar.getInstance();
            Calendar call_now = Calendar.getInstance();

            call_now.setTime(date);
            call_alarm.setTime(date);

            call_alarm.set(Calendar.HOUR_OF_DAY, mHour);
            call_alarm.set(Calendar.MINUTE, mMin);
            call_alarm.set(Calendar.SECOND, 0);

            if(call_alarm.before(call_now)){
                call_alarm.add(Calendar.DATE, 1);
            }

            Intent i = new Intent(SettingPage.this, AlarmNotificationReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(SettingPage.this, 244444, i, 0);
            alarmManager.set(AlarmManager.RTC_WAKEUP, call_alarm.getTimeInMillis(),pendingIntent);

            workoutDB.saveAlarmHour(mHour);
            workoutDB.saveAlarmMinute(mMin);
        }

        else{
            //Cancel alarm
            Intent intent = new Intent(SettingPage.this, AlarmNotificationReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0,intent,0);
            AlarmManager manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
            manager.cancel(pendingIntent);
            workoutDB.saveAlarmChecked(0);
            workoutDB.updateAlarmHour();
            workoutDB.updateAlarmMinute();
        }

    }

    private void saveWorkoutMode() {
        int selectedID = rdiGroup.getCheckedRadioButtonId();

        if(selectedID == rdiEasy.getId())
            workoutDB.saveSettingMode(0);
        else if(selectedID == rdiMedium.getId())
            workoutDB.saveSettingMode(1);
        else if(selectedID == rdiHard.getId())
            workoutDB.saveSettingMode(2);
    }

    private void setRadioButton(int mode) {
        if(mode == 0)
            rdiGroup.check(R.id.rdiEasy);
        else if(mode == 1)
            rdiGroup.check(R.id.rdiMedium);
        else if(mode == 2)
            rdiGroup.check(R.id.rdiHard);
    }

    public void ShowPopup(View v) {
        TextView txtclose;

        myDialog.setContentView(R.layout.custompopup_mode);
        txtclose = myDialog.findViewById(R.id.txtclose);

        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }
}