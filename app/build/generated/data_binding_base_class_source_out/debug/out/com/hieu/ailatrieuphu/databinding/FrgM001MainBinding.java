// Generated by view binder compiler. Do not edit!
package com.hieu.ailatrieuphu.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.hieu.ailatrieuphu.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FrgM001MainBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ImageView ivCup;

  @NonNull
  public final ImageView ivInfo;

  @NonNull
  public final ImageView ivPlay;

  @NonNull
  public final ImageView ivSetting;

  private FrgM001MainBinding(@NonNull ConstraintLayout rootView, @NonNull ImageView ivCup,
      @NonNull ImageView ivInfo, @NonNull ImageView ivPlay, @NonNull ImageView ivSetting) {
    this.rootView = rootView;
    this.ivCup = ivCup;
    this.ivInfo = ivInfo;
    this.ivPlay = ivPlay;
    this.ivSetting = ivSetting;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FrgM001MainBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FrgM001MainBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.frg_m001_main, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FrgM001MainBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.iv_cup;
      ImageView ivCup = ViewBindings.findChildViewById(rootView, id);
      if (ivCup == null) {
        break missingId;
      }

      id = R.id.iv_info;
      ImageView ivInfo = ViewBindings.findChildViewById(rootView, id);
      if (ivInfo == null) {
        break missingId;
      }

      id = R.id.iv_play;
      ImageView ivPlay = ViewBindings.findChildViewById(rootView, id);
      if (ivPlay == null) {
        break missingId;
      }

      id = R.id.iv_setting;
      ImageView ivSetting = ViewBindings.findChildViewById(rootView, id);
      if (ivSetting == null) {
        break missingId;
      }

      return new FrgM001MainBinding((ConstraintLayout) rootView, ivCup, ivInfo, ivPlay, ivSetting);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
