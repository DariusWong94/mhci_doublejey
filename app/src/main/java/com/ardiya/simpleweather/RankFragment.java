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
                "Dom Tiang", "Halimin", "Tan Jun He", "Yi Han", "Jovi Yeo","Gerry Kor",
                "Rapheal Chua", "Darius Wong", "Toh Yu Heng", "Ethan Chin"
        };


        int[] listviewImage = new int[]{
                R.drawable.rankusers, R.drawable.rankusers, R.drawable.rankusers, R.drawable.rankusers, R.drawable.rankusers,
                R.drawable.rankusers, R.drawable.rankusers, R.drawable.rankusers, R.drawable.rankusers, R.drawable.rankusers
        };

        int[] listviewIcon = new int[]{
                R.drawable.zzz_trophy_variant_outline, R.drawable.zzz_trophy_variant_outline, R.drawable.zzz_trophy_variant_outline, R.drawable.zzz_star, R.drawable.zzz_star,
                R.drawable.zzz_star, R.drawable.zzz_star, R.drawable.zzz_star, R.drawable.zzz_star, R.drawable.zzz_star
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
            hm.put("listview_icon", Integer.toString(listviewIcon[i]));
            aList.add(hm);
        }

        String[] from = {"listview_image", "listview_title", "listview_discription", "listview_icon" };
        int[] to = {R.id.listview_image, R.id.listview_item_title, R.id.listview_item_short_description, R.id.listview_icon};

        SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(), aList, R.layout.listview_activity, from, to);
        ListView androidListView = (ListView) view.findViewById(R.id.listview);
        androidListView.setAdapter(simpleAdapter);

        return view;
    }

}
