package com.worldweaver.delvegm.model.world.calendar;

import com.worldweaver.delvegm.model.WorldWeaverModel;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class GameCalendarMonth extends WorldWeaverModel {

    int numberOfDays;

}
