package fr.iut2.ecouledesloustiques.db.user;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao(); // Ajoutez cette ligne pour retourner le DAO de l'entité User
}

