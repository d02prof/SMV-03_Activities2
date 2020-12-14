package com.smv.activities2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity_2 extends AppCompatActivity
{
    Button buttonOpenActivity3;
    EditText text, number;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        setTitle("Activities2 - Activity 2");

        buttonOpenActivity3 = findViewById(R.id.buttonOpenActivity3);
        text = findViewById(R.id.editTextText);
        number = findViewById(R.id.editTextNumber);

        buttonOpenActivity3.setOnClickListener(OpenActivity3_OnClickListener);
    }

    //del za vračanje rezultata v prejšnjo aktivnost

    //v OnCLickListenerju, ki odpre naslednjo aktivnost moramo aktivnost odpreti drugače
        //odpremo Activity tako, da pričakujemo rezultat
        //ker bi lahko odpirali več aktivnosti, moramo vedeti, kateri rezultat bomo dobili nazaj,
        //zato rabimo reguestCode
        // namesto startActivity(intent); uporabimo  startActivityForResult(intent, 1);
    public View.OnClickListener OpenActivity3_OnClickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            //preberemo besedilo iz obeh polj
            String textRead = text.getText().toString();
            int numberRead;
            try
            {
                numberRead = Integer.parseInt(number.getText().toString());
            }
            catch(Exception ex)
            {
                //sicer EditView dovoljuje le številke, a do izjeme pride tudi, če je prazen
                numberRead = -1;
            }
            Intent intent = new Intent(getApplicationContext(), Activity_3.class);
            intent.putExtra("EXTRA_STRING", textRead);
            intent.putExtra("EXTRA_NUMBER", numberRead);
            //startActivity(intent);

            //del, ki se tiče prenosa parametrov
            //odpremo Activity tako, da pričakujemo rezultat
            //ker bi lahko odpirali več aktivnosti, moramo vedeti, kateri rezultat bomo dobili nazaj,
            //zato rabimo reguestCode
            //requestCode uporabimo tisti, ki ga izberemo sami
            startActivityForResult(intent, 1);
        }
    };


    //metoda, ki se izvede, ko se v aktivnost vrne rezultat
    //začnemo tipkati in jo izberemo, ali pa pritisnemo Ctrl+o (override) in jo izberemo
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        //ko smo poklicali Activity3, smo za requestCode posredovali vrednost 1, ki jo dobimo tudi nazaj
        if (requestCode == 1){
            //če je uporabnik uporabil katerega od gumbov za izračun in imamo rezultat
            if (resultCode == RESULT_OK){
                int result = data.getIntExtra("RESULT",0);
                Toast.makeText(this, "The result is " + result, Toast.LENGTH_LONG).show();
            }
            //če smo se vrnili brez rezultata (HW gumb, lahko tudi SW gumb za nazaj)
            //da v Activity2 dobimo puščico za nazaj, je treba v manifest dodati android:parentActivityName
            //če ga nastavimo na MainActivity, s puščico nazaj ne dobimo rezultata aktivnosti, prikaže se prazen Activity
            //če ga nastavimo na samo sebe (.Activity2) pa je obnašanje enako kot pri HW gumbu za nazaj: reultat je RESULT_CANCELED, vpisani podatki se ohranijo
            else if(resultCode == RESULT_CANCELED)
                Toast.makeText(this, "Nothing to display", Toast.LENGTH_SHORT).show();
        }
    }
}