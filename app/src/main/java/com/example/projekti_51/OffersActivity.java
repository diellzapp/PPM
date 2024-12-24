package com.example.projekti_51;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

public class OffersActivity extends AppCompatActivity {

    ListView offersListView;
    Button buttonBackHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offers);

        // Inicializimi i ListView dhe butonit
        offersListView = findViewById(R.id.offersListView);
        buttonBackHome = findViewById(R.id.buttonBackHome);

        // Të dhënat për ofertat
        String[] offers = {
                "Pizza Margherita - 50% zbritje",
                "Burger Klasik - Blej nje ,merr nje falas",
                "Pasta Carbonara - Zbritje 30%"
        };

        // Adapteri për ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, offers);
        offersListView.setAdapter(adapter);

        // Event për butonin për t'u kthyer në ekranin kryesor
        buttonBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Kthehu në MainActivity
                startActivity(new Intent(OffersActivity.this, MainActivity.class));
                finish(); // Mbyll OffersActivity për të mos e lënë në stack
            }
        });
    }
}
