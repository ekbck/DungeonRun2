package dungeonrun2;

public abstract class HeroesAbstract {
    
    private int initiative;
    private int health;
    private int attack;
    private int agility;
    private String name;

    public HeroesAbstract(int initiative, int health, int attack, int agility, String name) {
        this.initiative = initiative;
        this.health = health;
        this.attack = attack;
        this.agility = agility;
        this.name = name;
    }

    public int getInitiative() {
        return initiative;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttack() {
        return attack;
    }

    public int getAgility() {
        return agility;
    }

    public String getName() {
        return name;
    }
    
    
    
    public static void stats() {
        System.out.println("Nu blev det fel");
    }

    @Override
    public String toString() {
        return "Hero: " + name;
    }
}
