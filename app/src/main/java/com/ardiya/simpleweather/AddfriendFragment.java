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
                "ListView Title 1", "ListView Title 2", "ListView Title 3", "ListView Title 4",
                "ListView Title 5", "ListView Title 6", "ListView Title 7", "ListView Title 8",
        };


        int[] listviewImage = new int[]{
                R.drawable.zzz_face_profile, R.drawable.zzz_face_profile, R.drawable.zzz_face_profile, R.drawable.zzz_face_profile,
                R.drawable.zzz_face_profile, R.drawable.zzz_face_profile, R.drawable.zzz_face_profile, R.drawable.zzz_face_profile,
        };

        String[] listviewShortDescription = new String[]{
                "Android ListView Short Description", "Android ListView Short Description", "Android ListView Short Description", "Android ListView Short Description",
                "Android ListView Short Description", "Android ListView Short Description", "Android ListView Short Description", "Android ListView Short Description",
        };

        View view = inflater.inflate(R.layout.fragment_addfriend, container, false);

        List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();

        for (int i = 0; i < 8; i++) {
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

//        ListView listView = (ListView) view.findViewById(R.id.listview);
//
//        String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
//                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
//                "Linux", "OS/2" };
//        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, values);
//
//        listView.setAdapter(adapter);

        return view;
    }


}