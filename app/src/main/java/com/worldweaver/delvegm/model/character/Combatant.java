package com.worldweaver.delvegm.model.character;

import androidx.annotation.Nullable;

import com.worldweaver.delvegm.model.character.armor.CombatantArmorManager;
import com.worldweaver.delvegm.model.character.attributes.CharacterAttribute;
import com.worldweaver.delvegm.model.character.attributes.CharacterAttributeEffect;
import com.worldweaver.delvegm.model.character.health.CombatantHealthManager;

import java.util.List;

public interface Combatant {

    @Nullable
    Integer getInitiative();

    CombatantHealthManager getHealthManager();

    CombatantArmorManager getArmorManager();

    List<CharacterAttribute> getAttributes();

    List<CharacterAttribute> getSkills();

    List<CharacterAttributeEffect> getActiveAttributeEffects();

    void addDamage(int damageAmount);

    void addHealing(int healingAmount);

}
