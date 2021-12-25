package com.hamza.counter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView countertextview;
    int counter = 0,total,maxValue;
    ListView itemView;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ArrayList<String> array;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countertextview = findViewById(R.id.counterTextview);
        itemView=findViewById(R.id.ListView);
        creatSharedPreferences();
        creatEditor();
        setItemInArray();
        setArrayInListView();

    }

    public void increaseCounterInrelativeLayout(View view) {
        counter++;
        checkCounter();
        countertextview.setText(String.valueOf(counter));
    }

    private void checkCounter() {
        if (counter % 10 == 0)
            countertextview.setTextColor(getRandomcolor());

    }

    private int getRandomcolor() {
        int[] colors = {Color.BLUE, Color.RED, Color.YELLOW};
        int random = new Random().nextInt(colors.length);
        return colors[random];
    }


    public void resetCounter(View view) {
        counter = 0;
        countertextview.setText(String.valueOf(counter));
        countertextview.setTextColor(Color.BLACK);
    }
    public void creatSharedPreferences(){
        sharedPreferences=getSharedPreferences("counterValue",MODE_PRIVATE);
    }
    public void creatEditor(){
        editor=sharedPreferences.edit();
    }
    public void setItemInArray(){
        array=new ArrayList<>();
        array.add(String.valueOf(sharedPreferences.getInt("Total",0)));
        array.add(String.valueOf(sharedPreferences.getInt("Max",0)));
    }
    public void setArrayInListView(){
        arrayAdapter=new ArrayAdapter<String>(getBaseContext(),R.layout.support_simple_spinner_dropdown_item,array);
        itemView.setAdapter(arrayAdapter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        setTotalVales();
        setMaxValue();
        editor.apply();
    }
    public void setTotalVales(){
        editor.putInt("Total",sharedPreferences.getInt("Total",0)+counter);
    }
    public void setMaxValue(){
        if(sharedPreferences.getInt("Max",0)<counter){
            editor.putInt("Max",counter);
        }
    }





}
