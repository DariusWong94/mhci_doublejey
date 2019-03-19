package com.ardiya.simpleweather;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.location.Location;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.MapFragment;

import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.SupportMapFragment;
import com.ardiya.simpleweather.directionhelpers.FetchURL;
import com.ardiya.simpleweather.directionhelpers.TaskLoadedCallback;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class RoutingFragment extends Fragment implements OnMapReadyCallback {
    private GoogleMap mMap;
    private MarkerOptions place1, place2;
    ImageButton bt;
    ImageButton bt2;
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
    CountDownTimer countDownTimer;
    long bestTiming = 0L;
    TextView textView ;
    TextView bestTextView ;
    ImageButton start;
    boolean hasRaceStart = false;
    long MillisecondTime, StartTime, TimeBuff, UpdateTime = 0L ;
    Handler handler;
    int Seconds, Minutes, MilliSeconds ;
    String[] ListElements = new String[] {  };
    List<String> ListElementsArrayList ;
    ArrayAdapter<String> adapter ;
    private Polyline currentPolyline;
    public RoutingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_routing, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(this);


        place1 = new MarkerOptions().position(new LatLng(1.442987, 103.785380)).title("Route 1");
        place2 = new MarkerOptions().position(new LatLng(1.437974, 103.786382)).title("Route 2");
        bestTextView = (TextView)view.findViewById(R.id.besttextview);
        textView = (TextView)view.findViewById(R.id.textView);
        start = (ImageButton)view.findViewById(R.id.startbutton);
        System.out.println("Button Clicked");
        handler = new Handler() ;

        ListElementsArrayList = new ArrayList<String>(Arrays.asList(ListElements));

        adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1,
                ListElementsArrayList
        );
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(hasRaceStart){
                    raceStop();
                }

                else{
                    raceStart();
                }
//                if(StartTime != 0){
//                    System.out.println("++++++++++++++++++++++++++++++ Time Started +++++++++++++++++++++++++++++++++++++");
//                    start.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            handler.removeCallbacks(runnable);
//                            adapter.notifyDataSetChanged();
//                            bestTextView.setText("" + Minutes + ":"
//                                    + String.format("%02d", Seconds) + ":"
//                                    + String.format("%03d", MilliSeconds));
//                            MillisecondTime = 0L ;
//                            StartTime = 0L ;
//                            TimeBuff = 0L ;
//                            UpdateTime = 0L ;
//                            Seconds = 0 ;
//                            Minutes = 0 ;
//                            MilliSeconds = 0 ;
//                            if(StartTime == 0){
//                                textView.setText("00:00:00");
//                                start.setImageResource(R.drawable.start);
//                            }
//                        }
//                    });
//                }
//                else{
//                    StartTime = SystemClock.uptimeMillis();
//                    handler.postDelayed(runnable, 0);
//                    //reset.setEnabled(false);
//                    start.setImageResource(R.drawable.stop);
//                }
            }
        });


        bt = (ImageButton) view.findViewById(R.id.horn);
        bt2 =(ImageButton) view.findViewById(R.id.emergency);
        final MediaPlayer mp = MediaPlayer.create(this.getContext(), R.raw.bike);

        bt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mp.start();
            }
        });

        bt2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                final AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
                alertDialog.setTitle("Alert");
                alertDialog.setMessage("Are you sure you want to call an emergency number");
                alertDialog.setCanceledOnTouchOutside(false);
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                alertDialog.dismiss();
                                StopCounter();
                            }
                        });
                alertDialog.show();

                countDownTimer = new CountDownTimer(5000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        alertDialog.setMessage("Are you sure you want to call an emergency number " + "\n" + "00:"+ (millisUntilFinished/1000));

                    }

                    @Override
                    public void onFinish() {
                        int permissionCheck = ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.CALL_PHONE);
                        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.CALL_PHONE}, MY_PERMISSIONS_REQUEST_CALL_PHONE);
                        } else {
                            Intent intent = new Intent(Intent.ACTION_CALL);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.setData(Uri.parse("tel:" + "+6591134862"));
                            startActivity(intent);
                        }
                        alertDialog.dismiss();
                    }
                }.start();

            }
        });
    }
    public Runnable runnable = new Runnable() {

        public void run() {
            MillisecondTime = SystemClock.uptimeMillis() - StartTime;
            UpdateTime = TimeBuff + MillisecondTime;
            Seconds = (int) (UpdateTime / 1000);
            Minutes = Seconds / 60;
            Seconds = Seconds % 60;
            MilliSeconds = (int) (UpdateTime % 1000);
            textView.setText("" + Minutes + ":"
                    + String.format("%02d", Seconds) + ":"
                    + String.format("%03d", MilliSeconds));

            handler.postDelayed(this, 0);
        }

    };
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Log.d("mylog", "Added Markers");
        LatLng latLng = new LatLng(1.442987, 103.785380);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.setMinZoomPreference(16.0f);
        mMap.setMaxZoomPreference(25.0f);
        mMap.addMarker(place1);
        mMap.addMarker(place2);
    }

    private void raceStart() {
        StartTime = SystemClock.uptimeMillis();
        handler.postDelayed(runnable, 0);
//        reset.setEnabled(false);
        hasRaceStart = true;
        start.setImageResource(R.drawable.stop);

    }

    private void raceStop() {
        handler.removeCallbacks(runnable);
        adapter.notifyDataSetChanged();
        System.out.println(bestTiming + ", " + MillisecondTime);

        if(bestTiming == 0L){
            bestTiming = MillisecondTime;
            bestTextView.setText("Best: " + Minutes + ":"
                    + String.format("%02d", Seconds) + ":"
                    + String.format("%03d", MilliSeconds));
        }
        if(bestTiming > MillisecondTime){
            bestTiming = MillisecondTime;
            System.out.println(bestTiming);
            bestTextView.setText("Best: " + Minutes + ":"
                    + String.format("%02d", Seconds) + ":"
                    + String.format("%03d", MilliSeconds));
        }

        MillisecondTime = 0L ;
        StartTime = 0L ;
        TimeBuff = 0L ;
        UpdateTime = 0L ;
        Seconds = 0 ;
        Minutes = 0 ;
        MilliSeconds = 0 ;
        if(StartTime == 0){
            textView.setText("00:00:00");
            start.setImageResource(R.drawable.start);
        }
        hasRaceStart = false;
    }

    public void StopCounter(){

        countDownTimer.cancel();
    }
}
