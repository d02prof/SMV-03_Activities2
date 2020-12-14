package com.smv.activities2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_3 extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        setTitle("Activities2 - Activity 3");

        //preberemo Intent
        Intent intent = getIntent();

        //če želimo, lahko preverimo, ali je kaj v parametrih
        if(intent.hasExtra("EXTRA_STRING"))
        {
            //preberemo parameter
            String textRead = intent.getStringExtra("EXTRA_STRING");
            //komponente iz xml so lahko povezane z lokalnimi spremenljivkami
            TextView text = findViewById(R.id.textVievText);
            text.setText(textRead);
        }

        //če želimo, lahko preverimo, ali je kaj v parametrih
        //if(intent.hasExtra("EXTRA_NUMBER"))
        {
            //preberemo parameter
            int numberRead = intent.getIntExtra("EXTRA_NUMBER",0);
            //komponente iz xml so lahko povezane z lokalnimi spremenljivkami
            TextView number = findViewById(R.id.textViewNumber);
            number.setText("" + numberRead);
        }

        //del za vračanje rezultata v prejšnji aktivnost
        Button buttonAdd, buttonSubtract;
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonSubtract = findViewById(R.id.buttonSubtract);
        buttonAdd.setOnClickListener(BothButtons_Click);
        buttonSubtract.setOnClickListener(BothButtons_Click);

    }

    //del za vračanje rezultata v prejšnji aktivnost
    public View.OnClickListener BothButtons_Click = new View.OnClickListener()
    {
        @Override
        public void onClick(View v) {
            TextView number = findViewById(R.id.textViewNumber);
            int no1 = Integer.parseInt(number.getText().toString());
            int result = 0;
            //preverimo, kateri gumb je bil kliknjen (v C# bi uporabili sender)
            if (v == findViewById(R.id.buttonAdd)) result = no1 + 1;
            else if(v == findViewById(R.id.buttonSubtract)) result = no1 - 1;
            Toast.makeText(Activity_3.this, "Result = " + result, Toast.LENGTH_SHORT).show();

            Intent resultIntent = new Intent();
            resultIntent.putExtra("RESULT", result);
            setResult(RESULT_OK, resultIntent);

            //zapremo (uničimo) aktivnost
            finish();
        }
    };

}
