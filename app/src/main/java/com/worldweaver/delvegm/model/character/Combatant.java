package com.worldweaver.delvegm.model.character;

import com.worldweaver.delvegm.model.character.armor.CombatantArmorManager;
import com.worldweaver.delvegm.model.character.attributes.CharacterAttribute;
import com.worldweaver.delvegm.model.character.attributes.CharacterAttributeEffect;
import com.worldweaver.delvegm.model.character.health.CombatantHealthManager;

import java.util.List;

public interface Combatant<HM extends CombatantHealthManager,
                           AM extends CombatantArmorManager,
                           CA extends CharacterAttribute> {

    Integer getInitiative();

    HM getHealthManager();

    AM getArmorManager();

    List<CA> getAttributes();

    List<CA> getSkills();

    List<CharacterAttributeEffect> getActiveAttributeEffects();

    void addDamage(int damageAmount);

    void addHealing(int healingAmount);

}
