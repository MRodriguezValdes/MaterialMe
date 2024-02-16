package com.example.android.materialme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Inicializar vistas
        TextView sportsTitle = findViewById(R.id.titleDetail);
        TextView sportsSubTitle = findViewById(R.id.subTitleDetail);
        ImageView sportsImage = findViewById(R.id.sportsImageDetail);

        // Obtener datos del Intent
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null) {
            String title = intent.getStringExtra("title");
            String subTitle = intent.getStringExtra("subTitle");
            int imageResource = intent.getIntExtra("image_resource", 0);

            // Verificar si los datos son nulos
            if (title != null && imageResource != 0) {
                // Asignar datos a las vistas
                sportsTitle.setText(title);
                sportsSubTitle.setText(subTitle);
                Glide.with(this).load(imageResource).into(sportsImage);
            } else {
                // Manejar caso de datos nulos
                Toast.makeText(this, "Datos de la noticia no disponibles", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Manejar caso de Intent nulo
            Toast.makeText(this, "No se recibieron datos de la noticia", Toast.LENGTH_SHORT).show();
        }
    }
}

