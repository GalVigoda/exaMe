package com.example.galv.exame.handlers;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.galv.exame.R;
import com.example.galv.exame.activities.SplashActivity;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class AuthenticationHandler {
    private AppCompatActivity mContext;
    private FirebaseAuth mAuth;
    //private GoogleSignInClient mGoogleSignInClient;
    private GoogleApiClient mGoogleApiClient;

    public AuthenticationHandler(final AppCompatActivity context){
        this.mContext = context;

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(mContext.getString(R.string.default_web_client_id))
                //.requestIdToken("890665443902-7mf32rfaq2f6ebpvdpqerdudhpuiqggp.apps.googleusercontent.com")
                .requestEmail()
                .build();
        //mGoogleSignInClient = GoogleSignIn.getClient(mContext, gso);
        mGoogleApiClient = new GoogleApiClient.Builder(mContext)
                .enableAutoManage(mContext, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        Toast.makeText(mContext, "Error while getting google api connection", Toast.LENGTH_LONG);
                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();
        mAuth = FirebaseAuth.getInstance();
    }

    //do sign up
    public void SignUpWithEmailAndPassword(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(mContext, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Logger.ReportError("SignUpWithEmailAndPassword", "Create user success");
                }else{
                    Logger.ReportError("SignUpWithEmailAndPassword", "Create user FAILED");
                    Logger.ReportError("SignUpWithEmailAndPassword", task.getException().getMessage());
                }
            }
        });
        SignInWithEmailAndPassword(email, password);
    }

    // do login
    public void SignInWithEmailAndPassword(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(mContext, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Logger.ReportError("SignInWithEmailAndPassword", "Sign In success");

                }else{
                    Logger.ReportError("SignInWithEmailAndPassword", "Sign In FAILED");
                    Logger.ReportError("SignInWithEmailAndPassword", task.getException().getMessage());
                }
            }
        });
    }
    //do logout
    public void SignOut(){
        mAuth.signOut();
    }

    public FirebaseUser GetCurrentUser(){
        return mAuth.getCurrentUser();
    }

    public Intent GetGoogleLoginIntent(){
        //return mGoogleSignInClient.getSignInIntent();
        return  Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
    }

    public void AuthWithGoogleAccount (GoogleSignInAccount account){
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(mContext, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Logger.ReportError("AuthWithGoogleAccount.signInWithCredential:success: ", user.getDisplayName());
                            ((SplashActivity)mContext).UpdateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Logger.ReportError("AuthWithGoogleAccount.signInWithCredential:success: ", task.getException().getMessage());
                            ((SplashActivity)mContext).UpdateUI(null);
                        }

                    }
                });
    }
}