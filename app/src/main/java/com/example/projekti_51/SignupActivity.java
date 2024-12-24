package com.example.projekti_51;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {

    EditText signupName, signupEmail, signupUsername, signupPassword;
    Button signupButton;
    TextView loginRedirectText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup); // Referenca në XML për SignupActivity

        // Inicializimi i fushave dhe butonave
        signupName = findViewById(R.id.signup_name);
        signupEmail = findViewById(R.id.signup_email);
        signupUsername = findViewById(R.id.signup_username);
        signupPassword = findViewById(R.id.signup_password);
        signupButton = findViewById(R.id.signup_button);
        loginRedirectText = findViewById(R.id.loginRedirectText);

        // Aplikoni animacionin për butonin "Sign Up"
        Animation fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        signupButton.startAnimation(fadeInAnimation); // Start animation

        // Eventi për butonin "Sign Up"
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = signupName.getText().toString().trim();
                String email = signupEmail.getText().toString().trim();
                String username = signupUsername.getText().toString().trim();
                String password = signupPassword.getText().toString().trim();

                // Verifikimi i fushave
                if (name.isEmpty()) {
                    signupName.setError("Name is required");
                } else if (email.isEmpty()) {
                    signupEmail.setError("Email is required");
                } else if (username.isEmpty()) {
                    signupUsername.setError("Username is required");
                } else if (password.isEmpty()) {
                    signupPassword.setError("Password is required");
                } else if (!isPasswordValid(password)) {
                    signupPassword.setError("Password must contain at least 1 number, 1 special character, and 1 uppercase letter");
                } else {
                    // Mund të shtoni logjikën për ruajtjen në bazë të dhënash ose SharedPreferences
                    Toast.makeText(SignupActivity.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignupActivity.this, LoginActivity.class)); // Kaloni në LoginActivity pas regjistrimit
                    finish(); // Mbyll SignupActivity
                }
            }
        });

        // Eventi për kalimin në LoginActivity
        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this, LoginActivity.class)); // Kaloni në LoginActivity për të hyrë
                finish(); // Mbyll SignupActivity
            }
        });
    }

    /**
     * Funksioni për të kontrolluar validitetin e password-it.
     * Password-i duhet të përmbajë të paktën një numër, një karakter special dhe një shkronjë të madhe.
     */
    private boolean isPasswordValid(String password) {
        // Pattern për password të vlefshëm
        String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*(),.?\":{}|<>]).{8,}$";
        Pattern pattern = Pattern.compile(passwordPattern);
        Matcher matcher = pattern.matcher(password);

        return matcher.matches(); // Kthejmë true nëse password-i përmbush kriteret
    }
}
