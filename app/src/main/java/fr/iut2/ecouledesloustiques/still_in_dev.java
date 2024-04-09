package fr.iut2.ecouledesloustiques;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class still_in_dev extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_still_in_dev);

        Button retourButton = findViewById(R.id.button);
        retourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirection vers l'activité CourActivity
                startActivity(new Intent(still_in_dev.this, CourActivity.class));
                finish(); // Ferme l'activité actuelle
            }
        });
    }
}