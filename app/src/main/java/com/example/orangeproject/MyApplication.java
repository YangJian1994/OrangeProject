package com.example.orangeproject;

import android.app.Application;
import android.content.Context;

/**
 * Created by 杨健 on 2017/6/26.
 * function: 全局context类
 */

public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
