package ru.omstu.rtd.Items;

import ru.omstu.rtd.Entities.Hero;

public class HpPotion {
    public int hp;

    public HpPotion(int hp) {
        this.hp = hp;
    }

    void drink() {
        Hero.health += hp;
    }
}
