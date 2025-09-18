package summonersTerminal.champion.abilities;

import java.util.ArrayList;
import java.util.List;

import summonersTerminal.Minion;
import summonersTerminal.Stats;

public final class AbilityKits {
    private AbilityKits() {
    }

    public static Ability garenJudgment() {
        return new SimpleAbility("Judgment", 150, (self, target, originalWave) -> {
            Stats championStats = self.stats();

            int center = originalWave.indexOf(target);
            int damageTick = (int) Math.round(championStats.getAttackPower() * 0.4);

            System.out.println("\nGaren used 💫'Judgment'💫");

            for (int tick = 1; tick <= 3; tick++) {
                int aoeStart = Math.max(0, center - 2);
                int aoeEnd = Math.min(originalWave.size() - 1, center + 2);
                if (aoeStart > aoeEnd) {
                    break;
                }

                List<Minion> aoeTargets = new ArrayList<>(originalWave.subList(aoeStart, aoeEnd + 1));
                for (Minion minion : aoeTargets) {
                    if (minion != null) {
                        minion.takeDamage(damageTick, 0, originalWave, self);
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
        });
    }

    public static Ability katarinaBouncingBlade() {
        return new SimpleAbility("Bouncing Blade", 125, (self, target, originalWave) -> {
            Stats championStats = self.stats();

            int physicalDamage = (int) Math.round((championStats.getAttackPower() * 0.45));
            int spellDamage = (int) championStats.getAbilityPower();
            int targetIdx = originalWave.indexOf(target);
            int aoeEnd = Math.min(originalWave.size(), targetIdx + 3); // <--- Ability hits 3 targets, since toIndex in
                                                                       // subList
            // is exclusive
            System.out.println("\nKatarina used 🗡️'Bouncing Blade'🗡️");
            System.out.println("\n🗡️ " + physicalDamage + " 🔮 " + spellDamage);

            List<Minion> aoeTargets = new ArrayList<>(originalWave.subList(targetIdx, aoeEnd));

            for (Minion minion : aoeTargets) {
                if (minion != null) {
                    System.out.println("\n");
                    minion.takeDamage(physicalDamage, spellDamage, originalWave, self);
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
        });

    }

    public static Ability veigarBalefulStrike() {
        return new SimpleAbility("Bouncing Blade", 125, (self, target, originalWave) -> {
            Stats championStats = self.stats();
            int spellDamage = (int) championStats.getAbilityPower();
            int targetIdx = originalWave.indexOf(target);
            int aoeEnd = Math.min(originalWave.size(), targetIdx + 3);

            System.out.println("\nVeigar used 🔮'Baleful Strike'🔮");

            List<Minion> aoeTargets = new ArrayList<>(originalWave.subList(targetIdx, aoeEnd));
            for (Minion minion : aoeTargets) {
                if (minion != null) {
                    minion.takeDamage(0, spellDamage, originalWave, self);
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
        });

    }

}
