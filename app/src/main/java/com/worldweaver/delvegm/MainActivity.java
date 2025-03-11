package com.worldweaver.delvegm;

import android.os.Bundle;

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

    private RecyclerView featureCarousel;
    private FeatureCarouselAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getLayoutInflater();
        // Initialize the RecyclerView
        featureCarousel = findViewById(R.id.feature_carousel);

        // Set up horizontal layout with paging effect
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                this, LinearLayoutManager.HORIZONTAL, false);
        featureCarousel.setLayoutManager(layoutManager);

        // Add snap behavior for carousel effect
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(featureCarousel);

        // Create and set the adapter
        adapter = new FeatureCarouselAdapter(getFeaturesList(), this);
        featureCarousel.setAdapter(adapter);
    }

    /**
     * Get the list of features for the carousel
     */
    private List<Feature> getFeaturesList() {
        return Arrays.asList(
                Feature.builder()
                        .title(getResources().getString(R.string.feature_rule_sets_activity_title))
                        .description(getResources().getString(R.string.feature_rule_sets_activity_description))
                        .imageResId(R.drawable.dice_game_3)
                        .activityClass(RuleSetsActivity.class)
                        .build(),
                Feature.builder()
                        .title(getResources().getString(R.string.feature_game_worlds_activity_title))
                        .description(getResources().getString(R.string.feature_game_worlds_activity_description))
                        .imageResId(R.drawable.adventure_map)
                        .activityClass(GameWorldsActivity.class)
                        .build(),
                Feature.builder()
                        .title(getResources().getString(R.string.feature_weather_activity_title))
                        .description(getResources().getString(R.string.feature_weather_activity_description))
                        .imageResId(R.drawable.hurricane_1)
                        .activityClass(WeatherActivity.class)
                        .build(),
                Feature.builder()
                        .title(getResources().getString(R.string.feature_time_and_events_activity_title))
                        .description(getResources().getString(R.string.feature_time_and_events_activity_description))
                        .imageResId(R.drawable.calendars_1)
                        .activityClass(TimeTrackingActivity.class)
                        .build()
        );
    }
}