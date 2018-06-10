package com.travelfoots.ntitreetravelfoots;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.travelfoots.ntitreetravelfoots.domain.Pinpoint;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class PinpointListActivity extends AppCompatActivity {
    ListView listView;
    Pinpoint pinpoint;
    myAdapter adapter;
    List<Pinpoint> pinpointArrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pinpoint_list);

        listView = (ListView)findViewById(R.id.ListView);
        adapter = new myAdapter();
        listView.setAdapter(adapter);

    }
    class myAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return pinpointArrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return pinpointArrayList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView view = new TextView(getApplicationContext());
            view.setText(pinpointArrayList.get(position).getNo());
            view.setTextSize(50.0f);
            view.setTextColor(Color.BLUE);
            return convertView;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
