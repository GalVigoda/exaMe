package com.example.galv.exame.activities;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;
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
    private float numberOfWaittingExams,numberOfdoneExams,numberOfPassExams=3,numberOfFailExams=2;
    private float[]yData={numberOfWaittingExams,numberOfdoneExams};
    private String [] xData={"Waitting" ,"Done"};
    private float[]yData2={numberOfPassExams,numberOfFailExams};
    private String [] xData2={"Pass" ,"Fail"};
    private Float sumOfTests;
    private TextView tvGPA;
    PieChart pieChart;
    PieChart pieChart2;


    public statisticFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_statistic1, container, false);
        tvGPA= (Button)view.findViewById(R.id.gradeGpa);
        getdetailsExams();
        buildComponents(view);

        return view;
    }

    private void getdetailsExams() {
        numberOfWaittingExams=((MainActivity)getActivity()).getNewExams().size();
        numberOfdoneExams=((MainActivity)getActivity()).getOldExams().size();
        sumOfTests=(numberOfWaittingExams+numberOfdoneExams);
        yData[0]=numberOfWaittingExams;
        yData[1]=numberOfdoneExams;


        //for graph number 2 :
        numberOfPassExams=((MainActivity)getActivity()).getNumOfPassed();
        numberOfFailExams=((MainActivity)getActivity()).getNumOfFailed();
        yData2[0]=numberOfPassExams;
        yData2[1]=numberOfFailExams;
        float avarage=((MainActivity)getActivity()).getNumOfAverage();
        tvGPA.setText(Float.toString(avarage));
}

    private void buildComponents(View view) {
        pieChart=view.findViewById(R.id.idPieChart);

        pieChart.setDescription("Distribution of Exams");

        pieChart.setCenterText("Total "+sumOfTests+"\nExams"); //change>>  get number Of Tests
        pieChart.setCenterTextSize(15);
        pieChart.setHoleRadius(25f);
        pieChart.setRotationEnabled(true);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setDrawEntryLabels(true);

        pieChart2=view.findViewById(R.id.idPieChart2);
        pieChart2.setCenterText("Pass/Fails");
        pieChart2.setDescription("Success rates");
        pieChart2.setCenterTextSize(15);
        pieChart2.setHoleRadius(25f);
        pieChart2.setRotationEnabled(true);
        pieChart2.setTransparentCircleAlpha(0);
        pieChart2.setDrawEntryLabels(true);


        addDataToThePie();
        addDataToThePie2();
    }

    private void addDataToThePie2() {
        ArrayList<String> xEntries = new ArrayList<>();
        ArrayList<PieEntry> YEntries = new ArrayList<>();

        for (int i = 0; i < xData2.length; i++) {
            xEntries.add(xData2[i]);
        }

        for (int i = 0; i < yData2.length; i++) {
            YEntries.add(new PieEntry(yData2[i], i));
        }
        PieDataSet PieDataSet = new PieDataSet(YEntries, "Tests");
        PieDataSet.setSliceSpace(6);
        PieDataSet.setValueTextSize(15);


        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.rgb(248 ,121,7));
        colors.add(Color.rgb(49 ,192,240));

        PieDataSet.setColors(colors);

        Legend legend=pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);


        PieData pieData=new PieData(PieDataSet);
        pieChart2.setData(pieData);
        pieChart2.invalidate();


        pieChart2.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override

            public void onValueSelected(Entry e, Highlight h) {
                int pos1 = e.toString().indexOf("(sum): ");
                String string1 = e.toString().substring(pos1 + 7);
                for (int i = 0; i < yData2.length; i++){
                    if (yData2[i] == Float.parseFloat(string1)) {
                        pos1 = i;
                        break;
                    }
                }
            String string2= xData2[pos1+0];
                Toast.makeText(getActivity(),string1+" Exams are "+string2,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected() {
                //add toast option

            }
        });
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
                    int pos1 = e.toString().indexOf("(sum): ");
                    String string2 = e.toString().substring(pos1 + 7);
                    for (int i = 0; i < yData.length; i++){
                        if (yData[i] == Float.parseFloat(string2)) {
                            pos1 = i;
                            break;
                        }
                    }
                    String string1= xData[pos1+0];
                    Toast.makeText(getActivity(),string2+" Exams are "+string1,Toast.LENGTH_SHORT).show();
                }



            @Override
            public void onNothingSelected() {
                //add toast option

            }
        });
    }

}
