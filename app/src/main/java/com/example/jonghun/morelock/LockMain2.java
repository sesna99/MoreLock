package com.example.jonghun.morelock;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * Created by admin on 2016-05-30.
 */
public class LockMain2 extends AppCompatActivity {
    private ViewPager mViewPager;
    private PageAdapter2 adapter;
    private ViewPager.OnPageChangeListener onPageChangeListener;
    public static int gif[] = {R.drawable.gif_return, R.drawable.gif_star, R.drawable.gif_toggle, R.drawable.gif_return, R.drawable.gif_tree, R.drawable.gif_return, R.drawable.gif_return};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lock_main);

        PreferencesManager.getInstance(this).setCurType(0);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        adapter = new PageAdapter2(getSupportFragmentManager(), 1, this);
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(onPageChangeListener);
    }
}

class PageAdapter2 extends FragmentStatePagerAdapter {
    private int tabSize;
    private Context context;
    private ArrayList<LockFragment> fragments;

    public PageAdapter2(FragmentManager fm, int tabSize, Context context) {
        super(fm);
        this.tabSize = tabSize;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        if(fragments == null){
            fragments = new ArrayList<>();
            createFragment(0);
        }
        return fragments.get(position);
    }

    public void createFragment(int type){
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        LockFragment lockFragment = new LockFragment();
        lockFragment.setArguments(bundle);
        fragments.add(lockFragment);
    }

    @Override
    public int getCount() {
        return tabSize;
    }
}
