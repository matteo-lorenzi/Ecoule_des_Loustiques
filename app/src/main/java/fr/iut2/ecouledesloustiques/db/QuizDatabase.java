package fr.iut2.ecouledesloustiques.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Question.class, Reponse.class}, version = 1)
public abstract class QuizDatabase extends RoomDatabase {
    public abstract QuizDao quizDao();
}
