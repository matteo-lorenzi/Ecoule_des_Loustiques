package fr.iut2.ecouledesloustiques.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "reponses")
public class Reponse {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "reponse_text")
    public String reponseText;

    @ColumnInfo(name = "est_correcte")
    public boolean estCorrecte;

    @ColumnInfo(name = "question_id")
    public int questionId;
}
