package Mitch;

import java.util.Random;

public class Character {
    private String name;
    private CharacterType profession;
    private int[] abilities;

    private static Random random = new Random();

    public static Character generateRandomCharacter() {
        CharacterType characterType = CharacterType.intToEnum(random.nextInt(5));
        int[] values = new int[5];
        int total = 0;
        values[0] = random.nextInt(4) + 7;
        total += values[0];

        for (int i = 1; i < 5; i++) {
            values[i] = random.nextInt(7);
            total += values[i];
        }

        while (total > 28) {
            int index = random.nextInt(5);
            if (index == 0) continue;
            if (values[index] > 0) {
                values[index]--;
                total--;

            }
        }


        while (total < 8) {
            int index = random.nextInt(5);
            if (index == 0) continue;
            if (values[index] < 0) {
                values[index]++;
                total++;

            }

        }

        return new Character("NAME", characterType, values); // Placeholder name which the user later picks


    }

    public Character(String name, CharacterType profession, int[] abilities) {
        this.name = name;
        this.profession = profession;
        this.abilities = abilities;
    }

    public String getName() {
        return name;
    }

    public CharacterType getProfession() {
        return profession;
    }

    public int[] getAbilities() {
        return abilities;
    }

    public void setAbilities(int[] abilities) {
        this.abilities = abilities;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProfession(CharacterType profession) {
        this.profession = profession;
    }

    @Override
    public String toString() {
        String save = "";
        save += name + ",";
        save += profession + ",";

        for (int i = 0; i < abilities.length; i++) {
            save += abilities[i];
            if (i < abilities.length - 1) save += ",";
        }
        return save;
    }

    public boolean verify()
    {
        int total = 0;
        if(abilities[0] < 7 || abilities[0] > 10) return false;
        for(int i = 2; i < abilities.length; i++)
        {
            total += abilities[i];
        }
        return total < 28;
    }


}
