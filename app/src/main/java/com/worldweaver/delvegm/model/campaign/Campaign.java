package com.worldweaver.delvegm.model.campaign;

import com.worldweaver.delvegm.model.WorldWeaverModel;
import com.worldweaver.delvegm.model.world.GameWorld;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class Campaign extends WorldWeaverModel {

    GameWorld gameWorld;
}
