package com.worldweaver.delvegm.model.character.health;

import com.worldweaver.delvegm.model.time.GameTimeDuration;

public interface CombatantHealthManager {

    /**
     * Gets the reset value for the health.
     * The reset value represents the maximum health that a combatant can have
     * not including any of the following:
     *  - damage
     *  - temporary health
     *  - temporary bonus (from spells, magical items, etc.)
     *  - temporary debuffs (those caused by temporary conditions, cursed items, etc)
     * This is basically the value that the health would be set to after a long rest.
     * If a health modifier can be removed by time, a restoration spell, removal of an item or any other game mechanic,
     * than it should not be included as part of this value.
     * @return the int reset value
     */
    int getResetHealth();

    /**
     * Gets the current maximum health available assuming that no damage has been taken.
     * This should represent the amount of health that a combatant would have if fully healed
     * while all of the current temporary health, buffs, and debuffs are in effect.
     * (This value should include any temporary health that has been added to the combatant)
     * Basically, this should be the result of:
     *      getResetHealth()
     *      + getCurrentTemporaryHealth()
     *      + [any extra bonus health]
     *      - [any currently active debuffs]
     * @return current int maximum health value
     */
    int getMaxHealth();

    /**
     * Gets the current health value after any damage, buff and/or debuffs have been applied.
     * (This value should include any temporary health that has been added to the combatant)
     * Basically, this should be the result of:
     *      getResetHealth()
     *      + getCurrentTemporaryHealth()
     *      + [any extra bonus health]
     *      - [any currently active debuffs]
     *      - [all damage since start of game or last reset]
     *      + [all healing since start of game or last reset]
     * @return current int health value
     */
    int getCurrentHealth();

    /**
     * Gets the current amount of temporary health
     * (This should be kept separate from the total current health since it is handled differently)
     * @return current int temporary health value
     */
    int getCurrentTemporaryHealth();

    /**
     * Returns the duration of the current temporary health that is active on the combatant.
     * @return GameTimeDuration of the current temporary health
     */
    GameTimeDuration getCurrentTemporaryHealthDuration();

    /**
     * This method resets the current health.
     * Depending upon the rule set, it might do any (or none) of the following:
     *  - set the health back to the resetHealth value
     *  - clear all damage
     *  - clear all healing
     *  - clear all temporary health
     *  - clear all buffs
     *  - clear all debuffs
     */
    void resetHealth();

    /**
     * Adds damage to the combatant (reducing total health)
     * @param damageAmount - The amount of damage to apply to the health
     * (This should apply limits to the healing as per the current ruleset.
     *                     i.e. in DnD 5e health should never go below 0)
     */
    void addDamage(int damageAmount);

    /**
     * Adds healing to the combatant (increasing total health)
     * @param healAmount - The amount of healing to apply to the health
     * (This should apply limits to the healing as per the current ruleset.
     *                   i.e. DnD 5e does not normally allow healing beyond maximum health)
     */
    void addHealing(int healAmount);

    /**
     * Adds temporary health to the combatant.
     * @param tempHealth - The amount of temporary health to add to the combatant.
     * @param duration - the maximum duration of the temporary health (assuming no damage is taken)
     * @return int value of updated temporary health value.
     * (This should apply limits to temporary health as per the current ruleset.
     *                  i.e. DnD 5e does not allow temporary health to stack)
     */
    int addTemporaryHealth(int tempHealth, GameTimeDuration duration);

    /**
     * Add a buff (additional health bonus) to the combatant
     * @param buffAmount - The amount of additional bonus health to add to the combatant
     * @param duration - The duration of the additional health bonus affect
     */
    void addBuff(int buffAmount, GameTimeDuration duration);

    /**
     * Add a debuff (temporary reduction of maximum health) to the combatant
     * @param debuffAmount - The amount of reduction of the maximum health of the combatant
     * @param duration - The duration of the reduction of the maximum health affect
     */
    void addDebuff(int debuffAmount, GameTimeDuration duration);


}
