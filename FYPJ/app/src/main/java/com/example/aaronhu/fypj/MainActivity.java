package com.example.aaronhu.fypj;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void studyClick(View view){
        Intent i = new Intent(MainActivity.this,StudyActivity.class);
        startActivity(i);

    }

    public void examClick(View view){
        Intent i = new Intent(MainActivity.this,ExamActivity.class);
        startActivity(i);
    }

    public void analysisClick(View view){
        Toast ts = Toast.makeText(MainActivity.this,
                "You can't view result before finishing a task!", Toast.LENGTH_LONG);
        ts.show();
    }








}
