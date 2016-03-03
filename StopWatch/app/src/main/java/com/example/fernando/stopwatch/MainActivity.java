package com.example.fernando.stopwatch;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    boolean startClicked = true;
    boolean stopClicked = true;

    private Button startButton;
    private Button pauseButton;

    private ListView listTimes;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> arrayList;

    private TextView timerValue;
    private long startTime = 0L;
    private Handler customHandler = new Handler();

    String timeFormat;

    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedTime = 0L;


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

        timerValue = (TextView) findViewById(R.id.chronometerTextView);

        listTimes = (ListView) findViewById(R.id.listTimes);
        arrayList = new ArrayList<String>();

        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList);
        listTimes.setAdapter(adapter);

        startButton = (Button) findViewById(R.id.startButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startTime = SystemClock.uptimeMillis();
                customHandler.postDelayed(updateTimerThread, 0);
                startClicked = !startClicked;

                if (startClicked) {
                    arrayList.add(timeFormat);
                    adapter.notifyDataSetChanged();
                    startButton.setText("Start");
                    timerValue.setText("00:00:00.000");
                    timeSwapBuff = 0;
                    customHandler.removeCallbacks(updateTimerThread);
                }

                else {
                    startButton.setText("Restart");
                }

            }
        });

        pauseButton = (Button) findViewById(R.id.stopButton);
        pauseButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                stopClicked = !stopClicked;

                if (stopClicked) {
                    pauseButton.setText("Stop");
                    startTime = SystemClock.uptimeMillis();
                    customHandler.postDelayed(updateTimerThread, 0);
                }

                else {
                    timeSwapBuff += timeInMilliseconds;
                    customHandler.removeCallbacks(updateTimerThread);
                    pauseButton.setText("Resume");
                }
            }

        });

    }

    private Runnable updateTimerThread = new Runnable() {
        public void run() {
            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
            updatedTime = timeSwapBuff + timeInMilliseconds;

            int secs = (int) (updatedTime / 1000);
            int mins = secs / 60;
            int hours = mins / 60;
            secs = secs % 60;
            mins = mins % 60;
            int milliseconds = (int) (updatedTime % 1000);

            timeFormat = String.format("%02d", hours) + ":" + String.format("%02d", mins) + ":" + String.format("%02d", secs) + "." + String.format("%03d", milliseconds);
            timerValue.setText(timeFormat);

            customHandler.postDelayed(this, 0);

        }
    };

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
