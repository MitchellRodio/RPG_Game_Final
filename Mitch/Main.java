package Mitch;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        playGame();
    }

    static Game game;

    public static void playGame() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("        Welcome to Mitch's RPG");
        System.out.println("");

        boolean playing = true;

        while (playing) {
            System.out.println("GAME LOADED : " + (game != null && game.characters != null));
            System.out.println("Pick an option : ");

            //OPTIONS TO SELECT FROM
            System.out.println("[0] Create a New Game ");
            System.out.println("[1] Validate a Save File ");
            System.out.println("[2] Re-Roll a Character ");
            System.out.println("[3] Print Game Stats ");
            System.out.println("[4] Save Game File ");

            System.out.println("[Quit by typing anything else]");
            try {
                String input = scanner.nextLine();
                int inputvalue = Integer.valueOf(input);
                if ( inputvalue < 0 || inputvalue > 4 )
                {
                    System.out.println("Pick an option between 0 and 4 ");
                    continue;
                }
                switch (inputvalue) {
//                  Switch 0 = Create new game
                    case 0: {
                        game = new Game();
                        game.initGame();
                        break;
                    }
                    case 1:
//                      Switch 1 = validate save file
                        validateGameFile(scanner);
                        break;
                    case 2:
//                      Switch 2 = Re-roll specific / chosen file
                        reroll(scanner);
                        break;
                    case 3:
//                      Switch 3 = Print player stats
                        printGame();
                        break;
                    case 4:
//                       Switch 4 = save game file
                        saveGameFile(scanner);

                }
            } catch (NumberFormatException e) {

                break;
            } catch (Exception e) {

            }

        }
    }

    private static void printGame() {
        if (game == null || game.characters == null) {
            System.out.println("Error, game not started");
            return;
        }
        System.out.println(game.toString());
    }


    public static void reroll(Scanner scanner) {
        int input = 0;
        if (game == null) System.out.println("Game has not been initialized");

        else {

            boolean inputIsValid = true;
            do {
                try {
                    System.out.print("Pick a character index to re-roll (1-4) : ");
                    input = Integer.valueOf(scanner.nextLine());
                    if (input < 1 || input > 4) throw new Exception(); inputIsValid = true;
                } catch (Exception e) {
                    System.out.println("Invalid Input");
                    inputIsValid = false;
                }

            } while (!inputIsValid);
            input --;
            game.getCharacters()[input] = null;
            System.out.println("Picking Character #" + (input + 1));
            while (game.getCharacters()[input] == null)
            {
                Character temp = Character.generateRandomCharacter();
                System.out.println(temp);
                System.out.print("Would you like to keep this character? [Y N]: ");
                String inputChoice = scanner.nextLine();
                if (inputChoice.equals("Y")) {
                    game.getCharacters()[input] = temp;
                    System.out.print("Enter a character name : ");
                    String name = scanner.nextLine();
                    temp.setName(name);
                }

            }

            System.out.println("Success");
        }
    }

    public static boolean validateGameFile(Scanner scanner) {
        System.out.println("Enter a file name: ");

        String filename = scanner.nextLine();

        game = new Game();
        if (game.parseGameFile(filename)) {
            System.out.println("GAME FILE SUCCESSFULLY PARSED\n\n");
            return true;
        } else {
            System.out.println("Error\n\n");
            return false;
        }


    }

    public static boolean saveGameFile(Scanner scanner) {
        System.out.println("Enter a file name: ");

        if (game == null) {
            System.out.println("ERROR GAME NOT INITIALIZED ");
            return false;
        }

        String filename = scanner.nextLine();


        try {
            game.toFile(filename);
            System.out.println("GAME SUCCESSFULLY SAVED\n\n");
        } catch (IOException e) {

            System.out.println("Error\n\n");
            return false;
        }

        return true;


    }

    //Testing Class
    public static void testing() {

    }
}
