package fr.iut2.ecouledesloustiques.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;

@Entity(tableName = "questions")
public class Question {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "question_text")
    public String questionText;

    @ColumnInfo(name = "tag")
    public String tag;
}

