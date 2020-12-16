package com.boss.homeworkout;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.boss.homeworkout.Database.WorkoutDB;

public class MainActivity extends AppCompatActivity{

    Button btnExercises, btnSetting, btnCalendar;
    ImageView btnPlay;
    ImageView workoutTitle;
    ImageView img_trophy;
    TextView txtdesc;
    TextView txtTrophyNo;
    TextView trophy_info;

    Animation animimgpage, bttone, bttwo, btthree, ltr;

    WorkoutDB workoutDB;
    int currentWS = 0; //current workout set

    int trophyNo = 0;
    Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        myDialog = new Dialog(this);

        // load animation
        animimgpage = AnimationUtils.loadAnimation(this, R.anim.animimgpage);
        bttone = AnimationUtils.loadAnimation(this, R.anim.bttone);
        bttwo = AnimationUtils.loadAnimation(this, R.anim.bttwo);
        btthree = AnimationUtils.loadAnimation(this, R.anim.btthree);
        ltr = AnimationUtils.loadAnimation(this, R.anim.ltr);

        // import font
        Typeface MLight = Typeface.createFromAsset(getAssets(), "MLight.ttf");
        Typeface MMedium = Typeface.createFromAsset(getAssets(), "MMedium.ttf");
        Typeface Vidaloka = Typeface.createFromAsset(getAssets(), "Vidaloka.ttf");


        workoutTitle = (ImageView) findViewById(R.id.workoutTitle);
        btnPlay = (ImageView)findViewById(R.id.btnTraining);
        img_trophy = (ImageView) findViewById(R.id.img_trophy);

        btnExercises = (Button) findViewById(R.id.btnExercises);
        btnSetting = (Button) findViewById(R.id.btnSetting);
        btnCalendar = (Button) findViewById(R.id.btnCalendar);

        txtdesc = (TextView)findViewById(R.id.txtdesc);
        txtTrophyNo = (TextView) findViewById(R.id.txtTrophyNo);
        trophy_info = (TextView) findViewById(R.id.trophy_info);

        txtdesc.setTypeface(MLight);
        txtTrophyNo.setTypeface(MLight);
        trophy_info.setTypeface(MLight);

        // export animate
        workoutTitle.startAnimation(animimgpage);
        txtTrophyNo.startAnimation(bttone);
        img_trophy.startAnimation(bttone);
        txtdesc.startAnimation(bttone);
        trophy_info.startAnimation(bttone);
        btnPlay.startAnimation(bttwo);
        btnExercises.startAnimation(btthree);
        btnSetting.startAnimation(btthree);
        btnCalendar.startAnimation(btthree);

        workoutDB = new WorkoutDB(this);
        currentWS = workoutDB.daysCount();

        trophyNo = workoutDB.getTrophyNo();
        Log.d("DEBUG", "WD : "+trophyNo);
        if(trophyNo >= 0) {
            txtTrophyNo.setText(txtTrophyNo.getText() + String.valueOf(trophyNo));
        }

        else
            txtTrophyNo.setText("No trophy jet");

        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingPage.class);
                startActivity(intent);
            }
        });

        btnExercises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListExercises.class);
                startActivity(intent);
            }
        });

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder altdial = new AlertDialog.Builder(MainActivity.this);
                altdial.setMessage("Do you want to stretch before performing strength exercises?").setCancelable(false)
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(MainActivity.this, WorkoutList.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(MainActivity.this, Daily_Training.class);
                                startActivity(intent);
                            }
                        });

                altdial.create();
                altdial.show();
            }
        });

        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Calendar.class);
                startActivity(intent);
            }
        });
    }

    public void ShowPopup(View view) {
        TextView txtclose, txt_info;
        LinearLayout ll_info;

        myDialog.setContentView(R.layout.custompopup_mode);
        txtclose = myDialog.findViewById(R.id.txtclose);
        txt_info = myDialog.findViewById(R.id.txt_info);
        ll_info = myDialog.findViewById(R.id.ll_info);

        txt_info.setText("\nWhen you finish all 10 sets you will get a trophy.\nEach set has 10 strength exercises. It is recommended that you do stretching exercises first.\n\nKeep working out to become a champion!");
        ll_info.setBackgroundColor(Color.parseColor("#0D122C"));

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
