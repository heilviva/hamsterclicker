package com.example.hamsterclicker;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatDelegate;

public class MainActivity extends AppCompatActivity {

    private TextView scoreText;
    private ImageView hamsterImage;
    private Button changeThemeButton;

    private int score = 0;
    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "HamsterClickerPrefs";
    private static final String THEME_KEY = "theme";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        setTheme(sharedPreferences.getInt(THEME_KEY, AppCompatDelegate.MODE_NIGHT_NO));
        setContentView(R.layout.activity_main);

        scoreText = findViewById(R.id.scoreText);
        hamsterImage = findViewById(R.id.hamsterImage);
        changeThemeButton = findViewById(R.id.changeThemeButton);

        hamsterImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score++;
                updateScore();
            }
        });

        changeThemeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchTheme();
            }
        });
    }

    private void updateScore() {
        scoreText.setText("Очки: " + score);
    }

    private void switchTheme() {
        int currentTheme = sharedPreferences.getInt(THEME_KEY, AppCompatDelegate.MODE_NIGHT_NO);
        int newTheme = (currentTheme == AppCompatDelegate.MODE_NIGHT_NO)
                ? AppCompatDelegate.MODE_NIGHT_YES
                : AppCompatDelegate.MODE_NIGHT_NO;

        sharedPreferences.edit().putInt(THEME_KEY, newTheme).apply();
        AppCompatDelegate.setDefaultNightMode(newTheme);
        recreate();
    }
}
