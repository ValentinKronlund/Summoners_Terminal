package summonersTerminal.gameHelpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import summonersTerminal.Champion;
import summonersTerminal.ChampionID;
import summonersTerminal.Item;
import summonersTerminal.Minion;
import summonersTerminal.MinionType;
import summonersTerminal.Nexus;
import summonersTerminal.champion.abilities.Ability;
import summonersTerminal.champion.Passives.Factory;

public class Action {
    static Helpers helper = new Helpers();
    static Scanner scanner = new Scanner(System.in);
    static int meleeCounter = 0;
    static int casterCounter = 0;
    static int canonCounter = 0;

    public static Champion chooseChampion(String playerName) {
        Champion champion = null;

        while (champion == null) {
            String championRequest = helper.askLine(scanner, "");

            switch (championRequest) {
                case "Garen", "garen", "G", "g": {
                    champion = ChampionID.GAREN.create(playerName);
                    break;
                }
                case "Katarina", "katarina", "K", "k": {
                    champion = ChampionID.KATARINA.create(playerName);
                    break;
                }
                case "Veigar", "veigar", "V", "v": {
                    champion = ChampionID.VEIGAR.create(playerName);
                    break;
                }
                default: {
                    System.out.println("\n There is no champion named: " + championRequest);
                }
            }
        }

        return champion;
    }

    public static void generateMinionWave(List<Minion> minionWave, int waveNumber, boolean isEnemy) {
        List<Minion> wave = new ArrayList<>();
        String identifier = isEnemy ? "♦️ Enemy" : "🔷 Ally";

        for (int i = 0; i < 2; i++) { // Add Melee minions
            String uniqueIdentifier = "%s %d ".formatted(identifier, meleeCounter);
            Minion newMinion = new Minion(uniqueIdentifier, MinionType.MELEE);
            wave.add(newMinion);
            meleeCounter++;
        }
        for (int i = 0; i < 3; i++) { // Add Caster minions
            String uniqueIdentifier = "%s %d ".formatted(identifier, casterCounter);
            Minion newMinion = new Minion(uniqueIdentifier, MinionType.CASTER);
            wave.add(newMinion);
            casterCounter++;
        }
        if (waveNumber % 3 == 0) {
            String uniqueIdentifier = "%s %d ".formatted(identifier, canonCounter);
            Minion newMinion = new Minion(uniqueIdentifier, MinionType.CANON);
            wave.add(newMinion);
            canonCounter++;
        }

        for (Minion minion : wave) {
            minionWave.add(minion);
        }
    }

    public static boolean mainActionsLoop(
            Nexus enemyNexus,
            Nexus allyNexus,
            Champion playerChampion,
            Champion enemyChampion,
            List<Minion> enemyMinionWave,
            List<Minion> allyMinionWave,
            int playerActionCount) {
        /*
         * Right now we only progress the game on player actions, which is not suitable
         * for enemy behaviour.
         * Future patch should also consider enemy actions seperately
         */
        while (playerActionCount < 5 & playerChampion.isAlive() == true) {
            System.out.println("\n" + playerChampion.toString());
            Copy.baseActionChoiceCopy(playerActionCount);

            try {
                char playerChoice = helper.askChar(scanner, "");

                // Main combat choices below 👇🏽 -------------------------
                switch (playerChoice) {
                    case 'a': {
                        boolean successfulAttack = Action.abilityAction(playerChampion, enemyMinionWave);
                        if (successfulAttack)
                            playerActionCount++;
                        break;
                    }

                    case 'm': {
                        Action.attackAction(playerChampion, enemyMinionWave, enemyNexus);
                        playerActionCount++;
                        break;
                    }

                    case 'b': {
                        playerChampion.goToBase();
                        playerActionCount += 2;
                        break;
                    }

                    case 'i': {
                        Copy.viewItemsCopy();
                        continue; // <----- Continue here as to not lose a round or take damage.
                    }
                    case 'p': {
                        Action.purchaseOptions(playerChampion);
                        playerActionCount += 2;
                        break;
                    }

                    case 'q': {
                        Copy.allyWaveCopy(allyMinionWave);
                        continue;
                    }

                    case 'w': {
                        Copy.enemyWaveCopy(enemyMinionWave);
                        continue;
                    }

                    case 'e': {
                        System.out.println("\n" + enemyChampion.toString());
                        continue;
                    }

                    case 'r': {
                        Copy.nexusCopy(allyNexus);
                        continue;
                    }

                    case 't': {
                        Copy.nexusCopy(enemyNexus);
                        continue;
                    }

                    case 'x': {
                        Copy.quitCopy();
                        char quitConfirmation = helper.askChar(scanner, "");
                        switch (quitConfirmation) {
                            case 'x':
                                return true;
                            default:
                                continue;
                        }
                    }

                    default:
                        System.out.println("There is currently no command for: " + playerChoice);
                        continue;
                }
            } catch (AssertionError err) {
                System.out.println(err);
                continue;
            }

            if (!enemyNexus.isAlive() || !allyNexus.isAlive()) {
                return true;
            }

            for (Minion minion : enemyMinionWave) {
                if (minion != null && minion.isAlive()) {
                    minion.minionBehaviour(allyMinionWave, playerChampion, allyNexus);
                }
            }

            for (Minion minion : allyMinionWave) {
                if (minion != null && minion.isAlive()) {
                    minion.minionBehaviour(enemyMinionWave, enemyChampion, enemyNexus);
                }
            }

            if (playerChampion.isAlive() == false) {
                break;
            }

        }

        return false;
    }

    public static boolean attackAction(Champion playerChampion, List<Minion> minionWave, Nexus nexus) {
        while (true) {
            if (minionWave.size() > 0) {
                Copy.chooseTarget(minionWave);
                int targetIdx = helper.askInt(scanner, "");
                if (targetIdx > minionWave.size() || targetIdx <= 0)
                    targetIdx = 1;
                try {
                    if (minionWave.get(targetIdx - 1) == null) {
                        System.out.println("No minion at given index: " + targetIdx);
                        continue;
                    }

                    Minion targetMinion = minionWave.get(targetIdx - 1);
                    boolean successfulAttack = playerChampion.attack(targetMinion, minionWave);
                    return successfulAttack;
                } catch (IllegalArgumentException e) {
                    System.out.println("No minion at given index: " + targetIdx);
                    continue;
                }
            } else {
                Copy.attackNexusCopy(nexus);
                playerChampion.attackNexus(nexus);
                return true;
            }
        }
    }

    public static boolean abilityAction(Champion playerChampion, List<Minion> minionWave) {
        while (true) {
            List<Ability> playerAbilities = playerChampion.abilities();
            Copy.chooseAbility(playerAbilities);
            int abilityIndex = helper.askInt(scanner, "") - 1;

            if (!Validation.validateAbilityOption(abilityIndex, playerAbilities)) {
                continue;
            }

            Copy.chooseTarget(minionWave);
            int targetIdx = helper.askInt(scanner, "");

            try {
                if (!Validation.validateTargetChoice(targetIdx, minionWave)) {
                    continue;
                }

                Minion targetMinion = minionWave.get(targetIdx - 1);
                boolean successfulAttack = playerChampion.useAbility(abilityIndex, targetMinion, minionWave);
                return successfulAttack;
            } catch (IllegalArgumentException e) {
                System.out.println("No minion at given index: " + targetIdx);
                continue;
            }
        }
    }

    public static void purchaseOptions(Champion champion) {
        while (true) {
            int option = helper.askInt(scanner,
                    "\nWhat item would you like to purchase?"
                            + "\n[1] " + Item.THORN_MAIL.toString() +
                            "\n[2] " + Item.ROD_OF_AGES.toString() +
                            "\n[3] " + Item.INFINITY_EDGE.toString() +
                            "\n[4] " + Item.RABADONS_DEATHCAP.toString());

            switch (option) {
                case 1: {
                    champion.equip(Item.THORN_MAIL);
                    return;
                }
                case 2: {
                    champion.equip(Item.ROD_OF_AGES);
                    return;
                }
                case 3: {
                    champion.equip(Item.INFINITY_EDGE);
                    return;
                }
                case 4: {
                    champion.equip(Item.RABADONS_DEATHCAP);
                    return;
                }
                default: {
                    System.out.println("There is no item at the given index of: " + option);
                    continue;
                }
            }

        }

    }
}
