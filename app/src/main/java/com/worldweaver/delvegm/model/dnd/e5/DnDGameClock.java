package com.worldweaver.delvegm.model.dnd.e5;

import com.worldweaver.delvegm.model.time.GameClock;
import com.worldweaver.delvegm.model.time.GameDateTime;
import com.worldweaver.delvegm.model.time.GameDateTimeAdjustment;
import com.worldweaver.delvegm.model.time.GameTimeDuration;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
public class DnDGameClock implements GameClock<DnDGameTimeDuration> {
    @Setter(AccessLevel.NONE)
    GameDateTime<DnDGameTimeDuration> currentTime;

    @Override
    public void advanceTime(GameDateTimeAdjustment adjustment) {
        currentTime = currentTime.plus(adjustment);
        // Trigger time-based events, etc.
    }

    @Override
    public void rewindTime(GameDateTimeAdjustment adjustment) {
        currentTime = currentTime.minus(adjustment);
    }

    @Override
    public void advanceTime(DnDGameTimeDuration duration) {
        currentTime = currentTime.plus(duration);
    }

    @Override
    public void rewindTime(DnDGameTimeDuration duration) {
        currentTime = currentTime.minus(duration);
    }

    @Override
    public GameDateTime<DnDGameTimeDuration> getCurrentTime() {
        return currentTime;
    }
}
