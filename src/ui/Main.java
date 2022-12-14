package ui;

import model.Game;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private Scanner reader;
    private Game game;

    public Main() {
        reader = new Scanner(System.in);
        game = new Game();

    }

    public Scanner getReader() {
        return reader;
    }

    public static void main(String[] args) {
        Main main = new Main();
        int option = 0;
        boolean isDone = false;

        do {
            isDone = main.uiInitializeLevels();
            
            
            if (isDone == false) {
                System.out.println("Ingrese un valor valido");
                main.getReader().next();
            }
            

        } while (!isDone);

        do {

            option = main.getOptionShowMenu();
            main.executeOption(option);

        } while (option != 0);

    }

    public int validateIntegerOption() {
        int option = 0;

        if (reader.hasNextInt()) {
            option = reader.nextInt();
        } else {
            // clear reader.
            reader.nextLine();
            option = -1;
        }

        return option;
    }

    
    public int getOptionShowMenu() {
        int option = 0;
        System.out.println("<<<<< Bienvenido a la plataforma de desarrollo de videojuegos >>>>>");
        System.out.println(
            "1. Agregar un tesoro a un nivel\n" +
            "2. Agregar un enemigo a un nivel\n" +
            "3. Mostrar los tesoros y enemigos de un nivel\n" +
                        "4. Cantidad de un tesoro en todos los niveles\n" +
                        "5. Ver el tesoro que mas se repite\n" +
                        "6. Ver la cantidad de un tipo de enemigo en todos los niveles\n" +
                        "7. Agregar jugador\n" +
                        "8. Incrementar el puntaje de un jugador\n" +
                        "9. Mostrar el enemigo con mas puntaje\n" +
                        "10. Mostrar los jugadores con mas puntaje\n" +
                        "11. Consonantes de el nombre de un enemigo\n" +
                        "0. Exit. ");

        option = validateIntegerOption();

        return option;
    }

    public void executeOption(int option) {
        String msg = "";

        switch (option) {
            case 1: {
                // Add loot to a level
                msg = uiAddLootToALevel();

                System.out.println(msg);
                break;
            }

            case 2: {
                // Add enemy to a level
                msg = uiAddEnemyToALevel();

                System.out.println(msg);

                break;
            }

            case 3: {
                // Show loots and enemies from a level
                msg = uiReportEnemiesAndLootsOfLevel();

                System.out.println(msg);

                break;
            }

            case 4: {
                // Count loot in all levels by name
                msg = uiCountLootAllLevels();

                System.out.println(msg);

                break;
            }

            case 5: {
                // Most repeated loot
                msg = uiMostRepeatedLootAllLevels();

                System.out.println(msg);

                break;
            }

            case 6: {
                // Most repeated enemy type in all levels

                msg = uiMostReapeatedEnemyTypeAllLevels();

                System.out.println(msg);

                break;
            }

            case 7: {
                //Add a player to the game
                msg = uiAddPlayer();

                System.out.println(msg);

                break;
            }

            case 8: {
                //Increase the level of a player
                msg = uiIncreaseLevelOfPlayer();

                System.out.println(msg);

                break;
            }

            case 9: {
                //Enemy with highest level

                msg = uiEnemyWithHighestScore();

                System.out.println(msg);

                break;
            }

            case 10: {
                //Top 5 players
                
                msg = uiPlayersWithHighestScore();

                System.out.println(msg);
            }

            case 11: {
                //Consonants in a name

                msg = uiConsonats();

                System.out.println(msg);
            }

            case 0: {
                System.out.println("Hasta luego!");
                break;
            }

            default: {
                System.out.println("Opci??n inv??lida");
                break;
            }
        }
    }

    /**
     * This function intialize the 10 levels of the game
     * 
     * @return -1 if the number given by the user is not a double, otherwise will
     *         return the number given by the user
     */
    public boolean uiInitializeLevels() {
        boolean isDone = true;
        double amount = 0;
        System.out.println("... Iniciando la plataforma");
        System.out.println("Ingrese los puntos necesarios para que el jugador pase de nivel");
        System.out.println("El numero debe de ser mayor a 10 puesto que es el puntaje con el que inicial el jugador");

        try {
            amount = getReader().nextDouble();

            if (amount < 10) {
                throw new InputMismatchException();
            }

            game.generateInitialLevels(amount);

        } catch (Exception inputMismatchException) {
            isDone = false;
        }

        return isDone;
    }

    /**
     * This function adds a loot to a specific level
     * 
     * @return String confirming the operation
     */
    public String uiAddLootToALevel() {
        String msg = "No se ha podido a??adir el tesoro";

        try {
            System.out.println("Ingrese el nivel al que quiere agregar el tesoro: ");
            int levelId = reader.nextInt();

            System.out.println("Ingrese el nombre del tesoro que quiere agregar");
            String lootName = reader.next();

            System.out.println("Ingrese la URL de la imagen del tesoro: ");
            String URL = reader.next();

            System.out.println("Ingrese los puntos que dara el tesoro: ");
            double points = reader.nextDouble();

            System.out.println("Ingrese la cantidad que desea agregar del tesoro: ");
            int amount = reader.nextInt();

            msg = game.addLootToALevel(levelId, lootName, URL, points, amount);

        } catch (Exception inputMismatchException) {
            msg = "Por favor introduce un valor valido";
        }

        return msg;
    }

    /**
     * This function adds an enemy to a specific level
     * 
     * @return String confirming the operation
     */
    public String uiAddEnemyToALevel() {
        String msg = "No se ha podido a??adir el enemigo";

        try {

            System.out.println("Ingrese el nivel al que quiere agregar el enemigo: ");
            int levelId = reader.nextInt();

            System.out.println("Ingrese el nombre del enemigo que quiere agregar: ");
            String enemyName = reader.next();

            System.out.println("Ingrese el nombre del tipo de enemigo que desea agregar: ");
            System.out.println("* Ogro\n" +
                    "* Abstracto\n" +
                    "* Magico\n" +
                    "* Jefe");
            String type = reader.next();

            System.out.println("Ingrese los puntos que dara el enemigo: ");
            double pointsGiven = reader.nextDouble();

            System.out.println("Ingrese los puntos que quitara el enemigo: ");
            double pointsTaken = reader.nextDouble();

            msg = game.addEnemyToALevel(levelId, enemyName, type, pointsGiven, pointsTaken);

        } catch (Exception inputMismatchException) {
            msg = "Por favor ingrese un valor valido";
        }

        return msg;
    }

    /**
     * This function shows the loot and enemies of a level
     * 
     * @return String with the formated data
     */
    public String uiReportEnemiesAndLootsOfLevel() {
        String msg = "";

        try {
            System.out.println("Ingrese el valor del nivel del que quiere saber los enemigos y niveles");
            int levelPos = reader.nextInt();

            msg = game.reportEnemiesAndLootsOfLevel(levelPos);

        } catch (Exception inputMismException) {
            msg = "Por favor ingrese un numero valido";
        }

        return msg;
    }

    /**
     * This function shows the count the ocurrences of a loot name given by the user
     * 
     * @return String with the amount of the loot found in all levels
     */
    public String uiCountLootAllLevels() {
        String msg = "";

        System.out.println("Ingrese el nombre del tesoro que quiere contar: ");
        String lootName = reader.next();

        msg = game.countLootAllLevels(lootName);

        return msg;
    }

    /**
     * This function shows the most repeated loot through al levels
     * 
     * @return String with the most repeated loot in all levels
     */
    public String uiMostRepeatedLootAllLevels() {
        String msg = "";

        msg = game.mostRepeatedLootAllLevels();

        return msg;
    }

    /**
     * This function get an enemy type given by the user and return a String
     * with how many times that type appears in all levels
     * 
     * @return a String with the occurences of the enemy type
     */
    public String uiMostReapeatedEnemyTypeAllLevels() {
        String msg = "";

        System.out.println("Ingrese el tipo de enemigo que desea contar: ");
        System.out.println("* Ogro\n" + "* Magico\n" + "* Abstracto\n" + "* Jefe\n");
        String type = reader.next();
        type = type.toLowerCase();

        msg = game.typeInAllLevels(type);

        return msg;
    }

    /**
     * This function adds a player to the game
     * 
     * @return A String confirming the operation
     */
    public String uiAddPlayer() {
        String msg = "";

        System.out.println("Ingrese el nickname del jugador: ");
        String nickname = reader.next();

        System.out.println("Ingrese el nombre del jugador: ");
        String name = reader.next();

        msg = game.addPlayer(nickname, name);

        return msg;
    }

    /**
     * This function increase the level of a player
     * 
     * @return A String with the new level of the player
     */
    public String uiIncreaseLevelOfPlayer() {
        String msg = "";

        try {
            System.out.println("Ingrese el nickname del jugador: ");
            String nickname = reader.next();
    
            System.out.println("Ingrese la cantidad de puntaje que desea agregar: ");
            double score = reader.nextDouble();
    
            msg = game.increaseLevelOfPlayer(nickname, score);
        } catch (Exception inputMismatchException) {
            msg = "Por favor ingrese un numero valido";
        }

        return msg;
    }

    /**
     * This function reports the enemy with the highest score
     * 
     * @return String with the enemy with the higher score and the level it is located
     */
    public String uiEnemyWithHighestScore() {
        String msg = "";

        msg = game.reportTheEnemyWithHighestScore();

        return msg;
    }

    /**
     * This function returns the top 5 players in the game
     * 
     * @return String with the top 5 players in the game
     */
    public String uiPlayersWithHighestScore() {
        String msg = "";

        msg = game.playersWithHighestScore();

        return msg;
    }

    /**
     * This function returns the consonants of an Enemy name
     * 
     * @return String with the total consonants
     */
    public String uiConsonats() {
        System.out.println("Ingrese el nombre del enemigo: ");
        String enemyName = reader.next();

        String msg = game.consonantsEnemyName(enemyName);

        return msg;
    }
}
