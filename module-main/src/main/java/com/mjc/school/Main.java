package com.mjc.school;

import com.mjc.school.controller.MenuController;

import java.util.Scanner;

public class Main {
    private static final MenuController menuController = new MenuController();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        start();
    }

    private static void start() {
        while (true) {
            try {
                menuController.printMenu();
                String value = scanner.nextLine();
                while (value.equals("")) {
                    value = scanner.nextLine();
                }
                int valueInt = 0;
                try {
                    valueInt = Integer.parseInt(value);
                } catch (NumberFormatException e) {
                    System.out.println("Command not found.");
                    start();
                }
                switch (valueInt) {
                    case 1:
                        menuController.getAllMenu();
                        break;
                    case 2:
                        menuController.getNewsById();
                        break;
                    case 3:
                        menuController.createNews();
                        break;
                    case 4:
                        menuController.updateNews();
                        break;
                    case 5:
                        menuController.removeNewsById();
                        break;
                    case 0:
                        System.exit(0);
                    default: System.out.println("Command not found.");
                }
            } catch (RuntimeException e){
                System.out.println(e.getMessage());
            }
        }
    }
}
