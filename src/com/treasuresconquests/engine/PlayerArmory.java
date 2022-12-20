package com.treasuresconquests.engine;

import java.util.ArrayList;
import java.util.List;

public class PlayerArmory {

    private final List<WorldMap.Countries.WeaponStore.Weapons> weaponInventory = new ArrayList<>();

    public PlayerArmory () {
        addWeapon( new WorldMap.Countries.WeaponStore.Weapons("slingshot", 5, 10) );
        addWeapon( new WorldMap.Countries.WeaponStore.Weapons("knife", 15, 25) );
        addWeapon( new WorldMap.Countries.WeaponStore.Weapons("gun", 35, 50) );
    }

    public void addWeapon(WorldMap.Countries.WeaponStore.Weapons weapon) {
        weaponInventory.add(weapon);
    }

    // not Setter / Getter.  Consider remove
    // Consider putting in armory class.
    public void removeWeapon(WorldMap.Countries.WeaponStore.Weapons weapon) {
        getWeaponInventory().remove(weapon);
    }

    public List<WorldMap.Countries.WeaponStore.Weapons> getWeaponInventory() {
        return weaponInventory;
    }

    public void addWeaponToInventory(WorldMap.Countries.WeaponStore.Weapons weapon) {
        weaponInventory.add(weapon);
    }

}