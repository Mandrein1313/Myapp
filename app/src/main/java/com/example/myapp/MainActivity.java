package com.example.myapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.graphics.Color;
import android.view.Gravity;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // --- CRASH HANDLER ---
        Thread.setDefaultUncaughtExceptionHandler((thread, throwable) -> {
            try {
                java.io.File logDir = getExternalFilesDir(null);
                if (logDir != null) {
                    java.io.File crashLog = new java.io.File(logDir, "crash.log");
                    java.io.FileWriter writer = new java.io.FileWriter(crashLog, true);
                    writer.write("--- CRASH REPORT: " + new java.util.Date() + " ---\n");
                    throwable.printStackTrace(new java.io.PrintWriter(writer));
                    writer.write("\n----------------------------------------\n");
                    writer.close();
                }
            } catch (Exception ignored) {
            }
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(10);
        });

        // UI แบบง่าย ไม่พึ่ง layout ซับซ้อน
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER);
        root.setBackgroundColor(Color.parseColor("#1A1B26"));
        root.setPadding(48, 48, 48, 48);

        TextView title = new TextView(this);
        title.setText("Myapp · Crash Test");
        title.setTextColor(Color.parseColor("#C0CAF5"));
        title.setTextSize(20);
        title.setGravity(Gravity.CENTER);
        root.addView(title);

        TextView hint = new TextView(this);
        hint.setText("กดปุ่มเพื่อจำลองแอปหยุดทำงาน");
        hint.setTextColor(Color.parseColor("#565F89"));
        hint.setTextSize(14);
        hint.setGravity(Gravity.CENTER);
        hint.setPadding(0, 24, 0, 48);
        root.addView(hint);

        Button btnCrash = new Button(this);
        btnCrash.setText("Crash Now");
        btnCrash.setTextColor(Color.parseColor("#1A1B26"));
        btnCrash.setOnClickListener(v -> {
            throw new RuntimeException("Nexus CrashTest: กดปุ่มจาก Myapp");
        });
        root.addView(btnCrash);

        setContentView(root);
    }
}