package com.worldweaver.delvegm.model;

import java.io.Serial;
import java.io.Serializable;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class WorldWeaverModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Setter(AccessLevel.NONE) // Prevents @Data from creating a default setter for this field
    protected final Scope scope;
    @Setter(AccessLevel.NONE) // Prevents @Data from creating a default setter for this field
    protected final int id;
    @Setter(AccessLevel.NONE) // Prevents @Data from creating a default setter for this field
    protected final String name;
    @Setter(AccessLevel.NONE) // Prevents @Data from creating a default setter for this field
    protected final String description;
}
