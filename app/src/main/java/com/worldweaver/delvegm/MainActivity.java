package com.worldweaver.delvegm;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.worldweaver.delvegm.ui.features.Feature;
import com.worldweaver.delvegm.ui.viewadapter.FeatureCarouselAdapter;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FeatureCarouselAdapter adapter;
    private RecyclerView featureCarousel;
    private LinearLayout indicatorContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        featureCarousel = findViewById(R.id.feature_carousel);
        indicatorContainer = findViewById(R.id.indicator_container);

        // Set up the carousel
        setupCarousel();
    }

    private void setupCarousel() {
        // Set up horizontal layout with paging effect
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                this, LinearLayoutManager.HORIZONTAL, false);
        featureCarousel.setLayoutManager(layoutManager);

        // Add snap behavior for carousel effect
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(featureCarousel);

        // Add item decoration for proper spacing
        featureCarousel.addItemDecoration(new CarouselItemDecoration());

        // Create and set the adapter
        adapter = new FeatureCarouselAdapter(getFeaturesList(), this);
        featureCarousel.setAdapter(adapter);

        // Add indicators for the carousel
        addCarouselIndicators(featureCarousel, adapter.getItemCount());
    }

    // Custom item decoration for proper spacing according to Material 3 guidelines
    private class CarouselItemDecoration extends RecyclerView.ItemDecoration {
        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view,
                                   @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view);
            int itemCount = parent.getAdapter().getItemCount();

            // Apply proper spacing according to Material 3 guidelines
            if (position == 0) {
                outRect.left = dpToPx(16); // Start margin
            } else {
                outRect.left = dpToPx(8); // Item spacing
            }

            if (position == itemCount - 1) {
                outRect.right = dpToPx(16); // End margin
            } else {
                outRect.right = dpToPx(8); // Item spacing
            }
        }
    }

    // Add indicators for the carousel
    private void addCarouselIndicators(RecyclerView carousel, int itemCount) {
        // Create indicator views (dots) and add them to the container
        indicatorContainer.removeAllViews();

        for (int i = 0; i < itemCount; i++) {
            View indicator = new View(this);
            int size = dpToPx(8);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(size, size);
            params.setMargins(dpToPx(4), 0, dpToPx(4), 0);
            indicator.setLayoutParams(params);
            indicator.setBackgroundResource(R.drawable.indicator_dot);
            indicatorContainer.addView(indicator);
        }

        // Update indicators when scrolling
        carousel.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                updateIndicators(recyclerView);
            }

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    updateIndicators(recyclerView);
                }
            }
        });

        // Initial update
        updateIndicators(carousel);
    }

    // Update indicator dots based on current position
    private void updateIndicators(RecyclerView carousel) {
        LinearLayoutManager layoutManager = (LinearLayoutManager) carousel.getLayoutManager();
        if (layoutManager != null) {
            int currentPosition = layoutManager.findFirstCompletelyVisibleItemPosition();
            if (currentPosition == RecyclerView.NO_POSITION) {
                currentPosition = layoutManager.findFirstVisibleItemPosition();
            }

            for (int i = 0; i < indicatorContainer.getChildCount(); i++) {
                View indicator = indicatorContainer.getChildAt(i);
                indicator.setSelected(i == currentPosition);
            }
        }
    }

    // Convert dp to pixels
    private int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
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