package com.ardiya.simpleweather;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.R.*;


/**
 * A simple {@link Fragment} subclass.
 */

public class AddfriendFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_addfriend, container, false);
        String[] listviewTitle = new String[]{
                "Halimin Hasim",
                "Ming Loon",
                "Jia Le",
                "Siew Mun",
                "Dom Tiang",
                "Darius Wong"
        };


        int[] listviewImage = new int[]{
                R.drawable.zzz_person_box,
                R.drawable.zzz_person_box,
                R.drawable.zzz_person_box,
                R.drawable.zzz_person_box,
                R.drawable.zzz_person_box,
                R.drawable.zzz_person_box
        };



        List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();

        for (int i = 0; i < listviewTitle.length ; i++) {
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("listview_title", listviewTitle[i]);
            hm.put("listview_image", Integer.toString(listviewImage[i]));
            aList.add(hm);
        }

        String[] from = {"listview_image", "listview_title"};
        int[] to = {R.id.listview_image, R.id.listview_item_title};

        SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(), aList, R.layout.listview_addfriends, from, to);
        ListView androidListView = (ListView) view.findViewById(R.id.listview);
        androidListView.setAdapter(simpleAdapter);


        return view;
    }


}