package com.example.aaronhu.fypj;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;


/**
 * Created by aaronhu on 9/23/16.
 */
public class BookActivity extends Activity {
    private Firebase content1,content2,content3,content4,content5,content6,content7,content8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        Firebase.setAndroidContext(this);
        TextView title = (TextView)findViewById(R.id.courseTitle);
        Intent i = getIntent();
        String courseTitle = i.getStringExtra("title");
        int id = i.getIntExtra("id",1)+1;
        title.setText(courseTitle);
        initializeDatabase();
        final TextView courseContent = (TextView)findViewById(R.id.courseContent);

        if(id==1){
            content1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String data = dataSnapshot.getValue(String.class);
                    String[] paras = data.split("/");
                    String content = "";
                    for(String s: paras){
                        content+=s+"\n";
                    }
                    courseContent.setText(content);

                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
        }else if(id==2){
            content2.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String data = dataSnapshot.getValue(String.class);
                    String[] paras = data.split("/");
                    String content = "";
                    for(String s: paras){
                        content+=s+"\n";
                    }
                    courseContent.setText(content);

                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
        }else if(id==3){
            content3.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String data = dataSnapshot.getValue(String.class);
                    String[] paras = data.split("/");
                    String content = "";
                    for(String s: paras){
                        content+=s+"\n";
                    }
                    courseContent.setText(content);

                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
        }else if(id==4){
            content4.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String data = dataSnapshot.getValue(String.class);
                    String[] paras = data.split("/");
                    String content = "";
                    for(String s: paras){
                        content+=s+"\n";
                    }
                    courseContent.setText(content);

                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
        }else if(id==5){
            content5.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String data = dataSnapshot.getValue(String.class);
                    String[] paras = data.split("/");
                    String content = "";
                    for(String s: paras){
                        content+=s+"\n";
                    }
                    courseContent.setText(content);

                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
        }else if(id==6){
            content6.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String data = dataSnapshot.getValue(String.class);
                    String[] paras = data.split("/");
                    String content = "";
                    for(String s: paras){
                        content+=s+"\n";
                    }
                    courseContent.setText(content);

                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
        }else if(id==7){
            content7.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String data = dataSnapshot.getValue(String.class);
                    String[] paras = data.split("/");
                    String content = "";
                    for(String s: paras){
                        content+=s+"\n";
                    }
                    courseContent.setText(content);

                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
        }else if(id==8){
            content8.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String data = dataSnapshot.getValue(String.class);
                    String[] paras = data.split("/");
                    String content = "";
                    for(String s: paras){
                        content+=s+"\n";
                    }
                    courseContent.setText(content);

                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
        }





    }

    private void initializeDatabase(){
        content1 = new Firebase("https://fypj-6889d.firebaseio.com/Transaction analysis");
        content2 = new Firebase("https://fypj-6889d.firebaseio.com/Journal Entries");
        content3 = new Firebase("https://fypj-6889d.firebaseio.com/Trail balance");
        content4 = new Firebase("https://fypj-6889d.firebaseio.com/Posting");
        content5 = new Firebase("https://fypj-6889d.firebaseio.com/Adjusting");
        content6 = new Firebase("https://fypj-6889d.firebaseio.com/Worksheet");
        content7 = new Firebase("https://fypj-6889d.firebaseio.com/Financial statement");
        content8 = new Firebase("https://fypj-6889d.firebaseio.com/Closing entries");

    }

    public void FinishClick(View view){
        finish();
    }

}
