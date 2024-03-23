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
    private TextView questionTextView;
    private EditText answerEditText;
    private int tableNumber;
    private int currentNumber = 1;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        TextView titleTextView = findViewById(R.id.textView);
        questionTextView = findViewById(R.id.questionTextView);
        answerEditText = findViewById(R.id.answerEditText);
        Button validateButton = findViewById(R.id.validateButton);

        // Récupérer le numéro de table de l'intention
        tableNumber = getIntent().getIntExtra("tableNumber", 1);

        // Mettre à jour le titre avec le numéro de table
        titleTextView.setText("Table de " + tableNumber);

        // Afficher la première question
        displayQuestion();

        validateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });
    }

    private void displayQuestion() {
        String question = currentNumber + " x " + tableNumber + " = ?";
        questionTextView.setText(question);
    }

    private int errorCount = 0; // Ajoutez ce champ à votre classe

    private void checkAnswer() {
        String answerText = answerEditText.getText().toString();
        if (!answerText.isEmpty()) {
            if (answerText.matches("-?\\d+")) { // Vérifie si la chaîne est un nombre entier valide
                int answer = Integer.parseInt(answerText);
                if (currentNumber * tableNumber == answer) {
                    Toast.makeText(this, "Correct! Passons à la question suivante.", Toast.LENGTH_SHORT).show();
                    currentNumber++;
                    errorCount = 0; // Réinitialisez le compteur d'erreurs
                    if (currentNumber <= 10) {
                        displayQuestion();
                    } else {
                        // Créez une nouvelle Intent pour ouvrir l'activité de félicitations
                        Intent intent = new Intent(this, FelicitationsActivity.class);

                        // Ajoutez des extras à l'intent pour passer des informations à l'activité de félicitations
                        intent.putExtra("nextActivityCour", CourActivity.class);
                        intent.putExtra("nextActivityMultiplication", MultiplicationActivity.class);

                        // Démarrez l'activité
                        startActivity(intent);
                    }
                } else {
                    errorCount++; // Incrémente le compteur d'erreurs
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
