package com.example.aaronhu.fypj;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by aaronhu on 9/24/16.
 */
public class ExamActivity extends Activity {
    private static String choice = "1. Transaction analysis";
    int idd =1;
    boolean allow = false;
    String[] result;
    Firebase passorfail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        Firebase.setAndroidContext(this);
        passorfail = new Firebase("https://fypj-6889d.firebaseio.com/pass");

        ListView lv = (ListView) findViewById(R.id.courseList2);
        final TextView tx = (TextView) findViewById(R.id.choice2);
        final ArrayList<String> data = new ArrayList<String>();

        passorfail.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                tx.setText("Database finish");
                String pass_fail = dataSnapshot.getValue(String.class);
                result = pass_fail.split("/");
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        data.add("1. Transaction analysis");
        data.add("2. Journal entries");
        data.add("3. Trail balance");
        data.add("4. Posting");
        data.add("5. Adjusting");
        data.add("6. Worksheet");
        data.add("7. Financial statement");
        data.add("8. Closing entries");
        //data.add("9. Test All");

        //tx.setText(choice);

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
                idd = (int)id;
                choice = data.get((int)id);

                if(idd>=1){
                    if(result[idd-1].equals("pass")){
                        allow = true;
                        tx.setText("allow");
                    }else{
                        allow = false;
                        tx.setText("Not allow");
                    }
                }else{
                    tx.setText(choice);
                }

            }
        });


    }

    public void studyOnClick(View view){
        if(choice.equals("1. Transaction analysis")||allow){
            Intent intent = new Intent(ExamActivity.this,MultiChoiceActivity.class);
            intent.putExtra("title", choice);
            startActivity(intent);
            finish();
        }else {
            Toast ts = Toast.makeText(ExamActivity.this,
                    "Finish the previous section first!", Toast.LENGTH_LONG);
            ts.show();
        }


    }







}
