package com.example.myapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // ตัวอย่างรายการสินค้า
    private final String[] products = {
            "1. สมาร์ทโฟน Samsung Galaxy S24 – 50,000 บาท",
            "2. แล็ปท็อป Dell XPS 13 – 120,000 บาท",
            "3. หูฟัง Sony WH-1000XM4 – 15,000 บาท",
            "4. กล้อง Canon EOS 90D – 90,000 บาท",
            "5. นาฬิกา Apple Watch Series 9 – 30,000 บาท"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.product_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                products
        );
        listView.setAdapter(adapter);

        // เพิ่มการคลิกเพื่อแสดง Toast ชื่อสินค้า
        listView.setOnItemClickListener((parent, view, position, id) -> {
            String productName = products[position];
            Toast.makeText(MainActivity.this, productName, Toast.LENGTH_SHORT).show();
        });
    }
}
