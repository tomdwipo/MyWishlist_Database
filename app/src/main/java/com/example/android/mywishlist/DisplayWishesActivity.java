package com.example.android.mywishlist;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import data.DatabaseHandler;
import model.MyWish;

public class DisplayWishesActivity extends AppCompatActivity {
    private DatabaseHandler dba;
    private ArrayList<MyWish> dbWish = new ArrayList<>();
    private WishAdapter wishAdapter;
    private ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_wishes);
        listview = (ListView)findViewById(R.id.list);
        refreshData();
    }

    private void refreshData() {
        dbWish.clear();
        dba = new DatabaseHandler(getApplicationContext());
        ArrayList<MyWish> wishesFromDB = dba.getWishes();
        for (int i = 0 ; i<wishesFromDB.size(); i++){
            String title = wishesFromDB.get(i).getTitle();
            String dateText = wishesFromDB.get(i).getRecordDate();
            String content = wishesFromDB.get(i).getContent();

            MyWish myWish = new MyWish();
            myWish.setTitle(title);
            myWish.setRecordDate(dateText);
            myWish.setContent(content);
            dbWish.add(myWish);

        }
        dba.close();

        wishAdapter = new WishAdapter(DisplayWishesActivity.this,R.layout.wish_row, dbWish);
        listview.setAdapter(wishAdapter);
        wishAdapter.notifyDataSetChanged();
////
    }

    public class WishAdapter extends ArrayAdapter<MyWish>{
        Activity activity;
        int layoutResource;
        MyWish wish;
        ArrayList<MyWish> mData = new ArrayList<>();

        public WishAdapter(Activity act, int resource, ArrayList<MyWish> data) {
            super(act, resource, data);
            activity = act;
            layoutResource = resource;
            mData = data;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public MyWish getItem(int position) {
            return mData.get(position);
        }

        @Override
        public int getPosition(MyWish item) {
            return super.getPosition(item);
        }

        @Override
        public long getItemId(int position) {
            return super.getItemId(position);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            ViewHolder holder = null;

            if (row == null || (row.getTag())== null){
                LayoutInflater inflater = LayoutInflater.from(activity);
                row = inflater.inflate(layoutResource, null);
                holder = new ViewHolder();
                holder.mTitle = (TextView)row.findViewById(R.id.name);
                holder.mDate = (TextView)row.findViewById(R.id.dateText);

                row.setTag(holder);

            }else{
                holder = (ViewHolder)row.getTag();
            }

            holder.myWish = getItem(position);
            holder.mDate.setText(holder.myWish.getRecordDate());
            holder.mTitle.setText(holder.myWish.getTitle());

            return row;

        }

        class ViewHolder{
            MyWish myWish;
            TextView mTitle;
            TextView mId;
            TextView mCountent;
            TextView mDate;


        }


















    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_wishes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
