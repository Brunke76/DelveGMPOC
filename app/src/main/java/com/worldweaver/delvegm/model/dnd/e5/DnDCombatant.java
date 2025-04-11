package com.worldweaver.delvegm.model.dnd.e5;

import androidx.annotation.Nullable;

import com.worldweaver.delvegm.model.WorldWeaverModel;
import com.worldweaver.delvegm.model.character.Combatant;
import com.worldweaver.delvegm.model.character.attributes.CharacterAttribute;
import com.worldweaver.delvegm.model.character.attributes.CharacterAttributeEffect;

import java.util.Collections;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class DnDCombatant extends WorldWeaverModel implements Combatant {

    private Integer initiative;
    private DnDCombatantHealthManager healthManager;
    private DnDCombatArmorManager armorManager;

    @Override
    public int getCurrentHealth() {
        return healthManager.getCurrentHealth();
    }

    @Override
    public void addDamage(int damageAmount) {
        healthManager.addDamage(damageAmount);
    }

    @Override
    public void addHealing(int healingAmount) {
        healthManager.addHealing(healingAmount);
    }

    @Override
    public int getCurrentArmor() {
        return armorManager.getCurrentArmor();
    }

    @Override
    public List<CharacterAttribute> getAttributes() {
        return Collections.emptyList();
    }

    @Override
    public List<CharacterAttribute> getSkills() {
        return Collections.emptyList();
    }

    @Override
    public List<CharacterAttributeEffect> getActiveAttributeEffects() {
        return Collections.emptyList();
    }
}
