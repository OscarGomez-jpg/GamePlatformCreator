package model;

public class Game {
    public final static int LEVELS_SIZE = 10;
    private Level[] levels;

    public Game() {
        levels = new Level[LEVELS_SIZE];

        generateInitialLevels();
    }

    /**
     * This function creates the 10 levels that the game use
     */
    public void generateInitialLevels() {
        Level newLevel;
        boolean isEmpty = false;
        String id = "";
        for (int i = 0; i < LEVELS_SIZE && isEmpty == false; i++) {
            if (levels[i] == null) {
                id = String.valueOf(id);
                newLevel = new Level(id, 0, 0);
                levels[i] = newLevel;
                isEmpty = true;
            }
        }
    }

    /**
     * This function adds a loot to an especific level
     * 
     * @param levelPos position in which the loot is going to be added
     * @param loot     Loot the user wants to be added
     * @return message confirming the operation
     */
    public String addLootToALevel(int levelPos, Loot loot, int ammountOfLoots) {
        String msg = "No se ha podido encontrar el nivel";

        if (levelPos > 0 && levelPos < LEVELS_SIZE) {
            for (int i = 0; i < ammountOfLoots; i++) {
                msg += levels[levelPos - 1].addLoot(loot) + "\n";
            }
        }

        return msg;
    }

    /**
     * This function adds an Enemy to a level given by the user
     * 
     * @param levelPos The id of the level in which the enemy is going to be added
     * @param enemy    The enemy to be added
     * @return A message confirming the operation
     */
    public String addEnemyToALevel(int levelPos, Enemy enemy) {
        String msg = "No se ha podido encontrar el nivel";

        if (levelPos > 0 && levelPos < LEVELS_SIZE) {
            msg = levels[levelPos - 1].addEnemy(enemy);
        }

        return msg;
    }

    /**
     * This function returns a String from a selected position given by the user
     * @param levelPos number of the level that the user wants to access
     * @return A String with the information of the level
     */
    public String reportEnemiesAndLootsOfLevel(int levelPos) {
        String msg = "";

        msg = levels[levelPos].toString() + "\n";

        for (int j = 0; j < levels[levelPos].getEnemies().length; j++) {
            if (levels[levelPos].getEnemies()[j] != null) {
                msg += levels[levelPos].getEnemies()[j].toString() + "\n";
            }

            if (levels[levelPos].getLoots()[j] != null) {
                msg += levels[levelPos].getLoots()[j].toString() + "\n";
            }
        }

        return msg;
    }
}
