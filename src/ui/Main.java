package ui;

import model.Enemy;
import model.Game;
import model.Loot;

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
                        "0. Exit. ");

        option = validateIntegerOption();

        return option;
    }

    public void executeOption(int option) {
        String msg = "";

        switch (option) {
            case 1: {
                msg = uiAddLootToALevel();

                System.out.println(msg);
                break;
            }

            case 2: {
                msg = uiAddEnemyToALevel();

                System.out.println(msg);
            }

            case 3: {
                msg = uiReportEnemiesAndLootsOfLevel();

                System.out.println(msg);
            }

            case 0: {
                System.out.println("Hasta luego!");
                break;
            }

            default: {
                System.out.println("Opción inválida");
                break;
            }
        }
    }

    /**
     * This function adds a loot to a specific level
     * 
     * @return String confirming the operation
     */
    public String uiAddLootToALevel() {
        String msg = "No se ha podido añadir el tesoro";

        
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
        

        return msg;
    }

    /**
     * This function adds an enemy to a specific level
     * 
     * @return String confirming the operation
     */
    public String uiAddEnemyToALevel() {
        String msg = "No se ha podido añadir el enemigo";

        try {

            System.out.println("Ingrese el nivel al que quiere agregar el enemigo: ");
            int levelId = reader.nextInt();

            System.out.println("Ingrese el nombre del enemigo que quiere agregar: ");
            String enemyName = reader.next();

            System.out.println("Ingrese el nombre del tipo de enemigo que desea agregar: ");
            System.out.println("Ogro\n" +
                    "Abstracto\n" +
                    "Magico\n" +
                    "Jefe");
            String type = reader.next();

            System.out.println("Ingrese los puntos que dara el enemigo: ");
            double pointsGiven = reader.nextDouble();

            System.out.println("Ingrese los puntos que quitara el enemigo: ");
            double pointsTaken = reader.nextDouble();

            msg = game.addEnemyToALevel(levelId, enemyName, type, pointsGiven, pointsTaken);

        } catch (Exception inputMismatchException) {
            msg = "Por favor introduce un número";
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
}
