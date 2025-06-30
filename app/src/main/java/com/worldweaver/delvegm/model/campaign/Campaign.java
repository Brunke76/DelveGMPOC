package com.worldweaver.delvegm.model.campaign;

import com.worldweaver.delvegm.model.WorldWeaverModel;
import com.worldweaver.delvegm.model.world.GameWorld;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class Campaign extends WorldWeaverModel {

    GameWorld gameWorld;
}
