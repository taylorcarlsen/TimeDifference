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

                                    tvCalculation.setText(String.valueOf(startSecondsFromMidnight) + " " + String.valueOf(endSecondsFromMidnight));
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



                //String hour = etStart.getText().toString().substring(0,2).replaceFirst("^0+(?!$)", "");
                //String minute = etStart.getText().toString().substring(3,5).replaceFirst("^0+(?!$)", "");
                //String second = etStart.getText().toString().substring(6).replaceFirst("^0+(?!$)", "");


                    /*Date startTime = simpleDateFormat.parse(etStart.getText().toString());
                    Date endTime = simpleDateFormat.parse(etEnd.getText().toString());
                    long difference = startTime.getTime() - endTime.getTime();
                    tvCalculation.setText((int) difference);*/

                    //.replaceFirst("^0+(?!$)", "")
            }
        });
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }
}
