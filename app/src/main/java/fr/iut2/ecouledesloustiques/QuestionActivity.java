/**
 * Cette classe représente l'activité où l'utilisateur répond aux questions de multiplication.
 * L'utilisateur voit une question de multiplication à la fois et doit saisir la réponse dans un EditText.
 * Après avoir validé sa réponse, l'utilisateur est informé si sa réponse est correcte ou incorrecte.
 * S'il répond correctement à toutes les questions, il est redirigé vers l'activité FelicitationsActivity.
 */
package fr.iut2.ecouledesloustiques;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class QuestionActivity extends Activity {
    // Éléments de l'interface utilisateur
    private TextView questionTextView;
    private EditText answerEditText;

    // Numéro de table de multiplication
    private int tableNumber;
    // Numéro de la question actuelle
    private int currentNumber = 1;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        // Récupération des vues
        TextView titleTextView = findViewById(R.id.textView);
        questionTextView = findViewById(R.id.questionTextView);
        answerEditText = findViewById(R.id.answerEditText);
        Button validateButton = findViewById(R.id.validateButton);

        // Récupération du numéro de table de l'intent
        tableNumber = getIntent().getIntExtra("tableNumber", 1);

        // Mise à jour du titre avec le numéro de table
        titleTextView.setText("Table de " + tableNumber);

        // Affichage de la première question
        displayQuestion();

        // Gestion du clic sur le bouton de validation
        validateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });
    }

    /**
     * Méthode pour afficher la question actuelle dans la TextView.
     */
    private void displayQuestion() {
        String question = currentNumber + " x " + tableNumber + " = ?";
        questionTextView.setText(question);
    }

    // Champ pour compter les erreurs
    private int errorCount = 0;

    /**
     * Méthode pour vérifier la réponse donnée par l'utilisateur.
     */
    private void checkAnswer() {
        String answerText = answerEditText.getText().toString();
        if (!answerText.isEmpty()) {
            if (answerText.matches("-?\\d+")) { // Vérifie si la chaîne est un nombre entier valide
                int answer = Integer.parseInt(answerText);
                if (currentNumber * tableNumber == answer) {
                    Toast.makeText(this, "Correct! Passons à la question suivante.", Toast.LENGTH_SHORT).show();
                    currentNumber++;
                    errorCount = 0; // Réinitialisation du compteur d'erreurs
                    if (currentNumber <= 10) {
                        displayQuestion();
                    } else {
                        // Création d'une nouvelle Intent pour ouvrir l'activité de félicitations
                        Intent intent = new Intent(this, FelicitationsActivity.class);

                        // Ajout d'extras à l'intent pour passer des informations à l'activité de félicitations
                        intent.putExtra("nextActivityCour", CourActivity.class);
                        intent.putExtra("nextActivityMultiplication", MultiplicationActivity.class);

                        // Démarrage de l'activité
                        startActivity(intent);
                    }
                } else {
                    errorCount++; // Incrémentation du compteur d'erreurs
                    if (errorCount > 2) {
                        Toast.makeText(this, "Il semble que vous ayez du mal avec cette question. La réponse est " + (currentNumber * tableNumber) + ".", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Incorrect. Essayez encore.", Toast.LENGTH_SHORT).show();
                    }
                }
            } else {
                Toast.makeText(this, "Veuillez entrer un nombre entier valide.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Veuillez entrer une réponse.", Toast.LENGTH_SHORT).show();
        }
    }
}
