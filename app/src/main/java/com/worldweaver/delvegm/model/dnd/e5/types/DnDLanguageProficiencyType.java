package com.worldweaver.delvegm.model.dnd.e5.types;

import com.worldweaver.delvegm.R;
import com.worldweaver.delvegm.model.character.types.CharacterProficiencyType;

public enum DnDLanguageProficiencyType implements CharacterProficiencyType {
    ABYSSAL(R.string.language_abyssal_label),
    CELESTIAL(R.string.language_celestial_label),
    COMMON(R.string.language_common_label),
    COMMON_SIGN_LANGUAGE(R.string.language_common_sign_language_label),
    DEEP_SPEECH(R.string.language_deep_speech_label),
    DRACONIC(R.string.language_draconic_label),
    DRUIDIC(R.string.language_druidic_label),
    DWARVISH(R.string.language_dwarvish_label),
    ELVISH(R.string.language_elvish_label),
    GIANT(R.string.language_giant_label),
    GNOMISH(R.string.language_gnomish_label),
    GOBLIN(R.string.language_goblin_label),
    HALFLING(R.string.language_halfling_label),
    INFERNAL(R.string.language_infernal_label),
    ORC(R.string.language_orc_label),
    PRIMORDIAL(R.string.language_primordial_label),
    SYLVAN(R.string.language_sylvan_label),
    THIEVES_CANT(R.string.language_thieves_cant_label),
    UNDERCOMMON(R.string.language_undercommon_label);

    DnDLanguageProficiencyType(int label) {
        this.label = label;
    }

    private final int label;

    @Override
    public int getLabel() {
        return this.label;
    }
}
