package com.worldweaver.delvegm.model.world.weather;

import com.worldweaver.delvegm.model.WorldWeaverModel;

import java.math.BigDecimal;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class WeatherCondition extends WorldWeaverModel {
    // Minimum time (in seconds) that this weather condition will last
    long minDurationSeconds;
    // Maximum time (in seconds) that this weather condition will last
    long maxDurationSeconds;
    // Minimum temperature that this weather effect will occur (i.e. snowing won't happen if it is over 32 degrees)
    Integer minTemp;
    // Maximum temperature that this weather effect will occur (i.e. snowing won't happen unless it is 32 or lower)
    Integer maxTemp;
    // This is a number from 0-1 that represents the likelihood that this weather condition will occur
    BigDecimal weight;
}
