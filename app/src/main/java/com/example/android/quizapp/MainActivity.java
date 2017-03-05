package com.example.android.quizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import static android.R.attr.id;
import static android.R.attr.name;
import static android.R.id.message;

public class MainActivity extends AppCompatActivity {

    String ageMessage = "";
    int indexSpinner =-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void SubmitQuiz(View view) {
        EditText nameField = (EditText) findViewById(R.id.editText);
        String name = nameField.getText().toString();
        CheckBox lineFilter = (CheckBox) findViewById(R.id.checkBox);
        boolean useLineFilter = lineFilter.isChecked();
        CheckBox ups = (CheckBox) findViewById(R.id.checkBox2);
        boolean useUPS = ups.isChecked();
        CheckBox none = (CheckBox) findViewById(R.id.checkBox3);
        boolean useNone = none.isChecked();
        //Check gender
        Spinner genderSpinner = (Spinner) findViewById(R.id.spinner);
        String gender = "";
        //gender = genderSpinner.getItemAtPosition(id).toString();
        // Check what radio button was selected
        RadioGroup  group= (RadioGroup) findViewById(R.id.radioGroup);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                View radioButton = radioGroup.findViewById(i);
                indexSpinner = radioGroup.indexOfChild(radioButton);
            }
        });
        //Change age group display depending on which radio button was selected
        if (indexSpinner==-1) {
            Toast.makeText(this, getString(R.string.no_age_selected), Toast.LENGTH_LONG).show();
            return;
        } else if (indexSpinner==0) {
            ageMessage = getString(R.string.less_20);
        } else if (indexSpinner==1) {
            ageMessage = getString(R.string.betw_20_25);
        } else if (indexSpinner==2) {
            ageMessage = getString(R.string.betw_26_35);
        } else if (indexSpinner==3) {
            ageMessage = getString(R.string.betw_36_45);
        } else if (indexSpinner==4) {
            ageMessage = getString(R.string.more_45);
        }
        //Create final message to display
        String finalMessage = getString(R.string.name, name);
        finalMessage += "\n" + getString(R.string.gender_answer) + gender;
        finalMessage += "\n" + getString(R.string.age_group, ageMessage);
        finalMessage += "\n" + getString(R.string.use_line_filter) + useLineFilter;
        finalMessage += "\n" + getString(R.string.use_ups) + useUPS;
        finalMessage += "\n" + getString(R.string.use_none) + useNone;
        //Check index if needed
        //finalMessage += "\n" + "Index " + index;
        Toast.makeText(this, finalMessage, Toast.LENGTH_LONG).show();
    }
}
