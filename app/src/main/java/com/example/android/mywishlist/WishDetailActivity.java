package com.example.android.mywishlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class WishDetailActivity extends AppCompatActivity {
    private TextView title, context, date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_detail);

        title = (TextView)findViewById(R.id.titleWishSave);
        date = (TextView)findViewById(R.id.dateWish);
        context = (TextView)findViewById(R.id.myWishesSave);

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            title.setText(extras.getString("title"));
            date.setText("Created: "+ extras.getString("date"));
            context.setText(" \" " +extras.getString("context") +" \" ");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_wish_detail, menu);
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
