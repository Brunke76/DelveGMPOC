package com.worldweaver.delvegm.model.items;

import com.worldweaver.delvegm.model.WorldWeaverModel;
import com.worldweaver.delvegm.model.character.types.CharacterStatType;
import com.worldweaver.delvegm.model.character.types.CharacterSkillType;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class GameItem extends WorldWeaverModel {

    // The type of item this is
    GameItemType itemType;

    // Character Attributes and their minimum values needed for use of this item
    Map<CharacterStatType, Integer> requiredAttributesMin;
    // Character Attributes and their maximum values allowed for use of this item
    Map<CharacterStatType, Integer> requiredAttributesMax;
    // Any Character Skills that are required
    List<CharacterSkillType> requiredProficiencies;
    BigDecimal value;
    BigDecimal weight;

}
