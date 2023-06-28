package com.hieu.ailatrieuphu;

import com.hieu.ailatrieuphu.db.entites.Question;

import java.util.List;

public class Storage {
    public List<Question> listQuestion;

    public void setListQuestion(List<Question> listQuestion) {
        this.listQuestion = listQuestion;
    }

    public List<Question> getListQuestion() {
        return listQuestion;
    }
}
