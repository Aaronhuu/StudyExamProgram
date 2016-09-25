package com.example.aaronhu.fypj;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * Created by aaronhu on 9/24/16.
 */
public class EntranceActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void studyClick(View view){
        Intent i = new Intent(EntranceActivity.this,StudyActivity.class);
        startActivity(i);

    }

    public void examClick(View view){
        Intent i = new Intent(EntranceActivity.this,ExamActivity.class);
        startActivity(i);
    }

    public void analysisClick(View view){
        Toast ts = Toast.makeText(EntranceActivity.this,
                "You can't view result before finishing a task!", Toast.LENGTH_LONG);
        ts.show();
    }








}
