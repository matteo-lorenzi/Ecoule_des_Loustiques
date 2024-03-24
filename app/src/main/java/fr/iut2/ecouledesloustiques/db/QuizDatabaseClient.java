package fr.iut2.ecouledesloustiques.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class QuizDatabaseClient {

    private static QuizDatabaseClient instance;
    private QuizDatabase quizzDatabase;

    private QuizDatabaseClient(final Context context) {
        quizzDatabase = Room.databaseBuilder(context, QuizDatabase.class, "EcoleDesLoustics")
                .addCallback(roomDatabaseCallback)
                .build();
    }

    public static synchronized QuizDatabaseClient getInstance(Context context) {
        if (instance == null) {
            instance = new QuizDatabaseClient(context);
        }
        return instance;
    }

    public QuizDatabase getAppDatabase() {
        return quizzDatabase;
    }

    RoomDatabase.Callback roomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // Insérer des questions et des réponses pour la catégorie "français"
            db.execSQL("INSERT INTO questions (question_text, tag) VALUES(\"Quel est le féminin de 'beau' ?\", \"français\");");
            db.execSQL("INSERT INTO reponses (reponse_text, est_correcte, question_id) VALUES(\"belle\", 1, 1), (\"beau\", 0, 1), (\"beaux\", 0, 1);");

            db.execSQL("INSERT INTO questions (question_text, tag) VALUES(\"Quel est le pluriel de 'cheval' ?\", \"français\");");
            db.execSQL("INSERT INTO reponses (reponse_text, est_correcte, question_id) VALUES(\"chevaux\", 1, 2), (\"chevals\", 0, 2), (\"cheval\", 0, 2);");

            // Continuez à insérer plus de questions et de réponses pour la catégorie "français"...

            // Insérer des questions et des réponses pour la catégorie "histoire"
            db.execSQL("INSERT INTO questions (question_text, tag) VALUES(\"Qui a découvert l'Amérique ?\", \"histoire\");");
            db.execSQL("INSERT INTO reponses (reponse_text, est_correcte, question_id) VALUES(\"Christophe Colomb\", 1, 3), (\"Napoléon Bonaparte\", 0, 3), (\"Julius Caesar\", 0, 3);");

            db.execSQL("INSERT INTO questions (question_text, tag) VALUES(\"Quand a eu lieu la Révolution française ?\", \"histoire\");");
            db.execSQL("INSERT INTO reponses (reponse_text, est_correcte, question_id) VALUES(\"1789\", 1, 4), (\"1492\", 0, 4), (\"1945\", 0, 4);");

            // Continuez à insérer plus de questions et de réponses pour la catégorie "histoire"...

            // Insérer des questions et des réponses pour la catégorie "géographie"
            db.execSQL("INSERT INTO questions (question_text, tag) VALUES(\"Quelle est la capitale de la France ?\", \"géographie\");");
            db.execSQL("INSERT INTO reponses (reponse_text, est_correcte, question_id) VALUES(\"Paris\", 1, 5), (\"Lyon\", 0, 5), (\"Marseille\", 0, 5);");

            db.execSQL("INSERT INTO questions (question_text, tag) VALUES(\"Quel est le plus grand océan du monde ?\", \"géographie\");");
            db.execSQL("INSERT INTO reponses (reponse_text, est_correcte, question_id) VALUES(\"Océan Pacifique\", 1, 6), (\"Océan Atlantique\", 0, 6), (\"Océan Indien\", 0, 6);");

            // Continuez à insérer plus de questions et de réponses pour la catégorie "géographie"...
        }
    };

}
