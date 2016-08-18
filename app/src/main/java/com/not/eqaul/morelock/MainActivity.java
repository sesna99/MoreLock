package com.not.eqaul.morelock;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.fpang.lib.FpangSession;
import com.not.equal.morelock.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ActionBar actionBar;
    private ViewPager mViewPager;
    private PageAdapter adapter;
    private CommonTabLayout tabLayout;
    private ArrayList<CustomTabEntity> tabEntities;
    private int[] selectImg = {R.drawable.select_shop, R.drawable.select_status, R.drawable.select_notice};
    private int[] unselectImg = {R.drawable.unselect_shop, R.drawable.unselect_status, R.drawable.unselect_notice};
    public static MainActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TelephonyManager telManager = (TelephonyManager)this.getSystemService(this.TELEPHONY_SERVICE);
        String phoneNum = telManager.getLine1Number();

        FpangSession.init(MainActivity.this);
        FpangSession.setDebug(true);
        FpangSession.setUserId(phoneNum);
        FpangSession.showAdsyncList(MainActivity.this, "AdSync2");

        actionBar = getSupportActionBar();
        actionBar.setTitle("상 점");

        mViewPager = (ViewPager) findViewById(R.id.container);
        adapter = new PageAdapter(getSupportFragmentManager(), 3);
        mViewPager.setAdapter(adapter);

        tabEntities = new ArrayList<>();
        for(int i = 0; i < selectImg.length; i++)
            tabEntities.add(new TabEntity("", selectImg[i], unselectImg[i]));

        tabLayout = (CommonTabLayout) findViewById(R.id.tabs);
        tabLayout.setTabData(tabEntities);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        actionBar.setTitle("상 점");
                        break;
                    case 1:
                        actionBar.setTitle("프로필");
                        break;
                    case 2:
                        actionBar.setTitle("알 림");
                        break;
                }
                tabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                switch (position){
                    case 0:
                        actionBar.setTitle("상 점");
                        break;
                    case 1:
                        actionBar.setTitle("프로필");
                        break;
                    case 2:
                        actionBar.setTitle("알 림");
                        break;
                }
                mViewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

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
            Intent intent = new Intent(this, Setting.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

class PageAdapter extends FragmentStatePagerAdapter {
    private int tabSize;
    private ShopActivity shop;
    private Notice notice;
    private Profile profile;

    public PageAdapter(FragmentManager fm, int tabSize) {
        super(fm);
        this.tabSize = tabSize;
    }

    @Override
    public Fragment getItem(int position) {
        if(shop == null){
            shop = new ShopActivity();
            notice = new Notice();
            profile = new Profile();
        }
        switch (position){
            case 0:
                return shop;
            case 1:
                return profile;
            case 2:
                return notice;
        }
        return null;
    }

    @Override
    public int getCount() {
        return tabSize;
    }
}

class TabEntity implements CustomTabEntity {
    public String title;
    public int selectedIcon;
    public int unSelectedIcon;

    public TabEntity(String title, int selectedIcon, int unSelectedIcon) {
        this.title = title;
        this.selectedIcon = selectedIcon;
        this.unSelectedIcon = unSelectedIcon;
    }

    @Override
    public String getTabTitle() {
        return title;
    }

    @Override
    public int getTabSelectedIcon() {
        return selectedIcon;
    }

    @Override
    public int getTabUnselectedIcon() {
        return unSelectedIcon;
    }
}
