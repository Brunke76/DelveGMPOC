package com.worldweaver.delvegm.model.character;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.worldweaver.delvegm.model.WorldWeaverModel;
import com.worldweaver.delvegm.model.character.attributes.CharacterAttribute;
import com.worldweaver.delvegm.model.character.attributes.CharacterAttributeEffect;
import com.worldweaver.delvegm.model.character.types.CharacterStatType;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

public interface Combatant {

    @Nullable
    Integer getInitiative();

    int getCurrentHealth();

    void addDamage(int damageAmount);

    void addHealing(int healingAmount);

    int getCurrentArmor();

    List<CharacterAttribute> getAttributes();

    List<CharacterAttribute> getSkills();

    List<CharacterAttributeEffect> getActiveAttributeEffects();

}
