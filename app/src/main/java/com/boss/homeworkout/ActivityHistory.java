package com.boss.homeworkout;

import android.content.Intent;
import android.database.Cursor;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.boss.homeworkout.Adapter.RecycleViewAdapter;
import com.boss.homeworkout.Database.WorkoutDB;
import com.boss.homeworkout.Model.Exercise;

import java.util.ArrayList;
import java.util.List;

public class ActivityHistory extends AppCompatActivity {

    WorkoutDB workoutDB;

    ArrayList<String> listItem;
    ArrayAdapter adapter;
    ListView historyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        workoutDB = new WorkoutDB(this);

        listItem = new ArrayList<>();

        historyList = (ListView) findViewById(R.id.historyList);

        viewData();

        historyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                setContentView(R.layout.activity_list_exercises);
                Button btnBack = (Button)findViewById(R.id.btnBack);
                List<Exercise> exerciseList = new ArrayList<>();
                RecyclerView.LayoutManager layoutManager;
                RecyclerView recyclerView;
                RecycleViewAdapter adapter;

                switch(position){
                    case 0:
                        exerciseList.add(new Exercise(R.drawable.sprints,"Sprints"));
                        exerciseList.add(new Exercise(R.drawable.jumping_jacks,"Jumping jacks"));
                        exerciseList.add(new Exercise(R.drawable.mountain_climbers,"Mountain climbers"));
                        exerciseList.add(new Exercise(R.drawable.bridge,"Bridge"));
                        exerciseList.add(new Exercise(R.drawable.air_squat,"Air squat"));
                        exerciseList.add(new Exercise(R.drawable.squat_jump,"Squat jump"));
                        exerciseList.add(new Exercise(R.drawable.opposite_arm_opposite_leg,"Opposite arm opposite leg"));
                        exerciseList.add(new Exercise(R.drawable.dead_bug1,"Dead bug 1"));
                        exerciseList.add(new Exercise(R.drawable.dead_bug2,"Dead bug 2"));
                        exerciseList.add(new Exercise(R.drawable.plank,"Plank"));
                        break;
                    case 1:
                        exerciseList.add(new Exercise(R.drawable.butt_kicks,"Butt kicks"));
                        exerciseList.add(new Exercise(R.drawable.pulse_up,"Pulse up"));
                        exerciseList.add(new Exercise(R.drawable.swing_up,"Swing up"));
                        exerciseList.add(new Exercise(R.drawable.left_leg_reach,"Left leg reach"));
                        exerciseList.add(new Exercise(R.drawable.right_leg_reach,"Right leg reach"));
                        exerciseList.add(new Exercise(R.drawable.hollow_body_hold,"Hollow body hold"));
                        exerciseList.add(new Exercise(R.drawable.superman,"Superman"));
                        exerciseList.add(new Exercise(R.drawable.squat_jump,"Squat jump"));
                        exerciseList.add(new Exercise(R.drawable.side_lunges,"Side lunges"));
                        exerciseList.add(new Exercise(R.drawable.plank,"Plank"));
                        break;
                    case 2:
                        exerciseList.add(new Exercise(R.drawable.left_lunge,"Lunge left"));
                        exerciseList.add(new Exercise(R.drawable.right_lunge,"Lunge right"));
                        exerciseList.add(new Exercise(R.drawable.knee_plank,"Knee plank"));
                        exerciseList.add(new Exercise(R.drawable.forward_bend,"Forward bend"));
                        exerciseList.add(new Exercise(R.drawable.v_up,"V up"));
                        exerciseList.add(new Exercise(R.drawable.left_leg_hip_bridge,"Left leg hip bridge"));
                        exerciseList.add(new Exercise(R.drawable.right_leg_hip_bridge,"Right leg hip bridge"));
                        exerciseList.add(new Exercise(R.drawable.squat_jump,"Squat jump"));
                        exerciseList.add(new Exercise(R.drawable.tricep_dips,"Triceps dips"));
                        exerciseList.add(new Exercise(R.drawable.plank,"Plank"));
                        break;
                    case 3:
                        exerciseList.add(new Exercise(R.drawable.roll_over,"Roll over"));
                        exerciseList.add(new Exercise(R.drawable.back_extension,"Back extension"));
                        exerciseList.add(new Exercise(R.drawable.chair_hip_raise,"Chair hip raise"));
                        exerciseList.add(new Exercise(R.drawable.reverse_plank_bridge,"Reverse plank bridge"));
                        exerciseList.add(new Exercise(R.drawable.reverse_left_leg_lift,"Reverse left leg lift "));
                        exerciseList.add(new Exercise(R.drawable.reverse_right_leg_lift,"Reverse right leg lift"));
                        exerciseList.add(new Exercise(R.drawable.dead_bug1,"Dead bug 1"));
                        exerciseList.add(new Exercise(R.drawable.dead_bug2,"Dead bug 2"));
                        exerciseList.add(new Exercise(R.drawable.bear_crawl,"bear crawl"));
                        exerciseList.add(new Exercise(R.drawable.plank,"Plank"));
                        break;
                    case 4:
                        exerciseList.add(new Exercise(R.drawable.jumping_jacks,"Jumping jacks"));
                        exerciseList.add(new Exercise(R.drawable.air_squat,"Air squat"));
                        exerciseList.add(new Exercise(R.drawable.pulse_up,"Pulse up"));
                        exerciseList.add(new Exercise(R.drawable.russian_twist,"Russian twist"));
                        exerciseList.add(new Exercise(R.drawable.push_up,"Push up"));
                        exerciseList.add(new Exercise(R.drawable.swing_up,"Swing up"));
                        exerciseList.add(new Exercise(R.drawable.flutter_kicks,"Flutter kicks"));
                        exerciseList.add(new Exercise(R.drawable.thrust_burpee,"Thrust burpee"));
                        exerciseList.add(new Exercise(R.drawable.twist_body,"Twist body"));
                        exerciseList.add(new Exercise(R.drawable.plank_jack,"Plank jack"));
                        break;
                    case 5:
                        exerciseList.add(new Exercise(R.drawable.high_knees,"High knees"));
                        exerciseList.add(new Exercise(R.drawable.butt_kicks,"Butt kicks"));
                        exerciseList.add(new Exercise(R.drawable.side_lunges,"Side lunges"));
                        exerciseList.add(new Exercise(R.drawable.roll_over,"Roll over"));
                        exerciseList.add(new Exercise(R.drawable.leg_swing,"Leg swing"));
                        exerciseList.add(new Exercise(R.drawable.reverse_crunch,"Reverse crunch"));
                        exerciseList.add(new Exercise(R.drawable.dolphin_plank,"Dolphin plank"));
                        exerciseList.add(new Exercise(R.drawable.bridge,"Bridge"));
                        exerciseList.add(new Exercise(R.drawable.chair_hip_raise,"Chair hip raise"));
                        exerciseList.add(new Exercise(R.drawable.plank,"Plank"));
                        break;
                    case 6:
                        exerciseList.add(new Exercise(R.drawable.left_lunge,"Left lunge"));
                        exerciseList.add(new Exercise(R.drawable.right_lunge,"Right lunge"));
                        exerciseList.add(new Exercise(R.drawable.air_squat,"Air squat"));
                        exerciseList.add(new Exercise(R.drawable.squat_jump,"Squat jump"));
                        exerciseList.add(new Exercise(R.drawable.reverse_left_leg_lift,"Reverse left leg lift"));
                        exerciseList.add(new Exercise(R.drawable.reverse_right_leg_lift,"Reverse right leg lift"));
                        exerciseList.add(new Exercise(R.drawable.thrust_burpee,"Thrust burpee"));
                        exerciseList.add(new Exercise(R.drawable.flutter_kicks,"Flutter kicks"));
                        exerciseList.add(new Exercise(R.drawable.leg_lowering,"Leg lowering"));
                        exerciseList.add(new Exercise(R.drawable.plank,"Plank"));
                        break;
                    case 7:
                        exerciseList.add(new Exercise(R.drawable.high_knees,"High knees"));
                        exerciseList.add(new Exercise(R.drawable.mountain_climbers,"Mountain climbers"));
                        exerciseList.add(new Exercise(R.drawable.bear_crawl,"Bear crawl"));
                        exerciseList.add(new Exercise(R.drawable.leg_swing,"Leg swing"));
                        exerciseList.add(new Exercise(R.drawable.butt_up,"Butt up"));
                        exerciseList.add(new Exercise(R.drawable.dead_bug1,"Dead bug 1"));
                        exerciseList.add(new Exercise(R.drawable.dead_bug2,"Dead bug 2"));
                        exerciseList.add(new Exercise(R.drawable.russian_twist,"Russian twist"));
                        exerciseList.add(new Exercise(R.drawable.push_up,"Push up"));
                        exerciseList.add(new Exercise(R.drawable.plank,"Plank"));
                        break;
                    case 8:
                        exerciseList.add(new Exercise(R.drawable.jab_squats,"Jab squats"));
                        exerciseList.add(new Exercise(R.drawable.right_side_bridge,"Right side bridge"));
                        exerciseList.add(new Exercise(R.drawable.left_side_bridge,"Left side bridge"));
                        exerciseList.add(new Exercise(R.drawable.bridge,"Bridge"));
                        exerciseList.add(new Exercise(R.drawable.crunch_with_leg_reach,"Crunch with leg reach"));
                        exerciseList.add(new Exercise(R.drawable.left_leg_raise,"Left leg raises"));
                        exerciseList.add(new Exercise(R.drawable.right_leg_raise,"Right leg raises"));
                        exerciseList.add(new Exercise(R.drawable.bear_crawl,"Bear crawl"));
                        exerciseList.add(new Exercise(R.drawable.plank_knee_to_elbow,"Plank knee to elbow"));
                        exerciseList.add(new Exercise(R.drawable.plank,"Plank"));
                        break;
                    case 9:
                        exerciseList.add(new Exercise(R.drawable.left_side_plank,"Left side plank"));
                        exerciseList.add(new Exercise(R.drawable.right_side_plank,"Right side plank"));
                        exerciseList.add(new Exercise(R.drawable.bridge,"Bridge"));
                        exerciseList.add(new Exercise(R.drawable.leg_swing,"Leg swing"));
                        exerciseList.add(new Exercise(R.drawable.hollow_body_hold,"Hollow body hold"));
                        exerciseList.add(new Exercise(R.drawable.push_up,"Push up"));
                        exerciseList.add(new Exercise(R.drawable.reverse_plank_bridge,"Reverse plank bridge"));
                        exerciseList.add(new Exercise(R.drawable.air_squat,"Air squat"));
                        exerciseList.add(new Exercise(R.drawable.squat_jump,"Squat jump"));
                        exerciseList.add(new Exercise(R.drawable.plank,"Plank"));
                        break;
                    default:
                        break;
                }
                recyclerView = (RecyclerView)findViewById(R.id.list_ex);
                adapter = new RecycleViewAdapter(exerciseList, getBaseContext());
                layoutManager = new LinearLayoutManager(ActivityHistory.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);

                btnBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                });
            }
        });

    }

    private void viewData() {
        Cursor cursor = workoutDB.getSetDone();

        if(cursor.getCount() == 0)
            Toast.makeText(this, "No history", Toast.LENGTH_SHORT).show();
        else{
            while(cursor.moveToNext())
                listItem.add(cursor.getString(1)); //index 1 is set done
            /*adapter = new ArrayAdapter(this, R.layout.item_layout, listItem);*/
            adapter = new ArrayAdapter<String>(this, R.layout.calendar_item_listview,
                    R.id.textView, listItem);
            historyList.setAdapter(adapter);
        }

    }
}
