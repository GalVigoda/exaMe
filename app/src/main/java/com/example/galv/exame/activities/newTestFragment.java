package com.example.galv.exame.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.galv.exame.adapters.MyClickListener;
import com.example.galv.exame.adapters.examListAdapter;
import com.example.galv.exame.R;
import com.example.galv.exame.entities.Exam;
import com.example.galv.exame.handlers.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */

public class newTestFragment extends Fragment {

    private Button startTest;
    private MainActivity myActivity;
    private List<Exam> data;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    private examListAdapter examAdapter;
//    public newTestFragment() {
//        // Required empty public constructor
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_new_test, container, false);

        myActivity = (MainActivity) getActivity();
        data = myActivity.getNewExams();
        if (data == null)
            data = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recycler_exams_list);
        layoutManager = new LinearLayoutManager(myActivity);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        examAdapter = new examListAdapter(data, myActivity, new MyClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Exam exam = data.get(position);
                if (exam == null)
                    return;
                Logger.ReportError("newTestFragment.onItemClick:", "Exam isL" + exam.getTitle() );
                moveFromFramentToNewActivity(exam);
            }
        });
        recyclerView.setAdapter(examAdapter);

        DividerItemDecoration itemDecor = new DividerItemDecoration(myActivity,DividerItemDecoration.HORIZONTAL);
        recyclerView.addItemDecoration(itemDecor);

        return view;
    }

     private void moveFromFramentToNewActivity(Exam exam) {
        Intent intent = new Intent(getActivity(), ExamActivity.class);
        intent.putExtra("EXAM_DETAILS", exam);
        startActivity(intent);
        //((Activity) getActivity()).overridePendingTransition(0,0);

    }

    public void notifyOnDataChange(){
        if (myActivity == null)
            return;
        data = myActivity.getNewExams();
        examAdapter.notifyDataSetChanged();
    }
}
