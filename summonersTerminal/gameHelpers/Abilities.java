package summonersTerminal.gameHelpers;

import java.util.ArrayList;
import java.util.List;

import summonersTerminal.Champion;
import summonersTerminal.Minion;
import summonersTerminal.Stats;

public class Abilities {

    public static boolean ability(Champion champion, String championName, Minion target, List<Minion> minionWave) {
        Stats championStats = champion.getStats();

        switch (championName) {
            case "Garen": {
                int manaCost = 150;
                int damageTick = (int) Math.round(championStats.attackPower() * 0.15);
                int center = minionWave.indexOf(target);

                if (center < 0 || championStats.mana() < manaCost) {
                    System.out.println("\nNot enough mana to cast that spell!");
                    return false;
                }

                System.out.println("\nGaren used 💫'Judgment'💫 for " + manaCost + " mana");
                champion.useMana(manaCost);

                for (int tick = 1; tick <= 3; tick++) {
                    int aoeStart = Math.max(0, center - 2);
                    int aoeEnd = Math.min(minionWave.size() - 1, center + 2);
                    if (aoeStart > aoeEnd)
                        break; // <-- Wave empty
                    List<Minion> aoeTargets = new ArrayList<>(minionWave.subList(aoeStart, aoeEnd + 1));
                    for (Minion minion : aoeTargets) {
                        if (minion != null) {
                            minion.takeDamage(damageTick, minionWave, champion);
                        }
                    }

                    System.out.println("\n");

                    if (tick < 3) {
                        try {
                            Thread.sleep(300);
                        } catch (InterruptedException err) {
                            Thread.currentThread().interrupt();
                            break;
                        }
                    }
                }

                return true;
            }

            case "Katarina": {
                int damage = (int) Math.round((championStats.attackPower() * 0.45) + (championStats.abilityPower()));
                int manaCost = 125;
                int targetIdx = minionWave.indexOf(target);
                int aoeEnd = Math.min(minionWave.size(), targetIdx + 3); // <--- Ability hits 3 targets, since
                                                                         // toIndex in subList is exclusive

                if (targetIdx < 0 || championStats.mana() < manaCost) {
                    System.out.println("\nNot enough mana to cast that spell!");
                    return false;
                }

                System.out.println("\nKatarina used 🗡️'Bouncing Blade'🗡️ for " + manaCost + " mana");
                champion.useMana(manaCost);

                List<Minion> aoeTargets = new ArrayList<>(minionWave.subList(targetIdx, aoeEnd));
                for (Minion minion : aoeTargets) {
                    if (minion != null) {
                        System.out.println("\n");
                        minion.takeDamage(damage, minionWave, champion);
                    }
                    try {
                        Thread.sleep(400);
                    } catch (InterruptedException err) {
                        Thread.currentThread().interrupt();
                        break;
                    }

                }
                System.out.println("\n");

                return true;
            }

            case "Veigar": {
                int damage = championStats.abilityPower();
                int manaCost = 125;
                int targetIdx = minionWave.indexOf(target);
                int aoeEnd = Math.min(minionWave.size(), targetIdx + 3);

                if (targetIdx < 0 || championStats.mana() < manaCost) {
                    System.out.println("\nNot enough mana to cast that spell!");
                    return false;
                }

                System.out.println("\nVeigar used 🔮'Baleful Strike'🔮 for " + manaCost + " mana");
                champion.useMana(manaCost);

                List<Minion> aoeTargets = new ArrayList<>(minionWave.subList(targetIdx, aoeEnd));
                for (Minion minion : aoeTargets) {
                    if (minion != null) {
                        minion.takeDamage(damage, minionWave, champion);
                    }
                    try {
                        Thread.sleep(150);
                    } catch (InterruptedException err) {
                        Thread.currentThread().interrupt();
                        break;
                    }

                }
                System.out.println("\n");

                return true;
            }

            default: {
                target.takeDamage(championStats.abilityPower(), minionWave, champion);
                return false;
            }
        }
    }
}
