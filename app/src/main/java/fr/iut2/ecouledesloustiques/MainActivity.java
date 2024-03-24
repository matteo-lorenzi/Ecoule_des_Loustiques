/**
 * Cette classe représente l'activité principale de l'application, affichant la liste des utilisateurs et permettant d'en ajouter de nouveaux.
 * Les utilisateurs peuvent être ajoutés en cliquant sur le bouton "Ajouter", ce qui ouvre une nouvelle activité d'ajout d'utilisateur.
 * Les utilisateurs existants sont affichés dans une ListView, où les clics courts et longs sont gérés pour fournir des rétroactions à l'utilisateur.
 * Les données des utilisateurs sont stockées dans une base de données SQLite via Room.
 */
package fr.iut2.ecouledesloustiques;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import fr.iut2.ecouledesloustiques.db.DatabaseClient;
import fr.iut2.ecouledesloustiques.db.User;

public class MainActivity extends AppCompatActivity {

    // Constante pour le code de requête de l'ajout d'utilisateur
    private static final int REQUEST_CODE_ADD = 0;

    // Base de données
    private DatabaseClient mDb;

    // Adapter pour la liste des utilisateurs
    private UsersAdapter adapter;

    // Vues
    private Button buttonAdd;
    private ListView listUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialisation de la base de données
        mDb = DatabaseClient.getInstance(getApplicationContext());

        // Récupération des vues
        listUser = findViewById(R.id.listUser);
        buttonAdd = findViewById(R.id.button_add);

        // Initialisation de l'adapter pour la liste des utilisateurs
        adapter = new UsersAdapter(this, new ArrayList<User>());
        listUser.setAdapter(adapter);

        // Gestion du clic sur le bouton d'ajout d'utilisateur
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddUserActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ADD);
            }
        });

        // Gestion du clic court sur un utilisateur de la liste
        listUser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                User user = adapter.getItem(position);
                Toast.makeText(MainActivity.this, "Clique : " + user.getNom(), Toast.LENGTH_SHORT).show();
            }
        });

        // Gestion du clic long sur un utilisateur de la liste
        listUser.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                User user = adapter.getItem(position);
                Toast.makeText(MainActivity.this, "Clique long : " + user.getNom(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Mise à jour de la liste des utilisateurs à chaque démarrage de l'activité
        getUsers();
    }

    /**
     * Méthode permettant de récupérer la liste des utilisateurs depuis la base de données de manière asynchrone.
     * Les utilisateurs récupérés sont ensuite affichés dans la ListView à l'aide de l'adapter.
     */
    private void getUsers() {
        class GetUsers extends AsyncTask<Void, Void, List<User>> {
            @Override
            protected List<User> doInBackground(Void... voids) {
                return mDb.getAppDatabase()
                        .userDao()
                        .getAll();
            }

            @Override
            protected void onPostExecute(List<User> users) {
                super.onPostExecute(users);
                adapter.clear();
                adapter.addAll(users);
                adapter.notifyDataSetChanged();
            }
        }
        GetUsers getUsers = new GetUsers();
        getUsers.execute();
    }
}
