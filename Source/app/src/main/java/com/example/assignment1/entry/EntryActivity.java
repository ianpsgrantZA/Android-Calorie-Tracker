package com.example.assignment1.entry;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.assignment1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class EntryActivity extends AppCompatActivity {
    int location = 0;
    int maxLocation = 0;
    String data[];
    private static final String ENTRY_DATA = "entrydata.txt";
    private static final String LOCATION_DATA = "locationdata.txt";

    EditText txt1;
    EditText txt2;
    EditText txt3;
    EditText txt4;
    TextView txt5;
    TextView txt6;
    TextView txt7;
    TextView txtTitle;
    FloatingActionButton fabPrev;
    FloatingActionButton fabNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
        Toolbar toolbar = findViewById(R.id.toolbar);


        txt1 = findViewById(R.id.etxtSource2);
        txt2 = findViewById(R.id.etxtSource7);
        txt3 = findViewById(R.id.etxtSource6);
        txt4 = findViewById(R.id.etxtSource5);
        txt5 = findViewById(R.id.textView24);
        txt6 = findViewById(R.id.textView26);
        txt7 = findViewById(R.id.textView28);
        txtTitle = findViewById(R.id.textView4);
        fabPrev = findViewById(R.id.fabPrev);
        fabNext = findViewById(R.id.fabNext);

        setMaxLocation();
        setLocation();
        updateData();
        fabSetup();

        if (location==0){
            fabPrev.setEnabled(false);
        }
        if (location==maxLocation){
            fabNext.setEnabled(false);
        }

    }
    

    private void setMaxLocation() {
        FileInputStream fis = null;

        try {
            fis = openFileInput(ENTRY_DATA);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String text;
            int count=0;
            while ((text = br.readLine())!=null && !text.equals("")){
                count++;
            }
            maxLocation=count-1;
            fis.close();

        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void setLocation(){

        FileInputStream fis = null;

        try {
            fis = openFileInput(LOCATION_DATA);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();

            location = Integer.parseInt(br.readLine());


        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fabSetup(){
        //Add Entry Button

        fabNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                location++;
                updateData();
                fabPrev.setEnabled(true);
                if (location>0){
                    fabPrev.setEnabled(true);
                }
                if (location==maxLocation){
                    fabNext.setEnabled(false);
                }
            }
        });

        fabPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                location--;
                updateData();
                if (location==0){
                    fabPrev.setEnabled(false);
                }
                if (maxLocation>0){
                    fabNext.setEnabled(true);
                }
            }
        });

    }



    public void updateData(){

        String[] data;
        FileInputStream fis = null;
        data = new String[7];
        String[] dataLine;

        try {
            fis = openFileInput(ENTRY_DATA);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text="";
            //Toast.makeText(this, "Starting", Toast.LENGTH_SHORT).show();

            for (int i=0;i<location+1;i++){
                text = br.readLine();
            }
                //Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
                data = text.split(",");
                //Toast.makeText(this, data[2], Toast.LENGTH_SHORT).show();
                //Toast.makeText(this, location+"", Toast.LENGTH_SHORT).show();
                txt1.setText(data[2]);
                txt2.setText(data[3]);
                txt3.setText(data[4]);
                txt4.setText(data[5]);
                txt5.setText(Integer.parseInt(data[2])+Integer.parseInt(data[3])+"");
                txt6.setText(Integer.parseInt(data[4])+Integer.parseInt(data[5])+"");
                txt7.setText(data[1]);
                txtTitle.setText(data[0]);




            fis.close();


        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}
