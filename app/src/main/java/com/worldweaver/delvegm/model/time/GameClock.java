package com.worldweaver.delvegm.model.time;

public interface GameClock<T extends GameTimeDuration<?>> {

    void advanceTime(GameDateTimeAdjustment adjustment);

    void rewindTime(GameDateTimeAdjustment adjustment);

    void advanceTime(T duration);

    void rewindTime(T duration);

    GameDateTime<T> getCurrentTime();

}
