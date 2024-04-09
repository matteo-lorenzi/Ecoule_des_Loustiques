package fr.iut2.ecouledesloustiques;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class FrancaisActivity extends AppCompatActivity {

    private TextView questionTextView;
    private RadioGroup answersRadioGroup;
    private Button nextQuestionButton;

    private ArrayList<QuestionAnswerPair> questionAnswerPairs;
    private int currentQuestionIndex = 0;
    private int errorCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_francais);

        // Initialize views
        questionTextView = findViewById(R.id.questionTextView);
        answersRadioGroup = findViewById(R.id.answersRadioGroup);
        nextQuestionButton = findViewById(R.id.nextQuestionButton);

        // Initialize question-answer pairs
        questionAnswerPairs = new ArrayList<>();
        // Add questions and answers
        addQuestionAnswerPairs();

        // Shuffle the list
        Collections.shuffle(questionAnswerPairs);

        // Display the first question
        displayQuestion();

        nextQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check the answer and move to the next question
                checkAnswer();
            }
        });
    }

    private void addQuestionAnswerPairs() {
        // Add question-answer pairs with multiple possible answers
        ArrayList<String> possibleAnswers1 = new ArrayList<>();

        possibleAnswers1.add("gallinacé");
        possibleAnswers1.add("poule");
        possibleAnswers1.add("volaille");
        questionAnswerPairs.add(new QuestionAnswerPair("Quelle est la femelle du 'coq' ?", possibleAnswers1, 1)); // 0 représente l'index de la bonne réponse

        ArrayList<String> possibleAnswers2 = new ArrayList<>();

        possibleAnswers2.add("Alexandre Dumas");
        possibleAnswers2.add("Émile Zola");
        possibleAnswers2.add("Victor Hugo");
        questionAnswerPairs.add(new QuestionAnswerPair("Qui a écrit 'Les Misérables' ?", possibleAnswers2, 2)); // 0 représente l'index de la bonne réponse

        ArrayList<String> possibleAnswers3 = new ArrayList<>();
        possibleAnswers3.add("Charles Perrault");
        possibleAnswers3.add("Hans Christian Andersen");
        possibleAnswers3.add("Madame de Villeneuve");
        questionAnswerPairs.add(new QuestionAnswerPair("Quel est l'auteur de 'La Belle et la Bête' ?", possibleAnswers3, 0)); // 0 représente l'index de la bonne réponse

        ArrayList<String> possibleAnswers4 = new ArrayList<>();
        possibleAnswers4.add("Cendrillon");
        possibleAnswers4.add("Blanche-Neige");
        possibleAnswers4.add("petit chaperon");
        questionAnswerPairs.add(new QuestionAnswerPair("Comment s'appelle le petit chaperon rouge dans le conte de Charles Perrault ?", possibleAnswers4, 0)); // 0 représente l'index de la bonne réponse

        ArrayList<String> possibleAnswers5 = new ArrayList<>();

        possibleAnswers5.add("Wendy");
        possibleAnswers5.add("Alice");
        possibleAnswers5.add("Dorothy");
        questionAnswerPairs.add(new QuestionAnswerPair("Quel est le nom du personnage principal dans 'Alice au pays des merveilles' ?", possibleAnswers5, 1)); // 0 représente l'index de la bonne réponse

        ArrayList<String> possibleAnswers6 = new ArrayList<>();

        possibleAnswers6.add("Charles Perrault");
        possibleAnswers6.add("Jules Verne");
        possibleAnswers6.add("Antoine de Saint-Exupéry");
        questionAnswerPairs.add(new QuestionAnswerPair("Qui a écrit 'Le Petit Prince' ?", possibleAnswers6, 2)); // 0 représente l'index de la bonne réponse


        // Add more question-answer pairs with multiple possible answers...
    }

    private void displayQuestion() {
        if (currentQuestionIndex < questionAnswerPairs.size()) {
            QuestionAnswerPair currentQuestion = questionAnswerPairs.get(currentQuestionIndex);
            // Afficher la question
            questionTextView.setText(currentQuestion.getQuestion());

            // Effacer les réponses précédentes
            answersRadioGroup.removeAllViews();

            // Ajouter les boutons radio pour chaque réponse possible
            for (int i = 0; i < currentQuestion.getAnswers().size(); i++) {
                RadioButton radioButton = new RadioButton(this);
                radioButton.setText(currentQuestion.getAnswers().get(i));
                // Marquer la bonne réponse
                if (i == currentQuestion.getCorrectAnswerIndex()) {
                    radioButton.setTag(true); // Vous pouvez utiliser un tag pour marquer la bonne réponse
                } else {
                    radioButton.setTag(false);
                }
                answersRadioGroup.addView(radioButton);
            }
        } else {
            // Si toutes les questions ont été affichées, vous pouvez afficher un message ou une action appropriée.
            Toast.makeText(this, "Toutes les questions ont été affichées", Toast.LENGTH_SHORT).show();
        }
    }

    private void checkAnswer() {
        int correctAnswerIndex = questionAnswerPairs.get(currentQuestionIndex).getCorrectAnswerIndex();

        int selectedRadioButtonId = answersRadioGroup.getCheckedRadioButtonId();

        if (selectedRadioButtonId != -1) {
            RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);

            int selectedAnswerIndex = answersRadioGroup.indexOfChild(selectedRadioButton);

            if (selectedAnswerIndex == correctAnswerIndex) {
                Toast.makeText(this, "Bonne réponse!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Mauvaise réponse!", Toast.LENGTH_SHORT).show();
                errorCount++;
            }

            // Passer à la prochaine question
            currentQuestionIndex++;

            if (currentQuestionIndex < 5) {
                displayQuestion();
            } else {
                // Afficher une activité différente en fonction du nombre d'erreurs
                if (errorCount > 0) {
                    // Afficher l'activité pour les erreurs
                    Intent intent = new Intent(this, DommageActivity.class);
                    intent.putExtra("errorCount", errorCount);
                    startActivity(intent);
                    finish();
                } else {
                    // Afficher l'activité pour le succès
                    Intent intent = new Intent(this, FelicitationsActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        } else {
            // Aucune réponse sélectionnée, afficher un message d'erreur
            Toast.makeText(this, "Veuillez sélectionner une réponse", Toast.LENGTH_SHORT).show();
        }
    }


}
