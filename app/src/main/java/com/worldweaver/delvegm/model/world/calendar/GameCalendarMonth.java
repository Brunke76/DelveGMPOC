package com.worldweaver.delvegm.model.world.calendar;

import com.worldweaver.delvegm.model.WorldWeaverModel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class GameCalendarMonth extends WorldWeaverModel {

    int numberOfDays;

}
