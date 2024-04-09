package fr.iut2.ecouledesloustiques;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class QuestionActivity extends Activity {
    // Éléments de l'interface utilisateur
    private TextView questionTextView;
    private EditText answerEditText;
    private TextView timerTextView;
    private RadioGroup operationRadioGroup;

    // Numéro de table de multiplication
    private int tableNumber;
    // Numéro de la question actuelle
    private int currentNumber = 1;

    // Chronomètre
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis = 60000; // 10 secondes

    // Nouvelle variable pour stocker du bouton radio sélectionné
    String selectedRadioButtonId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        // Récupération des vues
        TextView titleTextView = findViewById(R.id.textView);
        questionTextView = findViewById(R.id.questionTextView);
        answerEditText = findViewById(R.id.answerEditText);
        Button validateButton = findViewById(R.id.validateButton);
        timerTextView = findViewById(R.id.timerTextView);


        // Récupération des extras de l'intent
        tableNumber = getIntent().getIntExtra("tableNumber", 1);
        boolean isTimerEnabled = getIntent().getBooleanExtra("isTimerEnabled", false);



        // Mise à jour du titre avec le numéro de table
        titleTextView.setText("Table de " + tableNumber);

        // Récupération de l'ID du bouton radio sélectionné
        selectedRadioButtonId = getIntent().getStringExtra("selectedRadioButtonId");

        // Affichage de la première question
        displayQuestion(selectedRadioButtonId);

        // Gestion du clic sur le bouton de validation
        validateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });

        // Si le chronomètre est activé, afficher le chronomètre
        if (isTimerEnabled) {
            timerTextView.setVisibility(View.VISIBLE);
            startTimer();
        } else {
            timerTextView.setVisibility(View.GONE);
        }
    }


    /**
     * Méthode pour afficher la question actuelle dans la TextView en fonction de l'opération sélectionnée.
     */
    private void displayQuestion(String selectedRadioButtonId) {
        String operation;
        if (selectedRadioButtonId.compareTo("Addition") == 0) {
            operation = "+";
        } else if (selectedRadioButtonId.compareTo("Soustraction")==0) {
            operation = "-";
        } else if (selectedRadioButtonId.compareTo("Multiplication")== 0) {
            operation = "x";
        } else {
            operation = ""; // Opération par défaut, si nécessaire
        }

        // Création de la question en fonction de l'opération et du numéro de table
        String question = currentNumber + " " + operation + " " + tableNumber + " = ?";
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
                int result;
                if (selectedRadioButtonId.compareTo("Addition") == 0) {
                    result = currentNumber + tableNumber;
                } else if (selectedRadioButtonId.compareTo("Soustraction")==0) {
                    result = currentNumber - tableNumber;
                } else
                    result = currentNumber * tableNumber;
                if (result == answer) {
                    Toast.makeText(this, "Correct! Passons à la question suivante.", Toast.LENGTH_SHORT).show();
                    currentNumber++;
                    errorCount = 0; // Réinitialisation du compteur d'erreurs
                    if (currentNumber <= 10) {
                        displayQuestion(selectedRadioButtonId);
                        answerEditText.setText("");
                    } else {
                        // Arrêter le chronomètre si toutes les questions sont correctement répondues
                        if (countDownTimer != null) {
                            countDownTimer.cancel();
                        }
                        // Création d'une nouvelle Intent pour ouvrir l'activité de félicitations
                        Intent intent = new Intent(this, FelicitationsActivity.class);
                        finish();
                        startActivity(intent);
                    }
                } else {
                    errorCount++; // Incrémentation du compteur d'erreurs
                    if (errorCount > 2) {
                        Toast.makeText(this, "Il semble que vous ayez du mal avec cette question. La réponse est " + result + ".", Toast.LENGTH_SHORT).show();
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

    /**
     * Méthode pour démarrer le chronomètre.
     */
    private void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {
                // Action à effectuer lorsque le chronomètre est terminé
                // Par exemple, fermer cette activité et ouvrir une nouvelle activité "DommageActivity"
                finish();
                startActivity(new Intent(QuestionActivity.this, DommageActivity.class));
            }
        }.start();
    }

    /**
     * Méthode pour mettre à jour l'affichage du chronomètre.
     */
    private void updateTimer() {
        int seconds = (int) (timeLeftInMillis / 1000);
        String timeLeftFormatted = String.format("%02d", seconds);
        timerTextView.setText(timeLeftFormatted);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}
