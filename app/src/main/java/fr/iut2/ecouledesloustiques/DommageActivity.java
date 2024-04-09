package fr.iut2.ecouledesloustiques;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DommageActivity extends Activity {

    private TextView errorCountTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dommage);

        // Get the error count from the intent
        int errorCount = getIntent().getIntExtra("errorCount", 0);

        System.out.println("NOMBRE D'ERREUR RETORNE" + errorCount);

        // Find the TextView
        errorCountTextView = findViewById(R.id.errorView);

        // Set the error count as text
        errorCountTextView.setText("Nombre d'erreurs : " + errorCount);


        Button retryButton = findViewById(R.id.retryButton);
        Button quitButton = findViewById(R.id.quitButton);

        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirection vers l'activité MultiplicationActivity
                startActivity(new Intent(DommageActivity.this, MultiplicationActivity.class));
                finish(); // Ferme l'activité actuelle
            }
        });

        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirection vers l'activité CourActivity
                startActivity(new Intent(DommageActivity.this, CourActivity.class));
                finish(); // Ferme l'activité actuelle
            }
        });
    }
}
