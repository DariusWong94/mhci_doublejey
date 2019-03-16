package com.ardiya.simpleweather;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class RankFragment extends Fragment {


    public RankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rank, container, false);

        String[] listviewTitle = new String[]{
                "ListView Title 1", "ListView Title 2", "ListView Title 3", "ListView Title 4", "ListView Title 10",
                "ListView Title 5", "ListView Title 6", "ListView Title 7", "ListView Title 8", "ListView Title 9"
        };


        int[] listviewImage = new int[]{
                R.drawable.rankusers, R.drawable.rankusers, R.drawable.rankusers, R.drawable.rankusers, R.drawable.rankusers,
                R.drawable.rankusers, R.drawable.rankusers, R.drawable.rankusers, R.drawable.rankusers, R.drawable.rankusers
        };

        String[] listviewShortDescription = new String[]{
                "Android ListView Short Description", "Android ListView Short Description", "Android ListView Short Description", "Android ListView Short Description", "Android ListView Short Description",
                "Android ListView Short Description", "Android ListView Short Description", "Android ListView Short Description", "Android ListView Short Description", "Android ListView Short Description",
        };



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
