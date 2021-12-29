package com.hamza.counter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView countertextview,total,maxValue;
    int counter = 0;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countertextview = findViewById(R.id.counterTextview);
       total=findViewById(R.id.Total);
       maxValue=findViewById(R.id.Max);

        creatSharedPreferences();
        creatEditor();
      setTotalInTextView();
      setMaxValueInTextView();


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
        editor = sharedPreferences.edit();
    }
   public void setTotalInTextView(){
        int totalInFile = sharedPreferences.getInt("Total",0);
        total.setText(getString(R.string.Total)+totalInFile);
   }
   public void setMaxValueInTextView(){
        int maxInFile = sharedPreferences.getInt("Max",0);
        maxValue.setText(getString(R.string.MaxValue)+maxInFile);

   }
public void inFlatMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        inFlatMenu(menu);
        return super.onCreateOptionsMenu(menu);
    }
public void deletTotal(){
    editor.putInt("Total",0);
    editor.apply();
    setTotalInTextView();
}
public void deletMaxValue(){
    editor.putInt("Max",0);
    editor.apply();
    setMaxValueInTextView();
}
public void selectSelectionFromMenu(MenuItem item){
        switch (item.getItemId()){
            case R.id.deletTotal:
                deletTotal();
                Toast.makeText(getBaseContext(), getString(R.string.TotalDelet), Toast.LENGTH_SHORT).show();break;
            case R.id.deletMax: deletMaxValue();
                Toast.makeText(getBaseContext(), getString(R.string.MaxDelet), Toast.LENGTH_SHORT).show();
        } }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        selectSelectionFromMenu(item);
        return super.onOptionsItemSelected(item);
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
