public class Character {
    String name;
    int power;
    int health;

    public Character (String name) {
        this.name = name;
        this.power = 2;
        this.health = 4;
    }

    public void setStat(int pow, int health) {
        this.power += pow;
        this.health += health;
    }

    public void takeDam() {
        this.health -= 1;
    }

    public int getPow() {
        return power;
    }

    public int getHealth() {
        return health;
    }

    public String getName() {
        return name;
    }
}
