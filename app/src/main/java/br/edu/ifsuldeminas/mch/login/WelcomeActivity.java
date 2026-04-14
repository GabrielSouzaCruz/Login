package br.edu.ifsuldeminas.mch.login;

import static br.edu.ifsuldeminas.mch.login.R.id.activity_welcome_id;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

import java.text.MessageFormat;

public class WelcomeActivity extends AppCompatActivity {

    private Button buttonTakePicture;
    private ImageView imageViewPicture;
    private Intent resultIntent;
    private static final String RESULTKEY = "resultado";
    private static final int REQUEST_PICTURE_CODE = 1234;

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
        String finalMessage = MessageFormat.format("{0} {1}!", getString(R.string.welcome_message), userName);

        View viewLayout = findViewById(activity_welcome_id);
        Snackbar snackbar = Snackbar.make(viewLayout, finalMessage, Snackbar.LENGTH_LONG);
        snackbar.show();

        buttonTakePicture = findViewById(R.id.buttonTakePictureId);
        imageViewPicture = findViewById(R.id.imageViewPictureId);

        buttonTakePicture.setOnClickListener((View view) -> {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(takePictureIntent, REQUEST_PICTURE_CODE);
        });

        resultIntent = new Intent();
        resultIntent.putExtra(RESULTKEY,"Não Bateu a foto");
        setResult(WelcomeActivity.RESULT_OK, resultIntent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_PICTURE_CODE){
            if (data != null) {
                Bundle extras = data.getExtras();
                if (extras != null) {
                    Bitmap image = (Bitmap) extras.get("data");
                    imageViewPicture.setImageBitmap(image);
                    resultIntent.putExtra(RESULTKEY, "Bateu a foto");
                }
            }
        }
    }
}