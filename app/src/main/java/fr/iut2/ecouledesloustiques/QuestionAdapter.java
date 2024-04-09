package fr.iut2.ecouledesloustiques;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import fr.iut2.ecouledesloustiques.db.quizz.Question;

public class QuestionAdapter extends ArrayAdapter<Question> {

    /**
     * Constructeur de l'adapter.
     *
     * @param mCtx      Contexte de l'application.
     * @param questionListList  Liste des utilisateurs à afficher.
     */
    public QuestionAdapter(Context mCtx, List<Question> questionListList) {
        super(mCtx, R.layout.template_quizz, questionListList);
    }

    /**
     * Remplit une ligne de la ListView avec les informations de l'utilisateur associé.
     *
     * @param position      Position de l'utilisateur dans la liste.
     * @param convertView   Vue recyclée pour afficher l'utilisateur.
     * @param parent        Parent de la vue convertie.
     * @return              Vue remplie avec les informations de l'utilisateur.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Récupération de l'utilisateur à la position donnée
        final Question question = getItem(position);

        // Si la vue convertie (convertView) est nulle, inflate une nouvelle vue
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.template_quizz, parent, false);
        }

        // Récupération des TextViews dans le template
        TextView textViewQuestion= convertView.findViewById(R.id.question_text_view);


        // Mise à jour des TextViews avec les informations de l'utilisateur
        textViewQuestion.setText(question.getQuestion());

        return convertView;
    }

}

