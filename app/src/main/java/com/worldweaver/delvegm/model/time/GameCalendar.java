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

    public int getSecondsInMonth(int year, int monthOfYear) {
        return getSecondsInDay() * getDaysInMonth(year, monthOfYear);
    }

    public int getDaysInWeek() {
        return this.weekDayNames.size();
    }

    public int getDaysInYear(int year) {
        return (leapYearRule.isLeapYear(year)) ? getDaysInRegularYear() + 1 : getDaysInRegularYear();
    }

    public int getDayOfYear(int year, int monthOfYear, int dayOfMonth) {
        // If this is the first month of the year, then the day of the year and day of the month will be the same
        int dayOfYear = dayOfMonth;
        // If the current month is after the first month:
        if (monthOfYear > 1) {
            // then we need to get a list of all of the months up to the current month
            // We need to subtract 2 from the current month so that we have the index of the month BEFORE the current
            // (since it is not over, we don't want to add all those days to the total)
            List<GameMonth> monthsSoFar = months.subList(0, monthOfYear - 2);
            // Now we need to loop through the months in the list and add the number of days
            // We only care about the normal number of days, we will add the leap-day later as needed
            dayOfYear += monthsSoFar.stream()
                                    .map(GameMonth::getNumberOfDays)
                                    .reduce(0, Integer::sum);
            // If this is a leap year and the leap month is already passed,
            // then we need to add the leap day to the total count
            if (leapYearRule.isLeapYear(year) && leapYearRule.getLeapMonth() < monthOfYear) {
                dayOfYear ++;
            }
        }
        return dayOfYear;
    }

    public int getDaysInMonth(int year, int monthOfYear) {
        int daysInMonth = months.size() > monthOfYear ? months.get(monthOfYear - 1).getNumberOfDays() : 0;
        // If this is a leap year, and the leap month we need to add one more day
        if (leapYearRule.isLeapMonth(year, monthOfYear)) {
            daysInMonth ++;
        }
        return daysInMonth;
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
