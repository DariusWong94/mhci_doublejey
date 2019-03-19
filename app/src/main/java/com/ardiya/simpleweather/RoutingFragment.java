package com.ardiya.simpleweather;


import android.location.Location;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.MapFragment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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


/**
 * A simple {@link Fragment} subclass.
 */
public class RoutingFragment extends Fragment implements OnMapReadyCallback, TaskLoadedCallback {
    private MapView mapView;
    private GoogleMap mMap;
    private MarkerOptions place1, place2;
    Button getDirection;
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
        getDirection = (Button) view.findViewById(R.id.btnRoute);
        getDirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new FetchURL(view.getContext()).execute(getUrl(place1.getPosition(), place2.getPosition(), "scooting"), "scooting");
            }
        });

        place1 = new MarkerOptions().position(new LatLng(1.442987, 103.785380)).title("Route 1");
        place2 = new MarkerOptions().position(new LatLng(1.437974, 103.786382)).title("Route 2");
//        mapView = (MapView) view.findViewById(R.id.map);
//        mapView.onCreate(savedInstanceState);
//        mapView.onResume();
//        mapView.getMapAsync(this);
    }

    //    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        if (view != null) {
//            ViewGroup parent = (ViewGroup) view.getParent();
//            if (parent != null)
//                parent.removeView(view);
//        }
//        try {
//            view = inflater.inflate(R.layout.fragment_routing, container, false);
//            getDirection = (Button) view.findViewById(R.id.btnRoute);
//            getDirection.setOnClickListener(this);
//
//        }
//        catch (InflateException e) {
//            /* map is already there, just return view as it is */
//        }
//
//        //27.658143,85.3199503
//        //27.667491,85.3208583
//        place1 = new MarkerOptions().position(new LatLng(1.442987, 103.785380)).title("Route 1");
//        place2 = new MarkerOptions().position(new LatLng(1.437974, 103.786382)).title("Route 2");
//        MapFragment mapFragment = (MapFragment) getActivity().getFragmentManager().findFragmentById(R.id.map);
//        mapFragment.getMapAsync(new RoutingFragment());
//        return view;
//
//    }

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

    private String getUrl(LatLng origin, LatLng dest, String directionMode) {
        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
        // Mode
        String mode = "mode=" + directionMode;
        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + mode;
        // Output format
        String output = "json";
        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters + "&key=" + getString(R.string.google_maps_key);
        return url;
    }

    public void onTaskDone(Object... values) {
        if (currentPolyline != null)
            currentPolyline.remove();
        if(currentPolyline == null){
            System.out.println("IS EMPTY----------------------------------------------------------");
        }
        currentPolyline = mMap.addPolyline((PolylineOptions) values[0]);
    }
}
