package com.worldweaver.delvegm.model.time;

public class GregorianLeapYearRule implements LeapYearRule {

    private static final int LEAP_MONTH = 2;

    @Override
    public boolean useLeapYears() {
        return true;
    }

    @Override
    public int getLeapMonth() {
        return LEAP_MONTH;
    }

    @Override
    public int getLeapMonthIndex() {
        return LEAP_MONTH - 1;
    }

    @Override
    public int getAdjustedSecondsInYear(int daysInRegularYear, int secondsInADay) {
        int leapSeconds = ((Double) Math.floor(0.24219 * secondsInADay)).intValue();
        return (daysInRegularYear * secondsInADay) + leapSeconds;
    }

    @Override
    public boolean isLeapMonth(int year, int calendarMonth) {
        return isLeapYear(year) && calendarMonth == LEAP_MONTH;
    }

    @Override
    public boolean isLeapYear(int year) {
        // Standard Gregorian leap year occurs on years divisible by 4
        return year % 4 == 0
                // Leap years do NOT occur if the year is divisible by 100
                // UNLESS the year is ALSO divisible by 400
                && (year % 100 != 0 || year % 400 == 0);
    }
}
