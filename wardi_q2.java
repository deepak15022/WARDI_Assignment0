package com.example.asus.wardi;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import java.util.*;
import android.os.Bundle;
import android.util.Log;
import android.hardware.SensorEvent;
import android.view.View;
import android.widget.TextView;
import android.view.ViewGroup;
import android.widget.Button;
import android.hardware.SensorEventListener;

/*
    REFRENCES:
    https://code.tutsplus.com/tutorials/android-sensors-in-depth-proximity-and-gyroscope--cms-28084

 */


public class MainActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    private SensorEventListener sensorEventListener;
    private Sensor gyroscope;
    boolean bool = true;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView msg = (TextView) findViewById(R.id.msg);
        Button reset = ( Button ) findViewById(R.id.reset);    
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                msg.setText(R.string.message_to_be_shown);
            }
        });



        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        if(gyroscope == null) {
            msg.setText("Gyroscope sensor is needed..");
        }

        else
        {
            sensorEventListener = new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent sensorEvent)
                {

                    if (sensorEvent.values[1] < -0.6f)
                    {
                        msg.setText(R.string.msg_on_false);
                        bool = false;
                    }

                    else if (sensorEvent.values[1] > 0.6f )
                    {
                        msg.setText(R.string.message_to_be_shown);
                        bool = true;
                    }
                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int i) {

                }
            };

            sensorManager.registerListener(sensorEventListener, gyroscope, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(sensorEventListener);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        sensorManager.registerListener(sensorEventListener, gyroscope, SensorManager.SENSOR_DELAY_NORMAL);

    }
}