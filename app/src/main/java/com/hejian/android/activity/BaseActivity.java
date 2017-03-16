package com.hejian.android.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by 何健 on 2017/3/16.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getViewById());
        ButterKnife.inject(this);
        initTitle();
        initData();
        initListener();
    }

    protected abstract void initListener();


    protected abstract void initData();


    protected abstract void initTitle();


    protected abstract int getViewById();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.reset(this);
    }
}
