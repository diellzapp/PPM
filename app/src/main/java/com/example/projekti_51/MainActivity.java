package com.example.projekti_51;

import static android.content.Context.MODE_PRIVATE;
import static androidx.core.content.ContextCompat.getSystemService;
import static androidx.core.content.ContextCompat.startActivity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;


public class MainActivity extends AppCompatActivity {

    private static final String CHANNEL_ID = "welcome_channel";
    private static final int NOTIFICATION_ID = 1;

    Button buttonLogin;
    Button buttonRegister;
    TextView welcomeText;
    ImageView mainImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonLogin = findViewById(R.id.buttonLogin);
        buttonRegister = findViewById(R.id.buttonRegister);
        welcomeText = findViewById(R.id.welcomeText);
        mainImage = findViewById(R.id.mainImage);

        createNotificationChannel();

        requestNotificationPermission();

        showNotificationEveryTime();

        buttonLogin.setOnClickListener(v -> {
            applyButtonAnimation(buttonLogin);
            applyFadeAnimation(buttonLogin);
            Toast.makeText(MainActivity.this, "Login button clicked", Toast.LENGTH_SHORT).show();
            Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(loginIntent);
        });

        buttonRegister.setOnClickListener(v -> {
            applyButtonAnimation(buttonRegister);
            applyFadeAnimation(buttonRegister);
            Toast.makeText(MainActivity.this, "Register button clicked", Toast.LENGTH_SHORT).show();
            Intent registerIntent = new Intent(MainActivity.this, SignupActivity.class);
            startActivity(registerIntent);
        });

    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Welcome Channel";
            String description = "Channel for welcome notifications";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void showNotification() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Welcome!")
                .setContentText("Welcome to our app! Enjoy your experience.")
                .setSmallIcon(R.drawable.ic_notification)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build();

        notificationManager.notify(NOTIFICATION_ID, notification);
    }

    private void showNotificationEveryTime() {
        showNotification();
    }

    private void requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, 1);
            }
        }
    }

    private void applyButtonAnimation(Button button) {
        button.animate()
                .scaleX(1.1f)
                .scaleY(1.1f)
                .setDuration(100)
                .withEndAction(() ->
                        button.animate()
                                .scaleX(1.0f)
                                .scaleY(1.0f)
                                .setDuration(100)
                ).start();
    }

    private void applyRotationAnimation(Button button) {
        button.animate()
                .rotation(360f)
                .setDuration(300)
                .start();
    }

    private void applyCombinedAnimation(Button button) {
        button.animate()
                .scaleX(1.1f)
                .scaleY(1.1f)
                .rotation(15f)
                .setDuration(100)
                .withEndAction(() ->
                        button.animate()
                                .scaleX(1.0f)
                                .scaleY(1.0f)
                                .rotation(0f)
                                .setDuration(100)
                ).start();
    }

    private void applyFadeAnimation(Button button) {
        button.setAlpha(0f);
        button.animate()
                .alpha(1f)
                .setDuration(200)
                .start();
    }
}
