package com.hejian.android.activity;

import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.hejian.android.R;
import com.hejian.android.fragment.HomeFragment;
import com.hejian.android.fragment.InvenstFragment;
import com.hejian.android.fragment.MoreFragment;
import com.hejian.android.fragment.PropertyFragment;
import com.hejian.android.ui.AppManager;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.InjectView;

public class MainActivity extends BaseActivity {


    @InjectView(R.id.fl_main_content)
    FrameLayout flMainContent;
    @InjectView(R.id.main_rg)
    RadioGroup mainRg;
    private HomeFragment homeFragment;
    private InvenstFragment invenstFragment;
    private MoreFragment moreFragment;
    private PropertyFragment propertyFragment;
    private boolean isDouble =false;

    @Override
    protected void initListener() {
        mainRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switchFragement(checkedId);
            }
        });
        switchFragement(R.id.rb_main);

    }

    private void switchFragement(int checkedId) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        HiddenAllFragment(ft);
        switch (checkedId){
            case R.id.rb_main:
                if(homeFragment==null){
                    homeFragment = new HomeFragment();
                    ft.add(R.id.fl_main_content,homeFragment);
                }
                ft.show(homeFragment);
                break;
            case R.id.rb_invest:
                if(invenstFragment==null){
                    invenstFragment = new InvenstFragment();
                    ft.add(R.id.fl_main_content,invenstFragment);
                }
                ft.show(invenstFragment);
                break;
            case R.id.rb_more:
                if(moreFragment==null){
                    moreFragment = new MoreFragment();
                    ft.add(R.id.fl_main_content,moreFragment);
                }
                ft.show(moreFragment);
                break;
            case R.id.rb_propert:
                if(propertyFragment==null){
                    propertyFragment = new PropertyFragment();
                    ft.add(R.id.fl_main_content,propertyFragment);
                }
                ft.show(propertyFragment);
                break;
        }
        ft.commit();

    }

    private void HiddenAllFragment(FragmentTransaction ft) {
        if(homeFragment!=null){
            ft.hide(homeFragment);
        }
        if(invenstFragment!=null){
            ft.hide(invenstFragment);
        }
        if(moreFragment!=null){
            ft.hide(moreFragment);
        }
        if(propertyFragment!=null){
            ft.hide(propertyFragment);
        }
    }

    @Override
    protected void initData() {
        AppManager.getInstance().AddActivity(this);
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected int getViewById() {
        //隐藏顶部的状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return R.layout.activity_main;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            if(isDouble){
                finish();
            }
            isDouble =true;
            Toast.makeText(MainActivity.this, "再次点击退出", Toast.LENGTH_SHORT).show();
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    isDouble =false;
                }
            },2000);
            return true;
        }
        return super.onKeyUp(keyCode, event);

    }
}
