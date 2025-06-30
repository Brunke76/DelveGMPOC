package com.worldweaver.delvegm.model.ruleset;

import com.worldweaver.delvegm.model.WorldWeaverModel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class Ruleset extends WorldWeaverModel {

    // Display value for the health or HP for this rule set
    String healthLabel;

    // The display names for the various character attributes used in this rule set
    List<String> attributes;

}
