package com.worldweaver.delvegm.ui.features;

import lombok.Builder;
import lombok.Data;

/**
 * Model class for a feature item
 */
@Data
@Builder
public class Feature {

    private final String title;
    private final String description;
    private final int imageResId;
    private final Class<?> activityClass;

}
