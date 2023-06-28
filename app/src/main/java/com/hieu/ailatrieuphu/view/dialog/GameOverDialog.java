package com.hieu.ailatrieuphu.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

import com.hieu.ailatrieuphu.OnMainCallBack;
import com.hieu.ailatrieuphu.R;

public class GameOverDialog extends Dialog {
    public static final String KEY_QUIT_GAME = "KEY_QUIT_GAME";
    public static final String KEY_PLAY_AGAIN = "KEY_PLAY_AGAIN";
    private final OnMainCallBack callBack;

    public GameOverDialog(@NonNull Context context, OnMainCallBack callBack) {
        super(context);
        setContentView(R.layout.view_game_over);
        initViews();
        this.callBack = callBack;
    }

    private void initViews() {
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        findViewById(R.id.bt_play_again).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doPlayAgain();
            }
        });
        findViewById(R.id.bt_quit_game).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doQuitGame();
            }
        });
    }

    private void doQuitGame() {
        callBack.callBack(null, KEY_QUIT_GAME);
        dismiss();
    }

    private void doPlayAgain() {
        callBack.callBack(null, KEY_PLAY_AGAIN);
        dismiss();
    }
}
