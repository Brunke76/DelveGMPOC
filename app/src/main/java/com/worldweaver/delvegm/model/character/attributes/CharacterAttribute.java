package com.worldweaver.delvegm.model.character.attributes;

import androidx.annotation.NonNull;

import com.worldweaver.delvegm.model.character.types.CharacterStatType;

public interface CharacterAttribute {

    @NonNull
    CharacterStatType getAttributeType();

    /**
     * Gets the current value for the attribute.
     * @return int current value
     */
    int getValue();

}
