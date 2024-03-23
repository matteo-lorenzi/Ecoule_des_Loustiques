package fr.iut2.ecouledesloustiques;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class CourActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cour);

        // Bouton Mathématiques
        Button buttonMath = findViewById(R.id.buttonMath);
        buttonMath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Rediriger vers la page des cours de mathématiques
                Intent intent = new Intent(CourActivity.this, MultiplicationActivity.class);
                startActivity(intent);
            }
        });

        // Bouton Culture générale
        Button buttonCultureGenerale = findViewById(R.id.buttonCultureGenerale);
        buttonCultureGenerale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Rediriger vers la page des cours de culture générale
                Intent intent = new Intent(CourActivity.this, CultureGeneraleActivity.class);
               startActivity(intent);
            }
        });

        // Bouton Français
        Button buttonFrancais = findViewById(R.id.buttonFrancais);
        buttonFrancais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Rediriger vers la page des cours de français
//                Intent intent = new Intent(CourActivity.this, FrancaisActivity.class);
//                startActivity(intent);
            }
        });
    }
}
