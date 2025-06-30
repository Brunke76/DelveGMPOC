package com.worldweaver.delvegm.model.dnd.e5.types;

import com.worldweaver.delvegm.R;
import com.worldweaver.delvegm.model.character.types.CharacterProficiencyType;

public enum DnDItemProficiencyType implements CharacterProficiencyType {

    ALCHEMISTS_SUPPLIES(R.string.item_alchemists_supplies_label),
    BAGPIPES(R.string.item_bagpipes_label),
    BREWERS_SUPPLIES(R.string.item_brewers_supplies_label),
    CALLIGRAPHERS_SUPPLIES(R.string.item_calligraphers_supplies_label),
    CARPENTERS_TOOLS(R.string.item_carpenters_tools_label),
    CARTOGRAPHERS_TOOLS(R.string.item_cartographers_tools_label),
    COBBLERS_TOOLS(R.string.item_cobblers_tools_label),
    COOKS_UTENSILS(R.string.item_cooks_utensils_label),
    DICE_SET(R.string.item_dice_set_label),
    DISGUISE_KIT(R.string.item_disguise_kit_label),
    DRAGONCHESS_SET(R.string.item_dragonchess_set_label),
    DRUM(R.string.item_drum_label),
    DULCIMER(R.string.item_dulcimer_label),
    FLUTE(R.string.item_flute_label),
    FORGERY_KIT(R.string.item_forgery_kit_label),
    GLASSBLOWERS_TOOLS(R.string.item_glassblowers_tools_label),
    HERBALISM_KIT(R.string.item_herbalism_kit_label),
    HORN(R.string.item_horn_label),
    JEWELERS_TOOLS(R.string.item_jewelers_tools_label),
    LAND_VEHICLES(R.string.item_land_vehicles_label),
    LEATHERWORKERS_TOOLS(R.string.item_leatherworkers_tools_label),
    LUTE(R.string.item_lute_label),
    LYRE(R.string.item_lyre_label),
    MASONS_TOOLS(R.string.item_masons_tools_label),
    NAVIGATORS_TOOLS(R.string.item_navigators_tools_label),
    PAINTERS_SUPPLIES(R.string.item_painters_supplies_label),
    PAN_FLUTE(R.string.item_pan_flute_label),
    PLAYING_CARD_SET(R.string.item_playing_card_set_label),
    POISONERS_KIT(R.string.item_poisoners_kit_label),
    POTTERS_TOOLS(R.string.item_potters_tools_label),
    SHAWM(R.string.item_shawm_label),
    SMITHS_TOOLS(R.string.item_smiths_tools_label),
    THIEVES_TOOLS(R.string.item_thieves_tools_label),
    THREE_DRAGON_ANTE_SET(R.string.item_three_dragon_ante_set_label),
    TINKERS_TOOLS(R.string.item_tinkers_tools_label),
    VIOL(R.string.item_viol_label),
    WATER_VEHICLES(R.string.item_water_vehicles_label),
    WEAVERS_TOOLS(R.string.item_weavers_tools_label),
    WOODCARVERS_TOOLS(R.string.item_woodcarvers_tools_label);

    DnDItemProficiencyType(int label) {
        this.label = label;
    }

    private final int label;

    @Override
    public int getLabel() {
        return this.label;
    }

}
