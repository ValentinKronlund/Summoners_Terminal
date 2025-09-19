package summonersTerminal.gameHelpers;

import java.util.List;
import summonersTerminal.Item;
import summonersTerminal.champion.abilities.Ability;
import summonersTerminal.minion.Minion;

public class Validation {

    public static boolean validateAbilityOption(int index, List<Ability> abilities) {
        if (index < 0 || index >= abilities.size() || abilities.get(index) == null) {
            System.out.println("No ability at given index: " + index);
            return false;
        }
        return true;
    }

    public static boolean validateTargetChoice(int index, List<Minion> wave) {
        if (index > wave.size() || index < 1 || wave.get(index - 1) == null) {
            System.out.println("There is no minion at that location: " + index);
            return false;
        }
        return true;
    }

    public static boolean validateEquipItem(Item item, List<Item> playerItems, int playerGold) {
        if (playerItems.size() >= 6) {
            System.out.println("\nYou don't have any more available item slots!");
            return false;
        }
        if (playerGold < item.cost()) {
            System.out.println("\nNot enough gold for that item!");
            return false;
        }
        return true;
    }
}
