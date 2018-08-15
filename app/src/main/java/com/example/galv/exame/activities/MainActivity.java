package com.example.galv.exame.activities;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.galv.exame.R;
import com.example.galv.exame.handlers.ExamsHandler;
import com.example.galv.exame.handlers.Logger;
import com.example.galv.exame.handlers.UpdateFor;

public class MainActivity extends CommonBaseActivity {


    private BottomNavigationView btNavigation;
    private FrameLayout frMain;
    private homeFragment homeFragment;
    private doneTestFragment doneTestFragment;
    private newTestFragment newTestFragment;
    private statisticFragment statisticFragment;
    private ExamsHandler mExamsHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mExamsHandler = new ExamsHandler(getUserId(), this);

        frMain=(FrameLayout) findViewById(R.id.main_frame);
        btNavigation=(BottomNavigationView)findViewById(R.id.main_navigation);

        homeFragment=new homeFragment();
        newTestFragment=new newTestFragment();
        doneTestFragment=new doneTestFragment();
        statisticFragment=new statisticFragment();
        setFragmet(homeFragment);

        btNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {


                switch(menuItem.getItemId()) {
                    case R.id.navigatin_home:
                        btNavigation.setItemBackgroundResource(R.color.design_default_color_primary);
                        setFragmet(homeFragment);
                        return true;

                    case R.id.navigatin_new:
                        btNavigation.setItemBackgroundResource(R.color.red);
                        setFragmet(newTestFragment);
                        return true;

                    case R.id.navigatin__done:
                        btNavigation.setItemBackgroundResource(R.color.green);
                        setFragmet(doneTestFragment);
                        return true;

                    case R.id.navigatin_statistic:
                        btNavigation.setItemBackgroundResource(R.color.yellow);
                        setFragmet(statisticFragment);
                        return true;
                }
                defult:
                return  false;

            }
        });

    }

    private void setFragmet(Fragment f1) {

        FragmentTransaction FragmentTransaction = getSupportFragmentManager().beginTransaction();

        FragmentTransaction.replace(R.id.main_frame,f1);
        FragmentTransaction.commit();

    }


    @Override
    public void UpdateUi(UpdateFor updateFor){
        Logger.ReportError("TestingsActivity.UpdateUi:", "Got Update for: " + updateFor.name() );

    }
}