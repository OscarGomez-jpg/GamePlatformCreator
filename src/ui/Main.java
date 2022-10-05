package ui;

import java.util.Scanner;

public class Main {
    private Scanner reader;

    public Main() {
        reader = new Scanner(System.in);

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
                "1. Agregar persona\n" +
                        "0. Exit. ");
        option = reader.nextInt();

        return option;
    }

    public void executeOption(int option) {

        switch (option) {
            case 1: {

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

    public Scanner getReader() {
        return reader;
    }
}
