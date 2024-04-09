package fr.iut2.ecouledesloustiques;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class FrancaisActivity extends AppCompatActivity {

    private TextView questionTextView;
    private EditText answerEditText;
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
        answerEditText = findViewById(R.id.answerEditText);
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
                checkAnswer();

                // Move to the next question if not reached the limit
                if (currentQuestionIndex < 9) { // Change 9 to 10 if you want to include 10 questions
                    currentQuestionIndex++;
                    displayQuestion();
                } else {
                    // All 10 questions have been answered, start a new activity
                    Intent intent;
                    if (errorCount == 0) {
                        intent = new Intent(FrancaisActivity.this, FelicitationsActivity.class);
                    } else {
                        intent = new Intent(FrancaisActivity.this, DommageActivity.class);
                        intent.putExtra("errorCount", errorCount); // Pass the error count to DommageActivity
                    }
                    startActivity(intent);
                    // Reset counters for a new session
                    errorCount = 0;
                    currentQuestionIndex = 0;
                }
            }
        });
    }


    private void addQuestionAnswerPairs() {
        // Add question-answer pairs
        questionAnswerPairs.add(new QuestionAnswerPair("Quel est le féminin de 'coq' ?", "poule"));
        questionAnswerPairs.add(new QuestionAnswerPair("Qui est l'auteur de 'Le Comte de Monte-Cristo' ?", "Alexandre Dumas"));
        questionAnswerPairs.add(new QuestionAnswerPair("Quel est le synonyme de 'rêver' ?", "songer"));
        questionAnswerPairs.add(new QuestionAnswerPair("Quel est le pluriel de 'cheval' ?", "chevaux"));
        questionAnswerPairs.add(new QuestionAnswerPair("Qui a écrit 'Les Contemplations' ?", "Victor Hugo"));
        questionAnswerPairs.add(new QuestionAnswerPair("Quel est le contraire de 'noir' ?", "blanc"));
        questionAnswerPairs.add(new QuestionAnswerPair("Quel est le synonyme de 'grand' ?", "vaste"));
        questionAnswerPairs.add(new QuestionAnswerPair("Qui est l'auteur de 'Les Liaisons dangereuses' ?", "Choderlos de Laclos"));
        questionAnswerPairs.add(new QuestionAnswerPair("Quel est le pluriel de 'père' ?", "pères"));
        questionAnswerPairs.add(new QuestionAnswerPair("Qui a écrit 'Les Contes d'Hoffmann' ?", "E.T.A. Hoffmann"));
        questionAnswerPairs.add(new QuestionAnswerPair("Quel est le féminin de 'mari' ?", "femme"));
        questionAnswerPairs.add(new QuestionAnswerPair("Quel est le synonyme de 'petit' ?", "minuscule"));
        questionAnswerPairs.add(new QuestionAnswerPair("Qui est l'auteur de 'La Peste' ?", "Albert Camus"));
        questionAnswerPairs.add(new QuestionAnswerPair("Quel est le contraire de 'long' ?", "court"));
        questionAnswerPairs.add(new QuestionAnswerPair("Qui est le président actuel de la France ?", "Emmanuel Macron"));
        questionAnswerPairs.add(new QuestionAnswerPair("Quel est le synonyme de 'joyeux' ?", "heureux"));
        questionAnswerPairs.add(new QuestionAnswerPair("Qui a écrit 'Les Misérables' ?", "Victor Hugo"));
        questionAnswerPairs.add(new QuestionAnswerPair("Quel est le pluriel de 'œil' ?", "yeux"));
        questionAnswerPairs.add(new QuestionAnswerPair("Qui est l'auteur de 'Madame Bovary' ?", "Gustave Flaubert"));
        questionAnswerPairs.add(new QuestionAnswerPair("Quel est le féminin de 'taureau' ?", "vache"));
        questionAnswerPairs.add(new QuestionAnswerPair("Quel est le synonyme de 'triste' ?", "mélancolique"));
        questionAnswerPairs.add(new QuestionAnswerPair("Qui a écrit 'Germinal' ?", "Émile Zola"));
        questionAnswerPairs.add(new QuestionAnswerPair("Quel est le pluriel de 'chat' ?", "chats"));
        questionAnswerPairs.add(new QuestionAnswerPair("Qui est l'auteur de 'Les Fleurs du Mal' ?", "Charles Baudelaire"));
        questionAnswerPairs.add(new QuestionAnswerPair("Quel est le contraire de 'vieux' ?", "jeune"));
        questionAnswerPairs.add(new QuestionAnswerPair("Qui est le Premier ministre actuel de la France ?", "Jean Castex"));
        questionAnswerPairs.add(new QuestionAnswerPair("Quel est le synonyme de 'intelligent' ?", "sagace"));
        questionAnswerPairs.add(new QuestionAnswerPair("Qui a écrit 'Le Petit Prince' ?", "Antoine de Saint-Exupéry"));

        // Add more question-answer pairs...
    }

    private void displayQuestion() {
        // Display the current question
        questionTextView.setText(questionAnswerPairs.get(currentQuestionIndex).getQuestion());

        // Clear the answer field
        answerEditText.setText("");
    }

    private void checkAnswer() {
        // Get the user's answer
        String userAnswer = answerEditText.getText().toString().trim();

        // Get the expected answer
        String expectedAnswer = questionAnswerPairs.get(currentQuestionIndex).getAnswer();

        // Check if the user's answer is correct
        if (userAnswer.equalsIgnoreCase(expectedAnswer)) {
            Toast.makeText(this, "Bonne réponse!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Mauvaise réponse. La réponse correcte est: " + expectedAnswer, Toast.LENGTH_SHORT).show();
            // Increase the error count
            errorCount++;
        }
    }
}
