package com.example.projekti_51;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.mindrot.jbcrypt.BCrypt;

import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {

    EditText signupName, signupEmail, signupUsername, signupPassword;
    Button signupButton;
    TextView loginRedirectText;

    private FirebaseAuth auth;
    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

    
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference("Users");

        signupName = findViewById(R.id.signup_name);
        signupEmail = findViewById(R.id.signup_email);
        signupUsername = findViewById(R.id.signup_username);
        signupPassword = findViewById(R.id.signup_password);
        signupButton = findViewById(R.id.signup_button);
        loginRedirectText = findViewById(R.id.loginRedirectText);

    
        signupButton.setOnClickListener(v -> {
            String name = signupName.getText().toString().trim();
            String email = signupEmail.getText().toString().trim();
            String username = signupUsername.getText().toString().trim();
            String password = signupPassword.getText().toString().trim();

            if (validateInputs(name, email, username, password)) {
                registerUser(name, email, username, password);
            }
        });

  
        loginRedirectText.setOnClickListener(v -> {
            startActivity(new Intent(SignupActivity.this, LoginActivity.class));
            finish();
        });
    }

    private boolean validateInputs(String name, String email, String username, String password) {
        if (TextUtils.isEmpty(name)) {
            signupName.setError("Name is required");
            return false;
        }
        if (TextUtils.isEmpty(email)) {
            signupEmail.setError("Email is required");
            return false;
        }
        if (TextUtils.isEmpty(username)) {
            signupUsername.setError("Username is required");
            return false;
        }
        if (!isValidPassword(password)) {
            signupPassword.setError("Password must be at least 8 characters, include a number, a special character, and an uppercase letter");
            return false;
        }
        return true;
    }

    private boolean isValidPassword(String password) {
        String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*(),.?\":{}|<>]).{8,}$";
        return Pattern.compile(passwordPattern).matcher(password).matches();
    }

    private void registerUser(String name, String email, String username, String password) {
      
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        auth.createUserWithEmailAndPassword(email, password) 
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser firebaseUser = auth.getCurrentUser();
                        String userId = firebaseUser.getUid();

                        User user = new User(name, email, username, userId, hashedPassword, false);

                        database.child(userId).setValue(user)
                                .addOnCompleteListener(task1 -> {
                                    if (task1.isSuccessful()) {
                                        sendVerificationEmail(firebaseUser); 
                                    } else {
                                        Toast.makeText(SignupActivity.this, "Database Error: " + task1.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                    } else {
                        Toast.makeText(SignupActivity.this, "Registration Failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void sendVerificationEmail(FirebaseUser firebaseUser) {
        firebaseUser.sendEmailVerification()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(SignupActivity.this, "Verification email sent. Please verify to log in.", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                        finish();
                    } else {
                        Toast.makeText(SignupActivity.this, "Error sending email: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}

