package model;

public class Level {
    public static final int AMOUNT_LOOTS = 50;

    private String id;
    private int amountOfEnemies;
    private int amountOfLoot;

    private Loot [] loots;

    public Level(String id, int amountOfEnemies, int amountOfLoot) {
        this.id = id;
        this.amountOfEnemies = amountOfEnemies;
        this.amountOfLoot = amountOfLoot;

        this.loots = new Loot[AMOUNT_LOOTS];
    }

    public String addLoot(Loot loot) {
        String msg = "No se ha podido añadir el loot";
        boolean isEmpty = true;

        for (int i = 0; i < amountOfLoot && isEmpty == true; i++) {
            if (loots[i] == null) {
                loots[i] = loot;
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
