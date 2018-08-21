package com.example.galv.exame.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.galv.exame.R;
import com.example.galv.exame.entities.Answer;
import com.example.galv.exame.entities.Exam;
import com.example.galv.exame.entities.Question;
import com.example.galv.exame.entities.Tag;
import com.example.galv.exame.entities.UserExam;
import com.example.galv.exame.handlers.AuthenticationHandler;
import com.example.galv.exame.handlers.ExamsHandler;
import com.example.galv.exame.handlers.Logger;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends CommonBaseActivity {
    public static final boolean TESTING_MODE = false;
    private AuthenticationHandler mAuthenticationHandler;
    private static final int RC_SIGN_IN = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuthenticationHandler = new AuthenticationHandler(this);

        FirebaseUser user = mAuthenticationHandler.GetCurrentUser();
        if (user == null) {
            Logger.ReportError("SplashActivity.onCreate", "user not exists");
            GoToLoginActivity();
        }
        else {
            String uName = user.getDisplayName();
            String uEmail = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();
            String uid = user.getUid();
            Logger.ReportError("SplashActivity.onCreate", uName + ", " + uEmail + ", " + photoUrl + ", " + uid);
            GoToMainActivity();
        }
    }

    private void GoToLoginActivity(){
        Logger.ReportError("SplashActivity.GoToLoginActivity", "GoToLoginActivity()");
        Intent intent = mAuthenticationHandler.GetGoogleLoginIntent();
        startActivityForResult(intent, RC_SIGN_IN);
    }

    private void GoToMainActivity(){
        Logger.ReportError("SplashActivity.GoToMainActivity", "GoToMainActivity()");
        Intent intent = new Intent(this, TESTING_MODE ? TestingsActivity.class : MainActivity.class);
        startActivity(intent);
        SplashActivity.this.finish();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Logger.ReportError("SplashActivity.onCreate", "Google sign in success: " + account.getDisplayName());
                mAuthenticationHandler.AuthWithGoogleAccount(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Logger.ReportError("SplashActivity.onCreate", "Google sign in failed " + e.getMessage());
                e.printStackTrace();
                UpdateUI(null);
            }
        }
    }

    public void UpdateUI(FirebaseUser user){
        if (user != null){
            createExamsForCurrentUser(user);
        }else{
            Logger.ReportError("SplashActivity.UpdateUI", "Got null user - exit");
            Toast.makeText(this, "Authentication failed - Contact support", Toast.LENGTH_LONG);
            finish();
        }
    }

    //for the testing of the application by the lecturer
    private void createExamsForCurrentUser(final FirebaseUser user){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage( getString(R.string.create_exam_dialog));
        builder1.setCancelable(false);

        builder1.setPositiveButton(
                getString(R.string.yes),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        createExams(user);
                        GoToMainActivity();
                    }
                });
        builder1.setNegativeButton(
                getString(R.string.no),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.cancel();
                        GoToMainActivity();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    private void createExams (FirebaseUser user){
        ExamsHandler mExamsHandler = new ExamsHandler(user.getUid(), this);
        for (int i =1; i < 5; i++) {
            Exam e1 = new Exam("Exam no." + i + " - Colors", 20);
            Question q1 = new Question("What is the color of the Sun?", Question.QUESTION_TYPE_AMERICAN, 1);
            q1.addAnswer(new Answer(1, "Red", 1));
            q1.addAnswer(new Answer(2, "White", 2));
            q1.addAnswer(new Answer(3, "Yellow", 3));
            q1.addAnswer(new Answer(4, "Green", 4));
            q1.addTag(new Tag(5, "Colors"));
            q1.addTag(new Tag(2, "Basics"));
            q1.setCorrectAnswer("3");
            q1.setExplanation("The sun is yellow cause it is hot there");
            e1.addQuestion(q1);

            Question q2 = new Question("What is the color of the Moon?", Question.QUESTION_TYPE_AMERICAN, 2);
            q2.addAnswer(new Answer(1, "Green", 1));
            q2.addAnswer(new Answer(2, "White", 2));
            q2.addAnswer(new Answer(3, "Yellow", 3));
            q2.addAnswer(new Answer(4, "Black", 4));
            q2.addTag(new Tag(5, "Colors"));
            q2.addTag(new Tag(2, "Basics"));
            q2.setCorrectAnswer("2");
            q2.setExplanation("The Moon is white cause it is cold there");
            e1.addQuestion(q2);

            Question q3 = new Question("What is the color of the Tomato?", Question.QUESTION_TYPE_AMERICAN, 3);
            q3.addAnswer(new Answer(1, "Green", 1));
            q3.addAnswer(new Answer(2, "Red", 2));
            q3.addAnswer(new Answer(3, "Yellow", 3));
            q3.addAnswer(new Answer(4, "Black", 4));
            q3.addTag(new Tag(5, "Colors"));
            q3.addTag(new Tag(2, "Basics"));
            q3.setCorrectAnswer("2");
            q3.setExplanation("The Tomato is red cause it is shy vegetable");
            e1.addQuestion(q3);

            Question q4 = new Question("What is the color of the Mouse?", Question.QUESTION_TYPE_AMERICAN, 4);
            q4.addAnswer(new Answer(1, "Green", 1));
            q4.addAnswer(new Answer(2, "Red", 2));
            q4.addAnswer(new Answer(3, "Yellow", 3));
            q4.addAnswer(new Answer(4, "Gray", 4));
            q4.addTag(new Tag(5, "Colors"));
            q4.addTag(new Tag(2, "Basics"));
            q4.setCorrectAnswer("4");
            q4.setExplanation("The Mouse is gray cause it is pessimistic animal");
            e1.addQuestion(q4);

            Question q5 = new Question("Sort the colors above by their names", Question.QUESTION_TYPE_SORTING, 5);
            q5.addAnswer(new Answer(1, "Green", 1));
            q5.addAnswer(new Answer(2, "Red", 2));
            q5.addAnswer(new Answer(3, "Yellow", 3));
            q5.addAnswer(new Answer(4, "Gray", 4));
            q5.addTag(new Tag(5, "Colors"));
            q5.addTag(new Tag(2, "Basics"));
            q5.setCorrectAnswer("4|1|2|3");
            q5.setExplanation("GRA < GRE < R < Y");
            e1.addQuestion(q5);

            Question q6 = new Question("Sort the numbers above by their value", Question.QUESTION_TYPE_SORTING, 6);
            q6.addAnswer(new Answer(1, "100", 1));
            q6.addAnswer(new Answer(2, "10", 2));
            q6.addAnswer(new Answer(3, "500", 3));
            q6.addAnswer(new Answer(4, "1000", 4));
            q6.addTag(new Tag(5, "Colors"));
            q6.addTag(new Tag(2, "Basics"));
            q6.setCorrectAnswer("2|1|3|4");
            q6.setExplanation("10 < 100 < 500 < 1000");
            e1.addQuestion(q6);

            UserExam ue = new UserExam(mExamsHandler.SaveExam(e1));
            mExamsHandler.SaveUserNewExam(ue);
        }
    }
}
