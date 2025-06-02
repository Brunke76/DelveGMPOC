package com.worldweaver.delvegm.model.time;

public interface GameDateTime<T extends GameTimeDuration<?>> {

    GameCalendar getCalendar();

    int getYear();

    /**
     * Gets the 1 based calendar month of the year.
     *      For example in Gregorian Calendar 1 == January, 2 == February, etc.
     * @return int (month of target year)
     */
    int getMonth();

    /**
     * Gets the name of the target month of the year represented by this object.
     *      For example in Gregorian Calendar "January", "February", etc.
     * @return String (month name of target year)
     */
    String getMonthName();

    /**
     * Gets the day of the current month.
     * @return int (day of target month)
     */
    int getDay();

    /**
     * Gets the day of the current year.
     * @return int (day of target year)
     */
    int getDayOfYear();

    /**
     * Gets the 1 based numeric day of the week represented by this object.
     *      For the UTC Weekday standard the value would be 1 == Monday, 2 == Tuesday, ...7 == Sunday
     * @return int (day of week)
     */
    int getDayOfWeek();

    /**
     * Gets the name of the day of the week represented by this object.
     *      For the UTC Weekday standard the value would be "Monday", "Tuesday", etc.
     * @return String (name of the day of the week)
     */
    String getDayOfWeekName();

    /**
     * Gets the full-day-length target hour of the target day represented by this object.
     *      For a 24 hour day, the value would be between 0 and 23 (inclusive)
     *      For a 36 hour day, the value would be between 0 and 35 (inclusive)
     *      etc.
     * @return int (hour of target day)
     */
    int getHour();

    /**
     * Gets the target minute of the target hour of the target day represented by this object.
     *      For a 60 minute long hour, the value would be between 0 and 59 (inclusive)
     *      For a 90 minute long hour, the value would be between 0 and 89 (inclusive)
     *      etc.
     * @return int (minute of target hour)
     */
    int getMinute();

    /**
     * Gets the target second of the target minute of the target hour of the target day represented by this object.
     *      For a 60 second long minute, the value would be between 0 and 59 (inclusive)
     *      For a 90 second long minute, the value would be between 0 and 89 (inclusive)
     * @return int (second of target minute)
     */
    int getSecond();

    /**
     * Gets the target second of the target day represented by this object.
     *      For a 60 second minute, 60 minute hour, and 24 hour day
     *      the value would be between 0 and 86399 (inclusive)
     * @return int (second of target minute)
     */
    int getSecondOfDay();

    // Time manipulation methods
    GameDateTime<T> plus(GameDateTimeAdjustment adjustment);

    GameDateTime<T> minus(GameDateTimeAdjustment adjustment);

    // Time manipulation methods
    GameDateTime<T> plus(T duration);

    GameDateTime<T> minus(T duration);

    GameDateTimeAdjustment until(GameDateTime<T> other);

    // Comparison methods
    boolean isBefore(GameDateTime<T> other);

    boolean isAfter(GameDateTime<T> other);

}
