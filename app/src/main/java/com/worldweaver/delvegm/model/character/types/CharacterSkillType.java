package com.worldweaver.delvegm.model.character.types;

import android.content.Context;

import androidx.annotation.NonNull;

import com.worldweaver.delvegm.model.WorldWeaverType;

public interface CharacterSkillType extends WorldWeaverType {

    String ATTRIBUTE_WRAPPER_START = " (";
    String ATTRIBUTE_WRAPPER_END = ")";

    @NonNull
    default String getLabelWithAttribute(@NonNull Context context) {
        String baseLabel = getLabel(context);
        String attributeLabel = getAttributeType().getShortLabel(context);
        return baseLabel + ATTRIBUTE_WRAPPER_START + attributeLabel + ATTRIBUTE_WRAPPER_END;
    }

    @NonNull
    CharacterStatType getAttributeType();

}
