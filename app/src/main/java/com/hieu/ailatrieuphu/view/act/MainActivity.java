package com.hieu.ailatrieuphu.view.act;

import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.hieu.ailatrieuphu.App;
import com.hieu.ailatrieuphu.MediaManager;
import com.hieu.ailatrieuphu.OnMainCallBack;
import com.hieu.ailatrieuphu.databinding.ActivityMainBinding;
import com.hieu.ailatrieuphu.db.entites.Question;
import com.hieu.ailatrieuphu.view.frg.M001MainFrg;
import com.hieu.ailatrieuphu.viewmodel.CommonVM;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseAct<ActivityMainBinding, CommonVM> {

    private static final String TAG = MainActivity.class.getName();
    private List<Question> lisDB;


    @Override
    protected Class<CommonVM> initViewModel() {
        return CommonVM.class;
    }

    protected void initViews() {
        initDB();

    }

    @Override
    protected ActivityMainBinding initViewBinding() {
        return ActivityMainBinding.inflate(getLayoutInflater());
    }

    private void initDB() {
        new Thread() {
            @Override
            public void run() {
                try {
                    lisDB = App.getInstance().getDb().getQuestionDAO().getAllQuestion();
                    lisDB = sortList();
                    Log.i(TAG, "listDB: " + lisDB);
                    runOnUI((data, key) -> gotoMainScreen(lisDB));
                } catch (Exception e) {
                    runOnUI((data, key) -> showAlert());
                }
            }
        }.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        MediaManager.getInstance().pauseSong();
    }

    @Override
    protected void onStart() {
        super.onStart();
        MediaManager.getInstance().playSong();
    }

    public void gotoMainScreen(List<Question> lisDB) {
        App.getInstance().getStorage().setListQuestion(lisDB);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                binding.ivLogo.setVisibility(View.GONE);
                binding.progressLoading.setVisibility(View.GONE);
                showFragment(M001MainFrg.TAG, null, false);
            }
        }, 2000);
    }

    private void showAlert() {
        notify("Không lấy được dữ liệu câu hỏi!");
    }

    private List<Question> sortList() {
        List<Question> tmpLisDB = new ArrayList<>(lisDB);
        for (int i = 0; i < tmpLisDB.size(); i++) {
            for (int j = 0; j < lisDB.size(); j++) {
                Question qJ = lisDB.get(j);
//                int levelJ = Integer.parseInt(qJ.level) - 1;
                int levelJ = qJ.level - 1;
                if (levelJ == i) {
                    tmpLisDB.set(levelJ, qJ);
                }
            }
        }
        return tmpLisDB;
    }

    public void runOnUI(OnMainCallBack callBack) {
        runOnUiThread(() -> callBack.callBack(null, null));
    }

}