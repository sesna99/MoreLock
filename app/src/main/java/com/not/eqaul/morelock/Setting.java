package com.not.eqaul.morelock;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.not.equal.morelock.R;

/**
 * Created by admin on 2016-06-07.
 */
public class Setting extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);

        getSupportActionBar().setTitle("설 정");
    }
}
