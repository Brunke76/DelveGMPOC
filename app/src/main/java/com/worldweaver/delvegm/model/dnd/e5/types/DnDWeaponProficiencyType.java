package com.worldweaver.delvegm.model.dnd.e5.types;

import com.worldweaver.delvegm.R;
import com.worldweaver.delvegm.model.character.types.CharacterProficiencyType;

public enum DnDWeaponProficiencyType implements CharacterProficiencyType {

    BATTLEAXE(R.string.weapon_battleaxe_label),
    BLOWGUN(R.string.weapon_blowgun_label),
    CLUB(R.string.weapon_club_label),
    DAGGER(R.string.weapon_dagger_label),
    DART(R.string.weapon_dart_label),
    FLAIL(R.string.weapon_flail_label),
    GLAIVE(R.string.weapon_glaive_label),
    GREATAXE(R.string.weapon_greataxe_label),
    GREATCLUB(R.string.weapon_greatclub_label),
    GREATSWORD(R.string.weapon_greatsword_label),
    HALBERD(R.string.weapon_halberd_label),
    HAND_CROSSBOW(R.string.weapon_hand_crossbow_label),
    HANDAXE(R.string.weapon_handaxe_label),
    HEAVY_CROSSBOW(R.string.weapon_heavy_crossbow_label),
    IMPROVISED_WEAPON(R.string.weapon_improvised_weapon_label),
    JAVELIN(R.string.weapon_javelin_label),
    LANCE(R.string.weapon_lance_label),
    LIGHT_CROSSBOW(R.string.weapon_light_crossbow_label),
    LIGHT_HAMMER(R.string.weapon_light_hammer_label),
    LONGBOW(R.string.weapon_longbow_label),
    LONGSWORD(R.string.weapon_longsword_label),
    MACE(R.string.weapon_mace_label),
    MARTIAL_WEAPONS(R.string.weapon_martial_weapons_label),
    MAUL(R.string.weapon_maul_label),
    MORNINGSTAR(R.string.weapon_morningstar_label),
    MUSKET(R.string.weapon_musket_label),
    NET(R.string.weapon_net_label),
    PIKE(R.string.weapon_pike_label),
    PISTOL(R.string.weapon_pistol_label),
    QUARTERSTAFF(R.string.weapon_quarterstaff_label),
    RAPIER(R.string.weapon_rapier_label),
    SCIMITAR(R.string.weapon_scimitar_label),
    SHORTBOW(R.string.weapon_shortbow_label),
    SHORTSWORD(R.string.weapon_shortsword_label),
    SICKLE(R.string.weapon_sickle_label),
    SIMPLE_WEAPONS(R.string.weapon_simple_weapons_label),
    SLING(R.string.weapon_sling_label),
    SPEAR(R.string.weapon_spear_label),
    TRIDENT(R.string.weapon_trident_label),
    UNARMED_STRIKE(R.string.weapon_unarmed_strike_label),
    WAR_PICK(R.string.weapon_war_pick_label),
    WARHAMMER(R.string.weapon_warhammer_label),
    WHIP(R.string.weapon_whip_label);

    DnDWeaponProficiencyType(int label) {
        this.label = label;
    }

    private final int label;

    @Override
    public int getLabel() {
        return this.label;
    }

}
