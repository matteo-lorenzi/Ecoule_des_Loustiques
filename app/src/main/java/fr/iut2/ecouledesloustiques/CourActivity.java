/**
 * Cette classe représente une activité affichant les différentes options de cours disponibles.
 * L'utilisateur peut choisir parmi les différentes catégories de cours et être redirigé vers les pages correspondantes.
 */
package fr.iut2.ecouledesloustiques;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.Executors;

//import fr.iut2.ecouledesloustiques.db.quizz.QuizDatabase;

public class CourActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cour);

        // Récupérer le nom de l'utilisateur passé par l'intent
        String userName = getIntent().getStringExtra("NameUser");

        // Afficher le nom de l'utilisateur. Supposons que vous avez un TextView pour le nom dans votre layout.
        TextView textViewNameUser = findViewById(R.id.nameUser);
        textViewNameUser.setText(userName);

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
                // Rediriger vers la page des cours de culture générale
                Intent intent = new Intent(CourActivity.this, CultureG.class);
                startActivity(intent);
            }
        });

        // Bouton Français
        Button buttonFrancais = findViewById(R.id.buttonFrancais);
        buttonFrancais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 // Rediriger vers la page des cours de français
                 Intent intent = new Intent(CourActivity.this, FrancaisActivity.class);
                 startActivity(intent);
            }
        });

        Button resetButton = findViewById(R.id.buttonReset);

    }
}
