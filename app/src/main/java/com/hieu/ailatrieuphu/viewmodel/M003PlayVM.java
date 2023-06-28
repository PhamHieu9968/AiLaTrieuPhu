package com.hieu.ailatrieuphu.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.hieu.ailatrieuphu.db.entites.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class M003PlayVM extends BaseVM {
    private static final String TAG = M003PlayVM.class.getName();
    private final MutableLiveData<Integer> time = new MutableLiveData<>(30);
    private final MutableLiveData<Integer> money = new MutableLiveData<>(0);
    private final MutableLiveData<Integer> index = new MutableLiveData<>(0);
    private boolean isRunning;
    private boolean isPaused = true;
    private String answer;
    private Question q;
    public Thread thCD;

    public MutableLiveData<Integer> getTime() {
        return time;
    }

    public MutableLiveData<Integer> getIndex() {
        return index;
    }

    public MutableLiveData<Integer> getMoney() {
        return money;
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String ans) {
        answer = ans;
    }

    public void setQuestion(Question q) {
        this.q = q;
    }

    public Question getQ() {
        return q;
    }

    public void startCountDown() {
       thCD =  new Thread(new Runnable() {
            @Override
            public void run() {
                while (isRunning) {
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }
                    if (isPaused) continue;
                    if (time.getValue() != null) {
                        if (time.getValue() > 0) {
                            time.postValue(time.getValue() - 1);
                        }
                    }
                }
            }
        });
       thCD.start();
    }

    @Override
    protected void onCleared() {
        isRunning = false;
        super.onCleared();
    }

    public boolean checkAnswer() {
        if (q.trueCase.equals(getAnswer())) {
            nextQuestion();
            return true;

        }
        return false;
    }

    public void nextQuestion() {
        if (index.getValue() != null) {
            index.setValue(index.getValue() + 1);
        }
    }

    public void updateTime() {
        time.postValue(30);
    }

    public List<Integer> delete5050(int indexTrue, int indexA, int indexB, int indexC, int indexD) {
        List<Integer> rs = new ArrayList<>();
        List<Integer> delete = new ArrayList<>();
        rs.add(indexA);
        rs.add(indexB);
        rs.add(indexC);
        rs.add(indexD);
        delete.add(indexTrue);
        Log.i(TAG, "delete5050: " + delete);
        rs.removeAll(delete);
        return rs;
    }
}
