package com.fdu.socialapp.activities;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toolbar;

import com.fdu.socialapp.R;
import com.fdu.socialapp.custom.CognitoSyncClientManager;
import com.fdu.socialapp.custom.MyPagerAdapter;
import com.fdu.socialapp.custom.PagerSlidingTabStrip;
import com.fdu.socialapp.devauth.client.AmazonSharedPreferencesWrapper;

public class Main extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getIntent();
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.id_toolbar);
        setActionBar(toolbar);

        //Set the sliding pages
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        MyPagerAdapter adapter = new MyPagerAdapter(getFragmentManager());
        pager.setAdapter(adapter);
        pager.addOnPageChangeListener(adapter);
        tabs.setViewPager(pager);
        tabs.setSelectedTextColor(getResources().getColor(R.color.myPrimaryDark));
        tabs.setTextColor(getResources().getColor(R.color.TextColorDark));
        tabs.setBackgroundColor(getResources().getColor(R.color.white));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

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
    public void userInfo(View view){

    }

    public void setting(View view){

    }

    public void logOut(View view){
        // wipe data
        CognitoSyncClientManager.getInstance()
                .wipeData();
        CognitoSyncClientManager.credentialsProvider = null;
        // Wipe shared preferences
        AmazonSharedPreferencesWrapper.wipe(PreferenceManager
                .getDefaultSharedPreferences(Main.this));
        Log.i("shit", "退出1");
        finish();
    }
}
