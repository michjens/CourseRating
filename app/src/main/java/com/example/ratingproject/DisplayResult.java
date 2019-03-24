package com.example.ratingproject;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



public class DisplayResult extends AppCompatActivity {
    TextView result, course, response;
    Button backButton, mail;
    ImageView gifImageView;
    String responseText, selectedCourse, teacherEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_result);
        init();
        handleData();


     backButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             onBackPressed();
         }
     });
     mail.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             sendEmail();
         }
     });

    }



    public void handleData(){
        Intent intent = getIntent();
        Bundle transferedData = intent.getExtras();
        Double avgScore = transferedData.getDouble("AVGSCORE");
        Course courseDetails = transferedData.getParcelable("DETAILS");
        calcResponse(avgScore);
        selectedCourse = courseDetails.getCourseName();
        teacherEmail = courseDetails.getTeacherEmail();
        result.setText("Average Score: " + avgScore);
        course.setText(courseDetails.getCourseName());





    }

    private void sendEmail(){
        //Opens Email client and auto inputs the correct values
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:" + teacherEmail));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, selectedCourse);
        emailIntent.putExtra(Intent.EXTRA_TEXT, responseText);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send email using..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "No email clients installed.", Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(this, "Mail complete", Toast.LENGTH_SHORT).show();

    }




    private void calcResponse(Double avgScore){
        String grade;
        //Grades the score and adds a reaction gif for fun
        if(avgScore > 90){
            grade = "A";
            gifImageView.setImageResource(R.drawable.agrade);
        }else if(avgScore > 80 && avgScore < 90){
            grade = "B";
            gifImageView.setImageResource(R.drawable.bgrade);
        }else if(avgScore > 70 && avgScore < 80){
            grade = "C";
            gifImageView.setImageResource(R.drawable.cgrade);
        }else if(avgScore > 60 && avgScore < 70){
            grade = "D";
            gifImageView.setImageResource(R.drawable.dgrade);
        }else if(avgScore > 50 && avgScore < 60){
            grade = "E";
            gifImageView.setImageResource(R.drawable.egrade);
        }else{
            grade = "You should consider a new job.";
            gifImageView.setImageResource(R.drawable.fgrade);
        }

        responseText = "Your grade is: " + "\n" + grade;
        response.setText(responseText);



    }
    private void init(){
        result = findViewById(R.id.result);
        course = findViewById(R.id.course);
        response = findViewById(R.id.response);
        backButton = findViewById(R.id.back);
        mail = findViewById(R.id.mail);
        gifImageView = findViewById(R.id.gifImageView);

    }
}
