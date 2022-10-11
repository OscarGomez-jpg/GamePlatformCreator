package model;

public class Game {
    public final static int LEVELS_SIZE = 10;
    private Level[] levels;

    public Game () {
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
     * @param levelPos position in which the loot is going to be added
     * @param loot Loot the user wants to be added
     * @return message confirming the operation
     */
    public String addLootToALevel(int levelPos, Loot loot) {
        String msg = "No se ha podido encontrar el nivel";

        if (levelPos > 0 && levelPos < LEVELS_SIZE) {
            msg = levels[levelPos].addLoot(loot);
        }

        return msg;
    }

    //Thinking if I will have to create an array here and copy from it
    public String mostRepeatedTreasure() {
        String msg = "";

        return msg;
    }
}
