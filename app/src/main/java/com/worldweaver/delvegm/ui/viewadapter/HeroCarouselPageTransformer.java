package com.worldweaver.delvegm.ui.viewadapter;

import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.viewpager2.widget.ViewPager2;
public class HeroCarouselPageTransformer implements ViewPager2.PageTransformer {
    private static final float MIN_WIDTH_SCALE = 0.6f;
    private static final float MIN_ALPHA = 0.7f;

    @Override
    public void transformPage(@NonNull View page, float position) {
        float pageWidth = page.getWidth();

        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            page.setAlpha(0.7f);
            page.setScaleX(MIN_WIDTH_SCALE);

        } else if (position <= 1) { // [-1,1]
            // Modify the default slide transition to shrink the page width
            float scaleFactor = Math.max(MIN_WIDTH_SCALE, 1 - Math.abs(position) * 0.4f);

            // Scale the page down (between MIN_SCALE and 1)
            page.setScaleX(scaleFactor);

            // Keep Y scale at 1 (no height change)
            page.setScaleY(1f);

            // Fade the page relative to its size
            page.setAlpha(0.7f + 0.3f * (1 - Math.abs(position)));

        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            page.setAlpha(0.7f);
            page.setScaleX(MIN_WIDTH_SCALE);
        }

        // Log for debugging
        if (position > -1.5f && position < 1.5f) {
            Log.d("Carousel", "Position: " + position + ", Scale: " + page.getScaleX());
        }
    }
}
