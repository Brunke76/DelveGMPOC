package com.worldweaver.delvegm.model.dnd.e5.types;

import com.worldweaver.delvegm.R;
import com.worldweaver.delvegm.model.character.types.CharacterStatType;
import com.worldweaver.delvegm.model.character.types.CharacterProficiencyType;
import com.worldweaver.delvegm.model.character.types.CharacterSkillType;

import lombok.Getter;

@Getter
public enum DnDCharacterSkillType implements CharacterSkillType, CharacterProficiencyType {

    ACROBATICS(R.string.skill_type_acrobatics_label, DnDCharacterAttributeStatType.DEXTERITY),
    ANIMAL_HANDLING(R.string.skill_type_animal_handling_label, DnDCharacterAttributeStatType.WISDOM),
    ARCANA(R.string.skill_type_arcana_label, DnDCharacterAttributeStatType.INTELLIGENCE),
    ATHLETICS(R.string.skill_type_athletics_label, DnDCharacterAttributeStatType.STRENGTH),
    DECEPTION(R.string.skill_type_deception_label, DnDCharacterAttributeStatType.CHARISMA),
    HISTORY(R.string.skill_type_history_label, DnDCharacterAttributeStatType.INTELLIGENCE),
    INSIGHT(R.string.skill_type_insight_label, DnDCharacterAttributeStatType.WISDOM),
    INTIMIDATION(R.string.skill_type_intimidation_label, DnDCharacterAttributeStatType.CHARISMA),
    INVESTIGATION(R.string.skill_type_investigation_label, DnDCharacterAttributeStatType.INTELLIGENCE),
    MEDICINE(R.string.skill_type_medicine_label, DnDCharacterAttributeStatType.WISDOM),
    NATURE(R.string.skill_type_nature_label, DnDCharacterAttributeStatType.INTELLIGENCE),
    PERCEPTION(R.string.skill_type_perception_label, DnDCharacterAttributeStatType.WISDOM),
    PERFORMANCE(R.string.skill_type_performance_label, DnDCharacterAttributeStatType.CHARISMA),
    PERSUASION(R.string.skill_type_persuasion_label, DnDCharacterAttributeStatType.CHARISMA),
    RELIGION(R.string.skill_type_religion_label, DnDCharacterAttributeStatType.INTELLIGENCE),
    SLEIGHT_OF_HAND(R.string.skill_type_sleight_of_hand_label, DnDCharacterAttributeStatType.DEXTERITY),
    STEALTH(R.string.skill_type_stealth_label, DnDCharacterAttributeStatType.DEXTERITY),
    SURVIVAL(R.string.skill_type_survival_label, DnDCharacterAttributeStatType.WISDOM);

    DnDCharacterSkillType(int label, DnDCharacterAttributeStatType attributeType) {
        this.label = label;
        this.attributeType = attributeType;
    }

    private final int label;

    private final CharacterStatType attributeType;

}
