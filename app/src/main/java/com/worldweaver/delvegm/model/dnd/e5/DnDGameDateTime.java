package com.worldweaver.delvegm.model.dnd.e5;

import androidx.annotation.NonNull;

import com.worldweaver.delvegm.R;
import com.worldweaver.delvegm.model.dnd.e5.types.DnDDurationType;
import com.worldweaver.delvegm.model.time.GameCalendar;
import com.worldweaver.delvegm.model.time.GameDateTime;
import com.worldweaver.delvegm.model.time.GameDateTimeAdjustment;
import com.worldweaver.delvegm.model.time.GameTimeDuration;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DnDGameDateTime implements GameDateTime<DnDGameTimeDuration> {

    private static final int ROUND_SECONDS = 6;
    private GameCalendar calendar;
    private int year;
    private int secondOfYear;

    /**
     * This constructor creates a GameDateTime object which uses the given GameCalendar for formatting.
     * The values for this object will be normalized based upon the GameCalendar.
     * So, if the secondOfYear value is higher than the allotted number of seconds in a year,
     * then the year value will be incremented and the secondOfYear value decremented until a valid secondOfYear value is achieved.
     * If the secondOfYear value is negative, then the result will be reducing the year value accordingly.
     * For instance, if we are using GameCalendar calendar, which only has 25 seconds in a year:
     *     - GameDateTime example = new GameDateTime(calendar, 10, 0) will result in example.getYear() = 10 and example.getSecondOfYear() = 0
     *     - GameDateTime example = new GameDateTime(calendar, 10, 24) will result in example.getYear() = 10 and example.getSecondOfYear() = 24
     *     - GameDateTime example = new GameDateTime(calendar, 10, 25) will result in example.getYear() = 11 and example.getSecondOfYear() = 0
     *     - GameDateTime example = new GameDateTime(calendar, 10, 52) will result in example.getYear() = 12 and example.getSecondOfYear() = 2
     *     - GameDateTime example = new GameDateTime(calendar, 10, -52) will result in example.getYear() = 7 and example.getSecondOfYear() = 23
     *
     * @param calendar - the GameCalendar which is used to determine the length of minutes, hours, days, months, and years.
     * @param year - The starting year for this GameDateTime object. This will be adjusted if the secondOfYear value is too high.
     * @param secondOfYear - This is the second counted from the start of the year where:
     *          - 0 is the very first second of New Year's Day
     *          - this.calendar.getSecondsInDay() * calendar.getDaysInYear(this.year) - 1 is the last second of New Year's Eve
     */
    public DnDGameDateTime(@NonNull GameCalendar calendar, int year, long secondOfYear) {
        this.calendar = calendar;
        long secondsInDay = calendar.getSecondsInDay();
        int workingYear = year;
        // This is to normalize the secondOfYear value.
        // if secondOfYear would make this date move into the next year (or more)
        // then we need to adjust both values properly so that it makes sense.
        while (secondOfYear >= secondsInDay * calendar.getDaysInYear(workingYear)) {
            secondOfYear -= secondsInDay * calendar.getDaysInYear(workingYear);
            workingYear++;
        }
        // This part normalizes values when the secondOfYear value is negative
        while (secondOfYear < 0) {
            secondOfYear += secondsInDay * calendar.getDaysInYear(workingYear);
            workingYear--;
        }
        this.year = workingYear;
        this.secondOfYear = ((Long)secondOfYear).intValue();
    }

    public DnDGameDateTime(@NonNull GameCalendar calendar, int year, int monthOfYear, int dayOfMonth, int hour, int minute, int second) {
        this.calendar = calendar;
        this.year = year;
        // Convert time components to seconds
        int minutesToSeconds = minute * calendar.getSecondsInMinute();
        int hoursToSeconds = hour * calendar.getSecondsInHour();

        int secondsInDay = calendar.getSecondsInDay();
        // Calculate days that have occurred so far this year (not including today)
        int daysNotIncludingToday = calendar.getDayOfYear(year, monthOfYear, dayOfMonth) - 1;
        int daysToSeconds = daysNotIncludingToday * secondsInDay;

        this.secondOfYear = second + minutesToSeconds + hoursToSeconds + daysToSeconds;
    }

    @Override
    public int getYear() {
        // Calculate from totalSeconds using calendar rules
        return this.year;
    }

    @Override
    public int getMonth() {
        // Calculate from totalSeconds using calendar rules
        int currentMonth = 1;
        int remainingSeconds = this.secondOfYear;
        while (remainingSeconds >= 0 && currentMonth <= calendar.getMonths().size()) {
            remainingSeconds -= calendar.getSecondsInMonth(this.year, currentMonth);
            currentMonth++;
        }
        return currentMonth;
    }

    @Override
    public int getDay() {
        // Calculate from totalSeconds using calendar rules
        int currentMonth = 1;
        int remainingSeconds = this.secondOfYear;
        while (remainingSeconds >= calendar.getSecondsInMonth(this.year, currentMonth)
                && currentMonth <= calendar.getMonths().size()) {
            remainingSeconds -= calendar.getSecondsInMonth(this.year, currentMonth);
            currentMonth++;
        }
        return Math.floorDiv(remainingSeconds, calendar.getSecondsInDay());
    }

    @Override
    public int getHour() {
        return ((secondOfYear % calendar.getSecondsInDay()) / 3600);
    }

    @Override
    public int getMinute() {
        return ((secondOfYear % calendar.getSecondsInMinute()) / 60);
    }

    @Override
    public int getSecond() {
        return secondOfYear % calendar.getSecondsInMinute();
    }

    @Override
    public DnDGameDateTime plus(GameDateTimeAdjustment adjustment) {
        return new DnDGameDateTime(calendar, this.year + adjustment.getYears(), this.secondOfYear + adjustment.getSeconds());
    }

    @Override
    public DnDGameDateTime minus(GameDateTimeAdjustment adjustment) {
        return new DnDGameDateTime(calendar, this.year - adjustment.getYears(), this.secondOfYear - adjustment.getSeconds());
    }

    @Override
    public DnDGameDateTime plus(DnDGameTimeDuration duration) {
        GameDateTimeAdjustment adjustmentAmount = getDateTimeAdjustment(duration);
        return this.plus(adjustmentAmount);
    }

    @Override
    public DnDGameDateTime minus(DnDGameTimeDuration duration) {
        GameDateTimeAdjustment adjustmentAmount = getDateTimeAdjustment(duration);
        return this.minus(adjustmentAmount);
    }

    @Override
    public GameDateTimeAdjustment until(GameDateTime<DnDGameTimeDuration> other) {
        int yearsUntil = other.getYear() - this.getYear();
        long secondsUntil = other.getSecondOfYear() - this.getSecondOfYear();
        return GameDateTimeAdjustment.builder()
                                     .years(yearsUntil)
                                     .seconds(secondsUntil)
                                     .build();
    }

    @Override
    public boolean isBefore(GameDateTime<DnDGameTimeDuration> other) {
        if (this.getYear() == other.getYear()) {
            return this.getSecondOfYear() < other.getSecondOfYear();
        }
        return this.getYear() < other.getYear();
    }

    @Override
    public boolean isAfter(GameDateTime<DnDGameTimeDuration> other) {
        if (this.getYear() == other.getYear()) {
            return this.getSecondOfYear() > other.getSecondOfYear();
        }
        return this.getYear() > other.getYear();
    }

    private GameDateTimeAdjustment getDateTimeAdjustment(DnDGameTimeDuration duration) {
        int durationAmount = duration.getDurationAmount();
        int yearAdjustmentAmount = 0;
        int secondsAdjustmentAmount = 0;
        switch (duration.getDurationType()) {
            case DnDDurationType.BEGINNING_OF_TARGETS_NEXT_TURN:
            case DnDDurationType.BEGINNING_OF_YOUR_NEXT_TURN:
            case DnDDurationType.END_OF_TARGETS_NEXT_TURN:
            case DnDDurationType.END_OF_YOUR_NEXT_TURN:
                secondsAdjustmentAmount = R.integer.battle_round_seconds;
                break;
            case DnDDurationType.TURNS:
            case DnDDurationType.ROUNDS:
                secondsAdjustmentAmount = durationAmount * R.integer.battle_round_seconds;
                break;
            case DnDDurationType.SECONDS:
                secondsAdjustmentAmount = durationAmount;
                break;
            case DnDDurationType.MINUTES:
                secondsAdjustmentAmount = durationAmount * calendar.getSecondsInMinute();
                break;
            case DnDDurationType.HOURS:
                secondsAdjustmentAmount = durationAmount * calendar.getSecondsInHour();
                break;
            case DnDDurationType.DAYS:
                secondsAdjustmentAmount = durationAmount * calendar.getSecondsInDay();
                break;
            case DnDDurationType.WEEKS:
                secondsAdjustmentAmount = durationAmount * calendar.getSecondsInWeek();
                break;
            case DnDDurationType.MONTHS:
                int numberOfMonthsInYear = calendar.getMonths().size();
                // If the amount of months is more than or equal to a full year, then we should adjust the number of years accordingly
                yearAdjustmentAmount = Math.floorDiv(durationAmount, numberOfMonthsInYear);
                // We need to focus on only those months that are left
                int monthsToAdjust = durationAmount % numberOfMonthsInYear;
                // Month lengths vary, so we will just use the average month length in days.
                int averageDaysInMonth = Math.floorDiv(calendar.getDaysInRegularYear(), calendar.getMonths().size());
                secondsAdjustmentAmount = calendar.getSecondsInDay() * averageDaysInMonth * monthsToAdjust;
                break;
            case DnDDurationType.YEARS:
                yearAdjustmentAmount = durationAmount;
                break;
            case DnDDurationType.PERMANENT:
                yearAdjustmentAmount = Integer.MAX_VALUE;
                secondsAdjustmentAmount = Integer.MAX_VALUE;
                break;
            case DnDDurationType.INSTANTANEOUS:
            default: break;
        }
        return GameDateTimeAdjustment.builder()
                                     .years(yearAdjustmentAmount)
                                     .seconds(secondsAdjustmentAmount)
                                     .build();
    }

    private record DateTimeAdjustmentAmount(int years, int seconds) {}

}
