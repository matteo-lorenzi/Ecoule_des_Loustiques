package fr.iut2.ecouledesloustiques;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import fr.iut2.ecouledesloustiques.db.quizz.DatabaseQuestion;
import fr.iut2.ecouledesloustiques.db.quizz.Question;

public class CultureG extends AppCompatActivity {
    private DatabaseQuestion mDbq;
    private QuestionAdapter adapter;
    private ListView listQuestionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_culture_g);

        mDbq = DatabaseQuestion.getInstance(getApplicationContext());

        listQuestionView = findViewById(R.id.question_View);

        // Initialiser l'adaptateur avec la liste des questions
        adapter = new QuestionAdapter(this, new ArrayList<Question>());
        // Définir l'adaptateur pour la liste
        listQuestionView.setAdapter(adapter);
    }


    @Override
    protected void onStart() {
        super.onStart();
        getQuestion();
    }

    private void getQuestion() {
        class GetQuestions extends AsyncTask<Void, Void, List<Question>> {
            @Override
            protected List<Question> doInBackground(Void... voids) {
                return mDbq.getAppDatabase()
                        .questionDao()
                        .getAll();
            }
        }
        GetQuestions getQuestions = new GetQuestions();
        getQuestions.execute();
    }
}


