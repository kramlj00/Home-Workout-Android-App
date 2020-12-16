package com.boss.homeworkout;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.boss.homeworkout.Database.WorkoutDB;

import java.util.Random;

public class Quotes extends AppCompatActivity {

    ImageView motivationimg;
    WorkoutDB workoutDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_quotes);

        motivationimg = (ImageView) findViewById(R.id.motivationimg);

        int[] images = {R.drawable.q1,R.drawable.q2,R.drawable.q3,R.drawable.q4,R.drawable.q5,R.drawable.q6,R.drawable.q7,R.drawable.q8,R.drawable.q9,
                R.drawable.q10,R.drawable.q11,R.drawable.q12,R.drawable.q13,R.drawable.q14,R.drawable.q15,R.drawable.q16,R.drawable.q17,R.drawable.q18,
                R.drawable.q19,R.drawable.q20,R.drawable.q21,R.drawable.q22,R.drawable.q23,R.drawable.q24,R.drawable.q25,R.drawable.q26,R.drawable.q27,
                R.drawable.q28,R.drawable.q29,R.drawable.q30,R.drawable.q31,R.drawable.q32,R.drawable.q33,R.drawable.q34,R.drawable.q35,R.drawable.q36,
                R.drawable.q37};
        Random rand = new Random();
        motivationimg.setImageResource(images[rand.nextInt(images.length)]);

        /*workoutDB = new WorkoutDB(this);
        Cursor cursor = workoutDB.getRandom();
        Log.d("DEBUG", "Cursor : "+cursor);

        int imageID = cursor.getInt(cursor.getColumnIndex("ID"));
        Log.d("DEBUG", "imageID : "+imageID);

        motivationimg.setImageBitmap(workoutDB.getImage(imageID));
*/
    }
}