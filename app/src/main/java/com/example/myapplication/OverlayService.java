package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

public class OverlayService extends Service {

    private WindowManager windowManager;
    private View blockView1, blockView2,blockView3,blockView4;
    private static final String TAG = "OverlayService";

    @SuppressLint("RtlHardcoded")
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "OverlayService started");

        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

        blockView1 = new View(this);
        blockView1.setBackgroundColor(Color.TRANSPARENT);

        WindowManager.LayoutParams params1 = new WindowManager.LayoutParams(
                227, 77,
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ?
                        WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY :
                        WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.RGBA_8888
        );

        params1.gravity = Gravity.TOP | Gravity.LEFT;
        params1.x = 1113;
        params1.y = 42;

        blockView1.setOnTouchListener((v, event) -> {
            Log.d(TAG, "Touch blocked in area 1!");
            return true;
        });

        blockView2 = new View(this);
        blockView2.setBackgroundColor(Color.TRANSPARENT);

        WindowManager.LayoutParams params2 = new WindowManager.LayoutParams(
                82, 81,
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ?
                        WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY :
                        WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.RGBA_8888
        );

        params2.gravity = Gravity.TOP | Gravity.LEFT;
        params2.x = 1463;
        params2.y = 566;

        blockView2.setOnTouchListener((v, event) -> {
            Log.d(TAG, "Touch blocked in area 2!");
            return true;
        });



        blockView3 = new View(this);
        blockView3.setBackgroundColor(Color.TRANSPARENT);

        WindowManager.LayoutParams params3 = new WindowManager.LayoutParams(
                10, 10,
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ?
                        WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY :
                        WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.RGBA_8888
        );

        params3.gravity = Gravity.BOTTOM | Gravity.LEFT;
        params3.x = 1;
        params3.y = 1;

        // New red rectangle in the right top corner
        blockView4 = new View(this);
        blockView4.setBackgroundColor(Color.TRANSPARENT);

        WindowManager.LayoutParams params4 = new WindowManager.LayoutParams(
                10, 10,
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ?
                        WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY :
                        WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.RGBA_8888
        );

        params4.gravity = Gravity.TOP | Gravity.RIGHT;
        params4.x = 1;
        params4.y = 1;

        windowManager.addView(blockView1, params1);
        windowManager.addView(blockView2, params2);
        windowManager.addView(blockView3, params3);
        windowManager.addView(blockView4, params4);


    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (blockView1 != null) {
            windowManager.removeView(blockView1);
        }
        if (blockView2 != null) {
            windowManager.removeView(blockView2);
        }
        if (blockView3 != null) {
            windowManager.removeView(blockView3);
        }
        if (blockView4 != null) {
            windowManager.removeView(blockView4);
        }

    }
}
