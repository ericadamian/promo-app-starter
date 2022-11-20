// Generated by view binder compiler. Do not edit!
package com.raywenderlich.android.promoapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.viewbinding.ViewBinding;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.raywenderlich.android.promoapp.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityPromoBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final MaterialButton btnClaimOffer;

  @NonNull
  public final MaterialCardView cardImage;

  @NonNull
  public final MaterialCardView cardPromo;

  @NonNull
  public final Button commerce;

  @NonNull
  public final Button content;

  @NonNull
  public final Button custom;

  @NonNull
  public final Group discountGroup;

  @NonNull
  public final ImageView imgProduct;

  @NonNull
  public final Button lifecycle;

  @NonNull
  public final TextView textView;

  @NonNull
  public final TextView tvOfferClaimed;

  @NonNull
  public final TextView tvProductLabel;

  @NonNull
  public final TextView tvPromoCode;

  @NonNull
  public final TextView tvPromoCodeLabel;

  private ActivityPromoBinding(@NonNull ConstraintLayout rootView,
      @NonNull MaterialButton btnClaimOffer, @NonNull MaterialCardView cardImage,
      @NonNull MaterialCardView cardPromo, @NonNull Button commerce, @NonNull Button content,
      @NonNull Button custom, @NonNull Group discountGroup, @NonNull ImageView imgProduct,
      @NonNull Button lifecycle, @NonNull TextView textView, @NonNull TextView tvOfferClaimed,
      @NonNull TextView tvProductLabel, @NonNull TextView tvPromoCode,
      @NonNull TextView tvPromoCodeLabel) {
    this.rootView = rootView;
    this.btnClaimOffer = btnClaimOffer;
    this.cardImage = cardImage;
    this.cardPromo = cardPromo;
    this.commerce = commerce;
    this.content = content;
    this.custom = custom;
    this.discountGroup = discountGroup;
    this.imgProduct = imgProduct;
    this.lifecycle = lifecycle;
    this.textView = textView;
    this.tvOfferClaimed = tvOfferClaimed;
    this.tvProductLabel = tvProductLabel;
    this.tvPromoCode = tvPromoCode;
    this.tvPromoCodeLabel = tvPromoCodeLabel;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityPromoBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityPromoBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_promo, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityPromoBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnClaimOffer;
      MaterialButton btnClaimOffer = rootView.findViewById(id);
      if (btnClaimOffer == null) {
        break missingId;
      }

      id = R.id.cardImage;
      MaterialCardView cardImage = rootView.findViewById(id);
      if (cardImage == null) {
        break missingId;
      }

      id = R.id.cardPromo;
      MaterialCardView cardPromo = rootView.findViewById(id);
      if (cardPromo == null) {
        break missingId;
      }

      id = R.id.commerce;
      Button commerce = rootView.findViewById(id);
      if (commerce == null) {
        break missingId;
      }

      id = R.id.content;
      Button content = rootView.findViewById(id);
      if (content == null) {
        break missingId;
      }

      id = R.id.custom;
      Button custom = rootView.findViewById(id);
      if (custom == null) {
        break missingId;
      }

      id = R.id.discountGroup;
      Group discountGroup = rootView.findViewById(id);
      if (discountGroup == null) {
        break missingId;
      }

      id = R.id.imgProduct;
      ImageView imgProduct = rootView.findViewById(id);
      if (imgProduct == null) {
        break missingId;
      }

      id = R.id.lifecycle;
      Button lifecycle = rootView.findViewById(id);
      if (lifecycle == null) {
        break missingId;
      }

      id = R.id.textView;
      TextView textView = rootView.findViewById(id);
      if (textView == null) {
        break missingId;
      }

      id = R.id.tvOfferClaimed;
      TextView tvOfferClaimed = rootView.findViewById(id);
      if (tvOfferClaimed == null) {
        break missingId;
      }

      id = R.id.tvProductLabel;
      TextView tvProductLabel = rootView.findViewById(id);
      if (tvProductLabel == null) {
        break missingId;
      }

      id = R.id.tvPromoCode;
      TextView tvPromoCode = rootView.findViewById(id);
      if (tvPromoCode == null) {
        break missingId;
      }

      id = R.id.tvPromoCodeLabel;
      TextView tvPromoCodeLabel = rootView.findViewById(id);
      if (tvPromoCodeLabel == null) {
        break missingId;
      }

      return new ActivityPromoBinding((ConstraintLayout) rootView, btnClaimOffer, cardImage,
          cardPromo, commerce, content, custom, discountGroup, imgProduct, lifecycle, textView,
          tvOfferClaimed, tvProductLabel, tvPromoCode, tvPromoCodeLabel);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}