package com.worldweaver.delvegm.model.world;

import com.worldweaver.delvegm.model.WorldWeaverModel;
import com.worldweaver.delvegm.model.world.calendar.GameCalendar;
import com.worldweaver.delvegm.model.world.weather.Season;

import java.util.List;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class WorldRegion extends WorldWeaverModel {

    List<Season> seasons;
    GameCalendar gameCalendar;

}
