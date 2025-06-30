package com.worldweaver.delvegm.ui.carousel;

import static android.widget.Toast.LENGTH_LONG;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.worldweaver.delvegm.R;

import java.util.List;

public class FeatureCarouselAdapter extends RecyclerView.Adapter<FeatureCarouselViewHolder> {
    List<CarouselFeature> arrayList;
    private final Activity parentActivity;

    public FeatureCarouselAdapter(List<CarouselFeature> arrayList, Activity parentActivity) {
        this.arrayList = arrayList;
        this.parentActivity = parentActivity;
    }

    @NonNull
    @Override
    public FeatureCarouselViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parentActivity).inflate(R.layout.carousel_item_container, parent, false);
        return new FeatureCarouselViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeatureCarouselViewHolder holder, int position) {
        CarouselFeature feature = arrayList.get(position);
        holder.titleView.setText(feature.getTitle());
        holder.descriptionView.setText(feature.getDescription());

        Glide.with(parentActivity).load(feature.getImageResId()).into(holder.imageView);
        float carouselCornerRadius = parentActivity.getResources().getDimension(R.dimen.carousel_rounded_corner_diameter);
        holder.frameLayout.setShapeAppearanceModel(ShapeAppearanceModel.builder().setAllCornerSizes(carouselCornerRadius).build());
        holder.itemView.setOnClickListener(view -> {
                Intent intent = new Intent(parentActivity, feature.getActivityClass());
                parentActivity.startActivity(intent);
                Toast.makeText(parentActivity, feature.getTitle() + " clicked", LENGTH_LONG).show();
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
