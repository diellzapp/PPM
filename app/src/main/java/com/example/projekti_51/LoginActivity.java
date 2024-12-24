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

public class LoginActivity extends AppCompatActivity {

    EditText loginUsername, loginPassword;
    Button loginButton;
    TextView signupRedirectText, forgotPasswordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); // Referenca në XML për LoginActivity

        // Inicializimi i fushave dhe butonave
        loginUsername = findViewById(R.id.login_username);
        loginPassword = findViewById(R.id.login_password);
        loginButton = findViewById(R.id.login_button);
        signupRedirectText = findViewById(R.id.signupRedirectText);
        forgotPasswordText = findViewById(R.id.forgotPasswordText);

        // Ngarko animacionin për butonin
        final Animation scaleAnimation = AnimationUtils.loadAnimation(LoginActivity.this, R.anim.scale_button);

        // Eventi për butonin "Login"
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aktivizo animacionin për butonin kur përdoruesi e shtyp
                v.startAnimation(scaleAnimation);

                String username = loginUsername.getText().toString().trim();
                String password = loginPassword.getText().toString().trim();

                // Verifikimi i fushave të hyrjes
                if (username.isEmpty()) {
                    loginUsername.setError("Username is required");
                } else if (password.isEmpty()) {
                    loginPassword.setError("Password is required");
                } else if (!isPasswordValid(password)) {
                    // Verifikimi i fjalëkalimit për të patur një numër dhe karakter special
                    loginPassword.setError("Password must contain at least one number and one special character");
                } else {
                    // Mund të shtoni verifikim me server ose ndonjë metodë tjetër për të kontrolluar kredencialet
                    // Këtu është vetëm një test i thjeshtë
                    if (username.equals("user") && password.equals("password")) {
                        // Navigimi në MainActivity nëse janë të sakta
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish(); // Mbyll LoginActivity
                    } else {
                        Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // Eventi për të drejtuar përdoruesin në ekranin e regjistrimit
        signupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Kalimi në SignupActivity
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            }
        });

        // Eventi për të drejtuar përdoruesin në aktivitetin për rikuperimin e fjalëkalimit
        forgotPasswordText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Kalimi në ForgotPasswordActivity
                startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class)); // Aktiviteti për rikuperimin e fjalëkalimit
            }
        });
    }

    // Funksioni për të validuar fjalëkalimin
    private boolean isPasswordValid(String password) {
        // Kontrollo që fjalëkalimi të ketë të paktën një numër dhe një karakter special
        String regex = "^(?=.*[0-9])(?=.*[!@#$%^&*(),.?\":{}|<>]).{6,}$";
        return password.matches(regex);
    }
}
