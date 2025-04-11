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
    private final long seconds;

     GameDateTimeAdjustment(int years, long seconds) {
        this.years = years;
        this.seconds = seconds;
    }


}
