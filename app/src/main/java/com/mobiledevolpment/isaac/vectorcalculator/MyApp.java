package com.mobiledevolpment.isaac.vectorcalculator;

import android.app.Application;
import android.os.SystemClock;

/**
 * Created by Isaac on 3/11/2018.
 */

public class MyApp extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        SystemClock.sleep(3000);
    }
}
