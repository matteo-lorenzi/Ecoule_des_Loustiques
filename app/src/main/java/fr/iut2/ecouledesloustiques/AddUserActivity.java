/**
 * Cette classe représente une activité permettant à l'utilisateur d'ajouter un nouvel utilisateur dans la base de données.
 * L'utilisateur peut saisir le nom et le prénom de l'utilisateur à ajouter, puis appuyer sur un bouton pour sauvegarder les informations.
 * Une fois sauvegardé, un toast s'affiche pour informer l'utilisateur que l'opération a été réussie.
 */
package fr.iut2.ecouledesloustiques;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import fr.iut2.ecouledesloustiques.db.user.DatabaseClient;
import fr.iut2.ecouledesloustiques.db.user.User;

public class AddUserActivity extends AppCompatActivity {

    // DATA
    private DatabaseClient mDb;

    // VIEW
    private EditText editTextNomView;
    private EditText editTextPrenomView;
    private Button saveView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        // Récupération du DatabaseClient
        mDb = DatabaseClient.getInstance(getApplicationContext());

        // Récupérer les vues
        editTextNomView = findViewById(R.id.editTextNom);
        editTextPrenomView = findViewById(R.id.editTextPrenom);
        saveView = findViewById(R.id.button_save);

        // Associer un événement au bouton save
        saveView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveUser();
            }
        });
    }

    /**
     * Méthode pour sauvegarder un nouvel utilisateur dans la base de données.
     * Les informations sont extraites des champs de saisie.
     * Si les champs requis ne sont pas remplis, des erreurs sont affichées.
     * Une fois l'utilisateur ajouté à la base de données, l'activité se termine et affiche un toast de confirmation.
     */
    private void saveUser() {

        // Récupérer les informations contenues dans les vues
        final String sNom = editTextNomView.getText().toString().trim();
        final String sPrenom = editTextPrenomView.getText().toString().trim();

        // Vérifier les informations fournies par l'utilisateur
        if (sNom.isEmpty()) {
            editTextNomView.setError("Task required");
            editTextNomView.requestFocus();
            return;
        }

        if (sPrenom.isEmpty()) {
            editTextPrenomView.setError("Desc required");
            editTextPrenomView.requestFocus();
            return;
        }

        /**
         * Classe asynchrone pour sauvegarder l'utilisateur dans la base de données de manière asynchrone.
         * Une fois la sauvegarde terminée, un toast est affiché pour indiquer que l'opération a réussi.
         */
        class SaveUser extends AsyncTask<Void, Void, User> {

            @Override
            protected User doInBackground(Void... voids) {

                // Création d'un nouvel utilisateur
                User user = new User();
                user.setNom(sNom);
                user.setPrenom(sPrenom);

                // Ajout de l'utilisateur à la base de données
                long id = mDb.getAppDatabase()
                        .userDao()
                        .insert(user);

                // Mise à jour de l'ID de l'utilisateur
                // Nécessaire si on souhaite avoir accès à l'ID plus tard dans l'activité
                user.setId(id);

                return user;
            }

            @Override
            protected void onPostExecute(User user) {
                super.onPostExecute(user);

                // Une fois l'utilisateur ajouté avec succès, terminer l'activité AddUserActivity
                setResult(RESULT_OK);
                finish();
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        }

        // Exécution de la tâche asynchrone pour sauvegarder l'utilisateur
        SaveUser st = new SaveUser();
        st.execute();
    }
}
