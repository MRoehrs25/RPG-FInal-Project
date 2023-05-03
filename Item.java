public class Item {
    String name;
    String description;
    int power;
    int health;

    public Item (String name, String description, int pow, int health) {
        this.name = name;
        this.description = description;
        this.power = pow;
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public String getDes() {
        return description;
    }

    public int getPow() {
        return power;
    }

    public int getHealth() {
        return health;
    }
}