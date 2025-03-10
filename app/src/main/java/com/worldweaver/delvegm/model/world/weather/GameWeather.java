package com.worldweaver.delvegm.model.world.weather;

import com.worldweaver.delvegm.model.WorldWeaverModel;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class GameWeather extends WorldWeaverModel {
    private WeatherCondition currentCondition;
    private List<WeatherCondition> weatherConditions;
    public GameWeather addWeatherCondition(@NonNull WeatherCondition weatherCondition) {
        if (this.weatherConditions == null) {
            this.weatherConditions = new ArrayList<>();
        }
        this.weatherConditions.add(weatherCondition);
        return this;
    }

}
