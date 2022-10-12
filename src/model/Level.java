package model;

public class Level {
    public static final int AMOUNT_LOOTS = 50;
    public static final int AMOUNT_ENEMIES = 25;

    private String id;
    private int amountOfEnemies;
    private int amountOfLoot;

    private Loot[] loots;
    private Enemy[] enemies;

    public Level(String id, int amountOfEnemies, int amountOfLoot) {
        this.id = id;
        this.amountOfEnemies = amountOfEnemies;
        this.amountOfLoot = amountOfLoot;

        this.loots = new Loot[AMOUNT_LOOTS];
    }

    /**
     * This function adds a loot when finds an empty slot
     * @param loot The loot to be added
     * @return a message confirming the operation
     */
    public String addLoot(Loot loot) {
        String msg = "No se ha podido añadir el loot";
        boolean isEmpty = true;

        for (int i = 0; i < AMOUNT_LOOTS && isEmpty == true; i++) {
            if (loots[i] == null) {
                loots[i] = loot;
                msg = "Tesoro agregado con exito";
                isEmpty = false;
            }
        }

        return msg;
    }

    /**
     * This function adds an enemy when finds an empty slot
     * @param enemy The enemy to be added
     * @return a message confirming the operation
     */
    public String addEnemy(Enemy enemy) {
        String msg = "No se ha podido añadir el enemigo";
        boolean isEmpty = true;

        for (int i = 0; i < AMOUNT_ENEMIES && isEmpty == true; i++) {
            if (enemies[i] == null) {
                enemies[i] = enemy;
                msg = "Tesoro agregado con exito";
                isEmpty = false;
            }
        }

        return msg;
    }

    public int countLootByName(String lootName) {
        int acu = 0;
        for (int i = 0; i < AMOUNT_LOOTS; i++) {
            if (loots[i].getName().equals(lootName)) {
                acu += 1;
            }
        }

        return acu;
    }
}
