package com.hejian.android.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hejian.android.R;
import com.hejian.android.ui.AppManager;

import butterknife.InjectView;

public class SplashActivity extends BaseActivity {


    @InjectView(R.id.iv_welcome_icon)
    ImageView ivWelcomeIcon;
    @InjectView(R.id.splash_tv_version)
    TextView splashTvVersion;
    @InjectView(R.id.activity_splash)
    RelativeLayout activitySplash;

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        AppManager.getInstance().AddActivity(this);
        //设置版本号
        setVersion();
        //设置动画
        setAinmation();
    }

    private void setAinmation() {
        AlphaAnimation animation = new AlphaAnimation(0, 1);
        animation.setDuration(2000);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                finish();

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        activitySplash.setAnimation(animation);
    }

    private void setVersion() {
        splashTvVersion.setText(getVersion());

    }

    private String getVersion() {
        try {
            //拿到包管理器
            PackageManager packageManager = getPackageManager();
            //拿到包信息
            PackageInfo packageInfo = packageManager.getPackageInfo(getPackageName(), 0);
            //每次发布新版本会加1
            int versionCode = packageInfo.versionCode;
            String versionName = packageInfo.versionName;
            return versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected int getViewById() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getInstance().remove(this);
    }

}
