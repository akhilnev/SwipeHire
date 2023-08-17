
package com.example.user_manager_v1;
import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.user_manager_v1.QuestionAnswer;

import org.json.JSONException;
import org.json.JSONObject;

/**

 This class represents the Java Quiz activity, where the user can take a quiz on Java programming language.
 It implements View.OnClickListener to handle the click events of the answer buttons and the submit button.
 @author - Hrishikesha Kyathsandra
 */
public class JavaQuiz extends AppCompatActivity implements View.OnClickListener{

    TextView totalQuestionsTextView;
    TextView questionTextView;
    Button ansA, ansB, ansC, ansD;
    Button submitBtn;

    int score=0;

    int finalScore = 0;

    String candidateName = "";

    int totalQuestion = QuestionAnswer.question.length;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";

    String nameOnly = "";

    /**
     * Called when the activity is starting. It initializes the UI components, gets the candidate name from SharedPreferences,
     * and loads the first question.
     * @param savedInstanceState a Bundle object containing the activity's previously saved state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_quiz);

        totalQuestionsTextView = findViewById(R.id.total_question);
        questionTextView = findViewById(R.id.question);
        ansA = findViewById(R.id.ans_A);
        ansB = findViewById(R.id.ans_B);
        ansC = findViewById(R.id.ans_C);
        ansD = findViewById(R.id.ans_D);
        submitBtn = findViewById(R.id.submit_btn);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submitBtn.setOnClickListener(this);

        totalQuestionsTextView.setText("Total questions : "+totalQuestion);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        candidateName = preferences.getString("email","");
        int index = candidateName.indexOf("@");
        nameOnly = "";
        for(int i = 0; i < index; i++){
            nameOnly+=candidateName.charAt(i);
        }

        loadNewQuestion();
    }

    /**
     * Handles the click events of the answer buttons and the submit button.
     * If the submit button is clicked, it checks if the selected answer is correct, updates the score, and loads the next question.
     * If an answer button is clicked, it updates the selected answer and highlights the button.
     * @param view the View object that was clicked
     */
    @Override
    public void onClick(View view) {

        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);

        Button clickedButton = (Button) view;
        if(clickedButton.getId()==R.id.submit_btn){
            if(selectedAnswer.equals(QuestionAnswer.correctAnswers[currentQuestionIndex])){
                score++;
            }
            currentQuestionIndex++;
            loadNewQuestion();

        }else{
            selectedAnswer  = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.blue(-65281));
        }

    }

    /**
     * Loads a new question and updates the UI components with the new question and answer choices.
     * If all questions have been answered, it sends the final score to the server and displays the score and pass status in an AlertDialog.
     */
    void loadNewQuestion(){

        if(currentQuestionIndex == totalQuestion ){ // Create a final score variable and send
            finalScore = score;
            finishQuiz();
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("skillAssessment", TestList.testType);
                jsonObject.put("score", finalScore);
                jsonObject.put("candidateID", candidateName);

                String url = "http://coms-309-017.class.las.iastate.edu:8080/skillAssess/add";
                RequestQueue queue = Volley.newRequestQueue(JavaQuiz.this);
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                        Request.Method.POST, url, jsonObject,
                        response -> {
                            // Handle the response
                        },
                        error -> {
                            // Handle the error
                            String errorMessage = error.getMessage();
                            if (errorMessage == null) {
                                errorMessage = "Unknown error";
                            }
                        }
                );
                queue.add(jsonObjectRequest);

            }catch (JSONException e){
                e.printStackTrace();
            }

            return;
        }

        questionTextView.setText(QuestionAnswer.question[currentQuestionIndex]);
        ansA.setText(QuestionAnswer.choices[currentQuestionIndex][0]);
        ansB.setText(QuestionAnswer.choices[currentQuestionIndex][1]);
        ansC.setText(QuestionAnswer.choices[currentQuestionIndex][2]);
        ansD.setText(QuestionAnswer.choices[currentQuestionIndex][3]);

    }

    /**
     * Calculates the pass status and displays the score and pass status in an AlertDialog.
     */
    void finishQuiz(){
        String passStatus = "";
        if(score > totalQuestion*0.60){
            passStatus = "Passed";
        }else{
            passStatus = "Failed";
        }

        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Score is "+ score+" out of "+ totalQuestion)
                .setPositiveButton("Restart",(dialogInterface, i) -> restartQuiz() )
                .setCancelable(false)
                .show();
    }

    /**
     * Resets the score and current question index to 0 and loads a new question.
     */
    void restartQuiz(){
        score = 0;
        currentQuestionIndex =0;
        loadNewQuestion();
    }

}
//package com.example.user_manager_v1;
//
//import android.app.AlertDialog;
//import android.content.SharedPreferences;
//import android.graphics.Color;
//import android.os.Bundle;
//import android.preference.PreferenceManager;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.toolbox.JsonObjectRequest;
//import com.android.volley.toolbox.Volley;
//import com.example.user_manager_v1.QuestionAnswer;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//public class JavaQuiz extends AppCompatActivity implements View.OnClickListener{
//
//    TextView totalQuestionsTextView;
//    TextView questionTextView;
//    Button ansA, ansB, ansC, ansD;
//    Button submitBtn;
//
//    int score=0;
//
//    int finalScore = 0;
//
//    String candidateName = "";
//
//    int totalQuestion = QuestionAnswer.question.length;
//    int currentQuestionIndex = 0;
//    String selectedAnswer = "";
//
//    String nameOnly = "";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_java_quiz);
//
//        totalQuestionsTextView = findViewById(R.id.total_question);
//        questionTextView = findViewById(R.id.question);
//        ansA = findViewById(R.id.ans_A);
//        ansB = findViewById(R.id.ans_B);
//        ansC = findViewById(R.id.ans_C);
//        ansD = findViewById(R.id.ans_D);
//        submitBtn = findViewById(R.id.submit_btn);
//
//        ansA.setOnClickListener(this);
//        ansB.setOnClickListener(this);
//        ansC.setOnClickListener(this);
//        ansD.setOnClickListener(this);
//        submitBtn.setOnClickListener(this);
//
//        totalQuestionsTextView.setText("Total questions : "+totalQuestion);
//
//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//        candidateName = preferences.getString("email","");
//        int index = candidateName.indexOf("@");
//        nameOnly = "";
//        for(int i = 0; i < index; i++){
//            nameOnly+=candidateName.charAt(i);
//        }
//
//        loadNewQuestion();
//    }
//
//    @Override
//    public void onClick(View view) {
//
//        ansA.setBackgroundColor(Color.WHITE);
//        ansB.setBackgroundColor(Color.WHITE);
//        ansC.setBackgroundColor(Color.WHITE);
//        ansD.setBackgroundColor(Color.WHITE);
//
//        Button clickedButton = (Button) view;
//        if(clickedButton.getId()==R.id.submit_btn){
//            if(selectedAnswer.equals(QuestionAnswer.correctAnswers[currentQuestionIndex])){
//                score++;
//            }
//            currentQuestionIndex++;
//            loadNewQuestion();
//
//        }else{
//            selectedAnswer  = clickedButton.getText().toString();
//            clickedButton.setBackgroundColor(Color.blue(-65281));
//        }
//
//    }
//
//    void loadNewQuestion(){
//
//        if(currentQuestionIndex == totalQuestion ){ // Create a final score variable and send
//            finalScore = score;
//            finishQuiz();
//            JSONObject jsonObject = new JSONObject();
//            try {
//                jsonObject.put("skillAssessment", TestList.testType);
//                jsonObject.put("score", finalScore);
//                jsonObject.put("candidateID", candidateName);
//
//                String url = "http://coms-309-017.class.las.iastate.edu:8080/skillAssess/add";
//                RequestQueue queue = Volley.newRequestQueue(JavaQuiz.this);
//                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
//                        Request.Method.POST, url, jsonObject,
//                        response -> {
//                            // Handle the response
//                        },
//                        error -> {
//                            // Handle the error
//                            String errorMessage = error.getMessage();
//                            if (errorMessage == null) {
//                                errorMessage = "Unknown error";
//                            }
//                        }
//                );
//                queue.add(jsonObjectRequest);
//
//            }catch (JSONException e){
//                e.printStackTrace();
//            }
//
//            return;
//        }
//
//        questionTextView.setText(QuestionAnswer.question[currentQuestionIndex]);
//        ansA.setText(QuestionAnswer.choices[currentQuestionIndex][0]);
//        ansB.setText(QuestionAnswer.choices[currentQuestionIndex][1]);
//        ansC.setText(QuestionAnswer.choices[currentQuestionIndex][2]);
//        ansD.setText(QuestionAnswer.choices[currentQuestionIndex][3]);
//
//    }
//
//    void finishQuiz(){
//        String passStatus = "";
//        if(score > totalQuestion*0.60){
//            passStatus = "Passed";
//        }else{
//            passStatus = "Failed";
//        }
//
//        new AlertDialog.Builder(this)
//                .setTitle(passStatus)
//                .setMessage("Score is "+ score+" out of "+ totalQuestion)
//                .setPositiveButton("Restart",(dialogInterface, i) -> restartQuiz() )
//                .setCancelable(false)
//                .show();
//    }
//
//    void restartQuiz(){
//        score = 0;
//        currentQuestionIndex =0;
//        loadNewQuestion();
//    }
//
//}