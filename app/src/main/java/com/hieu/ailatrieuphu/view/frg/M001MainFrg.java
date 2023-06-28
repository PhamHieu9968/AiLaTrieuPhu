package com.hieu.ailatrieuphu.view.frg;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hieu.ailatrieuphu.MediaManager;
import com.hieu.ailatrieuphu.R;
import com.hieu.ailatrieuphu.databinding.FrgM001MainBinding;
import com.hieu.ailatrieuphu.view.act.MainActivity;
import com.hieu.ailatrieuphu.viewmodel.CommonVM;

public class M001MainFrg extends BaseFrg<FrgM001MainBinding, CommonVM> {
    public final static String TAG = M001MainFrg.class.getName();

    @Override
    protected void initViews() {
        MediaManager.getInstance().playBG(R.raw.song_intro);


        binding.ivPlay.setOnClickListener(this);
        binding.ivInfo.setOnClickListener(this);
        binding.ivCup.setOnClickListener(this);
        binding.ivSetting.setOnClickListener(this);
    }

    @Override
    protected FrgM001MainBinding initViewBinding(LayoutInflater inflater, ViewGroup container) {
        return FrgM001MainBinding.inflate(getLayoutInflater(),container,false);
    }

    @Override
    protected Class<CommonVM> getClassViewModel() {
        return CommonVM.class;
    }



    @Override
    protected void clickView(View v) {
        if (v.getId()==R.id.iv_play){
            MediaManager.getInstance().stopBG();
            MainActivity act = (MainActivity) context;
            act.showFragment(M002RuleFrg.TAG,null,true);
        }
    }
}
