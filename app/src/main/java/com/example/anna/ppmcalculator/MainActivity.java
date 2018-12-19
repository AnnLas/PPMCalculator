package com.example.anna.ppmcalculator;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePickerFragment.CalendarListener {
    private TextView resultsTextView;
    private TextView ageTextView;
    private EditText weightText;
    private EditText heightText;
    private Spinner genderSpinner;
    private Spinner calculationWaySpinner;
    private Button calculateButton;
    private int age;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewsInit();


    }


    private void viewsInit() {
        resultsTextView = findViewById(R.id.results_text);
        weightText = findViewById(R.id.weight_edit_txt);
        heightText = findViewById(R.id.height_edit_txt);
        genderSpinner = findViewById(R.id.gender_spinner);
        calculationWaySpinner = findViewById(R.id.calculation_way_spinner);
        calculateButton = findViewById(R.id.button_calculate);
        ageTextView = findViewById(R.id.years_textv);

    }


    public void calculate(View view) {
        PPM ppm = new PPM();
        String calculationWay = (String) calculationWaySpinner.getSelectedItem();
        try {

            double height = Double.valueOf(heightText.getText().toString());
            double weight = Double.valueOf(weightText.getText().toString());
            String gender = genderSpinner.getSelectedItem().toString();
            double result = 0;
            if (calculationWay.equals("Benedict-Harris")) {
                result = ppm.pMMBenedictHarrisWay(gender, weight, height, getAge());
            } else {
                result = ppm.pMMMiffilinWay(gender, weight, height, getAge());
            }
            resultsTextView.setText("Result: " + result);


        } catch (NumberFormatException ex) {
            Toast.makeText(MainActivity.this, R.string.providing_data_failure, Toast.LENGTH_LONG).show();

        }
    }

    public void choose_birth_date(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "date picker");
    }


    @Override
    public void setDateOfBirth(int year, int month, int day) {
        setAge(calculateAge(year, month, day));


    }


    private int calculateAge(int year, int month, int day) {
        Calendar birthDate = Calendar.getInstance();
        Calendar currentDate = Calendar.getInstance();
        birthDate.set(year, month, day);
        int age = currentDate.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);

        if (currentDate.get(Calendar.DAY_OF_YEAR) < birthDate.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }

        if (age == 0 && currentDate.get(Calendar.DAY_OF_YEAR) < birthDate.get(Calendar.DAY_OF_YEAR)) {
            age = -1;
        }
        return age;

    }

    public void setAge(int age) {
        if (age >= 0) {
            this.age = age;
            ageTextView.setText(String.valueOf(age));
            calculateButton.setClickable(true);
        } else {
            Toast.makeText(MainActivity.this, R.string.providing_day_of_birth_failure, Toast.LENGTH_LONG).show();
            calculateButton.setClickable(false);
        }
    }

    public int getAge() {
        return age;
    }
}
