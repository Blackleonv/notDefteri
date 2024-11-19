package com.yusufpolat.ikinciuygulamam;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.yusufpolat.ikinciuygulamam.databinding.ActivitySeconScreenBinding;

public class seconScreen extends AppCompatActivity {
    private ActivitySeconScreenBinding binding;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivitySeconScreenBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // Toolbar'ı ayarlayın
        setSupportActionBar(binding.toolbar);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Veritabanını oluşturun
        try {
            database = this.openOrCreateDatabase("veriTabanı", MODE_PRIVATE, null);
            database.execSQL("CREATE TABLE IF NOT EXISTS VeriTabanı(veri VARCHAR)");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void addData(String veri) {
    try {
        database.execSQL("INSERT INTO VeriTabanı (veri) VALUES ('" + veri + "')");
    } catch (Exception e) {
        e.printStackTrace();
    }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu2) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.seconscreenmenu, menu2);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String veri = binding.textView.getText().toString();
        if (item.getItemId() == R.id.save) {
            if (!veri.isEmpty()) {
                addData(veri); // Veriyi ekle
                Toast.makeText(this, "Veri kaydedildi!", Toast.LENGTH_SHORT).show();
                binding.textView.setText(""); // EditText'i temizle

                // Ana sayfaya geri dön
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK); // Ana sayfayı temizle
                startActivity(intent);
                finish(); // Bu aktiviteyi kapat
            } else {
                Toast.makeText(this, "Lütfen bir metin girin!", Toast.LENGTH_SHORT).show();
            }
        } else if (item.getItemId() == R.id.exit) {
            Toast.makeText(this, "Uygulama Kapatılıyor", Toast.LENGTH_LONG).show();
            finish(); // Uygulamayı kapatmak için
        }
        return super.onOptionsItemSelected(item);
    }

    // Veritabanındaki verileri okuyan metot
    private String readData() {
        StringBuilder stringBuilder = new StringBuilder();
        Cursor cursor = database.rawQuery("SELECT * FROM VeriTabanı", null);
        while (cursor.moveToNext()) {
            String veri = cursor.getString(0); // İlk sütun
            stringBuilder.append(veri).append("\n");
        }
        cursor.close();
        return stringBuilder.toString();
    }
}