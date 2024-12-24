package com.example.projekti_51;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class InfoActivity extends AppCompatActivity {

    Button buttonBackHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        // Inicializimi i butonit
        buttonBackHome = findViewById(R.id.buttonBackHome);

        // Event për butonin për t'u kthyer në ekranin kryesor
        buttonBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Kthehu në HomeActivity
                startActivity(new Intent(InfoActivity.this, MainActivity.class));
                finish(); // Përdoret për të mbyllur InfoActivity dhe për të mos e lënë në stack
            }
        });
    }
}
