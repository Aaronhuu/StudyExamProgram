package com.example.aaronhu.fypj;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

/**
 * Created by aaronhu on 9/24/16.
 */
public class MultiChoiceActivity extends Activity {
    boolean prev = false, next = false;
    int questionIdx = 1, finishIndex = 6;
    TextView questionIndex,questionType,questionContent,
            choiceA,choiceB,choiceC,choiceD,questionNum;
    RadioButton answerA,answerB,answerC,answerD;
    Button Prev,Next;
    ArrayList<String> answerSheet= new ArrayList<String>();
    static Bundle s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mulitichoice);
        s = savedInstanceState;

        final Intent intent = getIntent();
        String type = intent.getStringExtra("title");
        initialize();

        questionType.setText(type);
        questionIndex.setText(questionIdx + "/" + finishIndex);
        questionNum.setText("Question: "+questionIdx);
        HandleNextAndPrev();
        HandleCurrent();

        if(questionIdx == finishIndex){
            Next.setText("Submit");
            Next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Boolean allow = false;
                    if(answerA.isChecked()){
                        answerSheet.add("A");
                        allow = true;
                    }else if(answerB.isChecked()){
                        answerSheet.add("B");
                        allow = true;
                    }else if(answerC.isChecked()){
                        answerSheet.add("C");
                        allow = true;
                    }else if(answerD.isChecked()){
                        answerSheet.add("D");
                        allow = true;
                    }else{
                        Toast ts = Toast.makeText(MultiChoiceActivity.this,
                                "Choose a answer before submit", Toast.LENGTH_LONG);
                        ts.show();
                    }
                    if(allow){
                        Intent intent1 = new Intent(MultiChoiceActivity.this,ResultActivity.class);
                        intent1.putExtra("answersheet",answerSheet);
                        startActivity(intent1);
                        finish();
                    }

                }
            });

        }else{
            Next.setText("Next");
        }


        //question content should be in the database
        //questionContent.setText("Whoa r u?");


    }

    private void HandleCurrent(){

        if(prev){
            String answer = answerSheet.get(questionIdx-1);
            if(answer.equals("A")){
                answerA.setChecked(true);
            }else if(answer.equals("B")){
                answerB.setChecked(true);
            }else if(answer.equals("C")){
                answerC.setChecked(true);
            }else if(answer.equals("D")){
                answerD.setChecked(true);
            }
        }else if(next){

            if(answerSheet.size()>questionIdx-1){
                String answer = answerSheet.get(questionIdx-1);
                if(answer.equals("A")){
                    answerA.setChecked(true);
                }else if(answer.equals("B")){
                    answerB.setChecked(true);
                }else if(answer.equals("C")){
                    answerC.setChecked(true);
                }else if(answer.equals("D")){
                    answerD.setChecked(true);
                }
            }

        }


    }

    private void HandleNextAndPrev(){
        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean sizeFit = false;
                if(answerSheet.size()>questionIdx-1){
                    sizeFit = true;
                }

                if(answerA.isChecked()){
                    //save result A to database here;

                    if(!sizeFit){
                        answerSheet.add("A");
                    }else{
                        answerSheet.set(questionIdx - 1, "A");
                    }
                    questionIdx++;
                    if(questionIdx<=finishIndex){
                        prev = false;
                        next = true;
                        onCreate(s);
                    }else{
                        questionIdx = finishIndex;
                    }

                }else if(answerB.isChecked()){
                    //save result B to database here;
                    if(!sizeFit){
                        answerSheet.add("B");
                    }else{
                        answerSheet.set(questionIdx - 1, "B");
                    }
                    questionIdx++;
                    if(questionIdx<=finishIndex){
                        prev = false;
                        next = true;
                        onCreate(s);
                    }else{
                        questionIdx = finishIndex;
                    }

                }else if(answerC.isChecked()){
                    //save result C to database here;
                    if(!sizeFit){
                        answerSheet.add("C");
                    }else{
                        answerSheet.set(questionIdx - 1, "C");
                    }
                    questionIdx++;
                    if(questionIdx<=finishIndex){
                        prev = false;
                        next = true;
                        onCreate(s);
                    }else{
                        questionIdx = finishIndex;
                    }

                }else if(answerD.isChecked()){
                    //save result D to database here;
                    if(!sizeFit){
                        answerSheet.add("D");
                    }else{
                        answerSheet.set(questionIdx - 1, "D");
                    }
                    questionIdx++;
                    if(questionIdx<=finishIndex){
                        prev = false;
                        next = true;
                        onCreate(s);
                    }else{
                        questionIdx = finishIndex;
                    }

                }else{
                    Toast ts = Toast.makeText(MultiChoiceActivity.this,
                            "Choose a answer before you see next question", Toast.LENGTH_LONG);
                    ts.show();
                }

            }
        });

        Prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    questionIdx--;
                    if(questionIdx>=1){
                        prev = true;
                        next = false;
                        onCreate(s);
                    }else{
                        questionIdx = 1;
                    }

            }
        });
    }

    private void initialize(){
        questionType = (TextView)findViewById(R.id.questionType);
        questionIndex = (TextView)findViewById(R.id.questionIndex);
        questionContent = (TextView)findViewById(R.id.questionContent);
        questionNum = (TextView)findViewById(R.id.questionNum);
        choiceA = (TextView)findViewById(R.id.choiceA);
        choiceB = (TextView)findViewById(R.id.choiceB);
        choiceC = (TextView)findViewById(R.id.choiceC);
        choiceD = (TextView)findViewById(R.id.choiceD);
        answerA  = (RadioButton)findViewById(R.id.answerA);
        answerB = (RadioButton)findViewById(R.id.answerB);
        answerC = (RadioButton)findViewById(R.id.answerC);
        answerD = (RadioButton)findViewById(R.id.answerD);
        Prev = (Button)findViewById(R.id.prev);
        Next = (Button)findViewById(R.id.next);
    }

    public void HandleSelectA(View view){
        answerB.setChecked(false);
        answerC.setChecked(false);
        answerD.setChecked(false);
    }

    public void HandleSelectB(View view){
        answerA.setChecked(false);
        answerC.setChecked(false);
        answerD.setChecked(false);
    }

    public void HandleSelectC(View view){
        answerB.setChecked(false);
        answerA.setChecked(false);
        answerD.setChecked(false);
    }

    public void HandleSelectD(View view){
        answerB.setChecked(false);
        answerC.setChecked(false);
        answerA.setChecked(false);
    }


}
