package com.example.galv.exame.activities;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.galv.exame.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * A simple {@link Fragment} subclass.
 */
public class homeFragment extends Fragment {

    private TextView userEmail, userName,numberOfNewTests;
    private ImageView imageView;
    private ImageView imageView2;
    FirebaseAuth mAuth;
    FirebaseUser user;

    public homeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(com.example.galv.exame.R.layout.fragment_home, container, false);
        findViewById(view);
        buildComponents(view);
        setOnClick();

        return view;
    }

    private void setOnClick() {

        numberOfNewTests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveFromFramentToNewActivity();
            }
        });
    }

    private void moveFromFramentToNewActivity() {
        ((MainActivity)getActivity()).goToNewExamFragment();

    }


    private void findViewById (View view){
            user = ((CommonBaseActivity) getActivity()).getUser();
            mAuth = FirebaseAuth.getInstance();
            userEmail = view.findViewById(R.id.textHome);
            userName = view.findViewById(R.id.textHome2);
            numberOfNewTests = view.findViewById(R.id.numberOfNewTests);
            imageView2 = view.findViewById(R.id.imageView2);

            // userExtra= view.findViewById(R.id.textHome3);
        }

        private void buildComponents (View view){


            imageView = view.findViewById(R.id.imageView3);
            user = mAuth.getCurrentUser();
            Glide.with(this)
                    .load(user.getPhotoUrl())
                    .into(imageView);
            userEmail.setText(user.getEmail());
            userName.setText(user.getDisplayName());
            int numberOfTests=(((MainActivity)getActivity()).getNewExams().size());
            numberOfNewTests.setText(""+numberOfTests);

            UpdateTheUi();
        }
        //  FirebaseUser user = ((CommonBaseActivity)getActivity()).getUser();

        public void UpdateTheUi(){
            int numberOfTests=(((MainActivity)getActivity()).getNewExams().size());
            numberOfNewTests.setText(""+numberOfTests);

        }

//    Query newExamsQuery = mFirebaseDatabase.child("userNewExams").child(mUId);
//        newExamsQuery.addChildEventListener(new ChildEventListener() {
//        @Override
//        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//            UserExam userExam = dataSnapshot.getValue(UserExam.class);
//            Logger.ReportInfo("ExamsHandler", "new user exam added, id: " + userExam.getExamKey());
//            newExams.add(userExam.getExamKey());
//            context.UpdateUi(UpdateFor.UPDATE_FOR_NEW_EXAMS);
//            GetExamByKey(userExam.getExamKey());
       // }



}