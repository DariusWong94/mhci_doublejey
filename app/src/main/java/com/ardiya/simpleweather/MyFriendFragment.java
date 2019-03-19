package com.ardiya.simpleweather;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyFriendFragment extends Fragment {


    public MyFriendFragment() {
        // Required empty public constructor
    }

    String[] listviewTitle = new String[]{
            "Sam Sea",
            "Lee yang",
            "Tessa Foo",
            "Terrance Lam",
            "Yi Han",
            "Jun Hong"
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_friend, container, false);



        int[] listviewImage = new int[]{
                R.drawable.zzz_person_box,
                R.drawable.zzz_person_box,
                R.drawable.zzz_person_box,
                R.drawable.zzz_person_box,
                R.drawable.zzz_person_box,
                R.drawable.zzz_person_box
        };

        String[] listviewShortDescription = new String[]{
                "Hi, how are you?",
                "I like banana",
                "I like apples",
                "Hey there",
                "Whats up?",
                "I like peach",
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

        androidListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getContext(), "Hello " +listviewTitle[position], Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getContext() , ChatActivity.class);
                intent.putExtra("names" , listviewTitle[position]);
                startActivity(intent);
            }
        });

        return view;
    }

}
