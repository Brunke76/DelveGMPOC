package com.worldweaver.delvegm.ui.viewadapter;

import static android.widget.Toast.LENGTH_LONG;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;
import com.worldweaver.delvegm.R;
import com.worldweaver.delvegm.ui.features.Feature;

import java.util.List;

public class FeatureCarouselAdapter extends RecyclerView.Adapter<FeatureCarouselViewHolder> {
    private List<Feature> features;
    private final Activity parentActivity;

    public FeatureCarouselAdapter(List<Feature> features, Activity parentActivity) {
        this.features = features;
        this.parentActivity = parentActivity;
    }

    @NonNull
    @Override
    public FeatureCarouselViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.carousel_item, parent, false);
        return new FeatureCarouselViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeatureCarouselViewHolder holder, int position) {
        Feature feature = features.get(position);
        holder.imageView.setImageResource(feature.getImageResId());
        holder.titleView.setText(feature.getTitle());
        holder.descriptionView.setText(feature.getDescription());

        // Apply Material 3 styling
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(parentActivity, feature.getActivityClass());
            parentActivity.startActivity(intent);
            Toast.makeText(parentActivity, feature.getTitle() + " clicked", LENGTH_LONG).show();
        });
    }

    @Override
    public int getItemCount() {
        return features.size();
    }
}
