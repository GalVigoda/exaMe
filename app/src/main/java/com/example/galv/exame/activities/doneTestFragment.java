package com.example.galv.exame.activities;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.galv.exame.R;
import com.example.galv.exame.adapters.MyClickListener;
import com.example.galv.exame.adapters.examListAdapter;
import com.example.galv.exame.entities.Exam;
import com.example.galv.exame.entities.UserExam;
import com.example.galv.exame.handlers.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class doneTestFragment extends Fragment {

    private MainActivity myActivity;
    private List<Exam> data;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    private examListAdapter examAdapter;

    public doneTestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_done_test, container, false);


        myActivity = (MainActivity) getActivity();
        data = myActivity.getOldExams();
        if (data == null)
            data = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recycler_exams_list);
        layoutManager = new LinearLayoutManager(myActivity);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        examAdapter = new examListAdapter(data, myActivity, new MyClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                // TODO - open old exam
            }
        });
        recyclerView.setAdapter(examAdapter);

        DividerItemDecoration itemDecor = new DividerItemDecoration(myActivity,DividerItemDecoration.HORIZONTAL);
        recyclerView.addItemDecoration(itemDecor);

        return view;
    }

}
