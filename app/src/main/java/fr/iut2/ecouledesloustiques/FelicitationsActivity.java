/**
 * Cette classe représente une activité de félicitations affichée à l'utilisateur après avoir terminé une activité avec succès.
 * L'utilisateur a la possibilité de choisir entre deux options pour passer à la prochaine activité.
 * Les boutons disponibles sont configurés pour rediriger l'utilisateur vers les activités suivantes en fonction des paramètres passés.
 */
package fr.iut2.ecouledesloustiques;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FelicitationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Définir le contenu de l'activité
        setContentView(R.layout.activity_felicitations);


        // Configurer les onClickListeners pour les boutons
        Button courButton = findViewById(R.id.courButton);
        Button multiplicationButton = findViewById(R.id.multiplicationButton);

        // Redirection vers l'activité suivante associée au bouton "Cours"
        courButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(FelicitationsActivity.this, CourActivity.class);
                finish();
                startActivity(intent);
            }
        });

        // Redirection vers l'activité suivante associée au bouton "Multiplication"
        multiplicationButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(FelicitationsActivity.this, MultiplicationActivity.class);
                finish();
                startActivity(intent);
            }
        });
    }
}
