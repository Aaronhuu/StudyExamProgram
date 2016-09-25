package com.example.aaronhu.fypj;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class StudyActivity extends AppCompatActivity {
    private static String choice = "";
    private static int choiceId = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);

        ListView lv = (ListView) findViewById(R.id.courseList);
        final TextView tx = (TextView) findViewById(R.id.choice);

        final ArrayList<String> data = new ArrayList<String>();


        data.add("1. Transaction analysis");
        data.add("2. Journal entries");
        data.add("3. Trail balance");
        data.add("4. Posting");
        data.add("5. Adjusting");
        data.add("6. Worksheet");
        data.add("7. Financial statement");
        data.add("8. Closing entries");

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

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                choice = data.get((int)id);
                choiceId = (int)id;
                tx.setText(choice);

            }
        });


    }

    public void studyOnClick(View view){

        Intent intent = new Intent(StudyActivity.this,BookActivity.class);
        intent.putExtra("title",choice);
        intent.putExtra("id",choiceId);
        startActivity(intent);

    }



}
