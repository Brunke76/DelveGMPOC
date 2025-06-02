package com.worldweaver.delvegm.model.time;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@EqualsAndHashCode
@Getter
@ToString
public class GameDateTimeAdjustment {

    private final int years;
    private final int days;
    private final int seconds;

     GameDateTimeAdjustment(int years, int days, int seconds) {
        this.years = years;
        this.days = days;
        this.seconds = seconds;
    }


}
