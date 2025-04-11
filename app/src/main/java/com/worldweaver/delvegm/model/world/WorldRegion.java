package com.worldweaver.delvegm.model.world;

import com.worldweaver.delvegm.model.WorldWeaverModel;
import com.worldweaver.delvegm.model.time.GameCalendar;
import com.worldweaver.delvegm.model.world.weather.Season;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class WorldRegion extends WorldWeaverModel {

    List<Season> seasons;
    GameCalendar gameCalendar;

}
