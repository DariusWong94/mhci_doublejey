package com.ardiya.simpleweather;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.github.anastr.speedviewlib.SpeedView;



public class SpeedometerFragment extends Fragment {

    SpeedView speedView;

    ImageButton bt;
    ImageButton bt2;
    private static final int PHONE_CALL_REQUEST=0;

    public SpeedometerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_speedometer, container, false);
        bt = (ImageButton) view.findViewById(R.id.horn);
        bt2 =(ImageButton) view.findViewById(R.id.emergency);
        final MediaPlayer mp = MediaPlayer.create(this.getContext(), R.raw.bike);

        bt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mp.start();
            }
        });


        SpeedView speedometer = (SpeedView) view.findViewById(R.id.speedView);
        // move to 50 Km/s
        speedometer.speedTo(25, 4000);
//        speedometer.setWithTremble(true);
        speedometer.setMinSpeed(0);
        speedometer.setMaxSpeed(25);
        speedometer.setTickNumber(5);
        return view;

    }

    public void btEmemergency_onClick(View view){
        String number ="12345678";
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setData(Uri.parse("tel:" + number));
        if(ContextCompat.checkSelfPermission(this.getContext(), android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this.getActivity(), new String[]{android.Manifest.permission.CALL_PHONE},PHONE_CALL_REQUEST);
        }
        else
        {
            Intent intent2 = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "+1234567"));
            startActivity(intent2);
        }
        }
    }


