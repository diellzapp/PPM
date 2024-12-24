package com.example.projekti_51;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class TwoFactorActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText etVerificationCode;
    private Button btnVerifyCode;
    private String verificationId;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_factor);

        mAuth = FirebaseAuth.getInstance();
        etVerificationCode = findViewById(R.id.etVerificationCode);
        btnVerifyCode = findViewById(R.id.btnVerifyCode);

        // Get verification ID from the Intent (you'll need to pass this from the login flow)
        verificationId = getIntent().getStringExtra("verificationId");

        btnVerifyCode.setOnClickListener(v -> {
            String code = etVerificationCode.getText().toString();
            if (!TextUtils.isEmpty(code)) {
                verifyCode(code);
            } else {
                Toast.makeText(TwoFactorActivity.this, "Please enter verification code", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void verifyCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // 2FA successful, proceed to the home screen or next activity
                        startActivity(new Intent(TwoFactorActivity.this, Home.class));
                        finish();
                    } else {
                        Toast.makeText(TwoFactorActivity.this, "Invalid code", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
