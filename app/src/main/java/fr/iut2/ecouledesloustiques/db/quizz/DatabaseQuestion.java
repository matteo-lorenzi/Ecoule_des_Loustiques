package fr.iut2.ecouledesloustiques.db.quizz;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import fr.iut2.ecouledesloustiques.db.user.DatabaseClient;

public class DatabaseQuestion {

    private static DatabaseQuestion instance;
    private AppDatabase appDatabase;

    private DatabaseQuestion(final Context context) {
        appDatabase = Room.databaseBuilder(context, AppDatabase.class, "EcoleDesLoustics").addCallback(roomDatabaseCallback).build();
    }

    // Méthode statique
    // Retourne l'instance de l'objet DatabaseClient
    public static synchronized DatabaseQuestion getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseQuestion(context);
        }
        return instance;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }

    RoomDatabase.Callback roomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(SupportSQLiteDatabase db) {
            super.onCreate(db);
            db.execSQL("INSERT INTO questions (question, reponse, tag) VALUES(\"Capitale France\", \"Paris\", \"Géographie\");");
        }
    };

}
