package com.example.fernando.currentconverter2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final  int REQUEST_CODE = 100;
    private static String fromStr = "";
    private static String toStr = "";
    private static final String PREFS_NAME = "Preferences";

    TextView fromTV;
    TextView toTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        String fromPrefStr = settings.getString("FromPreference", "MXN");
        String toPrefStr = settings.getString("ToPreference", "MXN");

        fromTV = (TextView) findViewById (R.id.fromCurrent);
        toTV = (TextView) findViewById (R.id.toCurrent);

        fromTV.setText(fromPrefStr);
        toTV.setText(toPrefStr);

        fromStr = fromPrefStr;
        toStr = toPrefStr;

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Button settingsButton = (Button) findViewById(R.id.settingsButton);

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), layout.second.MainActivity2.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                fromStr = data.getStringExtra("fromCurrency");
                toStr = data.getStringExtra("toCurrency");

                fromTV.setText(fromStr);
                toTV.setText(toStr);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void clickFunction (View view) {
        EditText numberEntered = (EditText) findViewById(R.id.quantityText);

        if (numberEntered.length() == 0) {
            Toast.makeText(getApplicationContext(), "Ingresa un valor.", Toast.LENGTH_LONG).show();
        }
        else {
            float number = Float.parseFloat(numberEntered.getText().toString());
            float result;

            switch (fromStr) {
                case "MXN":
                    switch (toStr) {
                        case "MXN":
                            result = number * 1;
                            Toast.makeText(getApplicationContext(), Float.toString(result), Toast.LENGTH_LONG).show();
                            break;
                        case "USD":
                            result = (float) (number * 0.053333);
                            Toast.makeText(getApplicationContext(), Float.toString(result), Toast.LENGTH_LONG).show();
                            break;
                        case "EUR":
                            result = (float) (number * .047443);
                            Toast.makeText(getApplicationContext(), Float.toString(result), Toast.LENGTH_LONG).show();
                            break;
                    }
                    break;
                case "USD":
                    switch (toStr) {
                        case "MXN":
                            result = (float) (number * 18.7599);
                            Toast.makeText(getApplicationContext(), Float.toString(result), Toast.LENGTH_LONG).show();
                            break;
                        case "USD":
                            result = (float) (number * 1);
                            Toast.makeText(getApplicationContext(), Float.toString(result), Toast.LENGTH_LONG).show();
                            break;
                        case "EUR":
                            result = (float) (number * .8900);
                            Toast.makeText(getApplicationContext(), Float.toString(result), Toast.LENGTH_LONG).show();
                            break;
                    }
                    break;
                case "EUR":
                    switch (toStr) {
                        case "MXN":
                            result = (float) (number * 21.0777);
                            Toast.makeText(getApplicationContext(), Float.toString(result), Toast.LENGTH_LONG).show();
                            break;
                        case "USD":
                            result = (float) (number * 1.1235);
                            Toast.makeText(getApplicationContext(), Float.toString(result), Toast.LENGTH_LONG).show();
                            break;
                        case "EUR":
                            result = (float) (number * 1);
                            Toast.makeText(getApplicationContext(), Float.toString(result), Toast.LENGTH_LONG).show();
                            break;
                    }
                    break;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onStop () {
        super.onStop();

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();

        //editor.clear();
        editor.putString("FromPreference", fromStr);
        editor.putString("ToPreference", toStr);
        editor.commit();
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
