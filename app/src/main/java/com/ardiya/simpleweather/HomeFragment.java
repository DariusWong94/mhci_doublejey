package com.ardiya.simpleweather;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

public class HomeFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }
    ImageButton bt;
    ImageButton bt2;
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
    CountDownTimer countDownTimer;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Create an instance of the tab layout from the view.
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.home_tab_layout);
        // Set the tabs to fill the entire layout.
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
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

        // Using PagerAdapter to manage page views in fragments.
        // Each page is represented by its own fragment.
        // This is another example of the adapter pattern.
        DotsIndicator dotsIndicator = (DotsIndicator)view.findViewById(R.id.dots_indicator);
        final ViewPager viewPager = (ViewPager) view.findViewById(R.id.home_pager);
        final MpagerAdapter adapter = new MpagerAdapter
                (getFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        dotsIndicator.setViewPager(viewPager);
        // Setting a listener for clicks.

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        return view;
    }
    private boolean hasPermission() {
        if (ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return false;
        }
        return true;
    }

    public void StopCounter(){

        countDownTimer.cancel();
    }
}
