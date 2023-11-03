package lastpencil;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int numberOfPencils;
        while (true) {
            System.out.println("How many pencils would you like to use:");
            String input = scanner.nextLine();

            try {
                numberOfPencils = Integer.parseInt(input);
                if (numberOfPencils < 1) {
                    System.out.println("The number of pencils should be a positive integer.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("The number of pencils should be numeric.");
            }
        }

        String firstPlayer;
        while (true) {
            System.out.println("Who will be the first (John, Jack):");
            firstPlayer = scanner.nextLine();
            if (!firstPlayer.equals("John") && !firstPlayer.equals("Jack")) {
                System.out.println("Choose between 'John' and 'Jack'.");
            } else {
                break;
            }
        }

        playGame(numberOfPencils, firstPlayer, scanner);
    }

    public static void playGame(int numberOfPencils, String firstPlayer, Scanner scanner) {
        String currentPlayer = firstPlayer;

        while (numberOfPencils > 0) {
            String pencilDisplay = new String(new char[numberOfPencils]).replace('\0', '|');
            System.out.println(pencilDisplay);

            if (currentPlayer.equals("John")) {
                System.out.println("John's turn!");
                int pencilsToRemove;
                while (true) {
                    String input = scanner.nextLine();
                    try {
                        pencilsToRemove = Integer.parseInt(input);
                        if (pencilsToRemove < 1 || pencilsToRemove > 3 || pencilsToRemove > numberOfPencils) {
                            if (pencilsToRemove > numberOfPencils) {
                                System.out.println("Too many pencils.");
                            } else {
                                System.out.println("Please choose between 1, 2, or 3 (within available pencils).");
                            }
                            System.out.println("Possible values: '1', '2', '3'");
                            System.out.println();
                            continue;
                        }
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a valid number.");
                        System.out.println("Possible values: '1', '2', '3'");
                        System.out.println();
                    }
                }
                numberOfPencils -= pencilsToRemove;

                if (numberOfPencils <= 0) {
                    System.out.println("Jack won!");
                    break;
                }
            } else {
                int botMove = calculateBotMove(numberOfPencils);
                if (botMove == 0) {
                    botMove = 1; // Set botMove to 1 if it's calculated as 0
                }
                numberOfPencils -= botMove;
                System.out.println("Jack's turn:");
                System.out.println(botMove);

                if (numberOfPencils <= 0) {
                    System.out.println("John won!");
                    break;
                }
            }

            currentPlayer = currentPlayer.equals("John") ? "Jack" : "John";
        }
    }

    public static int calculateBotMove(int pencils) {
        if (pencils == 1) {
            return 1;
        } else if (pencils % 4 == 0) {
            return 3;
        } else {
            return pencils % 4 - 1;
        }
    }
}

