package Mitch;

public enum CharacterType {

    Knight, Peasant, Cleric, Mage, Courtier;
    public static CharacterType stringToEnum(String input) {
        switch (input) {
            case "Knight": return Knight;
            case "Peasant": return Peasant;
            case "Cleric": return Cleric;
            case "Mage": return Mage;
            case "Courtier": return Courtier;

            default: return null;
        }
    }

    public static CharacterType intToEnum(int input) {
        switch (input) {
            case 0 : return Knight;
            case 1 : return Peasant;
            case 2 : return Cleric;
            case 3 : return Mage;
            case 4 : return Courtier;

            default: return null;
        }
    }

    

}
