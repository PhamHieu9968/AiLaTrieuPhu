package com.hieu.ailatrieuphu.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

import com.hieu.ailatrieuphu.OnMainCallBack;
import com.hieu.ailatrieuphu.R;

public class InformReadyDialog extends Dialog {
    public static final String KEY_BACK = "KEY_BACK";
    public static final String KEY_READY = "KEY_READY";
    private final OnMainCallBack callBack;

    public InformReadyDialog(@NonNull Context context, OnMainCallBack callBack) {
        super(context);
        setContentView(R.layout.view_ready);
        initViews();
        this.callBack = callBack;
    }

    private void initViews() {
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        findViewById(R.id.bt_play_again).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doReady();
            }
        });
        findViewById(R.id.bt_quit_game).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doBack();
            }
        });
    }

    private void doBack() {
        callBack.callBack(null,KEY_BACK);
        dismiss();
    }

    private void doReady() {
        callBack.callBack(null,KEY_READY);
        dismiss();
    }
}
