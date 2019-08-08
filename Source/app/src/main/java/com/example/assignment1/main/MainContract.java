package com.example.assignment1.main;

import java.util.ArrayList;

public interface MainContract {
    interface MainView{
        void showEntryScreen();
        void showCalcScreen();
        void setAverage();
    }
    interface Presenter{
        void newEntry();
        void viewEntry();
        void updateData(ArrayList<String> data, MyRecyclerViewAdapter adapter);
    }
}
