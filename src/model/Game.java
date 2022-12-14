package model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Game {
    public final static int LEVELS_SIZE = 10;
    private Level[] levels;
    private Map<String, Integer> lootNames;
    private Map<String, Integer> typeEnemies;
    private Map<String, Double> playerScores;

    public Game() {
        this.levels = new Level[LEVELS_SIZE];
        this.lootNames = new HashMap<String, Integer>();
        this.typeEnemies = new HashMap<String, Integer>();
        this.playerScores = new HashMap<String, Double>();
    }

    /**
     * This function creates the 10 levels that the game use
     * 
     * @param pointsToNextLevel The points that the player need to go to the next
     *                          level, it adds the acumulation of pointsToNextLevel
     *                          Every time
     */
    public void generateInitialLevels(double pointsToNextLevel) {
        Level newLevel;
        double acu = pointsToNextLevel;
        String id = "";
        for (int i = 0; i < LEVELS_SIZE; i++) {
            if (this.levels[i] == null) {
                id = String.valueOf(pointsToNextLevel);
                newLevel = new Level(id, pointsToNextLevel);

                if (i < LEVELS_SIZE - 1) {

                    pointsToNextLevel = acu + pointsToNextLevel;

                }

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

        if (levelPos > 0 && levelPos <= LEVELS_SIZE) {
            int check = this.levels[levelPos - 1].searchEnemyByName(enemyName);

            if (check != -1) {
                msg = "Este enemigo ya se encuentra en este nivel, intente con un nombre distinto";
                return msg;
            }

            Enemy newEnemy = new Enemy(enemyName, type, pointsGiven, pointsTaken);

            takeAllEnemiesType(newEnemy.getType());

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
     * @return A String with amount of a loot found in all levels
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
     * This function will iterate through the loot names and returns a message with
     * the most repeated
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

    /**
     * This function takes a String that represents the enemy type from the enmy
     * that was added to the game. Uses a HashMap to store the amount of an enemy
     * type in the game
     * 
     * @param type A String containing the enemy type that was added to a level
     */
    public void takeAllEnemiesType(String type) {
        if (typeEnemies.get(type) != null) {
            this.typeEnemies.put(type, typeEnemies.get(type) + 1);
        } else {
            this.typeEnemies.put(type, 1);
        }
    }

    /**
     * This function will return a String with amount of an enemy type
     * 
     * @param type The enemy type that is needed
     * @return a String with the amount of that enemy type in all levels
     */
    public String typeInAllLevels(String type) {
        String msg = "";

        type = type.toLowerCase();

        if (typeEnemies.get(type) == null) {
            return "Ese tipo de enemigo no existe";
        }

        int total = typeEnemies.get(type);

        msg = "La cantidad de " + type + " en todos los niveles es: " + total;

        return msg;
    }

    /**
     * This function adds a player to the game if the player nickname doesn't exists
     * 
     * @param nickname Nickname and identifier of the player that is going to be
     *                 added
     * @param name     name of the player that is going to be added
     * @return String validating the operation
     */
    public String addPlayer(String nickname, String name) {
        String msg = "Jugador agregado exitosamente";

        if (getLevelOfPlayer(nickname) != -1) {
            msg = "El jugador ya se encuentra en el juego";

            return msg;
        }

        Player newPlayer = new Player(nickname, name);

        
        boolean confirm = clasificatePlayerAccordingToScore(newPlayer);
        
        if (confirm == false) {
            msg = "No se ha podido agregar el jugador";
            return msg;
        }
        
        playerScores.put(nickname, newPlayer.getScore());
        
        return msg;
    }

    /**
     * Returns the position of the level where is positionated the player
     * 
     * @param playerNickName Identifier of the player
     * @return -1 if the player is not found, otherwise will return the level of the
     *         player
     */
    public int getLevelOfPlayer(String playerNickName) {
        int pos = -1;
        boolean isFound = false;

        for (int i = 0; i < LEVELS_SIZE && isFound == false; i++) {
            if (levels[i].checkPlayerByNickname(playerNickName)) {
                pos = i;
                isFound = true;
            }
        }

        return pos;
    }

    /**
     * This function will clasificate a player according to his score
     * 
     * @param player the player object
     * @return A boolean confirming the operation
     */
    public boolean clasificatePlayerAccordingToScore(Player player) {
        boolean isDone = false;
        boolean isFound = false;
        int playerOldPos = getLevelOfPlayer(player.getNickname());

        if (playerOldPos != -1) {
            levels[playerOldPos].deletePlayer(player.getNickname());
        }
        
        for (int i = 0; i < LEVELS_SIZE && isFound == false; i++) {
            if (levels[i].getNextLevelScore() > player.getScore()) {
                levels[i].addPlayer(player);
                isDone = true;
                isFound = true;
            } else if (i == LEVELS_SIZE - 1) {
                levels[i].addPlayer(player);
                isDone = true;
            }
        }

        return isDone;
    }

    /**
     * This function will increase the level of a player, if the player score is bigger
     * than the score needed to go to the next level, the player will be put in that level
     * otherwise, will show the score needed to go to the next level
     * 
     * @param nickname Nickname of the player that is going to change its level
     * @param pointsGiven The amount of score that is going to be added
     * @return A String with the new level of the player with the score needed to go to the next level
     */
    public String increaseLevelOfPlayer(String nickname, double pointsGiven) {
        String msg = "No se ha encontrado el jugador";
        
        int pos = getLevelOfPlayer(nickname);
        
        if (pos != -1) {
            System.out.println(pos);
            
            Player player = levels[pos].getPlayerByNickname(nickname);

            player.setScore(player.getScore() + pointsGiven);

            clasificatePlayerAccordingToScore(player);

            double total = ((levels[pos].getNextLevelScore() - player.getScore()) * -1);

            playerScores.put(player.getNickname(), playerScores.get(player.getNickname()) + player.getScore());

            msg = "Jugador ascendido con exito al nivel " + (getLevelOfPlayer(player.getNickname()) + 1) + "\n" +
                    "faltan " + total + " puntos para cambiar de nivel \n";
            
        } 

        return msg;
    }

    /**
     * This function returns a String with the enemy with that takes more score
     * 
     * @return String with the enemy with the highest score with the level it is located
     */
    public String reportTheEnemyWithHighestScore() {
        String msg = "";
        double acu = 0;
        int posLevel = 0;
        int posEnemy = 0; 

        for (int i = 0; i < LEVELS_SIZE; i++) {
            Level index = levels[i];
            for (int j = 0; j < index.getEnemies().length; j++) {
                if (index.getEnemies()[j] != null && index.getEnemies()[j].getPointsTaken() > acu) {
                    acu = index.getEnemies()[j].getPointsTaken();
                    posLevel = i;
                    posEnemy = j;
                }
            }
        }

        msg = "El enemigo con m??s nivel es: " + levels[posLevel].getEnemies()[posEnemy].getName() + " con un puntaje de: " +
        levels[posLevel].getEnemies()[posEnemy].getPointsTaken() + " en el nivel: " + (posLevel + 1);

        return msg;
    }

    /**
     * This function returns a String with the top 5 best players
     * 
     * @return String with the top 5 players in a no specific order
     */
    public String playersWithHighestScore() {
        String msg = "";
        int pos = 0;
        double biggerValue = 0;
        Map<String, Double> keys = new HashMap<String, Double>();

        Iterator<String> it = playerScores.keySet().iterator();
        
        for (int i = 0; i < 5; i++) {
            while (it.hasNext()) {
                String key = it.next();
                if (keys.containsKey(key) == false && playerScores.get(key) > biggerValue) {
                    biggerValue = playerScores.get(key);
                    keys.put(key, playerScores.get(key));
                    pos += 1;
                    msg += "Jugador " + pos + " es " + key + " puntaje: " + biggerValue + "\n";
                }
            }
        }
        
        return msg;
    }

    /**
     * This function receives a String and returns the number of consonants in it
     * 
     * @param enemyName A String given by the user
     * @return A String with the amount of consonants
     */
    public String consonantsEnemyName(String enemyName) {
        String msg = "";
        String [] vowals = {"a", "e", "i", "o", "u"};
        String [] word = enemyName.split("");
        int acu = word.length;

        for (int i = 0; i < word.length; i++) {
            for (int j = 0; j < vowals.length; j++) {
                if (word[i].equalsIgnoreCase(vowals[j])) {
                    acu -= 1;
                }
            }
        }

        msg = "El numero de consonantes en el nombre son: " + acu;

        return msg;
    }
}
