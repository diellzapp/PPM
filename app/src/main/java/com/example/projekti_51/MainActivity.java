package com.example.projekti_51;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MainActivity extends AppCompatActivity {

    // Identifikues për kanal dhe njoftim
    private static final String CHANNEL_ID = "welcome_channel";
    private static final int NOTIFICATION_ID = 1;

    Button buttonProfile, buttonOffers, buttonInfo, buttonLogin;
    ImageButton navHome, navSearch, navProfile;
    TextView welcomeText;
    ImageView mainImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  // Vendos layout-in e MainActivity

        // Inicializimi i butonave dhe elementeve të tjerë
        buttonProfile = findViewById(R.id.buttonProfile);
        buttonOffers = findViewById(R.id.buttonOffers);
        buttonInfo = findViewById(R.id.buttonInfo);
        buttonLogin = findViewById(R.id.buttonLogin);

        navHome = findViewById(R.id.navHome);
        navProfile = findViewById(R.id.navProfile);

        // Elementet që do të animohen
        welcomeText = findViewById(R.id.welcomeText);
        mainImage = findViewById(R.id.mainImage);

        // Aplikimi i animacionit për mirëseardhjen dhe imazhin
        applyAnimations();

        // Event për butonin e profilit
        buttonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
            }
        });

        // Event për butonin e ofertave
        buttonOffers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, OffersActivity.class));
            }
        });

        // Event për butonin e informacionit
        buttonInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, InfoActivity.class));
            }
        });

        // Navigimi nga shiriti i poshtëm
        navHome.setOnClickListener(v -> {
            // Qëndron në Home
            Toast.makeText(MainActivity.this, "You are already on the Home screen", Toast.LENGTH_SHORT).show();
        });

        navProfile.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ProfileActivity.class));
        });

        // Event për butonin "Hyr"
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aplikoni animacionin për butonin Hyr
                applyButtonAnimation();

                // Aktivizo mesazhin Toast
                Toast.makeText(MainActivity.this, "Login button clicked", Toast.LENGTH_SHORT).show();

                // Kaloni në LoginActivity
                Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(loginIntent);
            }
        });

        // Krijo kanalin e njoftimeve dhe shfaq njoftimin e mirëseardhjes
        createNotificationChannel();
        requestNotificationPermission();
    }

    /**
     * Krijon një kanal njoftimi për versionet e Android Oreo (API 26) dhe më të larta.
     */
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "WelcomeChannel";
            String description = "Channel for Welcome Notifications";
            int importance = NotificationManager.IMPORTANCE_HIGH; // Prioriteti më i lartë për shfaqje më të dukshme

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    /**
     * Kërko lejen për të dërguar njoftime.
     */
    private void requestNotificationPermission() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // Kërkoni lejen për njoftime
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, 1);
        } else {
            // Leja është dhënë, mund të shfaqim njoftimin menjëherë
            showWelcomeNotification();
        }
    }

    /**
     * Shfaq një njoftim mirëseardhjeje.
     */
    private void showWelcomeNotification() {
        // Sigurohuni që të ketë kaluar leja
        Toast.makeText(this, "Mirësevini në HaPiRri!", Toast.LENGTH_SHORT).show();

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification) // Sigurohuni që të keni një ikonë në res/drawable
                .setContentTitle("Mirësevini në HaPiRri!")
                .setContentText("Faleminderit që përdorni aplikacionin tonë. Shijoni eksperiencën tuaj!")
                .setPriority(NotificationCompat.PRIORITY_HIGH)  // Prioriteti më i lartë për shfaqje të dukshme
                .setAutoCancel(true); // Njoftimi do të fshihet automatikisht kur të prekni mbi të

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    // Metodë për trajtimin e rezultateve të kërkesës për leje
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Leja është dhënë, mund të shfaqim njoftimin
                Toast.makeText(this, "Permission granted!", Toast.LENGTH_SHORT).show();
                showWelcomeNotification();
            } else {
                // Leja është refuzuar, mund të informoni përdoruesin
                Toast.makeText(this, "Permission denied. Notifications will not be shown.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * Aplikon animacionin për mirëseardhjen dhe imazhin
     */
    private void applyAnimations() {
        // Animacion për mirëseardhjen (fade-in)
        AlphaAnimation fadeIn = new AlphaAnimation(0.0f, 1.0f);
        fadeIn.setDuration(2000);  // Kohëzgjatja 2 sekonda
        welcomeText.startAnimation(fadeIn);

        // Animacion për imazhin (slide-in)
        TranslateAnimation slideIn = new TranslateAnimation(-500, 0, 0, 0);
        slideIn.setDuration(2000);  // Kohëzgjatja 2 sekonda
        mainImage.startAnimation(slideIn);
    }

    /**
     * Aplikon animacion për butonin "Hyr"
     */
    private void applyButtonAnimation() {
        // Animacion për butonin (shumëfishim dhe pastaj kthim në madhësinë origjinale)
        AlphaAnimation pulseAnimation = new AlphaAnimation(1.0f, 0.5f);
        pulseAnimation.setDuration(500);  // Kohëzgjatja 0.5 sekonda
        pulseAnimation.setRepeatMode(Animation.REVERSE);  // Kthehu pas animacionit
        pulseAnimation.setRepeatCount(1);  // E bën animacionin të përsëritet një herë
        buttonLogin.startAnimation(pulseAnimation);
    }
}
