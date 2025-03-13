package com.worldweaver.delvegm;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import androidx.viewpager2.widget.ViewPager2;

import com.worldweaver.delvegm.ui.features.Feature;
import com.worldweaver.delvegm.ui.viewadapter.FeatureCarouselAdapter;
import com.worldweaver.delvegm.ui.viewadapter.HeroCarouselPageTransformer;
import com.worldweaver.delvegm.ui.viewadapter.HeroCarouselTransformer;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private LinearLayout indicatorContainer;
    private FeatureCarouselAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        viewPager = findViewById(R.id.view_pager);
        indicatorContainer = findViewById(R.id.indicator_container);
        if (viewPager == null) {
            // Handle the error or log it
            Log.e("MainActivity", "ViewPager2 not found in layout");
            return;
        }
        if (indicatorContainer == null) {
            // Handle the error or log it
            Log.e("MainActivity", "Indicator View not found in layout");
            return;
        }

        // Set up the carousel
        setupCarousel();
    }

    private void setupCarousel() {
        // Create adapter with feature data
        adapter = new FeatureCarouselAdapter(getFeaturesList(), this);
        viewPager.setAdapter(adapter);

        // Set offscreen page limit to keep adjacent pages ready
        viewPager.setOffscreenPageLimit(3);

        // Add page transformer for Hero carousel effect
        viewPager.setPageTransformer(new HeroCarouselPageTransformer());

        // Set page change callback for indicators
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                updateIndicators(position);
            }
        });


        // Add padding to show adjacent items
        int sidePadding = (int) (getResources().getDisplayMetrics().widthPixels * 0.15);

        try {
            Field recyclerViewField = ViewPager2.class.getDeclaredField("mRecyclerView");
            recyclerViewField.setAccessible(true);

            RecyclerView recyclerView = (RecyclerView) recyclerViewField.get(viewPager);
            if (recyclerView != null) {
                recyclerView.setPadding(sidePadding, 0, sidePadding, 0);
                recyclerView.setClipToPadding(false);
            }
        } catch (Exception e) {
            Log.e("MainActivity", "Error setting ViewPager2 padding", e);
            // Fallback
            viewPager.setPadding(sidePadding, 0, sidePadding, 0);
            viewPager.setClipToPadding(false);
        }

        // Add indicators for the carousel
        setupIndicators(adapter.getItemCount());

        // Set initial indicator
        updateIndicators(0);

        // Optional: Set page margin
        setPageMargins();
    }

    // Set page margins for better visual appearance
    private void setPageMargins() {
        // Create a page margin to show part of adjacent pages
        ViewPager2.PageTransformer compositeTransformer = (page, position) -> {
            // This is handled by our HeroCarouselPageTransformer
        };

        // Get the RecyclerView inside ViewPager2
        View recyclerView = viewPager.getChildAt(0);
        if (recyclerView instanceof RecyclerView) {
            // Add padding to show part of adjacent pages
            int padding = getResources().getDimensionPixelOffset(R.dimen.carousel_page_margin);
            recyclerView.setPadding(padding, 0, padding, 0);
            ((RecyclerView) recyclerView).setClipToPadding(false);
        }
    }

    // Set up indicator dots
    private void setupIndicators(int count) {
        indicatorContainer.removeAllViews();

        for (int i = 0; i < count; i++) {
            View indicator = new View(this);
            int size = getResources().getDimensionPixelSize(R.dimen.indicator_size);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(size, size);
            int margin = getResources().getDimensionPixelSize(R.dimen.indicator_margin);
            params.setMargins(margin, 0, margin, 0);
            indicator.setLayoutParams(params);
            indicator.setBackgroundResource(R.drawable.indicator_dot);
            indicatorContainer.addView(indicator);
        }
    }

    // Update indicators based on current position
    private void updateIndicators(int position) {
        for (int i = 0; i < indicatorContainer.getChildCount(); i++) {
            View indicator = indicatorContainer.getChildAt(i);
            indicator.setSelected(i == position);
        }
    }

    // Sample data for the carousel
    private List<Feature> getFeaturesList() {
        return Arrays.asList(
                com.worldweaver.delvegm.ui.features.Feature.builder()
                        .title(getResources().getString(R.string.feature_rule_sets_activity_title))
                        .description(getResources().getString(R.string.feature_rule_sets_activity_description))
                        .imageResId(R.drawable.dice_game_3)
                        .activityClass(RuleSetsActivity.class)
                        .build(),
                com.worldweaver.delvegm.ui.features.Feature.builder()
                        .title(getResources().getString(R.string.feature_game_worlds_activity_title))
                        .description(getResources().getString(R.string.feature_game_worlds_activity_description))
                        .imageResId(R.drawable.adventure_map)
                        .activityClass(GameWorldsActivity.class)
                        .build(),
                com.worldweaver.delvegm.ui.features.Feature.builder()
                        .title(getResources().getString(R.string.feature_weather_activity_title))
                        .description(getResources().getString(R.string.feature_weather_activity_description))
                        .imageResId(R.drawable.hurricane_1)
                        .activityClass(WeatherActivity.class)
                        .build(),
                com.worldweaver.delvegm.ui.features.Feature.builder()
                        .title(getResources().getString(R.string.feature_time_and_events_activity_title))
                        .description(getResources().getString(R.string.feature_time_and_events_activity_description))
                        .imageResId(R.drawable.calendars_1)
                        .activityClass(TimeTrackingActivity.class)
                        .build()
        );
    }
}