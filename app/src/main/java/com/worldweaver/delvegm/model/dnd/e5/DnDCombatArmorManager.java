package com.worldweaver.delvegm.model.dnd.e5;

import com.worldweaver.delvegm.model.character.armor.CombatantArmorManager;

public class DnDCombatArmorManager implements CombatantArmorManager {

    private DnDCombatant parent;
    private int baseArmor;

    @Override
    public int getCurrentArmor() {
        return 0;
    }
}
