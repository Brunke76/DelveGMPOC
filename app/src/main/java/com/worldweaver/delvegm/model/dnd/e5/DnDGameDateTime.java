package com.worldweaver.delvegm.model.dnd.e5;

import static java.lang.Math.floorDiv;

import androidx.annotation.NonNull;

import com.worldweaver.delvegm.R;
import com.worldweaver.delvegm.model.dnd.e5.types.DnDDurationType;
import com.worldweaver.delvegm.model.time.GameCalendar;
import com.worldweaver.delvegm.model.time.GameDateTime;
import com.worldweaver.delvegm.model.time.GameDateTimeAdjustment;
import com.worldweaver.delvegm.model.time.GameMonth;
import com.worldweaver.delvegm.model.time.InvalidDateTimeException;

import java.util.Calendar;
import java.util.Comparator;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DnDGameDateTime implements GameDateTime<DnDGameTimeDuration> {

    private static final int ROUND_SECONDS = 6;
    private GameCalendar calendar;
    private int year;
    private int dayOfYear;
    private int secondOfDay;

    /**
     * Private constructor used by the builder to construct a new object.
     * This constructor will normalize values as needed.
     * If the secondOfDay value is too high, it will add to the dayOfYear value accordingly.
     * If the dayOfYear value is too high, it will add to the year value accordingly.
     * The year will only be changed if the dayOfYear value causes it to be adjusted or if the initial value is 0
     *      (there is no year 0, so if the initial value is 0 it will be normalized to 1 also,
     *      the lack of year 0 will be taken into account when incrementing or decrementing the year value)
     * The constructor will also compensate for negative values in the dayOfYear and secondOfDay
     * values by reducing the other values accordingly.
     * For example:
     *      If there are 86400 seconds in a day
     *      And the dayOfYear value is 10
     *      And the secondOfDay value is -10
     *      then the result would be
     *          dayOfYear = 9,
     *          secondOfDay = 86390
     * @param calendar
     * @param year
     * @param dayOfYear
     * @param secondOfDay
     * @throws InvalidDateTimeException
     */
    private DnDGameDateTime(@NonNull GameCalendar calendar, int year, int dayOfYear, int secondOfDay) throws InvalidDateTimeException {
        this.calendar = calendar;
        int targetYear = year;

        // There is no year 0, the first year of the calendar is 1, the year before that is -1
        // So, we will normalize year 0 to year 1
        if (targetYear == 0) {
            targetYear = 1;
        }

        int targetDayOfYear = dayOfYear;
        // Taking the modulus of the seconds will give us the correct actual seconds even if the seconds value is negative
        // for instance, there are 86400 seconds in a Gregorian calendar day.
        // if the secondOfDay value is greater then 86400, then we would get the correct number of seconds into the actual day
        //      (The next step will add any additional days based upon the number of seconds in a day)
        // if the secondOfDay value is -180 then the correct value should be 180 seconds before the end of the previous day
        //      86580 % 86400 = 180 (which would be 180 days into the next year)
        //      and -180 % 86400 = 86220 (which is the same as 86400 - 180 or 180 days before the end of the previous year)
        int targetSecondOfDay = secondOfDay % calendar.getSecondsInDay();

        // Now that we have used the modulus to get the correct number of seconds in the target day.
        // We need to adjust the days if there are more total seconds then there are in a single day
        targetDayOfYear += Math.floorDiv(secondOfDay, calendar.getSecondsInDay());

        // And now that we have adjusted the day of the year by adding additional days from the number of seconds given.
        // We can check to see if the targetDayOfYear value should adjust the target year.
        // Unfortunately, each year may have different numbers of days (due to leap years),
        // so we need to loop through the years and get the number of days to be more accurate.
        if (targetDayOfYear < 0) {
            while (Math.abs(targetDayOfYear) > calendar.getDaysInYear(targetYear - 1)) {
                // targetDayOfYear is negative, so to reduce the absolute value we need to add to it
                // We need to reduce the absolute value by the number of days in the PREVIOUS year
                targetDayOfYear += calendar.getDaysInYear(targetYear - 1);
                // Now that we have reduced the absolute value of days by the previous year,
                // we need to decrement the target year and repeat
                targetYear--;

                // There is no year 0, the first year of the calendar is 1, the year before that is -1
                // So, if we are decrementing the year, and we reach year 0, we need to normalize it to year -1
                if (targetYear == 0) {
                    targetYear = -1;
                }
            }
        } else {
            while (targetDayOfYear > calendar.getDaysInYear(targetYear)) {
                // targetDayOfYear is positive so we reduce the amount by subtracting
                // the number of days in the current target year.
                targetDayOfYear -= calendar.getDaysInYear(targetYear);
                // Now tha we have removed the days of the current year from the target days value,
                // we can increment the target year.
                targetYear++;

                // There is no year 0, the first year of the calendar is 1, the year before that is -1
                // So, if we are incrementing the year, and we reach year 0, we need to normalize it to year 1
                if (targetYear == 0) {
                    targetYear = 1;
                }
            }
        }

        // if we set this to the modulus of the days of the current target year, then this
        // will give us the correct day of the year if targetDayOfYear is either positive or negative
        this.dayOfYear = targetDayOfYear % calendar.getDaysInYear(targetYear);
        // year values can be positive or negative, the only restriction is that they cannot be 0
        // Since we have already normalized the year so that it is not 0, we do not need to normalize it again.
        this.year = targetYear;
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
        int remainingDays = this.dayOfYear;
        while (remainingDays >= 0 && currentMonth <= calendar.getMonths().size()) {
            remainingDays -= calendar.getSecondsInMonth(this.year, currentMonth);
            currentMonth++;
        }
        return currentMonth;
    }

    @Override
    public String getMonthName() {
        return calendar.getMonths().get(getMonth() - 1).getName();
    }

    @Override
    public int getDay() {
        int currentMonth = this.getMonth();
        int daysUpToMonth = 0;
        if (currentMonth > 1) {
            daysUpToMonth = calendar.getMonths()
                                    .subList(0, this.getMonth() - 1)
                                    .stream()
                                    .map(GameMonth::getNumberOfDays)
                                    .reduce(0, Integer::sum);
        }
        return this.dayOfYear - daysUpToMonth;
    }

    @Override
    public int getDayOfYear() {
        return this.dayOfYear;
    }

    @Override
    public int getDayOfWeek() {
        return calendar.getDayOfWeek(this.year, this.dayOfYear);
    }

    @Override
    public String getDayOfWeekName() {
        return calendar.getWeekdayName(this.year, this.dayOfYear);
    }

    @Override
    public int getHour() {
        return Math.floorDiv(secondOfDay, calendar.getSecondsInHour());
    }

    @Override
    public int getMinute() {
        int secondsOfHour = secondOfDay % calendar.getSecondsInHour();
        return Math.floorDiv(secondsOfHour, calendar.getSecondsInMinute());
    }

    @Override
    public int getSecond() {
        return secondOfDay % calendar.getSecondsInMinute();
    }

    @Override
    public int getSecondOfDay() {
        return secondOfDay;
    }

    @Override
    public DnDGameDateTime plus(GameDateTimeAdjustment adjustment) {
        int targetYear = this.year + adjustment.getYears();
        // We need to compensate for the fact that there is no year 0
        if (targetYear >= 0 && this.year < 0) targetYear++;
        return builder(this.calendar, targetYear)
                                .month(getMonth())
                                .day(getDay() + adjustment.getDays())
                                .hour(getHour())
                                .minute(getMinute())
                                .second(getSecond() + adjustment.getSeconds())
                                .build();
    }

    @Override
    public DnDGameDateTime minus(GameDateTimeAdjustment adjustment) {
        int targetYear = this.year - adjustment.getYears();
        // We need to compensate for the fact that there is no year 0
        if (targetYear <= 0 && this.year > 0) targetYear--;
        return builder(calendar, targetYear)
                                .month(getMonth())
                                .day(getDay() - adjustment.getDays())
                                .hour(getHour())
                                .minute(getMinute())
                                .second(getSecond() - adjustment.getSeconds())
                                .build();
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
        int daysUntil = other.getDayOfYear() - this.getDayOfYear();
        int secondsUntil = other.getSecondOfDay() - this.getSecondOfDay();
        return GameDateTimeAdjustment.builder()
                                     .years(yearsUntil)
                                     .days(daysUntil)
                                     .seconds(secondsUntil)
                                     .build();
    }

    @Override
    public boolean isBefore(GameDateTime<DnDGameTimeDuration> other) {
        // If the current year is less than the other year, then we know it IS before
        if (this.getYear() < other.getYear()) {
            return true;
        }
        // If the current year is greater than the other year, then we know it is NOT before
        if (this.getYear() > other.getYear()) {
            return false;
        }
        // If we have made it this far, then we know that the years are equal
        // If the current day is less than the other day, then we know it IS before
        if (this.getDayOfYear() < other.getDayOfYear()) {
            return true;
        }
        // If the current day is greater than the other day, then we know it is NOT before
        if (this.getDayOfYear() > other.getDayOfYear()) {
            return false;
        }
        // If we have made it this far, then we know that the years and days are equal
        // Now we just need to compare the seconds to see if the current value is before the other value
        return this.getSecondOfDay() < other.getSecondOfDay();
    }

    @Override
    public boolean isAfter(GameDateTime<DnDGameTimeDuration> other) {
        // If the current year is less than the other year, then we know it is NOT after
        if (this.getYear() < other.getYear()) {
            return false;
        }
        // If the current year is greater than the other year, then we know it IS after
        if (this.getYear() > other.getYear()) {
            return true;
        }
        // If we have made it this far, then we know that the years are equal
        // If the current day is less than the other day, then we know it is NOT after
        if (this.getDayOfYear() < other.getDayOfYear()) {
            return false;
        }
        // If the current day is greater than the other day, then we know it IS after
        if (this.getDayOfYear() > other.getDayOfYear()) {
            return true;
        }
        // If we have made it this far, then we know that the years and days are equal
        // Now we just need to compare the seconds to see if the current value is after the other value
        return this.getSecondOfDay() > other.getSecondOfDay();
    }

    public static DnDGameDateTimeBuilder builder(GameCalendar calendar, int year) {
        return new DnDGameDateTimeBuilder(calendar, year);
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
                yearAdjustmentAmount = floorDiv(durationAmount, numberOfMonthsInYear);
                // We need to focus on only those months that are left
                int monthsToAdjust = durationAmount % numberOfMonthsInYear;
                // Month lengths vary, so we will just use the average month length in days.
                int averageDaysInMonth = floorDiv(calendar.getDaysInRegularYear(), calendar.getMonths().size());
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

    public static class DnDGameDateTimeBuilder {
        private GameCalendar calendar;
        private int year;
        private int month = 1;
        private int day = 1;
        private int hour;
        private int minute;
        private int second;

        public DnDGameDateTimeBuilder(@NonNull GameCalendar calendar, int year) throws InvalidDateTimeException {
            if (year == 0) {
                throw new InvalidDateTimeException("year can be a positive or negative integer, but cannot be 0");
            }
            this.calendar = calendar;
            this.year = year;
        }

        public DnDGameDateTimeBuilder month(int month) throws InvalidDateTimeException {
            if (month == 0) {
                throw new InvalidDateTimeException("month can be a positive or negative integer, but cannot be 0");
            } else {
                this.month = month;
            }

            return this;
        }

        public DnDGameDateTimeBuilder day(int day) throws InvalidDateTimeException {
            if (day == 0) {
                throw new InvalidDateTimeException("day can be a positive or negative integer, but cannot be 0");
            } else {
                this.day = day;
            }

            return this;
        }

        public DnDGameDateTimeBuilder hour(int hour) {
            this.hour = hour;
            return this;
        }

        public DnDGameDateTimeBuilder minute(int minute) {
            this.minute = minute;
            return this;
        }

        public DnDGameDateTimeBuilder second(int second) {
            this.second = second;
            return this;
        }

        public DnDGameDateTime build() {
            int dayOfYear = this.day;
            int targetYear = this.year;
            int targetMonth = this.month;
            if (targetMonth < 0) {
                int numOfMonths = calendar.getMonths().size();
                // We need to reduce the target year by one year if the Abs value of targetMonth
                // is less than a years worth of months
                // (because it is negative, so it would go back down into the previous year)
                // We will also decrease the year value further if there is more than a years worth of months being removed
                targetYear += Math.ceilDiv(targetMonth, numOfMonths);
                // this.year cannot be set to 0, so we know that if it is, then it is due to subtraction
                // Also, if targetYear < 0 and this.year > 0 then we also know that the value is due to subtraction.
                // We need to compensate for the fact that there is no year 0. We can do this by decrementing the value by 1.
                if (targetYear == 0 || targetYear < 0 && this.year > 0) targetYear--;
                // Now we need to remove any month amounts that would be more than a years worth
                // targetMonth is a negative value, so we need to negate numOfMonths to get the correct modulus value
                targetMonth = targetMonth % -numOfMonths;
                // Now that we have a correct number of months, we can loop through the list of months
                // to get the number of days for each month starting with the last and moving
                // backward through the list. Then we add (actually subtract since we are working in reverse)
                // the days for each month to the total number of days.
                for (int i = -1; i > targetMonth; i--) {
                    // We have a negative i value, so if we add that to the numOfMonths value,
                    // then we get the indexes moving from the end of the list forward.
                    // i.e.
                    //      -1 -> numOfMonths - 1 == last index of the list
                    //      -2 -> numOfMonths - 2 == second to last index of the list etc.
                    dayOfYear -= calendar.getDaysInMonth(targetYear, numOfMonths + i);
                }
            } else {
                int workingMonth = 1;
                // we want to stop when workingMonth == this.month because we should only be this.days into that month,
                // so we don't want to count those days
                while (workingMonth < this.month) {
                    dayOfYear += calendar.getDaysInMonth(year, workingMonth);
                    workingMonth++;
                }
            }

            int secondOfDay = hour * calendar.getSecondsInHour() + minute * calendar.getSecondsInMinute() + second;
            // The constructor will normalize the values, so we don't really need to worry about any of that here.
            return new DnDGameDateTime(this.calendar, this.year, dayOfYear, secondOfDay);
        }
    }

}
