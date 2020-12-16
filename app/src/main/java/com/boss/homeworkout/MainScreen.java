package com.boss.homeworkout;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainScreen extends AppCompatActivity {

    Dialog myDialog2;

    TextView titlepage, subtitlepage, btnexercise, btnListExercise;
    ImageView imgpage;
    Animation animimgpage, bttone, bttwo, btthree, ltr;
    View bgprogress, bgprogresstop;

    TextView activity_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        myDialog2 = new Dialog(this);

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

        activity_list = (TextView) findViewById(R.id.list);

        titlepage = (TextView) findViewById(R.id.titlepage);
        subtitlepage = (TextView) findViewById(R.id.subtitlepage);
        btnexercise = (TextView) findViewById(R.id.btnexercise);
        imgpage = (ImageView) findViewById(R.id.imgpage);
        bgprogress = (View) findViewById(R.id.bgprogress);
        bgprogresstop = (View) findViewById(R.id.bgprogresstop);

        // customize font
        titlepage.setTypeface(Vidaloka);
        subtitlepage.setTypeface(MLight);
        btnexercise.setTypeface(MMedium);

        // export animate
        imgpage.startAnimation(animimgpage);
        titlepage.startAnimation(bttone);
        subtitlepage.startAnimation(bttone);

        btnexercise.startAnimation(btthree);
        bgprogress.startAnimation(bttwo);
        bgprogresstop.startAnimation(ltr);

        activity_list.startAnimation(btthree);

        // give an event to another page
        btnexercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(MainScreen.this, MainActivity.class);
                a.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(a);
            }
        });

    }

    public void ShowPopup2(View v) {
        TextView txtClose, btnBMI, btnSettings, btnQuotes, btnStepCounter;

        myDialog2.setContentView(R.layout.custompopup_mainscreen);
        txtClose = (TextView) myDialog2.findViewById(R.id.txtClose);
        btnBMI = (TextView) myDialog2.findViewById(R.id.btnBMI);
        btnSettings = (TextView) myDialog2.findViewById(R.id.set);
        btnQuotes = (TextView) myDialog2.findViewById(R.id.btnQuotes);
        btnListExercise = (TextView) myDialog2.findViewById(R.id.btnListExercise);

        txtClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog2.dismiss();
            }
        });

        btnBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreen.this, BMICalcAct.class);
                startActivity(intent);
            }
        });

        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreen.this, SettingPage.class);
                startActivity(intent);
            }
        });

        btnQuotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreen.this, Quotes.class);
                startActivity(intent);
            }
        });

        btnListExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreen.this, WorkoutList.class);
                startActivity(intent);
            }
        });

        //myDialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog2.show();
    }
}
