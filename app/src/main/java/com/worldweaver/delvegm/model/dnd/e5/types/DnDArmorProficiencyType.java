package com.worldweaver.delvegm.model.dnd.e5.types;

import com.worldweaver.delvegm.R;
import com.worldweaver.delvegm.model.character.types.CharacterProficiencyType;

public enum DnDArmorProficiencyType implements CharacterProficiencyType {

    HEAVY_ARMOR(R.string.armor_type_heavy_label),
    LIGHT_ARMOR(R.string.armor_type_medium_label),
    MEDIUM_ARMOR(R.string.armor_type_light_label),
    SHIELDS(R.string.armor_type_shield_label);

    DnDArmorProficiencyType(int label) {
        this.label = label;
    }

    private final int label;

    @Override
    public int getLabel() {
        return this.label;
    }

}
