package com.hieu.ailatrieuphu.view.act;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import com.hieu.ailatrieuphu.OnMainCallBack;
import com.hieu.ailatrieuphu.OnUpdateUI;
import com.hieu.ailatrieuphu.R;
import com.hieu.ailatrieuphu.view.frg.BaseFrg;

import java.lang.reflect.Constructor;


public abstract class BaseAct<T extends ViewBinding, M extends ViewModel>
        extends AppCompatActivity implements View.OnClickListener, OnMainCallBack, OnUpdateUI {
    protected T binding;
    protected M viewModel;

    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = initViewBinding();
        viewModel = new ViewModelProvider(this).get(initViewModel());
        setContentView(binding.getRoot());
        initViews();
    }

    protected abstract Class<M> initViewModel();

    protected abstract void initViews();

    protected abstract T initViewBinding();

    @Override
    public void onClick(View v) {
        // do nothing
    }

    protected final void notify(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    protected final void notify(int msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("PrivateResource")
    public void showFragment(String tag, Object data, boolean isBack) {
        try {
            Class<?> clazz = Class.forName(tag);
            Constructor<?> constructor = clazz.getConstructor();//không có String.class
            BaseFrg<?, ?> baseFragment = (BaseFrg<?, ?>) constructor.newInstance();
            baseFragment.setCallBack(this);
            baseFragment.setData(data);
            baseFragment.setEventUI(this);

            FragmentTransaction trans = getSupportFragmentManager()
                    .beginTransaction();

            if (isBack) {
                trans.addToBackStack(null);
            }
            trans.replace(R.id.ln_main, baseFragment, tag)
                    .setCustomAnimations(androidx.fragment.R.animator.fragment_fade_enter,
                            androidx.fragment.R.animator.fragment_fade_exit)
//                    .setCustomAnimations(R.anim.enter, R.anim.exit,R.anim.pop_enter,R.anim.pop_exit)
                    .commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void callBack(Object data, String key) {

    }

    @Override
    public void updateUI(Runnable run) {
        runOnUiThread(run);
    }
}
