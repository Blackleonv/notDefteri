package com.yusufpolat.ikinciuygulamam;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.yusufpolat.ikinciuygulamam.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase database;
    private LinearLayout linearLayout; // TextView'ları eklemek için LinearLayout

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // LinearLayout'u tanımlayın
        linearLayout = findViewById(R.id.linearLayout);

        // Veritabanını açın
        database = this.openOrCreateDatabase("veriTabanı", MODE_PRIVATE, null);
        database.execSQL("CREATE TABLE IF NOT EXISTS VeriTabanı(veri VARCHAR)");

        // Verileri oku ve göster
        readData(); // Verileri oku ve göster
    }

    // Yeni bir TextView ekleyen metot
    private void addTextView(String veri) {
        TextView textView = new TextView(this);
        textView.setText(veri);
        textView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        linearLayout.addView(textView); // LinearLayout'a ekle
    }

    // FloatingActionButton'a tıklandığında çağrılacak metot
    public void buttonGecis(View view) {
        Intent intent = new Intent(this, seconScreen.class); // seconScreen'e geçiş
        startActivity(intent); // Yeni Activity'yi başlat
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.seconscreenmenu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item2) {
        if (item2.getItemId() == R.id.deleted) {
            Toast.makeText(this, "Silinecek Dosyayı Seçiniz!", Toast.LENGTH_LONG).show();
        } else if (item2.getItemId() == R.id.helper) {
            Toast.makeText(this, "Yardım Seçeneğine Hoşgeldiniz", Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item2);
    }

    // Veritabanındaki verileri okuyan metot
    private void readData() {
        Cursor cursor = database.rawQuery("SELECT * FROM VeriTabanı", null);
        while (cursor.moveToNext()) {
            String veri = cursor.getString(0); // İlk sütun
            addTextView(veri); // Yeni bir TextView ekle
        }
        cursor.close();
    }
}