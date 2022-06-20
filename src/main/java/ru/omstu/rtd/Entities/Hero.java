package ru.omstu.rtd.Entities;

import ru.omstu.rtd.Items.HpPotion;

import java.util.ArrayList;

public class Hero {
    public static ArrayList<HpPotion> inventory = new ArrayList<>();
    public static boolean alive = true;
    static public int health = 3000;
    static public int attackDamage = 700;

    static public int xp;
    static public int xpLevel;

    static public void takeDamage(int damage) {
        health = health - damage;
        if (health < 1) {
            alive = false;
        }
    }

    public static void getXp(int attackDamage) {
        xp += attackDamage;
        xpLevel = (int) Math.sqrt(xp/3.0);

    }

    public static void usePotion(int i) {
        Hero.health += inventory.get(i).hp;
        inventory.remove(i);
    }
}
