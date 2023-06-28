package com.hieu.ailatrieuphu.view.frg;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.MutableLiveData;

import com.hieu.ailatrieuphu.App;
import com.hieu.ailatrieuphu.MediaManager;
import com.hieu.ailatrieuphu.R;
import com.hieu.ailatrieuphu.databinding.FrgM003PlayBinding;
import com.hieu.ailatrieuphu.db.entites.Question;
import com.hieu.ailatrieuphu.view.act.MainActivity;
import com.hieu.ailatrieuphu.view.dialog.GameOverDialog;
import com.hieu.ailatrieuphu.view.dialog.AudienceDialog;
import com.hieu.ailatrieuphu.view.dialog.InformReadyDialog;
import com.hieu.ailatrieuphu.viewmodel.M003PlayVM;

import java.util.Collections;
import java.util.List;

public class M003PlayFrg extends BaseFrg<FrgM003PlayBinding, M003PlayVM> {
    public static final String TAG = M003PlayFrg.class.getName();
    public static final int[] ID_SONG_Q = {R.raw.ques1, R.raw.ques2, R.raw.ques3, R.raw.ques4, R.raw.ques5, R.raw.ques6, R.raw.ques7, R.raw.ques8, R.raw.ques9, R.raw.ques10, R.raw.ques11, R.raw.ques12, R.raw.ques13, R.raw.ques14, R.raw.ques15};

    public final MutableLiveData<Boolean> isChange =new MutableLiveData<>(true);
    public final MutableLiveData<Boolean> is5050 =new MutableLiveData<>(true);
    public final MutableLiveData<Boolean> isAudience =new MutableLiveData<>(true);


    @SuppressLint("SetTextI18n")
    @Override
    protected void initViews() {
        MediaManager.getInstance().playBG(R.raw.song_bg_1_5);
        viewModel.getTime().observe(this, this::checkTime);
        viewModel.startCountDown();
        viewModel.getMoney().observe(this, this::updateScore);
        viewModel.setPaused(true);
        viewModel.setRunning(true);

        initQuestion();
        binding.iv5050.setOnClickListener(this);
        binding.ivAudience.setOnClickListener(this);
        binding.ivChange.setOnClickListener(this);
        binding.ivStop.setOnClickListener(this);
        binding.tvAns1.setOnClickListener(this);
        binding.tvAns2.setOnClickListener(this);
        binding.tvAns3.setOnClickListener(this);
        binding.tvAns4.setOnClickListener(this);
    }

    private void updateScore(Integer value) {
        binding.tvMoney.setText(value.toString());
        //viewModel.nextQuestion();
    }

    @SuppressLint("SetTextI18n")
    private void checkTime(Integer value) {
        binding.tvTime.setText(value.toString());
        if (value == 0) {
            MediaManager.getInstance().stopBG();
            gameOver();
        }
    }

    private void gameOver() {
        if (viewModel.getAnswer() == null || !viewModel.checkAnswer()) {
            showDialogGameOver();
        }
    }

    private void showDialogGameOver() {
        GameOverDialog gameOver = new GameOverDialog(context, ((data, key) -> {
            if (key.equals(GameOverDialog.KEY_PLAY_AGAIN)) {
                playAgain();
            } else if (key.equals(GameOverDialog.KEY_QUIT_GAME)) {
                quitGame();
            }
        }));
        gameOver.show();
    }

    private void quitGame() {
        getActivity().finish();
        System.exit(0);
    }

    private void playAgain() {
        startActivity(new Intent(context, MainActivity.class));
    }

    @Override
    protected void clickView(View v) {
        if (v instanceof TextView) {
            MediaManager.getInstance().playGame(R.raw.song_touch, mp -> updateChooseAnswerUI(v));
            v.getBackground().setLevel(1);
            new Handler().postDelayed(() -> {
                if (v.getTag().equals(viewModel.getQ().trueCase)) {
                    v.getBackground().setLevel(2);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            viewModel.nextQuestion();
                            initQuestion();
                        }
                    }, 500);
                } else {
                    v.getBackground().setLevel(3);
                    showDialogGameOver();
                }
            }, 1000);
        } else if (v instanceof ImageView) {
            if (v.getId() == R.id.iv_5050) {
                is5050.postValue(false);
                    binding.iv5050.getDrawable().setLevel(1);
                    viewModel.setPaused(true);
                    MediaManager.getInstance().playGame(R.raw.song_5050, mp -> delete5050());
                    binding.iv5050.getDrawable().setLevel(2);
                    binding.iv5050.setEnabled(false);
            } else if (v.getId() == R.id.iv_stop) {
                gameOver();
            } else if (v.getId() == R.id.iv_change) {
                binding.ivChange.getDrawable().setLevel(1);
                viewModel.setPaused(true);
//                MediaManager.getInstance().playGame(R.raw.song_change_question, mp -> changeQuestion());
                changeQuestion();
                isChange.setValue(false);
                binding.ivChange.getDrawable().setLevel(2);
                binding.ivChange.setEnabled(false);
            }else if(v.getId()==R.id.iv_audience) {
                binding.ivAudience.getDrawable().setLevel(1);
                viewModel.setPaused(true);
                viewModel.thCD.interrupt();
                MediaManager.getInstance().playGame(R.raw.song_audience,mp -> gotoAudience());
                isAudience.setValue(false);
                binding.ivAudience.getDrawable().setLevel(2);
                binding.ivAudience.setEnabled(false);
            }
        }

    }

    private void gotoAudience() {
        MainActivity act = (MainActivity) context;
        act.showFragment(AudienceDialog.TAG, null, true);
        FragmentManager fm = requireActivity().getSupportFragmentManager();
        AudienceDialog audienceDialog = AudienceDialog.newInstance();
        audienceDialog.setCallBack((data, key) -> {
            if (AudienceDialog.KEY_BACK.equals(key)) {
                viewModel.startCountDown();
            }
        });
        audienceDialog.show(fm, AudienceDialog.TAG);
        viewModel.setPaused(false);;
    }

//    private void showAudienceDialog() {
//        AudienceDialog inform = new AudienceDialog(context,((data, key) -> {
//            if ((AudienceDialog.KEY_BACK).equals(key)){
//                viewModel.startCountDown();
//            }
//        }));
//        inform.show();
//    }



    private void changeQuestion() {
 //       viewModel.nextQuestion();
//        initQuestion();
//        Log.i(TAG, "changeQuestion: " + viewModel.getIndex().getValue());
        new Thread(new Runnable() {
            @Override
            public void run() {
                int indexChange = viewModel.getIndex().getValue();
                Question qChange = App.getInstance().getDb().getQuestionDAO().getQuestionByLevel((indexChange+1)+"");
                Log.i(TAG, "changeQuestion: " + qChange);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        App.getInstance().getStorage().getListQuestion().set(indexChange, qChange);
                        initQuestion();
                        viewModel.setPaused(false);
                    }
                });
            }
        }).start();

    }

    private void delete5050() {
        int indexTrue = Integer.parseInt(viewModel.getQ().trueCase);
        int indexA = Integer.parseInt((String) binding.tvAns1.getTag());
        int indexB = Integer.parseInt((String) binding.tvAns2.getTag());
        int indexC = Integer.parseInt((String) binding.tvAns3.getTag());
        int indexD = Integer.parseInt((String) binding.tvAns4.getTag());
        List<Integer> rs = viewModel.delete5050(indexTrue, indexA, indexB, indexC, indexD);
        Collections.shuffle(rs);
        deleteAnswer(rs);
        Log.i(TAG, "delete5050: " + rs);
        viewModel.setPaused(false);
    }

    private void deleteAnswer(List<Integer> rs) {
        if (rs.get(0) == 1) {
            binding.tvAns1.setText("");
        } else if (rs.get(0) == 2) {
            binding.tvAns2.setText("");
        } else if (rs.get(0) == 3) {
            binding.tvAns3.setText("");
        } else if (rs.get(0) == 4) {
            binding.tvAns4.setText("");
        }
        if (rs.get(1) == 1) {
            binding.tvAns1.setText("");
        } else if (rs.get(1) == 2) {
            binding.tvAns2.setText("");
        } else if (rs.get(1) == 3) {
            binding.tvAns3.setText("");
        } else if (rs.get(1) == 4) {
            binding.tvAns4.setText("");
        }
    }

    private void updateChooseAnswerUI(View v) {
        binding.tvAns1.getBackground().setLevel(0);
        binding.tvAns2.getBackground().setLevel(0);
        binding.tvAns3.getBackground().setLevel(0);
        binding.tvAns4.getBackground().setLevel(0);
        v.getBackground().setLevel(1);
    }

    private void initQuestion() {
        viewModel.updateTime();
        refreshAnswerUI();
        int index = viewModel.getIndex().getValue();
        Question q = App.getInstance().getStorage().listQuestion.get(index);
        viewModel.setQuestion(q);
        updateQuestionUI(index, q);
        MediaManager.getInstance().playGame(ID_SONG_Q[index], mp -> viewModel.setPaused(false));
    }

    private void updateQuestionUI(int index, Question q) {
        binding.tvTitle.setText(String.format("Câu %d", index + 1));
        binding.tvQuestion.setText(q.question);
        binding.tvAns1.setText(String.format("A: %s", q.caseA));
        binding.tvAns2.setText(String.format("B: %s", q.caseB));
        binding.tvAns3.setText(String.format("C: %s", q.caseC));
        binding.tvAns4.setText(String.format("D: %s", q.caseD));
        binding.flQuestion.startAnimation(AnimationUtils.loadAnimation(context,R.anim.anim_translate_right));
        binding.tvAns1.startAnimation(AnimationUtils.loadAnimation(context,R.anim.anim_translate_right));
        binding.tvAns2.startAnimation(AnimationUtils.loadAnimation(context,R.anim.anim_translate_right));
        binding.tvAns3.startAnimation(AnimationUtils.loadAnimation(context,R.anim.anim_translate_right));
        binding.tvAns4.startAnimation(AnimationUtils.loadAnimation(context,R.anim.anim_translate_right));
    }

    private void refreshAnswerUI() {
        binding.tvAns1.getBackground().setLevel(0);
        binding.tvAns2.getBackground().setLevel(0);
        binding.tvAns3.getBackground().setLevel(0);
        binding.tvAns4.getBackground().setLevel(0);
    }


    @Override
    protected Class<M003PlayVM> getClassViewModel() {
        return M003PlayVM.class;
    }

    @Override
    protected FrgM003PlayBinding initViewBinding(LayoutInflater inflater, ViewGroup container) {
        return FrgM003PlayBinding.inflate(getLayoutInflater());
    }

}
