package com.worldweaver.delvegm.model.time;

import com.worldweaver.delvegm.model.WorldWeaverModel;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public final class GameMonth extends WorldWeaverModel {

    /**
     * This is the regular number of days in the month
     * This value should NOT include extra days due to leap years
     */
    @Setter(AccessLevel.NONE) // Prevents @Data from creating a default setter for this field
    private int numberOfDays;

}
