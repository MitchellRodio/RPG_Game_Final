package Mitch;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Game {

    Scanner scanner = new Scanner(System.in);
    private int[] amounts = new int[5]; //Duplicate checker

    public void initGame() {

        System.out.println("Game Creater");
        System.out.print("Enter a game name: ");
        saveGameName = scanner.nextLine();


        System.out.println("Creating characters! Enter [Y] if you want to pick this character! Enter anything else if you want to skip this character!");
        int i = 0;
        while (characters[4 - 1] == null) {
            System.out.println("Picking Character #" + (i + 1));
            while (characters[i] == null) //0
            {
                Character temp = Character.generateRandomCharacter();
                while(amounts[temp.getProfession().ordinal()] + 1 > 2)
                {
                    temp = Character.generateRandomCharacter();
                }
                System.out.println(temp);
                System.out.print("Would you like to keep this character? : ");
                String input = scanner.nextLine();
                if (input.equals("Y")) {
                    characters[i] = temp;
                    amounts[temp.getProfession().ordinal()]++;

                    System.out.print("Enter a character name : "); //No ln cause same line input
                    String name = scanner.nextLine();
                    temp.setName(name);
                }

            }
            i++;
        }

        System.out.println("Game has been created! Name " + saveGameName);
        System.out.println("You picked following characters");
        for (Character h : characters) {
            System.out.println(">>      " + h);
        }

    }

    public boolean parseGameFile(String filePath) {
        File file = new File(filePath);
        System.out.println("Attempting to parse save file from following path : " + filePath);

        try {
            Scanner scanner = new Scanner(file);

            int i = 0;
            while (scanner.hasNext()) {
                if (i == 0) saveGameName = scanner.nextLine();
                else {
                    String[] valueArray = scanner.nextLine().split(",");
                    int[] values = {
                            Integer.valueOf(valueArray[2]),
                            Integer.valueOf(valueArray[3]),
                            Integer.valueOf(valueArray[5]),
                            Integer.parseInt(valueArray[6])
                    };




                    characters[i - 1] = new Character(
                            valueArray[0],
                            CharacterType.stringToEnum(valueArray[1]),
                            values
                    );
                    if(!characters[i-1].verify()) {

                        characters = null;
                        saveGameName = null;
                        return false;
                    }
                    if(++amounts[characters[i-1].getProfession().ordinal()] > 2)
                    {
                        characters = null;
                        saveGameName = null;
                        return false;
                    }


                }
                i++;
            }

        } catch (FileNotFoundException e) {
            System.out.println("FAILED PARSING FILE");
            characters = null;
            saveGameName = null;
              //Printing caused error
             return false;
        }
        return true;
    }


    @Override
    public String toString() {
        String output = "GAME : \n\n";
        output += saveGameName + "\n\n";
        for(int i = 0; i < 5 - 1; i++)  //5 - 1
        output += characters[i] + "\n" ;//0 // }{

        return output;
    }

    public void toFile(String filename) throws IOException {
        FileWriter fw = new FileWriter(filename,false);
        String filecontent = saveGameName + "\n";
        for(Character h : characters)
            filecontent += h + "\n"; //characters will get added

        fw.write(filecontent);
        fw.close();

    }

    public Character[] getCharacters() {
        return characters;
    }

    Character[] characters = new Character[4];
    String saveGameName;


}
