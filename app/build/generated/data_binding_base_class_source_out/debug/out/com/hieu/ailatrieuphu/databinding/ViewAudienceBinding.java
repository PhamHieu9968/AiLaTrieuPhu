// Generated by view binder compiler. Do not edit!
package com.hieu.ailatrieuphu.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.hieu.ailatrieuphu.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ViewAudienceBinding implements ViewBinding {
  @NonNull
  private final FrameLayout rootView;

  @NonNull
  public final Button btBack;

  @NonNull
  public final TextView tvA;

  @NonNull
  public final TextView tvB;

  @NonNull
  public final TextView tvC;

  @NonNull
  public final TextView tvD;

  @NonNull
  public final View viewA;

  @NonNull
  public final View viewB;

  @NonNull
  public final View viewC;

  @NonNull
  public final View viewD;

  private ViewAudienceBinding(@NonNull FrameLayout rootView, @NonNull Button btBack,
      @NonNull TextView tvA, @NonNull TextView tvB, @NonNull TextView tvC, @NonNull TextView tvD,
      @NonNull View viewA, @NonNull View viewB, @NonNull View viewC, @NonNull View viewD) {
    this.rootView = rootView;
    this.btBack = btBack;
    this.tvA = tvA;
    this.tvB = tvB;
    this.tvC = tvC;
    this.tvD = tvD;
    this.viewA = viewA;
    this.viewB = viewB;
    this.viewC = viewC;
    this.viewD = viewD;
  }

  @Override
  @NonNull
  public FrameLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ViewAudienceBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ViewAudienceBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.view_audience, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ViewAudienceBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.bt_back;
      Button btBack = ViewBindings.findChildViewById(rootView, id);
      if (btBack == null) {
        break missingId;
      }

      id = R.id.tv_a;
      TextView tvA = ViewBindings.findChildViewById(rootView, id);
      if (tvA == null) {
        break missingId;
      }

      id = R.id.tv_b;
      TextView tvB = ViewBindings.findChildViewById(rootView, id);
      if (tvB == null) {
        break missingId;
      }

      id = R.id.tv_c;
      TextView tvC = ViewBindings.findChildViewById(rootView, id);
      if (tvC == null) {
        break missingId;
      }

      id = R.id.tv_d;
      TextView tvD = ViewBindings.findChildViewById(rootView, id);
      if (tvD == null) {
        break missingId;
      }

      id = R.id.view_a;
      View viewA = ViewBindings.findChildViewById(rootView, id);
      if (viewA == null) {
        break missingId;
      }

      id = R.id.view_b;
      View viewB = ViewBindings.findChildViewById(rootView, id);
      if (viewB == null) {
        break missingId;
      }

      id = R.id.view_c;
      View viewC = ViewBindings.findChildViewById(rootView, id);
      if (viewC == null) {
        break missingId;
      }

      id = R.id.view_d;
      View viewD = ViewBindings.findChildViewById(rootView, id);
      if (viewD == null) {
        break missingId;
      }

      return new ViewAudienceBinding((FrameLayout) rootView, btBack, tvA, tvB, tvC, tvD, viewA,
          viewB, viewC, viewD);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
