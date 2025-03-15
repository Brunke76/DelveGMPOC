package com.worldweaver.delvegm;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.google.android.material.carousel.CarouselLayoutManager;
import com.google.android.material.carousel.CarouselSnapHelper;
import com.google.android.material.carousel.CarouselStrategy;
import com.google.android.material.carousel.HeroCarouselStrategy;
import com.google.android.material.carousel.MultiBrowseCarouselStrategy;
import com.worldweaver.delvegm.ui.carousel.CarouselFeature;
import com.worldweaver.delvegm.ui.carousel.FeatureCarouselAdapter;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

//    private final CarouselStrategy strategy = new MultiBrowseCarouselStrategy();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView featureCarousel = findViewById(R.id.featureCarousel);
        featureCarousel.setLayoutManager(new CarouselLayoutManager(new HeroCarouselStrategy()));

        FeatureCarouselAdapter adapter = new FeatureCarouselAdapter(getFeaturesList(), this);
        featureCarousel.setAdapter(adapter);

        SnapHelper snapHelper = new CarouselSnapHelper();
        snapHelper.attachToRecyclerView(featureCarousel);
    }

    // Sample data for the carousel
    private List<CarouselFeature> getFeaturesList() {
        return Arrays.asList(
                com.worldweaver.delvegm.ui.carousel.CarouselFeature.builder()
                        .title(getResources().getString(R.string.feature_rule_sets_activity_title))
                        .description(getResources().getString(R.string.feature_rule_sets_activity_description))
                        .imageResId(R.drawable.dice_game_1)
                        .activityClass(RuleSetsActivity.class)
                        .build(),
                com.worldweaver.delvegm.ui.carousel.CarouselFeature.builder()
                        .title(getResources().getString(R.string.feature_game_worlds_activity_title))
                        .description(getResources().getString(R.string.feature_game_worlds_activity_description))
                        .imageResId(R.drawable.adventure_map_1)
                        .activityClass(GameWorldsActivity.class)
                        .build(),
                com.worldweaver.delvegm.ui.carousel.CarouselFeature.builder()
                        .title(getResources().getString(R.string.feature_weather_activity_title))
                        .description(getResources().getString(R.string.feature_weather_activity_description))
                        .imageResId(R.drawable.hurricane_1)
                        .activityClass(WeatherActivity.class)
                        .build(),
                com.worldweaver.delvegm.ui.carousel.CarouselFeature.builder()
                        .title(getResources().getString(R.string.feature_time_and_events_activity_title))
                        .description(getResources().getString(R.string.feature_time_and_events_activity_description))
                        .imageResId(R.drawable.calendars_1)
                        .activityClass(TimeTrackingActivity.class)
                        .build()
        );
    }

}