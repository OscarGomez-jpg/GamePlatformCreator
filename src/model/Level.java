package model;

import java.util.HashMap;
import java.util.Map;

public class Level {
    public static final int AMOUNT_LOOTS = 50;
    public static final int AMOUNT_ENEMIES = 25;

    private String id;
    private difficultyLevel difficulty;
    private int totalEnemies;
    private int totalLoots;
    private int totalPointsGiven;
    private int totalPointsTaken;
    private double nextLevelScore;

    private Loot[] loots;
    private Enemy[] enemies;

    private Map<String, Player> players;

    public Level(String id, double nextLevelScore) {
        this.id = id;
        this.totalPointsGiven = 0;
        this.totalPointsTaken = 0;
        this.nextLevelScore = nextLevelScore;

        this.loots = new Loot[AMOUNT_LOOTS];
        this.enemies = new Enemy[AMOUNT_ENEMIES];
        this.players = new HashMap<String, Player>();
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
                this.totalPointsGiven += enemy.getPointsGiven();
                this.totalPointsTaken += enemy.getPointsTaken();
                setDifficulty();
                isEmpty = false;
            }
        }

        return msg;
    }

    /**
     * This functions adds a player to the level
     * @param player Th player that is going to be added
     * @return A String confirming the operation
     */
    public String addPlayer(Player player) {
        String msg = "";

        if (checkPlayerByNickname(player.getNickname())) {
            msg = "No se puede añadir el jugador porque ya esta en el nivel";
        } else {
            players.put(player.getName(), player);
            msg = "Jugador agregado con exito";
        }

        return msg;
    }

    /**
     * This funtion deletes the player from the game
     * @param playerNickname The player that is going to be deleted
     */
    public void deletePlayer(String playerNickname) {
        players.remove(playerNickname);
    }

    /**
     * This function checks if a player exists in the level
     * @param playerNickname Nickname (id) of the player
     * @return a boolean with a true if the player exists otherwise a false
     */
    public boolean checkPlayerByNickname(String playerNickname) {
        boolean isFound = false;

        if (players.containsKey(playerNickname)) {
            isFound = true;
        }

        return isFound;
    }

    /**
     * This function returns the object player by its nickname
     * 
     * @param playerNickname identifier of the player
     * @return an object Player that is needed
     */
    public Player getPlayerByNickname(String playerNickname) {
        Player player = null;

        if (checkPlayerByNickname(playerNickname)) {
            player = players.get(playerNickname);
        }

        return player;
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

    /**
     * This function will search the name of a loot, if the name is not found will
     * return a -1
     * otherwise, will return the position of the object with that name
     * 
     * @param lootName String with the name that is going to be searched
     * @return an int -1 if the name is not found otherwise will be the position of
     *         the enemy in the array
     */
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

    /**
     * This function will count the loots with the same name in the level.
     * Will start in the first position where it finds the first loot with the same
     * name
     * 
     * @param lootName Name that is going to be counted
     * @return an int with the loots with the same name
     */
    public int countLootByName(String lootName) {
        int acu = 0;
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

    public void setDifficulty() {
        double ans = this.totalPointsGiven / this.totalPointsTaken;

        if (ans < 0) {
            this.difficulty = difficultyLevel.HIGH;
        } else if (ans == 1) {
            this.difficulty = difficultyLevel.MEDIUM;
        } else {
            this.difficulty = difficultyLevel.LOW;
        }
    }
   
    public double getNextLevelScore() {
        return nextLevelScore;
    }

    public Loot[] getLoots() {
        return loots;
    }

    public Enemy[] getEnemies() {
        return enemies;
    }

    @Override
    public String toString() {
        return "Nivel:" + id + "\n" +
                            "   Total de enemigos=" + totalEnemies + "\n" +
                            "   Total de tesoros=" + totalLoots + "\n" +
                            "   Dificultad=" + difficulty + "\n";
    }
}
