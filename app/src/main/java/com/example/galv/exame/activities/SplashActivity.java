package com.example.galv.exame.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.galv.exame.R;
import com.example.galv.exame.handlers.AuthenticationHandler;
import com.example.galv.exame.handlers.Logger;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {

    private AuthenticationHandler mAuthenticationHandler;
    private static final int RC_SIGN_IN = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_splash);
        String email = "naordavid2@gmail.com";
        String password = "123456";
        mAuthenticationHandler = new AuthenticationHandler(this);
        //mAuthenticationHandler.SignUpWithEmailAndPassword(email, password);
        //mAuthenticationHandler.SignInWithEmailAndPassword(email, password);
        mAuthenticationHandler.SignOut();
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
        Intent intent = new Intent(this, MainActivity.class);


        startActivity(intent);
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
                UpdateUI(null);
            }
        }
    }

    public void UpdateUI(FirebaseUser user){
        if (user != null){
            GoToMainActivity();
        }else{
            Logger.ReportError("SplashActivity.UpdateUI", "Got null user - exit");
            finish();
        }
    }
}
