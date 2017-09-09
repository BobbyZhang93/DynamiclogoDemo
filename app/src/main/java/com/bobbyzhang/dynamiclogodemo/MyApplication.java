package com.bobbyzhang.dynamiclogodemo;

import android.app.Application;

import org.xutils.x;

/**
 * Created by BobbyZhang on 2017/9/9.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
    }
}
