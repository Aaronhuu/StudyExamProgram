package com.example.aaronhu.fypj;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by aaronhu on 9/24/16.
 */
public class ResultActivity extends Activity {
        @Override
        protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_result);

            Intent intent = getIntent();
            ArrayList<String>answersheet = intent.getStringArrayListExtra("answersheet");
            int Score  = 0;


            ListView lv = (ListView) findViewById(R.id.courseList3);
            TextView tx = (TextView) findViewById(R.id.score);
            Button finish = (Button)findViewById(R.id.reviewFinish);
            Button redo = (Button)findViewById(R.id.redo);

            finish.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

            redo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(ResultActivity.this,ExamActivity.class);
                    startActivity(i);
                    finish();
                }
            });

            final ArrayList<String> data = new ArrayList<String>();

            //we should know what the right answers are.
            for(int i=1;i<=answersheet.size();i++){
                data.add(i + ".       " + answersheet.get(i - 1) + "        B            " + answersheet.get(i - 1).equals("B"));
                if(answersheet.get(i-1).equals("B")){
                    Score+=100/answersheet.size();
                }
            }

           tx.setText("Your Score: "+Score);


            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.my_listitem, data);
            lv.setAdapter(adapter);

            int height = 0;
            for (int ioo = 0; ioo < data.size(); ioo++) {
                View item = adapter.getView(ioo, null, lv);
                item.measure(0, 0);
                height += item.getMeasuredHeight();
            }
            ViewGroup.LayoutParams params = lv.getLayoutParams();
            params.height = height + (lv.getDividerHeight() * (adapter.getCount() - 1));
            lv.setLayoutParams(params);


    }
}
