package com.worldweaver.delvegm.ui.viewadapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.worldweaver.delvegm.R;

public class FeatureCarouselViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView titleView;
    TextView descriptionView;

    FeatureCarouselViewHolder(View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.feature_image);
        titleView = itemView.findViewById(R.id.feature_title);
        descriptionView = itemView.findViewById(R.id.feature_description);
    }
}
