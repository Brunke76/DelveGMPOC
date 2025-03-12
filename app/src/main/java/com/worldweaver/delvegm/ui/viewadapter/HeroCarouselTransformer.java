package com.worldweaver.delvegm.ui.viewadapter;

import android.graphics.Canvas;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 *  This is a class that allows for the deformation of the carousel item as it moves
 *  into or away from the desired position in the carousel.
 */
public class HeroCarouselTransformer extends RecyclerView.ItemDecoration {
    private final float maxWidthScale = 1.0f;    // Width scale for centered item
    private final float minWidthScale = 0.8f;    // Width scale for items at the edges

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);

        int centerX = parent.getWidth() / 2;

        for (int i = 0; i < parent.getChildCount(); i++) {
            View child = parent.getChildAt(i);

            // Calculate the center of the child view
            int childCenterX = (child.getLeft() + child.getRight()) / 2;

            // Calculate distance from center as a percentage (-1 to 1)
            float distanceFromCenter = (childCenterX - centerX) / (float) centerX;
            distanceFromCenter = Math.min(1f, Math.max(-1f, distanceFromCenter)); // Clamp to -1 to 1

            // Calculate width scale based on distance from center
            float widthScale = maxWidthScale - Math.abs(distanceFromCenter) * (maxWidthScale - minWidthScale);

            // Apply width transformation only
            child.setScaleX(widthScale);

            // Keep height and rotation unchanged
            child.setScaleY(1.0f);
            child.setRotation(0f);
            child.setRotationY(0f);
        }
    }
}
