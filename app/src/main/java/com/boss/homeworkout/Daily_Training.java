package com.boss.homeworkout;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.boss.homeworkout.Database.WorkoutDB;
import com.boss.homeworkout.Model.Exercise;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Daily_Training extends AppCompatActivity {

    MediaPlayer player, player2;

    Button btnStart;
    ImageView ex_image;
    TextView txtGetReady, txtCountDown, txtTimer, ex_name;
    ProgressBar progressBar;
    LinearLayout layoutGetReady;

    int ex_id = 0;

    List<Exercise> list = new ArrayList<>();

    WorkoutDB workoutDB;
    int currentWS; //current workout set

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily__training);

        Intent intent = getIntent();
        final int currentSet = intent.getIntExtra("START_AGAIN", 0);

        workoutDB = new WorkoutDB(this);

        if(currentSet >= 10){
            workoutDB.updateWorkoutDays();
        }

        currentWS = workoutDB.daysCount();
        Log.d("DEBUG", "Workout day : "+ currentWS);

        if(currentWS == 0)
            initData1();
        else if(currentWS == 1)
            initData2();
        else if(currentWS == 2)
            initData3();
        else if(currentWS == 3)
            initData4();
        else if(currentWS == 4)
            initData5();
        else if(currentWS == 5)
            initData6();
        else if(currentWS == 6)
            initData7();
        else if(currentWS == 7)
            initData8();
        else if(currentWS == 8)
            initData9();
        else if(currentWS == 9)
            initData10();
        else {
            initData10();
        }


        btnStart = (Button)findViewById(R.id.btnStart);

        ex_image = (ImageView)findViewById(R.id.detail_image);

        txtGetReady = (TextView)findViewById(R.id.txtGetReady);
        txtCountDown = (TextView)findViewById(R.id.txtCountDown);
        txtTimer = (TextView)findViewById(R.id.timer);
        ex_name = (TextView)findViewById(R.id.title);

        progressBar = (ProgressBar)findViewById(R.id.progressBar);

        layoutGetReady = (LinearLayout)findViewById(R.id.layout_get_ready);

        //Set data
        progressBar.setMax(list.size());
        setExerciseInformation(ex_id);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnStart.getText().toString().toLowerCase().equals("start")){
                    showGetReady();
                    btnStart.setText("done");
                }
                else if(btnStart.getText().toString().toLowerCase().equals("done")){
                    if(workoutDB.getSettingMode() == 0)
                        exercisesEasyModeCountDown.cancel();
                    else if(workoutDB.getSettingMode() == 1)
                        exercisesMediumModeCountDown.cancel();
                    else if(workoutDB.getSettingMode() == 2)
                        exercisesHardModeCountDown.cancel();
                    restTimeCountDown.cancel();

                    if(ex_id<list.size()-1){
                        showRestTime();
                        ex_id++;
                        progressBar.setProgress(ex_id);
                        txtTimer.setText("");
                    }
                    else
                        showFinished();

                }
                else{
                    if(workoutDB.getSettingMode() == 0)
                        exercisesEasyModeCountDown.cancel();
                    else if(workoutDB.getSettingMode() == 1)
                        exercisesMediumModeCountDown.cancel();
                    else if(workoutDB.getSettingMode() == 2)
                        exercisesHardModeCountDown.cancel();

                    restTimeCountDown.cancel();

                    if(ex_id<list.size())
                        setExerciseInformation(ex_id);
                    else
                        showFinished();
                }
            }
        });
    }

    private void showRestTime() {
        ex_image.setVisibility(View.INVISIBLE);
        txtTimer.setVisibility(View.INVISIBLE);
        btnStart.setVisibility(View.VISIBLE);
        layoutGetReady.setVisibility(View.VISIBLE);

        if(player != null) {
            player.stop();
            player = null;
        }
        restTimeCountDown.start();

        btnStart.setText("Skip");
        txtGetReady.setText("REST TIME");
    }

    private void showGetReady() {
        ex_image.setVisibility(View.VISIBLE);
        btnStart.setVisibility(View.INVISIBLE);
        txtTimer.setVisibility(View.VISIBLE);
        layoutGetReady.setVisibility(View.VISIBLE);

        playGetReady();
        play();

        txtGetReady.setText("GET READY");
        new CountDownTimer(6000,1000){

            @Override
            public void onTick(long l) {
                txtCountDown.setText(""+(l-1000)/1000);
            }

            @Override
            public void onFinish() {
                showExercises();
            }
        }.start();
    }

    private void showExercises() {
        if(ex_id<list.size()) //list size contains all exercises
        {
            ex_image.setVisibility(View.VISIBLE);
            btnStart.setVisibility(View.VISIBLE);
            layoutGetReady.setVisibility(View.INVISIBLE);

            if(workoutDB.getSettingMode() == 0)
                exercisesEasyModeCountDown.start();
            else if(workoutDB.getSettingMode() == 1)
                exercisesMediumModeCountDown.start();
            else if(workoutDB.getSettingMode() == 2)
                exercisesHardModeCountDown.start();

            //Set data
            ex_image.setImageResource(list.get(ex_id).getImage_id());
            ex_name.setText(list.get(ex_id).getName());
        }
        else
            showFinished();
    }

    private void showFinished() {
        ex_image.setVisibility(View.INVISIBLE);
        txtTimer.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
        ex_name.setVisibility(View.INVISIBLE);
        layoutGetReady.setVisibility(View.VISIBLE);

        txtGetReady.setText("FINISHED!!");
        txtCountDown.setText("Congratulation ! \nYou are done with today's exercises.");
        txtCountDown.setTextSize(20);

        //spremanje dana u bazu podataka
        workoutDB.saveDay(""+ Calendar.getInstance().getTimeInMillis());
        //spremanje seta u bazu podataka
        currentWS++;
        workoutDB.saveSetDone(String.valueOf(currentWS));
        Log.d("DEBUG", "currentWS : "+currentWS);

        workoutDB.daysCount();
        Log.d("DEBUG", "WD : "+workoutDB.daysCount());

        if(workoutDB.daysCount() >= 10){
            int value = workoutDB.getTrophyNo();
            value++;

            workoutDB.updateTrophyNo(value);
            Intent intent = new Intent(Daily_Training.this, WorkoutFinished.class);
            startActivity(intent);
        }

        btnStart.setText("NEXT");
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Daily_Training.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    //CountDown
    CountDownTimer exercisesEasyModeCountDown = new CountDownTimer(Common.TIME_LIMIT_EASY, 1000) {
        @Override
        public void onTick(long l) {
            txtTimer.setText(""+(l/1000));

            if(l / 1000 == 4){
                play();
            }
        }

        @Override
        public void onFinish() {
            if(ex_id<list.size()-1){
                ex_id++;
                progressBar.setProgress(ex_id);
                txtTimer.setText("");

                showRestTime();
            }
            else
                showFinished();
        }
    };
    CountDownTimer exercisesMediumModeCountDown = new CountDownTimer(Common.TIME_LIMIT_MEDIUM, 1000) {
        @Override
        public void onTick(long l) {
            txtTimer.setText(""+(l/1000));

            if(l / 1000 == 4){
                play();
            }
        }

        @Override
        public void onFinish() {
            if(ex_id<list.size()-1){
                ex_id++;
                progressBar.setProgress(ex_id);
                txtTimer.setText("");

               showRestTime();
            }
            else
                showFinished();
        }
    };
    CountDownTimer exercisesHardModeCountDown = new CountDownTimer(Common.TIME_LIMIT_HARD, 1000) {
        @Override
        public void onTick(long l) {
            txtTimer.setText(""+(l/1000));

           if(l / 1000 == 4){
                play();
           }
        }

        @Override
        public void onFinish() {
            if(ex_id<list.size()-1){
                ex_id++;
                progressBar.setProgress(ex_id);
                txtTimer.setText("");

                showRestTime();
            }
            else
                showFinished();
        }
    };

    CountDownTimer restTimeCountDown = new CountDownTimer(10000, 1000) {
        @Override
        public void onTick(long l) {
            txtCountDown.setText(""+(l/1000));
        }

        @Override
        public void onFinish() {
            setExerciseInformation(ex_id);
            btnStart.setText("Start");
        }
    };

    private void setExerciseInformation(int id) {
        ex_image.setImageResource(list.get(id).getImage_id());
        ex_name.setText(list.get(id).getName());
        btnStart.setText("Start");

        ex_image.setVisibility(View.VISIBLE);
        btnStart.setVisibility(View.VISIBLE);
        txtTimer.setVisibility(View.VISIBLE);
        layoutGetReady.setVisibility(View.INVISIBLE);    }

    public void play (){
        if(player == null){
            player = MediaPlayer.create(this, R.raw.sound);
        }
        player.start();
    }

    public void playGetReady (){
        if(player2 == null){
            player2 = MediaPlayer.create(this, R.raw.get_ready);
        }
        player2.start();
    }

    private void initData1() {
        list.add(new Exercise(R.drawable.sprints,"Sprints"));
        list.add(new Exercise(R.drawable.jumping_jacks,"Jumping jacks"));
        list.add(new Exercise(R.drawable.mountain_climbers,"Mountain climbers"));
        list.add(new Exercise(R.drawable.bridge,"Bridge"));
        list.add(new Exercise(R.drawable.air_squat,"Air squat"));
        list.add(new Exercise(R.drawable.squat_jump,"Squat jump"));
        list.add(new Exercise(R.drawable.opposite_arm_opposite_leg,"Opposite arm opposite leg"));
        list.add(new Exercise(R.drawable.dead_bug1,"Dead bug 1"));
        list.add(new Exercise(R.drawable.dead_bug2,"Dead bug 2"));
        list.add(new Exercise(R.drawable.plank,"Plank"));
    }

    private void initData2() {
        list.add(new Exercise(R.drawable.butt_kicks,"Butt kicks"));
        list.add(new Exercise(R.drawable.pulse_up,"Pulse up"));
        list.add(new Exercise(R.drawable.swing_up,"Swing up"));
        list.add(new Exercise(R.drawable.left_leg_reach,"Left leg reach"));
        list.add(new Exercise(R.drawable.right_leg_reach,"Right leg reach"));
        list.add(new Exercise(R.drawable.hollow_body_hold,"Hollow body hold"));
        list.add(new Exercise(R.drawable.superman,"Superman"));
        list.add(new Exercise(R.drawable.squat_jump,"Squat jump"));
        list.add(new Exercise(R.drawable.side_lunges,"Side lunges"));
        list.add(new Exercise(R.drawable.plank,"Plank"));
    }

    private void initData3() {
        list.add(new Exercise(R.drawable.left_lunge,"Lunge left"));
        list.add(new Exercise(R.drawable.right_lunge,"Lunge right"));
        list.add(new Exercise(R.drawable.knee_plank,"Knee plank"));
        list.add(new Exercise(R.drawable.forward_bend,"Forward bend"));
        list.add(new Exercise(R.drawable.v_up,"V up"));
        list.add(new Exercise(R.drawable.left_leg_hip_bridge,"Left leg hip bridge"));
        list.add(new Exercise(R.drawable.right_leg_hip_bridge,"Right leg hip bridge"));
        list.add(new Exercise(R.drawable.squat_jump,"Squat jump"));
        list.add(new Exercise(R.drawable.tricep_dips,"Triceps dips"));
        list.add(new Exercise(R.drawable.plank,"Plank"));
    }

    private void initData4() {
        list.add(new Exercise(R.drawable.roll_over,"Roll over"));
        list.add(new Exercise(R.drawable.back_extension,"Back extension"));
        list.add(new Exercise(R.drawable.chair_hip_raise,"Chair hip raise"));
        list.add(new Exercise(R.drawable.reverse_plank_bridge,"Reverse plank bridge"));
        list.add(new Exercise(R.drawable.reverse_left_leg_lift,"Reverse left leg lift "));
        list.add(new Exercise(R.drawable.reverse_right_leg_lift,"Reverse right leg lift"));
        list.add(new Exercise(R.drawable.dead_bug1,"Dead bug 1"));
        list.add(new Exercise(R.drawable.dead_bug2,"Dead bug 2"));
        list.add(new Exercise(R.drawable.bear_crawl,"bear crawl"));
        list.add(new Exercise(R.drawable.plank,"Plank"));
    }

    private void initData5() {
        list.add(new Exercise(R.drawable.jumping_jacks,"Jumping jacks"));
        list.add(new Exercise(R.drawable.air_squat,"Air squat"));
        list.add(new Exercise(R.drawable.pulse_up,"Pulse up"));
        list.add(new Exercise(R.drawable.russian_twist,"Russian twist"));
        list.add(new Exercise(R.drawable.push_up,"Push up"));
        list.add(new Exercise(R.drawable.swing_up,"Swing up"));
        list.add(new Exercise(R.drawable.flutter_kicks,"Flutter kicks"));
        list.add(new Exercise(R.drawable.thrust_burpee,"Thrust burpee"));
        list.add(new Exercise(R.drawable.twist_body,"Twist body"));
        list.add(new Exercise(R.drawable.plank_jack,"Plank jack"));
    }

    private void initData6() {
        list.add(new Exercise(R.drawable.high_knees,"High knees"));
        list.add(new Exercise(R.drawable.butt_kicks,"Butt kicks"));
        list.add(new Exercise(R.drawable.side_lunges,"Side lunges"));
        list.add(new Exercise(R.drawable.roll_over,"Roll over"));
        list.add(new Exercise(R.drawable.leg_swing,"Leg swing"));
        list.add(new Exercise(R.drawable.reverse_crunch,"Reverse crunch"));
        list.add(new Exercise(R.drawable.dolphin_plank,"Dolphin plank"));
        list.add(new Exercise(R.drawable.bridge,"Bridge"));
        list.add(new Exercise(R.drawable.chair_hip_raise,"Chair hip raise"));
        list.add(new Exercise(R.drawable.plank,"Plank"));

    }

    private void initData7() {
        list.add(new Exercise(R.drawable.left_lunge,"Left lunge"));
        list.add(new Exercise(R.drawable.right_lunge,"Right lunge"));
        list.add(new Exercise(R.drawable.air_squat,"Air squat"));
        list.add(new Exercise(R.drawable.squat_jump,"Squat jump"));
        list.add(new Exercise(R.drawable.reverse_left_leg_lift,"Reverse left leg lift"));
        list.add(new Exercise(R.drawable.reverse_right_leg_lift,"Reverse right leg lift"));
        list.add(new Exercise(R.drawable.thrust_burpee,"Thrust burpee"));
        list.add(new Exercise(R.drawable.flutter_kicks,"Flutter kicks"));
        list.add(new Exercise(R.drawable.leg_lowering,"Leg lowering"));
        list.add(new Exercise(R.drawable.plank,"Plank"));
    }

    private void initData8() {
        list.add(new Exercise(R.drawable.high_knees,"High knees"));
        list.add(new Exercise(R.drawable.mountain_climbers,"Mountain climbers"));
        list.add(new Exercise(R.drawable.bear_crawl,"Bear crawl"));
        list.add(new Exercise(R.drawable.leg_swing,"Leg swing"));
        list.add(new Exercise(R.drawable.butt_up,"Butt up"));
        list.add(new Exercise(R.drawable.dead_bug1,"Dead bug 1"));
        list.add(new Exercise(R.drawable.dead_bug2,"Dead bug 2"));
        list.add(new Exercise(R.drawable.russian_twist,"Russian twist"));
        list.add(new Exercise(R.drawable.push_up,"Push up"));
        list.add(new Exercise(R.drawable.plank,"Plank"));
    }

    private void initData9() {
        list.add(new Exercise(R.drawable.jab_squats,"Jab squats"));
        list.add(new Exercise(R.drawable.right_side_bridge,"Right side bridge"));
        list.add(new Exercise(R.drawable.left_side_bridge,"Left side bridge"));
        list.add(new Exercise(R.drawable.bridge,"Bridge"));
        list.add(new Exercise(R.drawable.crunch_with_leg_reach,"Crunch with leg reach"));
        list.add(new Exercise(R.drawable.left_leg_raise,"Left leg raises"));
        list.add(new Exercise(R.drawable.right_leg_raise,"Right leg raises"));
        list.add(new Exercise(R.drawable.bear_crawl,"Bear crawl"));
        list.add(new Exercise(R.drawable.plank_knee_to_elbow,"Plank knee to elbow"));
        list.add(new Exercise(R.drawable.plank,"Plank"));
    }

    private void initData10() {
        list.add(new Exercise(R.drawable.left_side_plank,"Left side plank"));
        list.add(new Exercise(R.drawable.right_side_plank,"Right side plank"));
        list.add(new Exercise(R.drawable.bridge,"Bridge"));
        list.add(new Exercise(R.drawable.leg_swing,"Leg swing"));
        list.add(new Exercise(R.drawable.hollow_body_hold,"Hollow body hold"));
        list.add(new Exercise(R.drawable.push_up,"Push up"));
        list.add(new Exercise(R.drawable.reverse_plank_bridge,"Reverse plank bridge"));
        list.add(new Exercise(R.drawable.air_squat,"Air squat"));
        list.add(new Exercise(R.drawable.squat_jump,"Squat jump"));
        list.add(new Exercise(R.drawable.plank,"Plank"));
    }



}