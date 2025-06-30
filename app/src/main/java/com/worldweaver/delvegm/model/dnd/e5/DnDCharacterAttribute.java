package com.worldweaver.delvegm.model.dnd.e5;

import androidx.annotation.NonNull;

import com.worldweaver.delvegm.model.character.attributes.CharacterAttribute;
import com.worldweaver.delvegm.model.dnd.e5.types.DnDCharacterAttributeStatType;


import java.io.Serial;
import java.io.Serializable;

import lombok.Data;

@Data
public class DnDCharacterAttribute implements CharacterAttribute, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NonNull
    private final DnDCharacterAttributeStatType attributeType;

    private int value;

}
