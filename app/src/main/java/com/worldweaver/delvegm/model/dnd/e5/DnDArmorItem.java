package com.worldweaver.delvegm.model.dnd.e5;

import com.worldweaver.delvegm.model.dnd.e5.types.DnDArmorProficiencyType;
import com.worldweaver.delvegm.model.items.GameItem;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class DnDArmorItem extends GameItem {

    final DnDArmorProficiencyType armorType;
    final int baseArmorClass;
    final boolean includeDexBonus;
    final Integer maxDexBonus;
    final boolean disadvantageOnStealth;
    final Integer armorBonus;

}
