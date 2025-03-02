package com.worldweaver.delvegm.model;

import java.io.Serial;
import java.io.Serializable;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class WorldWeaverModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    protected Scope scope;
    protected int id;
    protected String name;
    protected String description;
}
