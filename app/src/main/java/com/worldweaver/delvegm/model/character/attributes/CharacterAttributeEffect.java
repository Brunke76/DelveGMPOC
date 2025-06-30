package com.worldweaver.delvegm.model.character.attributes;


import com.worldweaver.delvegm.model.WorldWeaverModel;
import com.worldweaver.delvegm.model.character.types.CharacterStatType;
import com.worldweaver.delvegm.model.time.DurationType;
import com.worldweaver.delvegm.model.time.GameTimeDuration;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class CharacterAttributeEffect extends WorldWeaverModel {

    CharacterStatType attributeType;
    int effectAmount;
    GameTimeDuration<?> duration;

}
