package com.worldweaver.delvegm.model.time;

import com.worldweaver.delvegm.model.WorldWeaverModel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public abstract class GameTimeDuration<T> extends WorldWeaverModel {

    public abstract T getDurationType();
    public abstract int getDurationAmount();

}
