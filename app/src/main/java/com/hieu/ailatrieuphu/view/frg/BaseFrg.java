package com.hieu.ailatrieuphu.view.frg;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import com.hieu.ailatrieuphu.OnMainCallBack;
import com.hieu.ailatrieuphu.OnUpdateUI;


public abstract class BaseFrg<B extends ViewBinding, V extends ViewModel>
        extends Fragment implements View.OnClickListener {
    protected Context context;
    protected B binding;
    protected V viewModel;
    protected Object mData;
    protected OnMainCallBack callBack;
    protected OnUpdateUI eventUI;

    public void setEventUI(OnUpdateUI eventUI) {
        this.eventUI = eventUI;
    }

    public final void onAttach(Context context){
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public final View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = initViewBinding(inflater,container);
        viewModel = new ViewModelProvider(this).get(getClassViewModel());
        initViews();
        return binding.getRoot();
    }

    @Override
    public final void onClick(View v) {
        v.setAnimation(AnimationUtils.loadAnimation(context, androidx.appcompat.R.anim.abc_fade_in));
        clickView(v);
    }

    protected void clickView(View v) {
        // do nothing
    }

    public void setCallBack(OnMainCallBack callBack) {this.callBack = callBack;}

    protected abstract void initViews();

    protected abstract Class<V> getClassViewModel();


    protected abstract B initViewBinding(LayoutInflater inflater, ViewGroup container);

    public void setData(Object data) {
        this.mData = data;
    }

    protected  final void notify(String msg){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }
    protected  final void notify(int msg){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }
}
