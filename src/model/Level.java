package model;

public class Level {
    public static final int AMOUNT_LOOTS = 50;
    public static final int AMOUNT_ENEMIES = 25;

    private String id;
    private int amountOfEnemies;
    private int amountOfLoot;
    private int totalEnemies;
    private int totalLoots;
    private int totalPointsGiven;
    private int totalPointsTaken;

    private Loot[] loots;
    private Enemy[] enemies;

    public Level(String id, int amountOfEnemies, int amountOfLoot) {
        this.id = id;
        this.amountOfEnemies = amountOfEnemies;
        this.amountOfLoot = amountOfLoot;
        this.totalPointsGiven = 0;
        this.totalPointsTaken = 0;

        this.loots = new Loot[AMOUNT_LOOTS];
        this.enemies = new Enemy[AMOUNT_ENEMIES];
    }

    /**
     * This function adds a loot when finds an empty slot
     * 
     * @param loot The loot to be added
     * @return a message confirming the operation
     */
    public String addLoot(Loot loot) {
        String msg = "No se ha podido añadir el loot";
        boolean isEmpty = true;
        for (int i = 0; i < AMOUNT_LOOTS && isEmpty == true; i++) {
            if (this.loots[i] == null) {
                this.loots[i] = loot;
                msg = "Tesoro agregado con exito";
                this.totalLoots += 1;
                isEmpty = false;
            }
        }

        return msg;
    }

    /**
     * This function adds an enemy when finds an empty slot
     * 
     * @param enemy The enemy to be added
     * @return a message confirming the operation
     */
    public String addEnemy(Enemy enemy) {
        String msg = "No se ha podido añadir el enemigo";
        boolean isEmpty = true;

        for (int i = 0; i < AMOUNT_ENEMIES && isEmpty == true; i++) {
            if (enemies[i] == null) {
                enemies[i] = enemy;
                msg = "Enemigo agregado";
                this.totalEnemies += 1;
                isEmpty = false;
            }
        }

        return msg;
    }

    public Loot[] getLoots() {
        return loots;
    }

    public Enemy[] getEnemies() {
        return enemies;
    }

    public String toString() {
        return "Level: \n" + "[id=" + id + ", amountOfEnemies=" + amountOfEnemies + ", amountOfLoot=" + amountOfLoot
                + ", totalEnemies=" + totalEnemies + ", totalLoots=" + totalLoots + "]";
    }
}
