package com.ardiya.simpleweather.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.ardiya.simpleweather.R;
import com.github.library.bubbleview.BubbleTextView;

import java.util.List;

import com.ardiya.simpleweather.Models.ChatModel;

/**
 * Created by reale on 2/28/2017.
 */

public class CustomAdapter extends BaseAdapter {

    private List<ChatModel> list_chat_models;
    private Context context;
    private LayoutInflater layoutInflater;

    public CustomAdapter(List<ChatModel> list_chat_models, Context context) {
        this.list_chat_models = list_chat_models;
        this.context = context;
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list_chat_models.size();
    }

    @Override
    public Object getItem(int position) {
        return list_chat_models.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null)
        {
            if(list_chat_models.get(position).isSend)
                view = layoutInflater.inflate(R.layout.item_message_sent,null);
            else
                view = layoutInflater.inflate(R.layout.item_message_received,null);

            BubbleTextView text_message = (BubbleTextView)view.findViewById(R.id.text_message);
            text_message.setText(list_chat_models.get(position).message);

        }
        return view;
    }
}
