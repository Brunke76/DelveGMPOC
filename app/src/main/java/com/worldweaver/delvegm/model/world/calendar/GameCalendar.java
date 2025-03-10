package com.worldweaver.delvegm.model.world.calendar;

import com.worldweaver.delvegm.model.WorldWeaverModel;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class GameCalendar extends WorldWeaverModel {

    int numberOfHoursInDay;
    int numberOfDaysInYear;
    List<String> weekDayNames;

}
