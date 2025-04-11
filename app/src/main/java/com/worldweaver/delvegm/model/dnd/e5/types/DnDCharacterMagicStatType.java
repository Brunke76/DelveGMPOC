package com.worldweaver.delvegm.model.dnd.e5.types;

import com.worldweaver.delvegm.R;
import com.worldweaver.delvegm.model.character.types.CharacterStatType;

import lombok.Getter;

@Getter
public enum DnDCharacterMagicStatType implements CharacterStatType {

    SPELL_ATTACK_BONUS(R.string.spell_attack_bonus_label),
    SPELL_SAVE_DC(R.string.spell_save_dc_label),
    SPELL_SLOTS(R.string.spell_slots_label);

    DnDCharacterMagicStatType(int label) {
        this.label = label;
        this.shortLabel = label;
    }

    private final int label;

    private final int shortLabel;

}
