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

    /**
     * This function will search the name of an enemy, if the name is not found will
     * return a -1
     * otherwise, will return the position of the object with that name
     * 
     * @param enemyName String with the name that is going to be searched
     * @return a -1 if the name is not found otherwise will be the position of the
     *         enemy in the array
     */
    public int searchEnemyByName(String enemyName) {
        int pos = -1;
        boolean isFound = false;

        for (int i = 0; i < AMOUNT_ENEMIES && !isFound; i++) {
            if (enemies[i] != null && enemies[i].getName().equals(enemyName)) {
                pos = i;
                isFound = true;
            }
        }

        return pos;
    }

    public int searchLootByName(String lootName) {
        int pos = -1;
        boolean isFound = false;

        for (int i = 0; i < AMOUNT_LOOTS && !isFound; i++) {
            if (loots[i] != null && loots[i].getName().equals(lootName)) {
                pos = i;
                isFound = true;
            }
        }

        return pos;
    }

    public int countLootByName(String lootName) {
        int acu = -1;
        int pos = searchLootByName(lootName);
        
        if (pos != -1) {
            acu = 0;
            for (int i = pos; i < AMOUNT_LOOTS; i++) {
                if (loots[i] != null && loots[i].getName().equals(lootName)) {
                    acu += 1;
                }
            }
        }

        return acu;
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
