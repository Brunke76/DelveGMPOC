package com.worldweaver.delvegm.ui.viewadapter;

import static android.widget.Toast.LENGTH_LONG;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;
import com.worldweaver.delvegm.R;
import com.worldweaver.delvegm.ui.features.Feature;

import java.util.List;

public class FeatureCarouselAdapter extends RecyclerView.Adapter<FeatureCarouselAdapter.FeatureViewHolder> {

    private final List<Feature> features;
    private final Activity parentActivity;

    public FeatureCarouselAdapter(List<Feature> features, Activity parentActivity) {
        this.features = features;
        this.parentActivity = parentActivity;
    }

    @NonNull
    @Override
    public FeatureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = parentActivity.getLayoutInflater().inflate(R.layout.item_feature_hero, parent, false);
        return new FeatureViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeatureViewHolder holder, int position) {
        Feature feature = features.get(position);
        holder.bind(feature);
    }

    @Override
    public int getItemCount() {
        return features.size();
    }

    /**
     * ViewHolder for a feature item in the carousel
     */
    public class FeatureViewHolder extends RecyclerView.ViewHolder {

        private final MaterialCardView cardView;
        private final MaterialTextView titleView;
        private final MaterialTextView descriptionView;

        public FeatureViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.feature_card);
            titleView = itemView.findViewById(R.id.feature_title);
            descriptionView = itemView.findViewById(R.id.feature_description);
        }

        public void bind(final Feature feature) {
            titleView.setText(feature.getTitle());
            descriptionView.setText(feature.getDescription());

            // Set the card background image
            cardView.setBackgroundResource(feature.getImageResId());

            // Set click listener to launch the feature activity
            cardView.setOnClickListener(v -> {
                Intent intent = new Intent(parentActivity, feature.getActivityClass());
                parentActivity.startActivity(intent);
                Toast.makeText(parentActivity, feature.getTitle() + " clicked", LENGTH_LONG).show();
            });
        }
    }
}
