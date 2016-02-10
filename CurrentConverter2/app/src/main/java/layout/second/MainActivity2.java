package layout.second;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.fernando.currentconverter2.R;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
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

        String[] currents = getResources().getStringArray(R.array.CurrentsArray);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, currents);
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final Spinner fromSpinner = (Spinner) findViewById(R.id.fromSpinner);
        fromSpinner.setAdapter(myAdapter);
        final Spinner toSpinner = (Spinner) findViewById(R.id.toSpinner);
        toSpinner.setAdapter(myAdapter);

        Button returnButton = (Button) findViewById(R.id.returnButton);


        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("fromCurrency", fromSpinner.getSelectedItem().toString());
                intent.putExtra("toCurrency", toSpinner.getSelectedItem().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }

}
