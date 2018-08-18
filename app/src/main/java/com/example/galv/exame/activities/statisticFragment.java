package com.example.galv.exame.activities;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.galv.exame.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class statisticFragment extends Fragment {


    //private static String Tag="statisticActivity";
    private int[]yData={5,2}; // new tests , waitting Tests
    private String [] xData={"New" ,"Done"};
    PieChart pieChart;

    public statisticFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(com.example.galv.exame.R.layout.fragment_statistic, container, false);
        buildComponents(view);
        return view;
    }

    private void buildComponents(View view) {
        pieChart=view.findViewById(R.id.idPieChart);
        pieChart.setDescription("Distribution of Exams");
        pieChart.setCenterText("Example of\n7 Tests"); //change>>  get number Of Tests
        pieChart.setCenterTextSize(15);
        pieChart.setHoleRadius(25f);
        pieChart.setRotationEnabled(true);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setDrawEntryLabels(true);

        addDataToThePie();
    }

    private void addDataToThePie() {
        ArrayList<String> xEntries = new ArrayList<>();
        ArrayList<PieEntry> YEntries = new ArrayList<>();

        for (int i = 0; i < xData.length; i++) {
            xEntries.add(xData[i]);
        }

        for (int i = 0; i < yData.length; i++) {
            YEntries.add(new PieEntry(yData[i], i));
        }
        PieDataSet PieDataSet = new PieDataSet(YEntries, "testtsss");
        PieDataSet.setSliceSpace(6);
        PieDataSet.setValueTextSize(15);


        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.RED);
        colors.add(Color.GREEN);

        PieDataSet.setColors(colors);

        Legend legend=pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);


    PieData pieData=new PieData(PieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();


        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                //add toast option
            }

            @Override
            public void onNothingSelected() {
                //add toast option

            }
        });
    }

}
