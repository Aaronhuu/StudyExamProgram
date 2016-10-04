package com.example.aaronhu.fypj;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
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
public class MultiChoiceActivity extends Activity {
    boolean prev = false, next = false;
    int questionIdx = 1, finishIndex = 5,multichoiceIndex=5,truefalseIndex=5,ansIndex=1;
    TextView questionIndex,questionType,questionContent,
            choiceA,choiceB,choiceC,choiceD,questionNum;
    RadioButton answerA,answerB,answerC,answerD;
    Button Prev,Next;
    Firebase AllQuestions;
    String queContent = "";
    EditText blank;
    ArrayList<String> answerSheet= new ArrayList<String>();
    static Bundle s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        s = savedInstanceState;
        final Intent intent = getIntent();
        String type = intent.getStringExtra("title");

        if(questionIdx<=multichoiceIndex){
            setContentView(R.layout.activity_mulitichoice);

        }else if(questionIdx<=truefalseIndex){
            setContentView(R.layout.activity_true_or_false);
        }else{
            setContentView(R.layout.activity_fillblank);
        }
        initialize();
        initialQuestions(type);
        SetAllLength();
        //setMultipleQuestion();
        HandleQuestionContent();

        questionType.setText(type);
        questionIndex.setText(questionIdx + "/" + finishIndex);
        questionNum.setText("Question: "+questionIdx);
        HandleNextAndPrev();
        HandleCurrent();
        HandleSubmit();



        //question content should be in the database
        //questionContent.setText("Whoa r u?");


    }

    private void HandleQuestionContent(){

        AllQuestions.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                queContent = dataSnapshot.getValue(String.class);
                String[] level1 = queContent.split("Question_list");
                String[] level2 = level1[1].split("Multichoice_question");
                String[] level4 = level2[1].split("True_or_False");
                String[] level5 = level4[1].split("Fill in blank");
                if(questionIdx<=multichoiceIndex){
                    String[] level3 = level2[0].split("/");
                    String[] QuestionList = level3[questionIdx-1].split("answer: ");
                    questionContent.setText(QuestionList[0]);
                    choiceA.setText(QuestionList[1]);
                    choiceB.setText(QuestionList[2]);
                    choiceC.setText(QuestionList[3]);
                    choiceD.setText(QuestionList[4]);
                }else if(questionIdx<=truefalseIndex){
                    //true or false question
                    String[] middle = level4[0].split("/");
                    String[] QuestionList = middle[questionIdx-1-multichoiceIndex].split("answer: ");
                    questionContent.setText(QuestionList[0]);
                    choiceA.setText(QuestionList[1]);
                    choiceB.setText(QuestionList[2]);
                }else{
                    //fill blank question
                    String[] middle = level5[0].split("/");
                    questionContent.setText(middle[questionIdx-1-truefalseIndex]);
                }


            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }

    private void initialQuestions(String list){

        if(list.equals("1. Transaction analysis")){
            AllQuestions = new Firebase("https://fypj-6889d.firebaseio.com/Questoion/Transaction");
            ansIndex =1;
        }else if(list.equals("2. Journal entries")){
            AllQuestions = new Firebase("https://fypj-6889d.firebaseio.com/Questoion/Journal entries");
            ansIndex =2;
        }else if(list.equals("3. Trail balance")){
            AllQuestions = new Firebase("https://fypj-6889d.firebaseio.com/Questoion/Trail balance");
            ansIndex =3;
        }else if(list.equals("4. Posting")){
            AllQuestions = new Firebase("https://fypj-6889d.firebaseio.com/Questoion/Posting");
            ansIndex =4;
        }else if(list.equals("5. Adjusting")){
            AllQuestions = new Firebase("https://fypj-6889d.firebaseio.com/Questoion/Adjusting");
            ansIndex =5;
        }else if(list.equals("6. Worksheet")){
            AllQuestions = new Firebase("https://fypj-6889d.firebaseio.com/Questoion/Worksheet");
            ansIndex =6;
        }else if(list.equals("7. Financial statement")){
            AllQuestions = new Firebase("https://fypj-6889d.firebaseio.com/Questoion/Financial statement");
            ansIndex =7;
        }else if(list.equals("8. Closing entries")){
            AllQuestions = new Firebase("https://fypj-6889d.firebaseio.com/Questoion/Closing entries");
            ansIndex =8;
        }else if(list.equals("9. Test All")){
            AllQuestions = new Firebase("https://fypj-6889d.firebaseio.com/Questoion/TestALL");
            ansIndex =9;
        }

    }

    private void HandleSubmit(){
        if(questionIdx == finishIndex){
            Next.setText("Submit");
            Next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Boolean allow = false;
                    if(!blank.getText().toString().equals("")){
                        answerSheet.add(blank.getText().toString());
                        allow = true;

                    }else{
                        Toast ts = Toast.makeText(MultiChoiceActivity.this,
                                "Choose a answer before submit", Toast.LENGTH_LONG);
                        ts.show();
                    }

                    if(allow){
                        Intent intent1 = new Intent(MultiChoiceActivity.this,ResultActivity.class);
                        intent1.putExtra("answersheet",answerSheet);
                        intent1.putExtra("ansIndex",ansIndex);
                        startActivity(intent1);
                        finish();
                    }

                }
            });

        }else{
            Next.setText("Next");
        }
    }

    private void HandleCurrent(){

        if(prev){
            String answer = answerSheet.get(questionIdx-1);
            if(questionIdx<=truefalseIndex){

                if(answer.equals("A")){
                    answerA.setChecked(true);
                }else if(answer.equals("B")){
                    answerB.setChecked(true);
                }else if(answer.equals("C")){
                    answerC.setChecked(true);
                }else if(answer.equals("D")){
                    answerD.setChecked(true);
                }
            }else{
                blank.setText(answer);
            }

        }else if(next){
                if(answerSheet.size()>questionIdx-1){
                    String answer = answerSheet.get(questionIdx-1);
                    if(questionIdx<=truefalseIndex){
                        if(answer.equals("A")){
                            answerA.setChecked(true);
                        }else if(answer.equals("B")){
                            answerB.setChecked(true);
                        }else if(answer.equals("C")){
                            answerC.setChecked(true);
                        }else if(answer.equals("D")){
                            answerD.setChecked(true);
                        }
                    }else{
                            blank.setText(answer);
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
                if(questionIdx<=multichoiceIndex){
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
                }else if(questionIdx<=truefalseIndex){
                    if(answerA.isChecked()){
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
                    }
                }else{
                    //fill blank
                    if(!sizeFit){
                        if(!blank.getText().toString().equals("")){
                            answerSheet.add(blank.getText().toString());
                            questionIdx++;
                            prev = false;
                            next = true;
                            onCreate(s);
                        }else{
                            Toast.makeText(MultiChoiceActivity.this,"Cannot be empty!",Toast.LENGTH_LONG);
                        }
                    }else{
                        if(!blank.getText().toString().equals("")){
                            answerSheet.set(questionIdx - 1, blank.getText().toString());
                            questionIdx++;
                            prev = false;
                            next = true;
                            onCreate(s);
                        }else{
                            Toast.makeText(MultiChoiceActivity.this, "Cannot be empty!", Toast.LENGTH_LONG);
                        }
                    }
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
        blank = (EditText)findViewById(R.id.blank);
    }

    public void HandleSelectA(View view){
        if(questionIdx<=multichoiceIndex){
            answerB.setChecked(false);
            answerC.setChecked(false);
            answerD.setChecked(false);
        }else{
            answerB.setChecked(false);
        }


    }

    public void HandleSelectB(View view){
        if(questionIdx<=multichoiceIndex){
            answerA.setChecked(false);
            answerC.setChecked(false);
            answerD.setChecked(false);
        }else {
            answerA.setChecked(false);
        }

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

    public void SetAllLength(){
        AllQuestions.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                queContent = dataSnapshot.getValue(String.class);
                String[] level1 = queContent.split("Question_list");
                String[] level2 = level1[0].split("Number ");
                finishIndex = Integer.parseInt(level2[0]);
                String[] level3 = level2[1].split("Multichoice_number ");
                multichoiceIndex = Integer.parseInt(level3[0]);
                String[] level4 = level3[1].split("True_False ");
                truefalseIndex = Integer.parseInt(level4[0]);
                questionIndex.setText(questionIdx + "/" + finishIndex);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

}
