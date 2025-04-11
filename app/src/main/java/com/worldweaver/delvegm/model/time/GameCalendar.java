package com.worldweaver.delvegm.model.time;

import com.worldweaver.delvegm.model.WorldWeaverModel;

import java.util.List;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class GameCalendar extends WorldWeaverModel {
    private final int secondsInMinute; // Could be configurable
    private final int minutesInHour;   // Could be configurable
    private final int hoursInDay;
    private final List<String> weekDayNames;
    private final List<GameMonth> months;
    private final LeapYearRule leapYearRule;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Integer daysInRegularYear;

    public int getDaysInRegularYear() {
        if (daysInRegularYear == null) {
            daysInRegularYear = months.stream()
                                      .map(GameMonth::getNumberOfDays)
                                      .reduce(0, Integer::sum);
        }
        return daysInRegularYear;
    }

    public int getAdjustedSecondsInYear() {
        return leapYearRule.getAdjustedSecondsInYear(getDaysInRegularYear(), getSecondsInDay());
    }

    // Add methods for calendar calculations

    public int getSecondsInHour() {
        return secondsInMinute * minutesInHour;
    }

    public int getSecondsInDay() {
        return getSecondsInHour() * hoursInDay;
    }

    public int getSecondsInWeek() {
        return getSecondsInDay() * getDaysInWeek();
    }

    public int getDaysInWeek() {
        return this.weekDayNames.size();
    }

    public int getDaysInYear(int year) {
        return (leapYearRule.isLeapYear(year)) ? getDaysInRegularYear() + 1 : getDaysInRegularYear();
    }

    public int getDayOfYear(int year, int monthOfYear, int dayOfMonth) {
        int dayOfYear = dayOfMonth;
        if (monthOfYear > 1) {
            List<GameMonth> monthsSoFar = months.subList(0, monthOfYear - 1);
            // We need to get a sublist containing all of the months BEFORE the current month
            // (since it is not over, we don't want to add all those days to the total)
            dayOfYear += monthsSoFar.stream()
                                    .map(GameMonth::getNumberOfDays)
                                    .reduce(0, Integer::sum);
            // If this is a leap year, we need to see if we should add one more day
            if (leapYearRule.isLeapYear(year) && leapYearRule.getLeapMonth() < monthOfYear) {
                dayOfYear ++;
            }
        }
        return dayOfYear;
    }

    public int getDaysInMonth(int year, int monthIndex) {
        int daysInMonth = months.size() > monthIndex ? months.get(monthIndex).getNumberOfDays() : 0;
        // If this is a leap year, and the leap month we need to add one more day
        if (leapYearRule.isLeapMonth(year, monthIndex + 1)) {
            daysInMonth ++;
        }
        return daysInMonth;
    }

    public int getSecondsInMonth(int year, int monthIndex) {
        return getSecondsInDay() * getDaysInMonth(year, monthIndex);
    }

    public long getAllDaysUpToYear(int year) {
        long days = 0;
        for (int i = 1; i < year; i++) {
            days += getDaysInYear(i);
        }
        return days;
    }

    public int getDayOfWeek(int year, int dayOfYear) {
        long totalDays = getAllDaysUpToYear(year) + dayOfYear;
        int numOfDaysInWeek = weekDayNames.size();
        int dayOfWeek = Long.valueOf(totalDays % numOfDaysInWeek).intValue();

        // last day of the week will be: X % X == 0, so we need to make it X
        return dayOfWeek > 0 ? dayOfWeek : numOfDaysInWeek;
    }

    public String getWeekdayName(int year, int dayOfYear) {
        return weekDayNames.get(getDayOfWeek(year, dayOfYear) - 1);
    }

}
