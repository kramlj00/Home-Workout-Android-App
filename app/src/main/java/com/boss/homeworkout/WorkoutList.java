package com.boss.homeworkout;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.boss.homeworkout.Adapter.ListViewAdapter;
import com.boss.homeworkout.Adapter.RecycleViewAdapter;
import com.boss.homeworkout.Model.Exercise;

import java.util.ArrayList;
import java.util.List;

public class WorkoutList extends AppCompatActivity {

  Toolbar mToolbar;
  ListView mListView;

  String[] workoutType = {"STRETCHING", "ABS WORKOUT", "ARMS WORKOUT", "LEGS WORKOUT", "BACK WORKOUT", "FULL BODY WORKOUT"};
  int[] workoutImage = {R.drawable.stretching,
          R.drawable.abs_workout,
          R.drawable.arm_workout,
          R.drawable.legs_workout,
          R.drawable.back_workout,
          R.drawable.full_body_workout,
  };

  @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_workout_list);

    mToolbar = (Toolbar) findViewById(R.id.toolbar);
    mToolbar.setTitle("All Exercises");
    mListView = (ListView) findViewById(R.id.listView);
    ListViewAdapter myAdapter = new ListViewAdapter(WorkoutList.this, workoutType, workoutImage);
    mListView.setAdapter(myAdapter);

    mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        setContentView(R.layout.activity_list_exercises);
        Button btnBack = (Button)findViewById(R.id.btnBack);
        List<Exercise> exerciseList = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager;
        RecyclerView recyclerView;
        RecycleViewAdapter adapter;

        switch(position){
          case 0:
            exerciseList.add(new Exercise(R.drawable.neck_release,"Neck release"));
            exerciseList.add(new Exercise(R.drawable.shoulder_stretch,"Shoulder stretch"));
            exerciseList.add(new Exercise(R.drawable.triceps_stretch,"Overhead triceps stretch"));
            exerciseList.add(new Exercise(R.drawable.side_bend_stretch,"Side bend stretch"));
            exerciseList.add(new Exercise(R.drawable.knee_hold,"Knee hold"));
            exerciseList.add(new Exercise(R.drawable.standing_quad_stretch,"Standing quadriceps stretch"));
            exerciseList.add(new Exercise(R.drawable.cross_body_touch_toe,"Cross body touch toe"));
            exerciseList.add(new Exercise(R.drawable.standing_touch_toe,"Standing touch toe"));
            exerciseList.add(new Exercise(R.drawable.standing_hamstring_stretch,"Standing hamstring stretch"));
            exerciseList.add(new Exercise(R.drawable.kneeling_lunge_left,"Kneeling lunge left"));
            exerciseList.add(new Exercise(R.drawable.kneeling_lunge_right,"Kneeling lunge right"));
            exerciseList.add(new Exercise(R.drawable.kneeling_lunge_left_with_toe_touch,"Kneeling lunge left with toe touch"));
            exerciseList.add(new Exercise(R.drawable.kneeling_lunge_right_with_toe_touch,"Kneeling lunge right with toe touch"));
            exerciseList.add(new Exercise(R.drawable.cat_cow,"Cat-cow"));
            exerciseList.add(new Exercise(R.drawable.fire_hydrant,"Fire hydrant"));
            exerciseList.add(new Exercise(R.drawable.left_elbow_up,"Left elbow up"));
            exerciseList.add(new Exercise(R.drawable.right_elbow_up,"Right elbow up"));
            exerciseList.add(new Exercise(R.drawable.left_leg_stretch,"Left leg stretch"));
            exerciseList.add(new Exercise(R.drawable.right_leg_stretch,"Right leg stretch"));
            exerciseList.add(new Exercise(R.drawable.toe_touch_stretch,"Toe touch stretch"));
            exerciseList.add(new Exercise(R.drawable.left_quad_stretch,"Left quad stretch"));
            exerciseList.add(new Exercise(R.drawable.right_quad_stretch,"Right quad stretch"));
            exerciseList.add(new Exercise(R.drawable.hamstring_stretch,"Hamstring stretch"));
            break;
          case 1: //abs
            exerciseList.add(new Exercise(R.drawable.heel_touching,"Heel touching"));
            exerciseList.add(new Exercise(R.drawable.high_crunches,"High crunches"));
            exerciseList.add(new Exercise(R.drawable.high_crunches_with_legs_up,"High crunches with legs up"));
            exerciseList.add(new Exercise(R.drawable.knee_sliding,"Knee sliding"));
            exerciseList.add(new Exercise(R.drawable.low_crunches,"Low crunches"));
            exerciseList.add(new Exercise(R.drawable.crunch_clap, "Crunch clap"));
            exerciseList.add(new Exercise(R.drawable.dead_bug1,"Dead bug 1"));
            exerciseList.add(new Exercise(R.drawable.dead_bug2,"Dead bug 2"));
            exerciseList.add(new Exercise(R.drawable.double_crunches,"Double crunches"));
            exerciseList.add(new Exercise(R.drawable.swing_up,"Swing up"));
            exerciseList.add(new Exercise(R.drawable.bicycle_crunch,"Bicycle crunch"));
            exerciseList.add(new Exercise(R.drawable.flutter_kicks,"Flutter kicks"));
            exerciseList.add(new Exercise(R.drawable.leg_swing,"Leg swing up"));
            exerciseList.add(new Exercise(R.drawable.reverse_crunch,"Reverse crunch"));
            exerciseList.add(new Exercise(R.drawable.russian_twist,"Russian twist"));
            exerciseList.add(new Exercise(R.drawable.sit_up,"Sit ups"));
            exerciseList.add(new Exercise(R.drawable.twist_body,"Twist body"));
            exerciseList.add(new Exercise(R.drawable.leg_lowering,"Leg lowering"));
            break;
          case 2: //arms
            exerciseList.add(new Exercise(R.drawable.plank_progression,"Plank progression"));
            exerciseList.add(new Exercise(R.drawable.shoulder_touching,"Shoulder touching"));
            exerciseList.add(new Exercise(R.drawable.reverse_plank_bridge,"Reverse plank bridge"));
            exerciseList.add(new Exercise(R.drawable.reverse_plank_pose,"Reverse plank pose"));
            exerciseList.add(new Exercise(R.drawable.chair_plank2,"Chair plank"));
            exerciseList.add(new Exercise(R.drawable.right_side_bridge,"Right side bridge"));
            exerciseList.add(new Exercise(R.drawable.left_side_bridge,"Left side bridge"));
            exerciseList.add(new Exercise(R.drawable.outstreched_arm_plank,"Outstretched arm plank"));
            exerciseList.add(new Exercise(R.drawable.chair_push,"Chair push"));
            exerciseList.add(new Exercise(R.drawable.left_side_plank,"Left side plank"));
            exerciseList.add(new Exercise(R.drawable.right_side_plank,"Right side plank"));
            exerciseList.add(new Exercise(R.drawable.tricep_dips,"Triceps dips"));
            exerciseList.add(new Exercise(R.drawable.butt_up,"Butt up"));
            exerciseList.add(new Exercise(R.drawable.jab_squats,"Jab squats"));
            exerciseList.add(new Exercise(R.drawable.left_t_stabilisation,"Left t stabilisation"));
            exerciseList.add(new Exercise(R.drawable.right_t_stabilisation,"Right t stabilisation"));
            break;
          case 3: //legs
            exerciseList.add(new Exercise(R.drawable.groiners_left,"Groiners left"));
            exerciseList.add(new Exercise(R.drawable.groiners_right,"Groiners right"));
            exerciseList.add(new Exercise(R.drawable.sprints,"Sprints"));
            exerciseList.add(new Exercise(R.drawable.squat_plus_left_leg_side_kick,"Squat + left leg side kick"));
            exerciseList.add(new Exercise(R.drawable.squat_plus_right_leg_side_kick,"Squat + right leg side kick"));
            exerciseList.add(new Exercise(R.drawable.tuck_jump,"Tuck jump"));
            exerciseList.add(new Exercise(R.drawable.bottoms_up_with_left_leg_raise,"Bottoms up with left leg raise"));
            exerciseList.add(new Exercise(R.drawable.bottoms_up_with_right_leg_raise,"Bottoms up with right leg raise"));
            exerciseList.add(new Exercise(R.drawable.chair_hip_raise,"Chair hip raise"));
            exerciseList.add(new Exercise(R.drawable.butt_kicks,"Butt kicks"));
            exerciseList.add(new Exercise(R.drawable.left_knee_kick,"Left knee kick"));
            exerciseList.add(new Exercise(R.drawable.right_knee_kick,"Right knee kick"));
            exerciseList.add(new Exercise(R.drawable.high_knees,"High knees"));
            exerciseList.add(new Exercise(R.drawable.left_leg_raise,"Left leg raise"));
            exerciseList.add(new Exercise(R.drawable.right_leg_raise,"Right leg raise"));
            exerciseList.add(new Exercise(R.drawable.left_leg_reach,"Left leg reach"));
            exerciseList.add(new Exercise(R.drawable.side_lunges,"Side lunges"));
            exerciseList.add(new Exercise(R.drawable.squat_posture,"Squat posture"));
            exerciseList.add(new Exercise(R.drawable.air_squat,"Air squat"));
            exerciseList.add(new Exercise(R.drawable.bear_crawl,"Bear crawl"));
            exerciseList.add(new Exercise(R.drawable.left_lunge,"Left Lunges"));
            exerciseList.add(new Exercise(R.drawable.right_lunge,"Right Lunges"));
            exerciseList.add(new Exercise(R.drawable.reverse_left_leg_lift,"Reverse left leg lift"));
            exerciseList.add(new Exercise(R.drawable.reverse_right_leg_lift,"Reverse right leg lift"));
            exerciseList.add(new Exercise(R.drawable.squat_jump,"Squat jump"));
            exerciseList.add(new Exercise(R.drawable.thrust_burpee,"Thrust burpee"));
            break;
          case 4: //back
            exerciseList.add(new Exercise(R.drawable.forward_bend,"Forward bend"));
            exerciseList.add(new Exercise(R.drawable.pulse_up,"Pulse up"));
            exerciseList.add(new Exercise(R.drawable.wipers,"Wipers"));
            exerciseList.add(new Exercise(R.drawable.roll_over,"Roll over"));
            exerciseList.add(new Exercise(R.drawable.bird_dog,"Bird-dog"));
            exerciseList.add(new Exercise(R.drawable.back_extension,"Back extension"));
            exerciseList.add(new Exercise(R.drawable.bridge,"Bridge"));
            exerciseList.add(new Exercise(R.drawable.left_leg_hip_bridge,"Left leg hip bridge"));
            exerciseList.add(new Exercise(R.drawable.right_leg_hip_bridge,"Right leg hip bridge"));
            exerciseList.add(new Exercise(R.drawable.superman,"Superman"));
            exerciseList.add(new Exercise(R.drawable.cat_cow,"Cat cow"));
            exerciseList.add(new Exercise(R.drawable.cobra_stretch,"Cobra stretch"));
            exerciseList.add(new Exercise(R.drawable.chair_reverse_plank,"Chair reverse plank"));
            break;
          case 5: //all body
            exerciseList.add(new Exercise(R.drawable.jumping_jacks,"Jumping jacks"));
            exerciseList.add(new Exercise(R.drawable.opposite_arm_opposite_leg,"Opposite arm opposite leg"));
            exerciseList.add(new Exercise(R.drawable.boat_pose,"Boat pose"));
            exerciseList.add(new Exercise(R.drawable.chair_mountain_climber,"Chair mountain climber"));
            exerciseList.add(new Exercise(R.drawable.mountain_climbers,"Mountain climber"));
            exerciseList.add(new Exercise(R.drawable.chair_plank,"Chair mountain climber"));
            exerciseList.add(new Exercise(R.drawable.forearm_plank_with_knee_drop,"Forearm plank with knee drop"));
            exerciseList.add(new Exercise(R.drawable.hollow_body_hold,"Hollow body hold"));
            exerciseList.add(new Exercise(R.drawable.plank_knee_to_elbow,"Plank knee to elbow"));
            exerciseList.add(new Exercise(R.drawable.plank_passe_twist,"Plank passe twist"));
            exerciseList.add(new Exercise(R.drawable.left_side_plank_crunch,"Left side plank crunch"));            exerciseList.add(new Exercise(R.drawable.left_side_plank_crunch,"Left side plank crunch"));
            exerciseList.add(new Exercise(R.drawable.right_side_plank_crunch,"Right side plank crunch"));
            exerciseList.add(new Exercise(R.drawable.left_side_plank_hip_abduction,"Left side plank hip abduction"));
            exerciseList.add(new Exercise(R.drawable.right_side_plank_hip_abduction,"Right side plank hip abduction"));
            exerciseList.add(new Exercise(R.drawable.crunch_with_leg_reach,"Crunch with leg reach"));
            exerciseList.add(new Exercise(R.drawable.plank_jack,"Plank jack"));
            exerciseList.add(new Exercise(R.drawable.reverse_left_leg_raise_plank,"Reverse left leg raise plank"));
            exerciseList.add(new Exercise(R.drawable.reverse_right_leg_raise_plank,"Reverse right leg raise plank"));
            exerciseList.add(new Exercise(R.drawable.dolphin_plank,"Dolphin plank"));
            exerciseList.add(new Exercise(R.drawable.knee_plank,"Knee plank"));
            exerciseList.add(new Exercise(R.drawable.plank,"Plank"));
            exerciseList.add(new Exercise(R.drawable.push_up,"Push ups"));
            exerciseList.add(new Exercise(R.drawable.reverse_plank,"Reverse plank"));
            exerciseList.add(new Exercise(R.drawable.cross_chops,"Cross chops"));
            exerciseList.add(new Exercise(R.drawable.v_up,"V-up"));
            break;
          default:
            break;
        }
        recyclerView = (RecyclerView)findViewById(R.id.list_ex);
        adapter = new RecycleViewAdapter(exerciseList, getBaseContext());
        layoutManager = new LinearLayoutManager(WorkoutList.this);
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

}
