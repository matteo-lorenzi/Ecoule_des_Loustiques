package fr.iut2.ecouledesloustiques.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface QuizDao {
    @Insert
    void insertQuestion(Question question);

    @Insert
    void insertReponse(Reponse reponse);

    @Query("SELECT * FROM questions WHERE tag = :tag")
    List<Question> getQuestions(String tag);

    @Query("SELECT * FROM reponses WHERE question_id = :questionId")
    List<Reponse> getReponses(int questionId);
}
