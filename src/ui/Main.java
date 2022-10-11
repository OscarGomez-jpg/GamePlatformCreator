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

    public int getOptionShowMenu() {
        int option = 0;
        System.out.println("<<<<< Bienvenido al planetario >>>>>");
        System.out.println(
                "1. Agregar un tesoro a un nivel\n" +
                        "0. Exit. ");
        option = reader.nextInt();

        return option;
    }

    public void executeOption(int option) {
        String msg = "";

        switch (option) {
            case 1: {
                msg = addLootToALevel();

                System.out.println(msg);
                break;
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
     * @return String confirming the operation
     */
    public String addLootToALevel() {
        String msg = "No se ha podido añadir el tesoro";

        System.out.println("Ingrese el nivel al que quiere ser agregado");
        int levelId = reader.nextInt();

        System.out.println("Ingrese el nombre del tesoro que quiere agregar");
        String lootName = reader.next();

        System.out.println("Ingrese la URL de la imagen del tesoro: ");
        String URL = reader.next();

        System.out.println("Ingrese los puntos que dara el tesoro: ");
        double points = reader.nextDouble();

        Loot newLoot = new Loot(lootName, URL, points);

        msg = game.addLootToALevel(levelId, newLoot);
        
        return msg;
    }

}
