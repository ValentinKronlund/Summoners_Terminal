package summonersTerminal;

import java.util.List;

public class Minion {
    static int indexId;
    String minionName;
    MinionType minionType;
    private Stats stats;
    private int goldValue;

    public Minion(
            int indexId,
            MinionType minionType) {
        this.minionName = minionType.nameType() + "[" + indexId + "]";
        this.minionType = minionType;
        this.goldValue = minionType.goldValue();

        Stats base = minionType.base();
        this.stats = new Stats(
                base.health(),
                base.mana(),
                base.armor(),
                base.resistance(),
                base.attackPower(),
                base.abilityPower());
    }

    private void onDeath(
            List<Minion> wave,
            Champion champion,
            String takenDamageString) {
        String minionDeathString = minionName + " has died! "
                + champion.championName + " has been awarded with "
                + goldValue + "🪙";

        System.out.println(
                takenDamageString + " | " + minionDeathString);

        wave.remove(this);
        champion.addGold(goldValue);
    }

    public int attack() {
        return this.stats.attackPower();
    }

    public boolean takeDamage(int damageAmount, List<Minion> wave, Champion champion) {
        try {
            this.stats = this.stats.minus(new Stats(damageAmount, 0, 0, 0, 0, 0));

            String takenDamageString = this.minionName + " Minion has taken " + damageAmount + " damage!" + " | HP: "
                    + this.stats.health();

            if (this.stats.health() <= 0) {
                onDeath(wave, champion, takenDamageString);
            } else {
                System.out.println(takenDamageString);
            }

            return true;
        } catch (Exception e) {
            System.out.println("Some spooky shit happened when minion tried to take damage 👻");
            return false;
        }
    }

    @Override
    public String toString() {
        return "%s HP:%d | Gold Value: %d".formatted(minionName, stats.health(), goldValue);
    }
}
