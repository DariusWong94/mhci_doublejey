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



        String[] listviewTitle = new String[]{
                "User 1",
                "User 2",
                "User 3",
                "User 4",
                "User 5",
                "User 6"
        };


        int[] listviewImage = new int[]{
                R.drawable.profile,
                R.drawable.profile,
                R.drawable.profile,
                R.drawable.profile,
                R.drawable.profile,
                R.drawable.profile
        };

        String[] listviewShortDescription = new String[]{
                "Hi, how are you?",
                "I like banana",
                "I like apples",
                "Hey there",
                "Whats up?",
                "I like peach",
        };

        View view = inflater.inflate(R.layout.fragment_addfriend, container, false);

        List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();

        for (int i = 0; i < listviewTitle.length ; i++) {
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("listview_title", listviewTitle[i]);
            hm.put("listview_discription", listviewShortDescription[i]);
            hm.put("listview_image", Integer.toString(listviewImage[i]));
            aList.add(hm);
        }

        String[] from = {"listview_image", "listview_title", "listview_discription"};
        int[] to = {R.id.listview_image, R.id.listview_item_title, R.id.listview_item_short_description};

        SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(), aList, R.layout.listview_activity, from, to);
        ListView androidListView = (ListView) view.findViewById(R.id.listview);
        androidListView.setAdapter(simpleAdapter);


        return view;
    }


}