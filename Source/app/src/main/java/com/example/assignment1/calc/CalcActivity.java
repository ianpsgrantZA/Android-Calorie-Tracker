package com.example.assignment1.calc;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.assignment1.R;
import com.example.assignment1.main.MainActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class CalcActivity extends AppCompatActivity{
    private static final String ENTRY_DATA = "entrydata.txt";
    private Button buttonAdd;
    EditText txt1;
    EditText txt2;
    EditText txt3;
    EditText txt4;
    TextView txt5;
    TextView txt6;
    TextView txt7;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        Toolbar toolbar = findViewById(R.id.toolbar);

        txt1 = findViewById(R.id.etxtSource2);
        txt2 = findViewById(R.id.etxtSource7);
        txt3 = findViewById(R.id.etxtSource6);
        txt4 = findViewById(R.id.etxtSource5);
        txt5 = findViewById(R.id.textView24);
        txt6 = findViewById(R.id.textView26);
        txt7 = findViewById(R.id.textView28);




        setSupportActionBar(toolbar);
        setListeners();

        buttonAdd = (Button) findViewById(R.id.button);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                save(view);
                Menu();
            }
        });



    }
    void setListeners(){
        txt1.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                if (txt1.getText().toString().equals("")){
                    txt1.setText("0");
                }
                updateVals();
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
        txt2.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                if (txt2.getText().toString().equals("")){
                    txt2.setText("0");
                }
                updateVals();            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
        txt3.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                if (txt3.getText().toString().equals("")){
                    txt3.setText("0");
                }
                updateVals();            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
        txt4.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                if (txt4.getText().toString().equals("")){
                    txt4.setText("0");
                }
                updateVals();            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
    }

    void updateVals(){

        txt5.setText(Integer.parseInt(txt1.getText().toString())+Integer.parseInt(txt2.getText().toString())+"");
        txt6.setText(Integer.parseInt(txt3.getText().toString())+Integer.parseInt(txt4.getText().toString())+"");
        txt7.setText(Integer.parseInt(txt5.getText().toString())-Integer.parseInt(txt6.getText().toString())+"");
    }
    public void save(View view){
        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {
            fis = openFileInput(ENTRY_DATA);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;
            int count = 1;
//            Toast.makeText(this, "Starting", Toast.LENGTH_SHORT).show();
            while ((text = br.readLine())!=null && !text.equals("")){
                count++;
                //Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
            }
            fos = openFileOutput(ENTRY_DATA,MODE_APPEND);
//            fos.write(("Entry 1,-100,2100,700,1600,1300\n" +
//                    "Entry 2,600,1500,700,800,800\n").getBytes());
            String outLine ="Entry "+count+","+txt7.getText()+","+txt1.getText()+","+txt2.getText()+","+txt3.getText()+","+txt4.getText()+"\n";
            fos.write(outLine.getBytes());

            fis = openFileInput(ENTRY_DATA);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            sb = new StringBuilder();
            while ((text = br.readLine())!=null){
                //Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
            }
            fis.close();

        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    void Menu(){
        Intent showMenu = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(showMenu);
    }

}
