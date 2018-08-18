package com.example.galv.exame.activities;


import android.content.Intent;
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


//    public newTestFragment() {
//        // Required empty public constructor
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_new_test, container, false);

        startTest = (Button) view.findViewById(R.id.startNewTest);
        startTest.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                moveFromFramentToNewActivity();
            }
        });

        return view;
    }

     private void moveFromFramentToNewActivity() {
        Intent intent = new Intent(getActivity(), ExamActivity.class);
        startActivity(intent);
        //((Activity) getActivity()).overridePendingTransition(0,0);

    }

}
