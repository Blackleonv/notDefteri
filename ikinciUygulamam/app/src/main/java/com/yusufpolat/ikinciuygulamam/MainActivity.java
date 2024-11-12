package com.yusufpolat.ikinciuygulamam;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.yusufpolat.ikinciuygulamam.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        //View Binding'i kullanmak için yaptığımız ayarlar

        binding=ActivityMainBinding.inflate(getLayoutInflater());
        View view =binding.getRoot();
        setContentView(view);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Doğru Toolbar sınıfını kullanarak Toolbar'ı tanımlayıp ActionBar olarak ayarlıyoruz
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }



    public void buttonGecis(View view){
        Intent intent = new Intent(MainActivity.this,seconScreen.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.art_menu, menu);
        return true; // super.onCreateOptionsMenu(menu) yerine true döndürmek gerekiyor
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.deleted) {
            Toast.makeText(this, "Silindi", Toast.LENGTH_LONG).show();
        } else if (item.getItemId() == R.id.helper) {
            Toast.makeText(this, "Allah'ın yardımı dışında yardıma muhtaç değilsin", Toast.LENGTH_LONG).show();
        } else if (item.getItemId() == R.id.added) {
            Intent intent = new Intent(MainActivity.this, seconScreen.class);
            startActivity(intent);
        }
        return true;
    }

}
