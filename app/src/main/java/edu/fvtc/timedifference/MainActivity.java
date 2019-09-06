package edu.fvtc.timedifference;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.String.valueOf;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private static final String TAG = "myDebug";
    Button btnCalculate;
    EditText etStart;
    EditText etEnd;
    TextView tvCalculation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.println(Log.DEBUG, TAG, "The activity_main is loaded.");

        btnCalculate = findViewById(R.id.btnCalculate);
        etStart = findViewById(R.id.etStartText);
        etEnd = findViewById(R.id.etEndText);
        tvCalculation = findViewById(R.id.tvTimeDifference);
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"Calculate clicked...");

                int hour;
                int minute;
                int second;
                int startSecondsFromMidnight;
                int endSecondsFromMidnight;

                //checks start input and makes seconds
                Date formattedStart;
                try {
                    formattedStart = simpleDateFormat.parse(etStart.getText().toString());
                    String stringStart = simpleDateFormat.format(formattedStart);

                    try{
                        hour = Integer.parseInt(stringStart.substring(0,2));
                        minute = Integer.parseInt(stringStart.substring(3,5));
                        second = Integer.parseInt(stringStart.substring(6));

                        startSecondsFromMidnight = (hour * 3600) + (minute * 60) + second;

                        //Checks end input and makes seconds
                        Date formattedEnd;
                        try {
                            formattedEnd = simpleDateFormat.parse(etEnd.getText().toString());
                            String stringEnd = simpleDateFormat.format(formattedEnd);

                            try{
                                hour = Integer.parseInt(stringEnd.substring(0,2));
                                minute = Integer.parseInt(stringEnd.substring(3,5));
                                second = Integer.parseInt(stringEnd.substring(6));

                                endSecondsFromMidnight = (hour * 3600) + (minute * 60) + second;

                                if(endSecondsFromMidnight > startSecondsFromMidnight){

                                    int difference = endSecondsFromMidnight - startSecondsFromMidnight;
                                    int p1 = difference % 60;
                                    int p2 = difference / 60;
                                    int p3 = p2 % 60;
                                    p2 = p2 / 60;
                                    String sP1;
                                    String sP2;
                                    String sP3;

                                    if(p1 < 10) {
                                        sP1 = "0" + String.valueOf(p1);
                                    }else{
                                        sP1 = String.valueOf(p1);
                                    }
                                    if(p2 < 10){
                                        sP2 = "0" + String.valueOf(p2);
                                    }else{
                                        sP2 = String.valueOf(p2);
                                    }
                                    if(p3 < 10){
                                        sP3 = "0" + String.valueOf(p3);
                                    }else{
                                        sP3 = String.valueOf(p3);
                                    }

                                    tvCalculation.setText(valueOf(difference) + " " + "=" + " " + sP2 + ":" + sP3 + ":" + sP1);

                                    Log.d(TAG, "Successful input to user.");
                                }
                                else{
                                    Toast.makeText(MainActivity.this, "End time must be later than start time.", Toast.LENGTH_LONG).show();
                                }


                            }catch(Exception e) {
                                Log.d(TAG,"Could not parse times");
                            }

                        } catch (ParseException e) {
                            Log.d(TAG, "Could not parse start.");
                            Toast.makeText(MainActivity.this, "Please use format 00:00:00 on time start.", Toast.LENGTH_LONG).show();
                        }

                    }catch(Exception e) {
                        Log.d(TAG,"Could not parse times");
                    }

                } catch (ParseException e) {
                    Log.d(TAG, "Could not parse start.");
                    Toast.makeText(MainActivity.this, "Please use format 00:00:00 on time start.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }
}
