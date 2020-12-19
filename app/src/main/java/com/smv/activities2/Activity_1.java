package com.smv.activities2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity_1 extends AppCompatActivity
{
    private Button buttonOpenActivity2;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);
        setTitle("Activities2 - Activity 1");
        buttonOpenActivity2 = findViewById(R.id.buttonOpenActivity2);
        buttonOpenActivity2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Activity_1.this, Activity_2.class);
                startActivity(intent);
            }
        });
    }
}