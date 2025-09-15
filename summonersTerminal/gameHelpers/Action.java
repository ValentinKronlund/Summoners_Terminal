import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import summonersTerminal.Champion;
import summonersTerminal.Item;
import summonersTerminal.Minion;
import summonersTerminal.MinionType;
import summonersTerminal.Nexus;

public class Action
{
    static Helpers helper = new Helpers();
    static Scanner scanner = new Scanner(System.in);

    public static void generateMinionWave(List<Minion> minionWave, int waveNumber)
    {
        List<Minion> wave = new ArrayList<>();

        for (int i = 0; i < 2; i++)
        { // Add Melee minions
            Minion newMinion = new Minion(MinionType.MELEE);
            wave.add(newMinion);
        }
        for (int i = 0; i < 3; i++)
        { // Add Caster minions
            Minion newMinion = new Minion(MinionType.CASTER);
            wave.add(newMinion);
        }

        if (waveNumber % 3 == 0)
        {
            Minion newMinion = new Minion(MinionType.CANON);
            wave.add(newMinion);
        }

        for (Minion minion : wave)
        {
            minionWave.add(minion);
        }
    }

    public static boolean mainActionOptions(
            Nexus nexus,
            Champion playerChampion,
            Champion enemyChampion,
            List<Minion> minionWave,
            int playerActionCount)
    {
        while (playerActionCount < 5 & playerChampion.getIsDead() == false)
        {
            Copy.baseActionChoiceCopy(playerActionCount);

            try
            {
                char playerChoice = helper.askChar(scanner, "");

                // Main combat choices below 👇🏽 -------------------------
                switch (playerChoice)
                {
                    case 'a':
                    {
                        boolean successfulAttack = Action.abilityAction(playerChampion, minionWave);
                        if (successfulAttack)
                            playerActionCount++;
                        break;
                    }

                    case 'm':
                    {
                        Action.attackAction(playerChampion, minionWave, nexus);
                        playerActionCount++;
                        break;
                    }

                    case 'b':
                    {
                        playerChampion.goToBase();
                        playerActionCount += 2;
                        break;
                    }

                    case 'i':
                    {
                        Copy.viewItemsCopy();
                        continue; // <----- Continue here as to not lose a round or take damage.
                    }
                    case 'p':
                    {
                        Action.purchaseOptions(playerChampion);
                        playerActionCount += 2;
                        break;
                    }

                    case 's':
                    {
                        System.out.println("\n" + playerChampion.toString());
                        continue; // <----- Continue here as to not lose a round or take damage.
                    }

                    case 'e':
                    {
                        System.out.println("\n" + enemyChampion.toString());
                        continue; // <----- Continue here as to not lose a round or take damage.
                    }

                    case 'w':
                    {
                        Copy.waveCopy(minionWave);
                        continue; // <----- Continue here as to not lose a round or take damage.
                    }

                    case 'q':
                    {
                        Copy.quitCopy();
                        char quitConfirmation = helper.askChar(scanner, "");
                        switch (quitConfirmation)
                        {
                            case 'q':
                                return true;
                            default:
                                continue;
                        }
                    }

                    default:
                        System.out.println("There is currently no command for: " + playerChoice);
                        continue;
                }
            } catch (AssertionError err)
            {
                System.out.println(err);
                continue;
            }

            if (nexus.isDestroyed)
            {
                return true;
            }

            if (playerChampion.getInBase() == false)
            {
                for (Minion minion : minionWave)
                {
                    if (playerChampion.getIsDead() == true)
                    {
                        break;
                    }
                    playerChampion.takeDamage(minion.attack());
                }
            }

            if (playerChampion.getIsDead() == true)
            {
                break;
            }

        }

        return false;
    }

    public static boolean attackAction(Champion playerChampion, List<Minion> minionWave, Nexus nexus)
    {
        while (true)
        {
            if (minionWave.size() > 0)
            {
                Copy.chooseTarget(minionWave);
                int targetIdx = helper.askInt(scanner, "");
                if (targetIdx > minionWave.size() || targetIdx < 0)
                    targetIdx = minionWave.size() - 1;
                try
                {
                    if (minionWave.get(targetIdx - 1) == null)
                    {
                        System.out.println("No minion at given index: " + targetIdx);
                        continue;
                    }

                    Minion targetMinion = minionWave.get(targetIdx - 1);
                    boolean successfulAttack = playerChampion.attack(targetMinion, minionWave);
                    return successfulAttack;
                } catch (IllegalArgumentException e)
                {
                    System.out.println("No minion at given index: " + targetIdx);
                    continue;
                }
            } else
            {
                Copy.attackNexusCopy(nexus);
                playerChampion.attackNexus(nexus);
                return true;
            }
        }
    }

    public static boolean abilityAction(Champion playerChampion, List<Minion> availableTargets)
    {
        // 1. Player has chosen to use an ability -> Champion -> List<Targets> (minion wave for now)
        // 2. Player chooses which ability to use -> Ability index
        // 3. The ability gets passed list of targets, asks for target index. -> Champion, List<Targets>
        // 4. The ability decides depending on target, how to distribute damage across list


        return playerChampion.ability(availableTargets);
    }
}

public static void purchaseOptions(Champion champion)
{
    while (true)
    {
        int option = helper.askInt(scanner,
                "\nWhat item would you like to purchase?"
                        + "\n[1] " + Item.THORN_MAIL.toString() +
                        "\n[2] " + Item.ROD_OF_AGES.toString() +
                        "\n[3] " + Item.INFINITY_EDGE.toString() +
                        "\n[4] " + Item.RABADONS_DEATHCAP.toString());

        switch (option)
        {
            case 1:
            {
                champion.equip(Item.THORN_MAIL);
                return;
            }
            case 2:
            {
                champion.equip(Item.ROD_OF_AGES);
                return;
            }
            case 3:
            {
                champion.equip(Item.INFINITY_EDGE);
                return;
            }
            case 4:
            {
                champion.equip(Item.RABADONS_DEATHCAP);
                return;
            }
            default:
            {
                System.out.println("There is no item at the given index of: " + option);
                continue;
            }
        }

    }

}