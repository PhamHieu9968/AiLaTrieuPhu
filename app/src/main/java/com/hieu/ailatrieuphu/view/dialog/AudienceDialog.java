package com.hieu.ailatrieuphu.view.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.MutableLiveData;

import com.hieu.ailatrieuphu.App;
import com.hieu.ailatrieuphu.OnMainCallBack;
import com.hieu.ailatrieuphu.R;
import com.hieu.ailatrieuphu.databinding.ViewAudienceBinding;
import com.hieu.ailatrieuphu.viewmodel.AudienceVM;

public class AudienceDialog extends BaseDialog<ViewAudienceBinding, AudienceVM> {
    public static final String KEY_BACK = "KEY_BACK";
    public static String TAG = AudienceDialog.class.getName();
    private static AudienceDialog instance;
    public OnMainCallBack callBack;

    public AudienceDialog() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }

    public static AudienceDialog newInstance() {
        if (instance == null) {
            instance = new AudienceDialog();
        }
        return instance;
    }

    @Override
    protected Class<AudienceVM> getClassViewModel() {
        return AudienceVM.class;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.TransparentDialog);
    }

    public void setCallBack(OnMainCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.initRate();
        initView();
    }

    @Override
    protected void initView() {

        int maxHeight = (int) App.getInstance().getResources().getDimension(R.dimen.d_400);
        LinearLayout.LayoutParams paramsA = (LinearLayout.LayoutParams) binding.viewA.getLayoutParams();
        LinearLayout.LayoutParams paramsB = (LinearLayout.LayoutParams) binding.viewB.getLayoutParams();
        LinearLayout.LayoutParams paramsC = (LinearLayout.LayoutParams) binding.viewC.getLayoutParams();
        LinearLayout.LayoutParams paramsD = (LinearLayout.LayoutParams) binding.viewD.getLayoutParams();

        viewModel.drawColumns(paramsA, paramsB, paramsC, paramsD);
        viewModel.getParamsDataA().observe(this, params -> renderData(params, binding.tvA, binding.viewA, viewModel.getTextViewA()));
        viewModel.getParamsDataB().observe(this, params -> renderData(params, binding.tvB, binding.viewB, viewModel.getTextViewB()));
        viewModel.getParamsDataC().observe(this, params -> renderData(params, binding.tvC, binding.viewC, viewModel.getTextViewC()));
        viewModel.getParamsDataD().observe(this, params -> renderData(params, binding.tvD, binding.viewD, viewModel.getTextViewD()));
        binding.btBack.setVisibility(View.VISIBLE);
        binding.btBack.setOnClickListener(v -> {
            doBack();
        });
    }

    private void renderData(LinearLayout.LayoutParams params, TextView textViewColumm, View viewColumn, MutableLiveData<String> textViewData) {
        textViewColumm.setText(textViewData.getValue());
        viewColumn.setLayoutParams(params);
        viewColumn.postInvalidate();
    }

    @Override
    protected ViewAudienceBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return ViewAudienceBinding.inflate(inflater, container, false);
    }

    private void doBack() {
        callBack.callBack(null, KEY_BACK);
        dismiss();
    }


}