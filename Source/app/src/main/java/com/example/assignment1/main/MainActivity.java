package com.example.assignment1.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment1.R;
import com.example.assignment1.calc.CalcActivity;
import com.example.assignment1.entry.EntryActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainContract.MainView, MyRecyclerViewAdapter.ItemClickListener {

    private MainPresenter mainPresenter;
    MyRecyclerViewAdapter adapter;
    private static final String ENTRY_DATA = "entrydata.txt";
    private static final String LOCATION_DATA = "locationdata.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainPresenter = new MainPresenter(this);


        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        // data to populate the RecyclerView with
        ArrayList<String> exampleData = new ArrayList<>();
        exampleData.add("Entry 1 : 13kJ");
        exampleData.add("Entry 2 : 24kJ");
        exampleData.add("Entry 3 : -22kJ");
        exampleData.add("Entry 4 : 67kJ");
        exampleData.add("Entry 5 : -3kJ");

        FileOutputStream fos = null;
        try {

            fos = openFileOutput(ENTRY_DATA,MODE_APPEND);
            fos.close();

        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }
        try {

            fos = openFileOutput(LOCATION_DATA,MODE_APPEND);
            fos.close();

        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }


        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerEntries);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(this, getdata());
        //adapter = new MyRecyclerViewAdapter(this, exampleData);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        //Add Entry Button
        FloatingActionButton fab = findViewById(R.id.fabNew);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mainPresenter.newEntry();
            }
        });

        setAverage();
    }
    @Override
    public void onResume(){
        super.onResume();
        //Toast.makeText(this, "You clicked AADAF", Toast.LENGTH_SHORT).show();

        mainPresenter.updateData(getdata(),adapter);
        setAverage();

    }



//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }

    //    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
    //Gets data from entryData.txt
    public ArrayList<String>  getdata(){
        ArrayList<String> data = new ArrayList<>();
        FileInputStream fis = null;
        data = new ArrayList<String>();
        String[] dataLine;

        try {
            fis = openFileInput(ENTRY_DATA);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;
            //Toast.makeText(this, "Starting", Toast.LENGTH_SHORT).show();
            while ((text = br.readLine())!=null && !text.equals("")){
                //Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
                dataLine = text.split(",");
                data.add(dataLine[0]+" : "+dataLine[1]+"kJ");
            }
            fis.close();


        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public void updateData(ArrayList<String> data) {

        mainPresenter.updateData(data,adapter);


    }


    @Override
    public void showEntryScreen() {
        Intent showEntry = new Intent(getApplicationContext(), EntryActivity.class);
        startActivity(showEntry);
    }

    @Override
    public void showCalcScreen() {
        Intent addEntry = new Intent(getApplicationContext(), CalcActivity.class);
        startActivity(addEntry);
    }

    @Override
    public void setAverage() {
        ArrayList<String> data = new ArrayList<>();
        FileInputStream fis = null;
        String[] dataLine;
        int max=0;
        int average=0;
        int count=0;
        TextView txt1 = findViewById(R.id.textView8);

        try {
            fis = openFileInput(ENTRY_DATA);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String text;
            while ((text = br.readLine())!=null && !text.equals("")){
                dataLine = text.split(",");
                count++;
                max+=Integer.parseInt(dataLine[1]);
            }
            if (count!=0){average = max/count;}


            fis.close();
            txt1.setText(average+"kJ");
//            Toast.makeText(this, ""+average, Toast.LENGTH_SHORT).show();

        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onItemClick(View view, int position) {
        //Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();

        FileOutputStream fos = null;

        try {
            fos = openFileOutput(LOCATION_DATA,MODE_PRIVATE);
            fos.write((position+"").getBytes());


        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mainPresenter.viewEntry();

    }
}
