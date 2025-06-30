package com.worldweaver.delvegm.model.time;

public interface LeapYearRule {

    /**
     * Simply returns true if this leap year rule actually has leap years.
     * If the leap year rule does not use leap years, then this will return false.
     *  - This should pretty much always be true, except for the NoLeapYearRule.
     * @return true if leap years are used, or false otherwise
     */
    boolean useLeapYears();

    /**
     * Gets the month of the year (1 based) that is the leap month in a leap year.
     *  - This is similar to getLeapMonthIndex() except that it is 1 based.
     *  - So, the first month of the year would be 1, second month would be 2, etc.
     * @return 1 based month that is the leap month for this leap year rule.
     */
    int getLeapMonth();

    /**
     * Gets the index of the month (0 based) that is the leap month in a leap year.
     *  - This is similar to getLeapMonth() except that it is 0 based.
     *  - So, the first month of the year would be 0, second month would be 1, etc.
     * @return 0 based month that is the leap month for this leap year rule.
     */
    int getLeapMonthIndex();

    /**
     * Gets the total number of seconds in a year
     * including the additional seconds that causes a leap year.
     * For example:
     *  - For 'no leap year' rule, this value would be daysInRegularYear * secondsInADay
     *  - The Gregorian calendar has 365.24219 days, And there are 86400 seconds in a day.
     *      So the Adjusted amount of seconds in a year would be:
     *          0.24219 * 86400 = 20925 seconds
     *          So the resulting AdjustedSecondsInYear would be (365 * 86400) + 20925
     * @param daysInRegularYear
     * @param secondsInADay
     * @return number of extra seconds in any given year
     */
    int getAdjustedSecondsInYear(int daysInRegularYear, int secondsInADay);

    /**
     * Gets the total number of days from the start of startYear up to (but not including) endYear
     * including the additional days from leap years.
     * For example:
     *  - For 'no leap year' rule, this value would be daysInRegularYear * (endYear - startYear)
     *  - The Gregorian calendar has 365.24219 days, And there are 86400 seconds in a day.
     *      So the amount of days between 2 years would be:
     *          365.24219 * (endYear - startYear)
     * @param startYear - The starting year in the comparison (included in day count)
     * @param endYear - The ending year of the comparison (excluded from day count)
     * @param daysInRegularYear - number of days in a normal (non-leap) year
     * @param secondsInADay - number of seconds in a day (useful for calculating partial days when dealing with average year length)
     * @return number of days from the start of startYear to the start of endYear
     */
    int getNumberOfDaysBetweenYears(int startYear, int endYear, int daysInRegularYear);

    /**
     * Returns true if the input month (1 based) of the input year is a leap month.
     * If the input year is not a leap year, or the input month is not the leap month, then should return false.
     * @param year
     * @param calendarMonth
     * @return boolean true if the input year and month is a leap month or false if not.
     */
    boolean isLeapMonth(int year, int calendarMonth);

    /**
     * Returns true if the input year is a leap year. Otherwise, returns false.
     * @param year
     * @return boolean true if the input year is a leap year or false if not.
     */
    boolean isLeapYear(int year);

}
