package com.hamza.counter;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


    TextView counter;
    int i=0 , change=1;
    int[]  color;
    Random random;
    int temp=0,index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        counter = findViewById(R.id.Counter);
        random = new Random();
        color = new int[5];
        color[0] = Color.YELLOW;
        color[1] = Color.BLUE;
        color[2] = Color.WHITE;
        color[3] = Color.RED;
        color[4] = Color.GREEN;
        counter.setTextColor(Color.WHITE);
    }






    public  void incres(View v){
        counter.setText(++i +"");
        if (i==change*10) {
            index=random.nextInt(5);
            while(index==temp){
                index =random.nextInt(5);
            }

            colorChange();
        }
    }

    public void colorChange(){
        counter.setTextColor(color[index]);
        temp=index;
        change++;
    }

    public void restart(View v){
        i=0;change=1;
        counter.setText(i +"");
    }}
