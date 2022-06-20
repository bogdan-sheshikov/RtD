package ru.omstu.rtd.Entities;

import ru.omstu.rtd.Items.HpPotion;

import java.util.ArrayList;
import java.util.List;

public class Treasure implements Entity {
    public ArrayList<HpPotion> items = new ArrayList<>();

    public Treasure(HpPotion... items) {
        this.items.addAll(List.of(items));
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (HpPotion item : items) {
            stringBuilder.append(item.toString());
        }
        return stringBuilder.toString();
    }
}
