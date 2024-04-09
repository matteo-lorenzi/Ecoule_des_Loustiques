package fr.iut2.ecouledesloustiques.db.quizz;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface QuestionDao {
    @Insert
    void insert(Question question);

    @Query("SELECT * FROM questions WHERE tag = :tag")
    List<Question> getQuestionsByTag(String tag);

    @Query("SELECT * FROM questions")
    List<Question> getAll();


    @Query("DELETE FROM questions")
    void deleteAll();
}

