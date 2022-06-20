package ru.omstu.rtd.Entities;

public class Monster implements Entity {
    public int power;
    public boolean alive = true;
    public int health;
    public int attackDamage;
    //0 - head 1 - body 2 - legs
    public double[] hitChance;

    public Monster(int power, int health, int attackDamage, double headHitChance, double bodyHitChance, double legsHitChance) {
        this.power = power * 2;
        this.health = (int) (health * Math.sqrt(79 + this.power));
        this.attackDamage = attackDamage + this.power;
        hitChance = new double[]{headHitChance, bodyHitChance, legsHitChance};
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health < 1) {
            alive = false;
        }
    }
}
