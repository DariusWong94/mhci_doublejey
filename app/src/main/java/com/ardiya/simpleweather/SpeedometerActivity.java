package com.ardiya.simpleweather;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.github.anastr.speedviewlib.SpeedView;

public class SpeedometerActivity extends AppCompatActivity {
    SpeedView speedView;

    Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speedometer);

        bt = (Button)findViewById(R.id.horn);
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.bike);
        bt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mp.start();
            }
        });

        SpeedView speedometer = (SpeedView) findViewById(R.id.speedView);
        // move to 50 Km/s
        speedometer.speedTo(25, 4000);
//        speedometer.setWithTremble(true);
        speedometer.setMinSpeed(0);
        speedometer.setMaxSpeed(25);
        speedometer.setTickNumber(5);

//        speedometer.setTrembleData(trembleDegree,trembleDuration);

    }
}
