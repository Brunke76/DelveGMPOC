package com.worldweaver.delvegm.model.character.types;

import android.content.Context;

import com.worldweaver.delvegm.model.WorldWeaverType;

public interface CharacterStatType extends WorldWeaverType {

    int getShortLabel();

    default String getShortLabel(Context context) {
        return context.getResources().getString(getLabel());
    }

}
