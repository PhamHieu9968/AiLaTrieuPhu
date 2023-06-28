package com.hieu.ailatrieuphu.view.frg;

import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import com.hieu.ailatrieuphu.MediaManager;
import com.hieu.ailatrieuphu.R;
import com.hieu.ailatrieuphu.databinding.FrgM002RuleBinding;
import com.hieu.ailatrieuphu.view.act.MainActivity;
import com.hieu.ailatrieuphu.view.dialog.InformReadyDialog;
import com.hieu.ailatrieuphu.viewmodel.CommonVM;

public class M002RuleFrg extends BaseFrg<FrgM002RuleBinding, CommonVM> {
    public final static String TAG = M002RuleFrg.class.getName();

    @Override
    protected void initViews() {
        MediaManager.getInstance().playGame(R.raw.song_rule, new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                MediaManager.getInstance().playGame(R.raw.song_ready, mp1 -> showReadyDialog());
            }
        });
        binding.lnMilestone.startAnimation(AnimationUtils.loadAnimation(context,R.anim.slide_left));
        binding.btHide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaManager.getInstance().stopPlayGame();
                showReadyDialog();
            }
        });
    }

    private void showReadyDialog() {
        InformReadyDialog inform = new InformReadyDialog(context,((data, key) -> {
            if (key.equals(InformReadyDialog.KEY_BACK)){
                doBack();
            } else if (key.equals(InformReadyDialog.KEY_READY)) {
                doReady();
            }
        }));
        inform.show();
    }

    private void doReady() {
        MediaManager.getInstance().stopPlayGame();
        MainActivity act = (MainActivity) context;
        act.showFragment(M003PlayFrg.TAG,null,true);
    }

    private void doBack() {
        MediaManager.getInstance().stopPlayGame();
        MainActivity act = (MainActivity) context;
        act.onBackPressed();
    }

    @Override
    protected FrgM002RuleBinding initViewBinding(LayoutInflater inflater, ViewGroup container) {
        return FrgM002RuleBinding.inflate(getLayoutInflater(),container,false);
    }

    @Override
    protected Class<CommonVM> getClassViewModel() {
        return CommonVM.class;
    }

}
