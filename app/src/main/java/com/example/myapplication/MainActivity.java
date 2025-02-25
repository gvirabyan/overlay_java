package com.example.myapplication;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final int OVERLAY_PERMISSION_REQUEST_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);

        Button blockButton = findViewById(R.id.blockButton);

        if (!Settings.canDrawOverlays(MainActivity.this)) {
            requestOverlayPermission();
        } else {
            startOverlayService();
        }
    }

    private void requestOverlayPermission() {
        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                Uri.parse("package:" + getPackageName()));
        startActivityForResult(intent, OVERLAY_PERMISSION_REQUEST_CODE);
        Toast.makeText(this, "Please enable overlay permission", Toast.LENGTH_LONG).show();
    }
    public void minimizeApp() {
        moveTaskToBack(true);
    }

    private void startOverlayService() {
        Intent serviceIntent = new Intent(this, OverlayService.class);
        startService(serviceIntent);
        Log.d("MainActivity", "Overlay Service Started.");
        Toast.makeText(this, "Overlay Started", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(this::minimizeApp, 2000);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == OVERLAY_PERMISSION_REQUEST_CODE) {
            if (Settings.canDrawOverlays(this)) {
                startOverlayService();
            } else {
                Toast.makeText(this, "Overlay Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
