package com.example.ratingproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText editRelevans, editPerformance, editPrep, editFeedback, editExamples, editJob;
    TextView result;
    Spinner selectCourse;
    ArrayList<String> editTexts;
    ArrayList<EditText> validateEdits;
    ArrayList<Course> courseArrayList;
    List<String> listCourse, teacherInfo;
    Bundle transferData = new Bundle();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        addItemsOnSpinner();
        addDetailsToCourse();




    }

    public void sendMessage(View view) {
        Double totalScore = 0.0;


        Intent intent = new Intent(this, DisplayResult.class);

        //All input fields added to Array for easy handling
        editTexts = new ArrayList<>(Arrays.asList(editRelevans.getText().toString(),
                                                    editPerformance.getText().toString(),
                                                    editPrep.getText().toString(),
                                                    editFeedback.getText().toString(),
                                                    editExamples.getText().toString(),
                                                    editJob.getText().toString()));
        //Check on button click if input is missing.
        for(int j = 0; j < editTexts.size(); j++){
            if(TextUtils.isEmpty(editTexts.get(j))) {
                Toast.makeText(getApplicationContext(), "Missing Input", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        //Parse all input to double and add to totalscore
        for(int i = 0; i < editTexts.size(); i++) {
            totalScore += Double.parseDouble(editTexts.get(i));
        }
        //Loop through Array containing Course+Email objects to check for object selected.
        for(int i = 0; i < courseArrayList.size(); i++){
            if(courseArrayList.get(i).getCourseName().equalsIgnoreCase(selectCourse.getSelectedItem().toString())){
                //Selected object is passed through to next activity using Parcel
                transferData.putParcelable("DETAILS", courseArrayList.get(i));
            }
        }
        Double avgScore = (double) Math.round((totalScore / editTexts.size()) * 100) / 100;
        transferData.putDouble("AVGSCORE", avgScore);

        //All data is send to next activity using Bundle
        intent.putExtras(transferData);
        startActivity(intent);


    }


    public void addItemsOnSpinner(){
        listCourse = new ArrayList<>();
        listCourse.add("Android App");
        listCourse.add("Android Game");
        listCourse.add("Python C");
        listCourse.add("AI");

        ArrayAdapter<String> dataAdapterCourse = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listCourse);
        dataAdapterCourse.setDropDownViewResource(android.R.layout.simple_spinner_item);
        selectCourse.setAdapter(dataAdapterCourse);



    }
    public void addDetailsToCourse(){

       teacherInfo = new ArrayList<>
               (Arrays.asList(
               "michael.diksi@hotmail.com",
               "michael.diksi@gmail.com",
               "michael.diksi@gmail.dk",
               "michael.diksi@hotmail.dk"));
       //Connect course and email. Add to Array containing object
       courseArrayList = new ArrayList<>();
       for(int z = 0; z < listCourse.size(); z++){
           Course courseDetails = new Course(listCourse.get(z), teacherInfo.get(z));
           courseArrayList.add(courseDetails);
       }


    }

    private void init() {
        validateEdits = new ArrayList<>(Arrays.asList(
            editRelevans = findViewById(R.id.editRelevans),
            editPerformance = findViewById(R.id.editPerformance),
            editPrep = findViewById(R.id.editPrep),
            editFeedback = findViewById(R.id.editFeedback),
            editExamples = findViewById(R.id.editExamples),
            editJob = findViewById(R.id.editJob)
            ));

        validateInput(validateEdits);
        result = findViewById(R.id.course);
        selectCourse = findViewById(R.id.selectCourse);




    }
    private void validateInput(ArrayList validateEdits){
        //Cannot type anything besides numbers from 1-100
        System.out.println();
        for (int i = 0; i < validateEdits.size(); i++){
            EditText validateEditText = (EditText) validateEdits.get(i);
                    validateEditText.setFilters(new InputFilter[]{ new InputFilterMinMax("1", "100")});
        }
    }
}
