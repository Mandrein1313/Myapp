package com.example.myapp;

import android.app.Activity;
import android.os.Bundle;
import com.example.myapp.R;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState);

        // --- CRASH HANDLER ---
        Thread.setDefaultUncaughtExceptionHandler((thread, throwable) -> {
            try {
                java.io.File logDir = getExternalFilesDir(null);
                java.io.File crashLog = new java.io.File(logDir, "crash.log");
                java.io.FileWriter writer = new java.io.FileWriter(crashLog, true);
                writer.write("--- CRASH REPORT: " + new java.util.Date() + " ---\n");
                throwable.printStackTrace(new java.io.PrintWriter(writer));
                writer.write("\n----------------------------------------\n");
                writer.close();
            } catch (Exception ignored) {}
            android.os.Process.killProcess(android.os.Process.myPid());
        });

        setContentView(R.layout.activity_main);
    }
}