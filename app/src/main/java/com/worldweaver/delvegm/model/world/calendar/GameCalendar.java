package com.worldweaver.delvegm.model.world.calendar;

import com.worldweaver.delvegm.model.WorldWeaverModel;

import java.util.List;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class GameCalendar extends WorldWeaverModel {

    int numberOfHoursInDay;
    int numberOfDaysInYear;
    List<String> weekDayNames;

}
