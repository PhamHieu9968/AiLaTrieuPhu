package com.hieu.ailatrieuphu.db.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.hieu.ailatrieuphu.db.entites.Question;

import java.util.List;

@Dao
public interface QuestionDAO {
    String SELECT_15_Q = "SELECT*FROM(SELECT*FROM question WHERE level = 1 ORDER by random() LIMIT 1)\n" +
            "UNION SELECT*FROM(SELECT*FROM question WHERE level = 2 ORDER by random() LIMIT 1)\n" +
            "UNION SELECT*FROM(SELECT*FROM question WHERE level = 3 ORDER by random() LIMIT 1)\n" +
            "UNION SELECT*FROM(SELECT*FROM question WHERE level = 4 ORDER by random() LIMIT 1)\n" +
            "UNION SELECT*FROM(SELECT*FROM question WHERE level = 5 ORDER by random() LIMIT 1)\n" +
            "UNION SELECT*FROM(SELECT*FROM question WHERE level = 6 ORDER by random() LIMIT 1)\n" +
            "UNION SELECT*FROM(SELECT*FROM question WHERE level = 7 ORDER by random() LIMIT 1)\n" +
            "UNION SELECT*FROM(SELECT*FROM question WHERE level = 8 ORDER by random() LIMIT 1)\n" +
            "UNION SELECT*FROM(SELECT*FROM question WHERE level = 9 ORDER by random() LIMIT 1)\n" +
            "UNION SELECT*FROM(SELECT*FROM question WHERE level = 10 ORDER by random() LIMIT 1)\n" +
            "UNION SELECT*FROM(SELECT*FROM question WHERE level = 11 ORDER by random() LIMIT 1)\n" +
            "UNION SELECT*FROM(SELECT*FROM question WHERE level = 12 ORDER by random() LIMIT 1)\n" +
            "UNION SELECT*FROM(SELECT*FROM question WHERE level = 13 ORDER by random() LIMIT 1)\n" +
            "UNION SELECT*FROM(SELECT*FROM question WHERE level = 14 ORDER by random() LIMIT 1)\n" +
            "UNION SELECT*FROM(SELECT*FROM question WHERE level = 15 ORDER by random() LIMIT 1)\n" +
            "ORDER BY level ASC";
    @Query(SELECT_15_Q)
    List<Question> getAllQuestion();

    @Query("SELECT * FROM Question WHERE level = :level ORDER BY RANDOM() LIMIT 1")
    Question getQuestionByLevel(String level);
}
