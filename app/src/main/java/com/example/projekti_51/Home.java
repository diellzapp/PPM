package com.example.projekti_51;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity {

    Button buttonProfile, buttonOffers, buttonInfo,buttonLogout;;
    ImageButton navHome, navProfile;
    TextView welcomeText;
    ImageView mainImage;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home); // Refers to your HomeActivity layout

        // Initialize views
        buttonProfile = findViewById(R.id.buttonProfile);
        buttonOffers = findViewById(R.id.buttonOffers);
        buttonInfo = findViewById(R.id.buttonInfo);
        buttonLogout = findViewById(R.id.buttonLogout);

        navHome = findViewById(R.id.navHome);
        navProfile = findViewById(R.id.navProfile);

        welcomeText = findViewById(R.id.welcomeText);
        mainImage = findViewById(R.id.mainImage);

        // Button actions
        buttonProfile.setOnClickListener(v ->
                startActivity(new Intent(Home.this, ProfileActivity.class))
        );

        buttonOffers.setOnClickListener(v ->
                startActivity(new Intent(Home.this, OffersActivity.class))
        );

        buttonInfo.setOnClickListener(v ->
                startActivity(new Intent(Home.this, InfoActivity.class))
        );

        // Navigation actions
        navHome.setOnClickListener(v ->
                Toast.makeText(Home.this, "You are already on the Home screen", Toast.LENGTH_SHORT).show()
        );

        buttonProfile.setOnClickListener(v ->
                startActivity(new Intent(Home.this, ProfileActivity.class))
        );

        navProfile.setOnClickListener(v ->
                startActivity(new Intent(Home.this, ProfileActivity.class))
        );
        buttonLogout.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();


            startActivity(new Intent(Home.this, MainActivity.class));
            finish();
        });
    }
}
