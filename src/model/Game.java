package model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Game {
    public final static int LEVELS_SIZE = 10;
    private Level[] levels;
    private Map<String, Integer> lootNames;

    public Game() {
        this.levels = new Level[LEVELS_SIZE];
        this.lootNames = new HashMap<String, Integer>();

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
            }
        }
    }

    /**
     * This function creates a Loot and then adds it to a level given by the user
     * 
     * @param levelPos       The level in which the user is going to add the loot
     * @param lootName       The name that is going to have the loot
     * @param URL            The URL corresponding to the image of the loot
     * @param points         The points that the loot is going to give
     * @param ammountOfLoots The total of the same loot that is going to be added to
     *                       the level
     * @return a String with the result of adding each loot
     */
    public String addLootToALevel(int levelPos, String lootName, String URL, double points, int ammountOfLoots) {
        String msg = "";

        if (levelPos > 0 && levelPos <= LEVELS_SIZE) {
            Loot newLoot = new Loot(lootName, URL, points);
            for (int i = 0; i < ammountOfLoots; i++) {
                takeAllLootsName(lootName);
                msg += this.levels[levelPos - 1].addLoot(newLoot) + "\n";
            }
        }

        return msg;
    }

    /**
     * This function creates an Enemy and then adds it to a level given by the user
     * 
     * @param levelPos    The level in which the user is going to add the enemy
     * @param enemyName   The name that is going to have the enemy
     * @param type        The type of the enemy given by a String from the user
     * @param pointsGiven The points the enemy gives once is defeated
     * @param pointsTaken The points the enemy takes if defeat the player
     * @return a String confirming the operation
     */
    public String addEnemyToALevel(int levelPos, String enemyName, String type, double pointsGiven,
            double pointsTaken) {
        String msg = "No se ha podido encontrar el nivel";

        int check = this.levels[levelPos].searchEnemyByName(enemyName);

        if (check != -1) {
            msg = "Este enemigo ya se encuentra en este nivel, intente con un nombre distinto";
            return msg;
        }

        Enemy newEnemy = new Enemy(enemyName, type, pointsGiven, pointsTaken);

        if (levelPos > 0 && levelPos <= LEVELS_SIZE) {
            msg = this.levels[levelPos - 1].addEnemy(newEnemy);
        }

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

    /**
     * This function counts all the loot that corresponds to the lootName given by
     * the user in all levels
     * 
     * @param lootName The name of the loot that is going to be counted
     * @return
     */
    public String countLootAllLevels(String lootName) {
        String msg = "";
        int acu = 0;

        for (int i = 0; i < LEVELS_SIZE; i++) {
            acu += this.levels[i].countLootByName(lootName);
        }

        msg = "La cantidad de " + lootName + " en todos los niveles es: " + acu + "\n";

        return msg;
    }

    /**
     * This function will use the name of the loots added as a key in a Hashmap
     * then the value in eack key will be used as an acumulator
     * 
     * @param name The name that will work as key
     */
    public void takeAllLootsName(String name) {
        if (this.lootNames.containsKey(name)) {
            this.lootNames.put(name, this.lootNames.get(name) + 1);
        } else {
            this.lootNames.put(name, 1);
        }

    }

    /**
     * This function will iterate through the loot names and returns a message with the most repeated
     * treasure through all levels
     * 
     * @return A String with the most repeated treasure through all levels
     */
    public String mostRepeatedLootAllLevels() {
        String msg = "";
        String keyMsg = "";
        int bigger = 0;

        Iterator<String> it = lootNames.keySet().iterator();

        while (it.hasNext()) {
            String key = it.next();
            if (bigger < lootNames.get(key)) {
                bigger = lootNames.get(key);
                keyMsg = key;
            }
        }

        msg = "El tesoro que mas se repite es: '" + keyMsg + "' con un total de: " + bigger;

        return msg;
    }
}
