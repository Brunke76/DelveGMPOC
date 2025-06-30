package com.worldweaver.delvegm.model.character.armor;

public interface CombatantArmorManager {

    /**
     * Gets the current armor value taking into account armor being worn as well as any additional bonuses.
     * Basically, this should be the result of:
     *      base armor
     *      + current armor
     *      + [any currently active armor buffs]
     *      - [any currently active debuffs]
     * @return the int current armor value
     */
    int getCurrentArmor();

}
