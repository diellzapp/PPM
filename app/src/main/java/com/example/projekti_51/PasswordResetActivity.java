package com.example.projekti_51;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class PasswordResetActivity extends AppCompatActivity {

    private EditText emailEditText;
    private Button sendResetEmailButton, BackButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset);

        emailEditText = findViewById(R.id.emailEditText);
        sendResetEmailButton = findViewById(R.id.sendResetEmailButton);
        BackButton = findViewById(R.id.BackButton);
        BackButton.setOnClickListener(v -> {
            startActivity(new Intent(PasswordResetActivity.this, ProfileActivity.class));
            finish();
        });
        sendResetEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString().trim();
                if (!email.isEmpty()) {
                    sendPasswordResetEmail(email);
                } else {
                    Toast.makeText(PasswordResetActivity.this, "Please enter a valid email address.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void sendPasswordResetEmail(String email) {
        FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(PasswordResetActivity.this, "Password reset email sent.", Toast.LENGTH_SHORT).show();
                        finish(); // Close the activity after sending the email
                    } else {
                        Toast.makeText(PasswordResetActivity.this, "Failed to send reset email: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
