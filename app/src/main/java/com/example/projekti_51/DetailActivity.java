package com.example.projekti_51;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    ImageView detailImage;
    TextView detailDescription;
    Button buttonBack;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Inicializimi i elementeve
        detailImage = findViewById(R.id.detailImage);
        detailDescription = findViewById(R.id.detailDescription);
        buttonBack = findViewById(R.id.buttonBack);

        // Merrni të dhënat nga Intent
        Intent intent = getIntent();
        String description = intent.getStringExtra("description");
        int imageResId = intent.getIntExtra("imageResId", -1);

        // Vendosni informacionin
        detailDescription.setText(description);
        if (imageResId != -1) {
            detailImage.setImageResource(imageResId);
        }

        // Butoni për të kthyer në ofertat
        buttonBack.setOnClickListener(v -> finish());
    }
}
