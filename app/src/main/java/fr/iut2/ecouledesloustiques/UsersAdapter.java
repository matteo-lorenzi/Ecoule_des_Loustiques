/**
 * Adapter personnalisé pour afficher une liste d'utilisateurs dans une ListView.
 */
package fr.iut2.ecouledesloustiques;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import fr.iut2.ecouledesloustiques.db.User;

public class UsersAdapter extends ArrayAdapter<User> {

    /**
     * Constructeur de l'adapter.
     *
     * @param mCtx      Contexte de l'application.
     * @param userList  Liste des utilisateurs à afficher.
     */
    public UsersAdapter(Context mCtx, List<User> userList) {
        super(mCtx, R.layout.template_user, userList);
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
        final User user = getItem(position);

        // Si la vue convertie (convertView) est nulle, inflate une nouvelle vue
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.template_user, parent, false);
        }

        // Récupération des TextViews dans le template
        TextView textViewNom = convertView.findViewById(R.id.textViewUser);
        TextView textViewPrenom = convertView.findViewById(R.id.textViewPrenom);

        // Mise à jour des TextViews avec les informations de l'utilisateur
        textViewNom.setText(user.getNom());
        textViewPrenom.setText(user.getPrenom());

        return convertView;
    }
}
