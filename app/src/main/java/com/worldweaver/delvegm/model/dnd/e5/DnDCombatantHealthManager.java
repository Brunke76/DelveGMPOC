package com.worldweaver.delvegm.model.dnd.e5;

import com.worldweaver.delvegm.model.character.Combatant;
import com.worldweaver.delvegm.model.character.health.CombatantHealthManager;
import com.worldweaver.delvegm.model.time.GameTimeDuration;

public class DnDCombatantHealthManager implements CombatantHealthManager {

    private Combatant parent;
    private int resetHealth;
    private int currentHealth;
    private int temporaryHealth;
    private GameTimeDuration temporaryHealthDuration;


    @Override
    public int getResetHealth() {
        return 0;
    }

    @Override
    public int getMaxHealth() {
        return 0;
    }

    @Override
    public int getCurrentHealth() {
        return 0;
    }

    @Override
    public int getCurrentTemporaryHealth() {
        return 0;
    }

    @Override
    public GameTimeDuration getCurrentTemporaryHealthDuration() {
        return null;
    }

    @Override
    public void resetHealth() {

    }

    @Override
    public void addDamage(int damageAmount) {

    }

    @Override
    public void addHealing(int healAmount) {

    }

    @Override
    public int addTemporaryHealth(int tempHealth, GameTimeDuration duration) {
        return 0;
    }

    @Override
    public void addBuff(int buffAmount, GameTimeDuration duration) {

    }

    @Override
    public void addDebuff(int debuffAmount, GameTimeDuration duration) {

    }
}
