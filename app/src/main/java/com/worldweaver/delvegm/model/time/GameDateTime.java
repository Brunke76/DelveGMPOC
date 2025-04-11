package com.worldweaver.delvegm.model.time;

public interface GameDateTime<T extends GameTimeDuration<?>> {

    GameCalendar getCalendar();

    int getSecondOfYear();

    int getYear();

    int getMonth();

    int getDay();

    int getHour();

    int getMinute();

    int getSecond();

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
