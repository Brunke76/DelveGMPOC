package com.worldweaver.delvegm.model.dnd.e5.types;

import com.worldweaver.delvegm.R;
import com.worldweaver.delvegm.model.character.types.CharacterStatType;

import lombok.Getter;

@Getter
public enum DnDCharacterVitalStatType implements CharacterStatType {

    HIT_POINTS(R.string.hit_points_label, R.string.hit_points_label_short),
    ARMOR_CLASS(R.string.armor_class_label, R.string.armor_class_label_short),
    ATTACK_BONUS(R.string.attack_bonus_label, R.string.attack_bonus_label_short),
    DAMAGE_BONUS(R.string.damage_bonus_label, R.string.damage_bonus_label_short);

    DnDCharacterVitalStatType(int label, int shortLabel) {
        this.label = label;
        this.shortLabel = shortLabel;
    }

    private final int label;

    private final int shortLabel;

}
