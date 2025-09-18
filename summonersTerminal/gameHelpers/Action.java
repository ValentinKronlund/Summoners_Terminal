package summonersTerminal.gameHelpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import summonersTerminal.Champion;
import summonersTerminal.ChampionID;
import summonersTerminal.Item;
import summonersTerminal.Minion;
import summonersTerminal.MinionType;
import summonersTerminal.Nexus;
import summonersTerminal.champion.abilities.Ability;

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
            Nexus playerNexus,
            Nexus npcNexus,
            Champion playerChampion,
            Champion npcChampion,
            List<Minion> playerMinionWave,
            List<Minion> npcMinionWave,
            int actionCount) {
        Random random = new Random();
        boolean playerSkipNextTurn = false;
        boolean npcSkipNextTurn = false;

        while (actionCount > 0 && playerNexus.isAlive() && npcNexus.isAlive()) {
            if (!playerSkipNextTurn) {
                Action.playerActions(playerNexus, npcNexus, playerChampion, npcChampion,
                        playerMinionWave, npcMinionWave,
                        actionCount, playerSkipNextTurn);
                System.out.println("FINISHED PLAYER ACTION");
            }
            if (!npcSkipNextTurn) {
                Action.npcActions(npcNexus, playerNexus, npcChampion, playerChampion, npcMinionWave, playerMinionWave,
                        actionCount, npcSkipNextTurn);
                System.out.println("FINISHED NPC ACTION");

            }

            int minionAttackOrder = random.nextInt(0, 2);
            if (minionAttackOrder == 0) {
                Action.allyMinionActions(playerMinionWave, npcMinionWave, npcChampion, npcNexus);
                Action.npcMinionActions(npcMinionWave, playerMinionWave, playerChampion, playerNexus);
                System.out.println("FINISHED MINION ACTION - Prio Ally 🔷");

            } else {
                Action.npcMinionActions(npcMinionWave, playerMinionWave, playerChampion, playerNexus);
                Action.allyMinionActions(playerMinionWave, npcMinionWave, npcChampion, npcNexus);
                System.out.println("FINISHED MINION ACTION - Prio NPC ♦️");
            }

            playerSkipNextTurn = false;
            npcSkipNextTurn = false;
            actionCount--;

        }

        return false;
    }

    private static void playerActions(
            Nexus playerNexus,
            Nexus npcNexus,
            Champion playerChampion,
            Champion npcChampion,
            List<Minion> playerMinionWave,
            List<Minion> npcMinionWave,
            int actionCount,
            boolean playerSkipNextTurn) {
        if (playerChampion.isAlive()) {
            System.out.println("\n" + playerChampion.toString());
            Copy.baseActionChoiceCopy(actionCount);
            boolean takenAction = false;

            while (!takenAction) {
                char playerChoice = helper.askChar(scanner, "");

                // Main combat choices below 👇🏽 -------------------------
                switch (playerChoice) {
                    case 'a': {
                        takenAction = Action.playerAbilityAction(playerChampion, npcMinionWave);
                        break;
                    }

                    case 'm': {
                        takenAction = Action.playerAttackAction(playerChampion, npcMinionWave, npcNexus);
                        break;
                    }

                    case 'b': {
                        playerChampion.goToBase();
                        playerSkipNextTurn = true;
                        takenAction = true;
                        break;
                    }

                    case 'i': {
                        Copy.viewItemsCopy();
                        continue; // <----- Continue here as to not lose a round or take damage.
                    }
                    case 'p': {
                        Action.purchaseOptions(playerChampion);
                        playerSkipNextTurn = true;
                        takenAction = true;
                        break;
                    }

                    case 'q': {
                        Copy.allyWaveCopy(playerMinionWave);
                        continue;
                    }

                    case 'w': {
                        Copy.enemyWaveCopy(npcMinionWave);
                        continue;
                    }

                    case 'e': {
                        Copy.enemyChampionCopy(npcChampion);
                        continue;
                    }

                    case 'r': {
                        Copy.nexusCopy(playerNexus);
                        continue;
                    }

                    case 't': {
                        Copy.nexusCopy(npcNexus);
                        continue;
                    }

                    case 'x': {
                        Copy.quitCopy();
                        char quitConfirmation = helper.askChar(scanner, "");
                        switch (quitConfirmation) {
                            case 'x':
                                playerNexus.onDeath();
                            default:
                                continue;
                        }
                    }

                    default:
                        System.out.println("There is currently no command for: " + playerChoice);
                        continue;
                }
            }
        }
    }

    private static void npcActions(
            Nexus npcNexus,
            Nexus playerNexus,
            Champion npcChampion,
            Champion playerChampion,
            List<Minion> npcMinionWave,
            List<Minion> playerMinionWave,
            int actionCount,
            boolean npcSkipNextTurn) {
        if (npcChampion.isAlive()) {
            Random random = new Random();
            boolean takenAction = false;
            while (!takenAction) {

                int npcChoice = random.nextInt(0, 2);

                // Main combat choices below 👇🏽 -------------------------
                switch (npcChoice) {
                    case 0: {
                        takenAction = Action.npcAttackAction(npcChampion, playerMinionWave, playerNexus, random);
                        break;
                    }

                    case 1: {
                        takenAction = Action.npcAbilityAction(npcChampion, playerMinionWave, random);
                        break;
                    }
                }

            }
        }
    }

    public static void allyMinionActions(List<Minion> allyMinionWave, List<Minion> enemyMinionWave,
            Champion enemyChampion, Nexus enemyNexus) {
        for (Minion minion : allyMinionWave) {
            if (minion != null && minion.isAlive()) {
                minion.minionBehaviour(enemyMinionWave, enemyChampion, enemyNexus);
            }
        }
    }

    public static void npcMinionActions(List<Minion> enemyMinionWave, List<Minion> allyMinionWave,
            Champion playerChampion, Nexus allyNexus) {
        for (Minion minion : enemyMinionWave) {
            if (minion != null && minion.isAlive()) {
                minion.minionBehaviour(allyMinionWave, playerChampion, allyNexus);
            }
        }
    }

    public static boolean playerAttackAction(Champion playerChampion, List<Minion> npcMinionWave, Nexus npcNexus) {
        while (true) {
            if (npcMinionWave.size() > 0) {
                Copy.chooseTarget(npcMinionWave);
                int targetIdx = helper.askInt(scanner, "");
                if (targetIdx > npcMinionWave.size() || targetIdx <= 0) {
                    targetIdx = 1;
                }
                try {
                    if (npcMinionWave.get(targetIdx - 1) == null) {
                        System.out.println("No minion at given index: " + targetIdx);
                        continue;
                    }

                    Minion targetMinion = npcMinionWave.get(targetIdx - 1);
                    boolean successfulAttack = playerChampion.attack(targetMinion, npcMinionWave);
                    return successfulAttack;
                } catch (IllegalArgumentException e) {
                    System.out.println("No minion at given index: " + targetIdx);
                    continue;
                }
            } else {
                Copy.attackNexusCopy(npcNexus);
                playerChampion.attackNexus(npcNexus);
                return true;
            }
        }
    }

    public static boolean npcAttackAction(Champion npcChampion, List<Minion> playerMinionWave, Nexus playerNexus,
            Random random) {
        boolean hasAttacked = false;
        while (!hasAttacked) {
            if (playerMinionWave.size() > 0) {
                int targetIdx = random.nextInt(0, playerMinionWave.size());
                if (targetIdx > playerMinionWave.size() || targetIdx <= 0) {
                    targetIdx = 1;
                }

                try {
                    if (playerMinionWave.get(targetIdx - 1) == null) {
                        System.out.println("%s targeted incorrect index: %d".formatted(npcChampion.name(), targetIdx));
                        continue;
                    }

                    Minion targetMinion = playerMinionWave.get(targetIdx - 1);
                    boolean successfulAttack = npcChampion.attack(targetMinion, playerMinionWave);
                    return successfulAttack;
                } catch (IllegalArgumentException e) {
                    System.out.println(
                            "Unexpected error when %s tried to target %d".formatted(npcChampion.name(), targetIdx));
                }
            } else {
                Copy.attackNexusCopy(playerNexus);
                return npcChampion.attackNexus(playerNexus);
            }
        }

        return true;
    }

    public static boolean playerAbilityAction(Champion playerChampion, List<Minion> minionWave) {
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

    public static boolean npcAbilityAction(Champion npcChampion, List<Minion> playerMinionWave, Random random) {
        List<Ability> npcAbilities = npcChampion.abilities();
        boolean hasUsedAbility = false;

        while (!hasUsedAbility) {
            int abilityIndex = random.nextInt(0, npcAbilities.size());
            int targetIdx = random.nextInt(0, playerMinionWave.size());
            if (targetIdx > playerMinionWave.size() || targetIdx <= 0) {
                targetIdx = 1;
            }

            if (!Validation.validateAbilityOption(abilityIndex, npcAbilities)
                    || !Validation.validateTargetChoice(targetIdx, playerMinionWave)) {
                continue;
            }

            try {

                Minion targetMinion = playerMinionWave.get(targetIdx - 1);
                boolean successfulAttack = npcChampion.useAbility(abilityIndex, targetMinion, playerMinionWave);
                return successfulAttack;

            } catch (IllegalArgumentException e) {
                System.out.println("No minion at given index: " + targetIdx);
                continue;
            }
        }

        return true;
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
