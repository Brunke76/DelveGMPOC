package com.worldweaver.delvegm.model.dnd.e5;

import com.worldweaver.delvegm.model.dnd.e5.types.DnDDurationType;
import com.worldweaver.delvegm.model.time.GameTimeDuration;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class DnDGameTimeDuration extends GameTimeDuration<DnDDurationType> {

    @NonNull
    private DnDDurationType durationType;

    private int durationAmount;

    @androidx.annotation.NonNull
    @Override
    public DnDDurationType getDurationType() {
        return this.durationType;
    }

}
