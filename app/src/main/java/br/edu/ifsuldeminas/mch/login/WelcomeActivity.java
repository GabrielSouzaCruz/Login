package br.edu.ifsuldeminas.mch.login;

import static br.edu.ifsuldeminas.mch.login.R.id.activity_welcome_id;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

import java.text.MessageFormat;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(activity_welcome_id), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intentQueChamou = getIntent();
        String userName = intentQueChamou.getStringExtra("user_name");

        TextView textViewWelcome = findViewById(R.id.textViewWecolmeTextId);
        String originalMessage = textViewWelcome.getText().toString();
        String finalMessage = MessageFormat.format("{0} {1}!", originalMessage, userName);
        textViewWelcome.setText(finalMessage);

        View viewLayout = findViewById(activity_welcome_id);
        Snackbar snackbar = Snackbar.make(viewLayout, finalMessage, Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}