package com.example.assignment1.main;

import android.widget.TextView;

import java.util.ArrayList;

public class MainPresenter implements MainContract.Presenter {
    private MainContract.MainView mView;

    MainPresenter(MainContract.MainView view) {
        mView = view;
    }

    @Override
    public void newEntry() {
        mView.showCalcScreen();
    }

    @Override
    public void viewEntry() {

        mView.showEntryScreen();
    }



    @Override
    public void updateData(ArrayList<String> data, MyRecyclerViewAdapter adapter) {
        adapter.updateData(data);


    }

}
