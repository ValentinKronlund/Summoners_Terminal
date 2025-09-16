package summonersTerminal.champion.abilities;

import java.util.ArrayList;
import java.util.List;

import summonersTerminal.Minion;
import summonersTerminal.Stats;

public final class AbilityKits {
    private AbilityKits() {
    }

    public static Ability garenJudgment() {
        return new SimpleAbility("Judgment", 150, (self, target, wave) -> {
            Stats championStats = self.stats();
            int center = wave.indexOf(target);
            int damageTick = (int) Math.round(championStats.attackPower() * 0.4);

            System.out.println("\nGaren used 💫'Judgment'💫");

            for (int tick = 1; tick <= 3; tick++) {
                int aoeStart = Math.max(0, center - 2);
                int aoeEnd = Math.min(wave.size() - 1, center + 2);
                if (aoeStart > aoeEnd) {
                    break;
                }

                List<Minion> aoeTargets = new ArrayList<>(wave.subList(aoeStart, aoeEnd + 1));
                for (Minion minion : aoeTargets) {
                    if (minion != null) {
                        minion.takeDamage(damageTick, wave, self);
                    }
                }

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
        return new SimpleAbility("Bouncing Blade", 125, (self, target, wave) -> {
            Stats championStats = self.stats();
            int damage = (int) Math.round((championStats.attackPower() * 0.45) + (championStats.abilityPower()));
            int targetIdx = wave.indexOf(target);
            int aoeEnd = Math.min(wave.size(), targetIdx + 3); // <--- Ability hits 3 targets, since toIndex in subList
                                                               // is exclusive

            System.out.println("\nKatarina used 🗡️'Bouncing Blade'🗡️");

            List<Minion> aoeTargets = new ArrayList<>(wave.subList(targetIdx, aoeEnd));
            for (Minion minion : aoeTargets) {
                if (minion != null) {
                    System.out.println("\n");
                    minion.takeDamage(damage, wave, self);
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
        return new SimpleAbility("Bouncing Blade", 125, (self, target, wave) -> {
            Stats championStats = self.stats();
            int damage = championStats.abilityPower();
            int targetIdx = wave.indexOf(target);
            int aoeEnd = Math.min(wave.size(), targetIdx + 3);

            System.out.println("\nVeigar used 🔮'Baleful Strike'🔮");

            List<Minion> aoeTargets = new ArrayList<>(wave.subList(targetIdx, aoeEnd));
            for (Minion minion : aoeTargets) {
                if (minion != null) {
                    minion.takeDamage(damage, wave, self);
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
