package com.example.projekti_51;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    private TextView profileBio;
    private Button buttonBackHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Gjejmë TextView për Bio
        profileBio = findViewById(R.id.profileBio);

        // Shembuj të të dhënave dinamike të biografisë (mund të vijnë nga një databazë ose API në një aplikacion të vërtetë)
        String bioData = "A passionate developer, coffee lover, and traveler. Always learning new things!";

        // Vendosim tekstin dinamika për biografinë
        profileBio.setText(bioData);

        // Gjejmë butonin "Back to Home"
        buttonBackHome = findViewById(R.id.buttonBackHome);

        // Vendosim OnClickListener për butonin "Back to Home"
        buttonBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Krijojmë një Intent për të hapur MainActivity
                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(intent);

                // Mund ta quajmë finish() për të hequr ProfileActivity nga staku i aktiviteteve
                finish();
            }
        });
    }
}
