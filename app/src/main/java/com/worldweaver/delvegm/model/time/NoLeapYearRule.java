package com.worldweaver.delvegm.model.time;

public class NoLeapYearRule implements LeapYearRule {

    private static final int LEAP_MONTH = Integer.MAX_VALUE;

    @Override
    public boolean useLeapYears() {
        return false;
    }

    @Override
    public int getLeapMonth() {
        return LEAP_MONTH;
    }

    @Override
    public int getLeapMonthIndex() {
        return LEAP_MONTH;
    }

    @Override
    public int getAdjustedSecondsInYear(int daysInRegularYear, int secondsInADay) {
        return daysInRegularYear * secondsInADay;
    }

    @Override
    public boolean isLeapMonth(int year, int month) {
        return false;
    }

    @Override
    public boolean isLeapYear(int year) {
        return false;
    }
}
