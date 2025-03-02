package com.worldweaver.delvegm.model.world;

import com.worldweaver.delvegm.model.WorldWeaverModel;
import com.worldweaver.delvegm.model.ruleset.Ruleset;
import com.worldweaver.delvegm.model.world.calendar.GameCalendar;

import java.util.List;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class GameWorld extends WorldWeaverModel {

    Ruleset ruleset;
    GameCalendar defaultCalendar;
    List<WorldRegion> worldRegions;

}
