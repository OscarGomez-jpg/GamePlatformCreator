package ui;

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

        main.getReader().close();
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
        System.out.println("<<<<< Bienvenido al planetario >>>>>");
        System.out.println(
                "1. Agregar un tesoro a un nivel\n" +
                        "2. Agregar un enemigo a un nivel\n" +
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
        int levelId = validateIntegerOption();

        System.out.println("Ingrese la cantidad que desea agregar del tesosro: ");
        int amount = validateIntegerOption();

        if (levelId == -1 || amount == -1) {
            msg = "Por favor introduce un número";
        } else {
            System.out.println("Ingrese el nombre del tesoro que quiere agregar");
            String lootName = reader.next();
    
            System.out.println("Ingrese la URL de la imagen del tesoro: ");
            String URL = reader.next();
    
            System.out.println("Ingrese los puntos que dara el tesoro: ");
            double points = reader.nextDouble();
    
            Loot newLoot = new Loot(lootName, URL, points);
    
            msg = game.addLootToALevel(levelId, newLoot, amount);
        }


        return msg;
    }

    public String uiAddEnemyToALevel() {
        String msg = "";
        return msg;
    }

}
