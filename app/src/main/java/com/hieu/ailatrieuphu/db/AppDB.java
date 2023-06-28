package com.hieu.ailatrieuphu.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.hieu.ailatrieuphu.db.dao.QuestionDAO;
import com.hieu.ailatrieuphu.db.entites.Question;

@Database(entities = {Question.class},version = 1)
public abstract class AppDB extends RoomDatabase {
    public abstract QuestionDAO getQuestionDAO();
}
