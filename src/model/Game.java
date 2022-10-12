package model;

public class Game {
    public final static int LEVELS_SIZE = 10;
    private Level[] levels;

    public Game() {
        this.levels = new Level[LEVELS_SIZE];

        generateInitialLevels();
    }

    /**
     * This function creates the 10 levels that the game use
     */
    public void generateInitialLevels() {
        Level newLevel;
        String id = "";
        for (int i = 0; i < LEVELS_SIZE; i++) {
            if (this.levels[i] == null) {
                id = String.valueOf(i + 1);
                newLevel = new Level(id, 0, 0);
                this.levels[i] = newLevel;
                System.out.println(levels[i].toString());
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
    public String addLootToALevel(int levelPos, String lootName, String URL, double points, int ammountOfLoots) {
        String msg = "";
        Loot newLoot = new Loot(lootName, URL, points);

        for (int i = 0; i < ammountOfLoots; i++) {
            msg += this.levels[levelPos - 1].addLoot(newLoot) + "\n";
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
    public String addEnemyToALevel(int levelPos, String enemyName, String type, double pointsGiven, double pointsTaken) {
        String msg = "No se ha podido encontrar el nivel";

        Enemy newEnemy = new Enemy(enemyName, type, pointsGiven, pointsTaken);

        msg = this.levels[levelPos - 1].addEnemy(newEnemy);

        return msg;
    }

    /**
     * This function returns a String from a selected position given by the user
     * 
     * @param levelPos number of the level that the user wants to access
     * @return A String with the information of the level
     */
    public String reportEnemiesAndLootsOfLevel(int levelPos) {
        String msg = "";

        msg = this.levels[levelPos - 1].toString() + "\n";

        for (int j = 0; j < this.levels[levelPos - 1].getEnemies().length; j++) {
            if (this.levels[levelPos - 1].getEnemies()[j] != null) {
                msg += this.levels[levelPos - 1].getEnemies()[j].toString() + "\n";
            }

            if (this.levels[levelPos - 1].getLoots()[j] != null) {
                msg += this.levels[levelPos - 1].getLoots()[j].toString() + "\n";
            }
        }

        return msg;
    }
}
