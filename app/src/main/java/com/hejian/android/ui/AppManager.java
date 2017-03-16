package com.hejian.android.ui;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by 何健 on 2017/3/16.
 */

public class AppManager {
    /**
     * 统一应用程序中所有的Activity的栈管理（单例）
     * 涉及到activity的添加、删除指定、删除当前、删除所有、返回栈大小的方法
     */
    private AppManager(){}
    private static AppManager appManager = new AppManager();
    public static AppManager getInstance(){
        return appManager;
    }

    private Stack<Activity> stack = new Stack<>();

    public void AddActivity(Activity activity){
        if(activity!=null){
            stack.add(activity);
        }
    }

    public void removeAll(){
        for(int i = stack.size()-1; i >0 ; i--) {
            Activity activity = stack.get(i);
            stack.remove(activity);
            activity.finish();

        }
    }

    public void removeCurrentActivity(){
        //Activity activity = stack.get(stack.size() - 1);
        Activity currentActivity = stack.lastElement();
        stack.remove(currentActivity);
        currentActivity.finish();

    }

    public int getStackSize(){
        return stack.size();
    }

    //从stack中删除activity
    public void remove(Activity activity){
        if(activity!=null){
            for (int i =stack.size()-1;i>0;i--){
                if(stack.get(i)==activity){
                    stack.remove(activity);
                    //activity.finish();当acitivy退出时候
                }
            }
        }
    }
    }
