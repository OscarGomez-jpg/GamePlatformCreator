package model;

public class Game {
    public final static int LEVELS_SIZE = 10;
    private Level[] levels;

    public Game () {
        levels = new Level[LEVELS_SIZE];

        generateInitialLevels();
    }
    
    /**
     * This function creates the 10 levels in which the game consists
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
}
