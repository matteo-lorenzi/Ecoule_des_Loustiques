/**
 * Cette classe représente l'activité de quiz où l'utilisateur répond à une série de questions de culture générale.
 * Les questions sont récupérées depuis une base de données SQLite et affichées une par une à l'utilisateur.
 * L'utilisateur sélectionne une réponse parmi les options proposées, et son score est calculé en fonction de ses réponses correctes.
 * Une fois que toutes les questions ont été répondues, le score total est affiché à l'utilisateur.
 */
package fr.iut2.ecouledesloustiques;

import android.app.AlertDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import fr.iut2.ecouledesloustiques.db.Question;
import fr.iut2.ecouledesloustiques.db.QuizDatabaseClient;
import fr.iut2.ecouledesloustiques.db.Reponse;

public class QuizActivity extends AppCompatActivity {

    // Base de données
    private QuizDatabaseClient mDb;

    // Liste des questions du quiz
    private List<Question> questions;

    // Index de la question actuelle
    private int currentQuestionIndex = 0;

    // Score de l'utilisateur
    private int score = 0;

    // Éléments de l'interface utilisateur
    private TextView questionText;
    private RadioButton[] answerButtons = new RadioButton[3];
    private Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Initialisation de la base de données
        mDb = QuizDatabaseClient.getInstance(getApplicationContext());

        // Récupération des vues
        questionText = findViewById(R.id.question_text);
        answerButtons[0] = findViewById(R.id.answer_button_1);
        answerButtons[1] = findViewById(R.id.answer_button_2);
        answerButtons[2] = findViewById(R.id.answer_button_3);
        nextButton = findViewById(R.id.next_button);

        // Gestion du clic sur le bouton "Suivant"
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswerAndUpdateScore();
                currentQuestionIndex++;
                if (currentQuestionIndex < questions.size()) {
                    displayQuestion(currentQuestionIndex);
                } else {
                    showScore();
                }
            }
        });

        // Récupération des questions depuis la base de données
        getQuestions();
    }

    /**
     * Méthode pour récupérer les questions depuis la base de données.
     */
    private void getQuestions() {
        class GetQuestions extends AsyncTask<Void, Void, List<Question>> {
            @Override
            protected List<Question> doInBackground(Void... voids) {
                return mDb.getAppDatabase().quizDao().getQuestions("culture générale");
            }

            @Override
            protected void onPostExecute(List<Question> questionsFromDb) {
                questions = questionsFromDb;
                if (!questions.isEmpty()) {
                    displayQuestion(currentQuestionIndex);
                }
            }
        }

        GetQuestions getQuestions = new GetQuestions();
        getQuestions.execute();
    }

    /**
     * Méthode pour afficher une question spécifique dans l'interface utilisateur.
     */
    private void displayQuestion(int questionIndex) {
        Question question = questions.get(questionIndex);
        questionText.setText(question.questionText);

        // Récupération des réponses pour cette question depuis la base de données
        class GetAnswers extends AsyncTask<Integer, Void, List<Reponse>> {
            @Override
            protected List<Reponse> doInBackground(Integer... integers) {
                return mDb.getAppDatabase().quizDao().getReponses(integers[0]);
            }

            @Override
            protected void onPostExecute(List<Reponse> reponses) {
                for (int i = 0; i < reponses.size(); i++) {
                    answerButtons[i].setText(reponses.get(i).reponseText);
                    answerButtons[i].setTag(reponses.get(i).estCorrecte);
                }
            }
        }

        GetAnswers getAnswers = new GetAnswers();
        getAnswers.execute(question.id);
    }

    /**
     * Méthode pour vérifier la réponse sélectionnée par l'utilisateur et mettre à jour le score.
     */
    private void checkAnswerAndUpdateScore() {
        for (RadioButton answerButton : answerButtons) {
            if (answerButton.isChecked() && (boolean) answerButton.getTag()) {
                score++;
                break;
            }
        }
    }

    /**
     * Méthode pour afficher le score total de l'utilisateur à la fin du quiz.
     */
    private void showScore() {
        // Affichage du score à l'utilisateur
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Quiz Finished");
        builder.setMessage("Your score is: " + score);
        builder.setPositiveButton("OK", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
