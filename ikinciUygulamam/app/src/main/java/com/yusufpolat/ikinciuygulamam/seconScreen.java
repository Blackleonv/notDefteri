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
    private SQLiteDatabase database; // Yeni: Veritabanı değişkeni sınıf düzeyinde tanımlandı

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

    // Yeni: Veriyi veritabanına ekleyen metot
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
        return true; // super.onCreateOptionsMenu(menu) yerine true döndürmek gerekiyor
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String veri = binding.textView.getText().toString();
        if (item.getItemId() == R.id.save) {
            if (!veri.isEmpty()) {
                addData(veri); // Yeni: Veriyi ekle
                Toast.makeText(this, "Veri kaydedildi!", Toast.LENGTH_LONG).show();
                binding.textView.setText(""); // EditText'i temizle
                Intent intent = new Intent(this,MainActivity.class);

                startActivity(intent);
            } else {
                Toast.makeText(this, "Lütfen bir metin girin!", Toast.LENGTH_SHORT).show(); // Yeni: Boş metin kontrolü
            }
        } else if (item.getItemId() == R.id.exit) {
            Toast.makeText(this, "Uygulama Kapatılıyor", Toast.LENGTH_LONG).show();
            finish(); // Uygulamayı kapatmak için
        }
        return super.onOptionsItemSelected(item); // Burada super çağrısını ekleyin
    }


    // Yeni: Veritabanındaki verileri okuyan metot
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