package com.worldweaver.delvegm.model;

import android.app.Activity;
import android.content.Context;

import java.io.Serial;
import java.io.Serializable;

public interface WorldWeaverType extends Serializable {
    @Serial
    static final long serialVersionUID = 1L;

    int getLabel();

    default String getLabel(Context context) {
        return context.getResources().getString(getLabel());
    }

}
