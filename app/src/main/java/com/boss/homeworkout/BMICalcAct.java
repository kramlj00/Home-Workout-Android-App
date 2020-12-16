package com.boss.homeworkout;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class BMICalcAct extends AppCompatActivity {
    Dialog myDialog;

    private EditText height;
    private EditText weight;
    private TextView result;
    private Button calcBMI;
    private RadioButton rdiMale, rdiFemale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmicalc);

        myDialog = new Dialog(this);

        height = findViewById(R.id.height);
        weight = findViewById(R.id.weight);
        result = findViewById(R.id.result);
        calcBMI = findViewById(R.id.calc);

        rdiMale = findViewById(R.id.rdiMale);
        rdiFemale = findViewById(R.id.rdiFemale);

        calcBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI(v);
            }
        });
    }

    public void calculateBMI(View v) {
        String heightStr = height.getText().toString();
        String weightStr = weight.getText().toString();

        if("0".equals(heightStr) || "0".equals(weightStr))
            Toast.makeText(BMICalcAct.this, "Height or weight cannot be 0!", Toast.LENGTH_SHORT).show();

        else if (heightStr != null && !"".equals(heightStr)
                && weightStr != null  &&  !"".equals(weightStr)) {
            float heightValue = Float.parseFloat(heightStr) / 100;
            float weightValue = Float.parseFloat(weightStr);

            float bmi = weightValue / (heightValue * heightValue);

            displayBMI(bmi, heightValue, weightValue);
        }
        else
            Toast.makeText(BMICalcAct.this, "Wrong input!", Toast.LENGTH_SHORT).show();
    }

    private void displayBMI(float bmi, float heightValue, float weightValue) {
        String bmiLabel = "";
        String label2 = "";
        float idealWeight;

        idealWeight = calculateIdealWeight(heightValue);

        if (Float.compare(bmi, 15f) <= 0) {
            result.setTextColor(Color.GRAY);
            bmiLabel = getString(R.string.very_severely_underweight);
            label2 = "You should gain " + (idealWeight - weightValue) + "kg";
        } else if (Float.compare(bmi, 15f) > 0  &&  Float.compare(bmi, 16f) <= 0) {
            result.setTextColor(Color.LTGRAY);
            bmiLabel = getString(R.string.severely_underweight);
            label2 = "You should gain " + (idealWeight - weightValue) + "kg";
        } else if (Float.compare(bmi, 16f) > 0  &&  Float.compare(bmi, 18.5f) <= 0) {
            result.setTextColor(Color.YELLOW);
            bmiLabel = getString(R.string.underweight);
            label2 = "You should gain " + (idealWeight - weightValue) + "kg";
        } else if (Float.compare(bmi, 18.5f) > 0  &&  Float.compare(bmi, 25f) <= 0) {
            result.setTextColor(Color.GREEN);
            bmiLabel = getString(R.string.normal);
        } else if (Float.compare(bmi, 25f) > 0  &&  Float.compare(bmi, 30f) <= 0) {
            result.setTextColor(Color.MAGENTA);
            bmiLabel = getString(R.string.overweight);
            label2 = "You should lose " + (weightValue - idealWeight) + "kg";
        } else if (Float.compare(bmi, 30f) > 0  &&  Float.compare(bmi, 35f) <= 0) {
            result.setTextColor(Color.MAGENTA);
            bmiLabel = getString(R.string.obese_class_i);
            label2 = "You should lose " + (weightValue - idealWeight) + "kg";
        } else if (Float.compare(bmi, 35f) > 0  &&  Float.compare(bmi, 40f) <= 0) {
            result.setTextColor(Color.RED);
            bmiLabel = getString(R.string.obese_class_ii);
            label2 = "You should lose " + (weightValue - idealWeight) + "kg";
        } else {
            result.setTextColor(Color.RED);
            bmiLabel = getString(R.string.obese_class_iii);
            label2 = "You should lose " + (weightValue - idealWeight) + "kg";
        }

        bmiLabel = bmi + "\n\n" + bmiLabel + "\nIdeal weight is: " + idealWeight + "\n" + label2;

        result.setText(bmiLabel);
    }

    public void ShowPopup(View v) {
        TextView txtclose;
        Button btn_learnMore;

        myDialog.setContentView(R.layout.custompopup);
        txtclose = myDialog.findViewById(R.id.txtclose);
        btn_learnMore = myDialog.findViewById(R.id.btn_learnMore);

        btn_learnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.cdc.gov/healthyweight/assessing/bmi/index.html"));
                startActivity(intent);
            }
        });

        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }


    //idealna težina mjerena prema Broca formuli
    public float calculateIdealWeight(float height) {
        float idealWeight = 0;
        float heightInCm = height * 100;

        if(rdiMale.isChecked())
            idealWeight = (float) ((heightInCm - 100) - ((heightInCm - 100) * 0.1));
        else if (rdiFemale.isChecked())
            idealWeight = (float) ((heightInCm - 100) - ((heightInCm - 100) * 0.15));
        else
            Toast.makeText(BMICalcAct.this, "Wrong input!", Toast.LENGTH_SHORT).show();

        return idealWeight;
    }

}
