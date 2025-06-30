package com.worldweaver.delvegm.model.dnd.e5.types;

import com.worldweaver.delvegm.R;
import com.worldweaver.delvegm.model.character.types.CharacterStatType;

import lombok.Getter;

@Getter
public enum DnDCharacterAttributeStatType implements CharacterStatType {

    STRENGTH(R.string.attribute_type_strength_label, R.string.attribute_type_strength_label_short),
    DEXTERITY(R.string.attribute_type_dexterity_label, R.string.attribute_type_strength_label_short),
    CONSTITUTION(R.string.attribute_type_constitution_label, R.string.attribute_type_constitution_label_short),
    INTELLIGENCE(R.string.attribute_type_intelligence_label, R.string.attribute_type_intelligence_label_short),
    WISDOM(R.string.attribute_type_wisdom_label, R.string.attribute_type_wisdom_label_short),
    CHARISMA(R.string.attribute_type_charisma_label, R.string.attribute_type_charisma_label_short);

    DnDCharacterAttributeStatType(int label, int shortLabel) {
        this.label = label;
        this.shortLabel = shortLabel;
    }

    private final int label;

    private final int shortLabel;

}
