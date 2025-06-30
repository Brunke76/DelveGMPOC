package com.worldweaver.delvegm.ui.carousel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CarouselFeature {

    private final String title;
    private final String description;
    private final int imageResId;
    private final Class<?> activityClass;

}
