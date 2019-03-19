package com.ardiya.simpleweather;

import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;


import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import com.ardiya.simpleweather.Adapter.CustomAdapter;
import com.ardiya.simpleweather.Helper.HttpDataHandler;
import com.ardiya.simpleweather.Models.ChatModel;
import com.ardiya.simpleweather.Models.SimsimiModel;

public class ChatActivity extends AppCompatActivity {
    ListView listView;
    EditText editText;
    List<com.ardiya.simpleweather.Models.ChatModel> list_chat = new ArrayList<>();
    FloatingActionButton btn_send_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat1);

        String title = getIntent().getStringExtra("names");
        getSupportActionBar().setTitle(title);

        listView = (ListView)findViewById(R.id.list_of_message);
        editText = (EditText)findViewById(R.id.user_message);
        btn_send_message = (FloatingActionButton)findViewById(R.id.fab);

        btn_send_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();
                com.ardiya.simpleweather.Models.ChatModel model = new com.ardiya.simpleweather.Models.ChatModel(text,true); // user send message
                list_chat.add(model);
                new SimsimiAPI().execute(list_chat);

                //remove user message
                editText.setText("");
            }
        });



    }

    private class SimsimiAPI extends AsyncTask<List<com.ardiya.simpleweather.Models.ChatModel>,Void,String> {
        String stream = null;
        List<com.ardiya.simpleweather.Models.ChatModel> models;
        String text = editText.getText().toString();

        @Override
        protected String doInBackground(List<com.ardiya.simpleweather.Models.ChatModel>... params) {
            String url = String.format("http://sandbox.api.simsimi.com/request.p?key=%s&lc=en&ft=1.0&text=%s",getString(R.string.simsimi_api),text);
            models = params[0];
            com.ardiya.simpleweather.Helper.HttpDataHandler httpDataHandler = new com.ardiya.simpleweather.Helper.HttpDataHandler();
            stream = httpDataHandler.GetHTTPData(url);
            return stream;
        }

        @Override
        protected void onPostExecute(String s) {
            Gson gson = new Gson();
            com.ardiya.simpleweather.Models.SimsimiModel response = gson.fromJson(s, com.ardiya.simpleweather.Models.SimsimiModel.class);

            com.ardiya.simpleweather.Models.ChatModel chatModel = new com.ardiya.simpleweather.Models.ChatModel(response.getResponse(),false); // get response from simsimi
            models.add(chatModel);
            com.ardiya.simpleweather.Adapter.CustomAdapter adapter = new com.ardiya.simpleweather.Adapter.CustomAdapter(models,getApplicationContext());
            listView.setAdapter(adapter);
        }
    }
}