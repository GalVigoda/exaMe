package com.example.galv.exame.activities;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.galv.exame.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class newTestFragment extends Fragment {

    Button startTest;


    public newTestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       // setOnClickButton();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_test, container, false);


    }

//    private void viewby() {
//        startTest=(Button) startTest.findViewById(R.id.startNewTest);
//    }
//
//    private void setOnClickButton() {
//        startTest.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//
//               // Intent intent = new Intent(newTestFragment.this, newExamFragment.class);
//              //  startActivity(intent);
//
//            }
//
//        });
//    }
}
