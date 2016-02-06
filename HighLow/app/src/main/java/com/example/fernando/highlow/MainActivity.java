package com.example.fernando.highlow;

import android.os.Bundle;
import android.os.Debug;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
public class MainActivity extends AppCompatActivity {

    int tries = 1;
    Random randomGenerator = new Random ();
    int randomNumber = randomGenerator.nextInt(21);


    public  void  clickFunction (View view) {
        EditText numberEntered = (EditText) findViewById(R.id.editText);
        TextView numberTries = (TextView) findViewById(R.id.resultText);

        if (numberEntered.length() == 0) {
            Toast.makeText(getApplicationContext(), "Ingresa un valor.", Toast.LENGTH_LONG).show();
        }

        else {
            int number = Integer.parseInt(numberEntered.getText().toString());

            if (number < randomNumber) {
                numberTries.setText("El número que ingresaste es menor.");
                numberEntered.setText("");
                tries++;
            }
            else if (number > randomNumber) {
                numberTries.setText("El número que ingresaste es mayor.");
                numberEntered.setText("");
                tries++;
            }
            else if (number == randomNumber) {
                numberTries.setText("Adivinaste en " + tries + " intentos.\n ¿Crees poder lograrlo en menos? Vuelve a intentarlo");
                numberEntered.setText("");
                tries = 1;
                randomNumber = randomGenerator.nextInt(21);
            }
        }

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
