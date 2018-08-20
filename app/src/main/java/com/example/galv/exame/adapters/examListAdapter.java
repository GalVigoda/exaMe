package com.example.galv.exame.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.galv.exame.R;
import com.example.galv.exame.activities.CommonBaseActivity;
import com.example.galv.exame.activities.MainActivity;
import com.example.galv.exame.entities.Exam;

import java.util.ArrayList;
import java.util.List;

public class examListAdapter extends RecyclerView.Adapter<examListAdapter.examViewHolder> {
    private List<Exam> data;

    private Context myContext;
    private MyClickListener listener;

    public examListAdapter(List<Exam> data, Context context, MyClickListener listener) {
        this.data = data;
        this.myContext = context;
        this.listener = listener;
    }

    public class examViewHolder extends RecyclerView.ViewHolder{

        private TextView examNameText;

        public examViewHolder (View view){
            super(view);
            examNameText = (TextView) view.findViewById(R.id.exam_name_text_view);

        }
    }

    @Override
    public examViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.exam_line_layout, viewGroup, false);
        final examViewHolder mViewHolder = new examViewHolder(itemView) {
        };
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(view, mViewHolder.getAdapterPosition());
            }
        });

        return mViewHolder;
    }

    @Override
    public void onBindViewHolder( examViewHolder examViewHolder, int i) {
        Exam exam = data.get(i);
        examViewHolder.examNameText.setText(exam.getTitle());
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 :data.size();
    }
}
