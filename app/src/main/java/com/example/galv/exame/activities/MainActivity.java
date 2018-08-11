package com.example.galv.exame.activities;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;

import com.example.galv.exame.R;

public class MainActivity extends AppCompatActivity {


    private BottomNavigationView btNavigation;
    private FrameLayout frMain;
    private homeFragment homeFragment;
    private doneTestFragment doneTestFragment;
    private newTestFragment newTestFragment;
    private statisticFragment statisticFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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

}