package summonersTerminal.minion;

import java.util.List;
import summonersTerminal.Nexus;
import summonersTerminal.Stats;
import summonersTerminal.Target;
import summonersTerminal.champion.Champion;
import summonersTerminal.champion.Passives.Base.Passive;
import summonersTerminal.gameHelpers.Damage;
import summonersTerminal.gameHelpers.MinionTargetingSystem;

public final class Minion implements Target {
    String minionName;
    MinionType minionType;
    private final Stats stats;
    private final int goldValue;
    private boolean _isAlive;
    private int _level;

    public Minion(
            String uniqueIdentifier,
            MinionType minionType,
            int level) {
        this.minionType = minionType;
        this.stats = new Stats(
                minionType.base().GetMaxHealth(),
                minionType.base().GetMaxMana(),
                minionType.base().GetMaxArmor(),
                minionType.base().GetMaxResistance(),
                minionType.base().GetMaxAttackPower(),
                minionType.base().GetMaxAbilityPower());
        this.goldValue = minionType.goldValue();
        this._isAlive = true;
        this._level = level;
        this.minionName = "%s%s (Lvl: %d)".formatted(uniqueIdentifier, minionType.nameType(), _level);

        minionStatsPerLevel(_level);
    }

    public void minionStatsPerLevel(int level) {
        for (int i = 1; i < level; i++) {
            stats.AddMaxStats(minionType.growthPerCycle());
        }
        stats.RestoreToMax();
    }

    public void minionBehaviour(List<Minion> enemyWave, Champion enemyChampion, Nexus nexus) {
        Target enemyTarget = MinionTargetingSystem.targetPriority(enemyWave, enemyChampion, nexus);
        if (enemyTarget != null && enemyTarget.isAlive()) {
            this.attack(enemyTarget, enemyWave);
        }

    }

    private void attack(Target target, List<Minion> waveIAmIn) {
        int physicalDamage = this.stats.GetCurrentAttackPower();
        if (target instanceof Minion) {
            target.takeDamage(physicalDamage, 0, waveIAmIn, this);
            return;
        }
        target.takeDamage(physicalDamage, 0);

    }

    @Override

    public boolean takeDamage(int physicalDamage, int spellDamage, List<Minion> waveIAmIn, Target attackingEnemy) {
        int damageAmount = Damage.damageAfterReduction(physicalDamage, spellDamage, stats.GetCurrentArmor(),
                stats.GetCurrentResistance());

        this.stats.MinusCurrentHealth(damageAmount);

        int currHealth = this.stats.GetCurrentHealth();

        String dmgString = "%s has taken %d damage! | HP: %d".formatted(minionName, damageAmount, currHealth);

        if (currHealth <= 0) {
            if (attackingEnemy instanceof Champion ch) {
                deathByChampion(waveIAmIn, ch, dmgString);
            } else {
                deathByNPC(waveIAmIn, dmgString);
            }
        } else {
            System.out.println(dmgString);
        }

        return true;
    }

    private void deathByChampion(
            List<Minion> waveIAmIn,
            Champion champion,
            String dmgString) {
        String deathString = "%s | %s has died! %s has been awarded with %d🪙".formatted(dmgString, minionName,
                champion.name(), goldValue);

        System.out.println(deathString);

        this._isAlive = false;
        waveIAmIn.remove(this);
        champion.addGold(goldValue);

        if (champion.getPassive().mType == Passive.ePassiveType.COMBAT) {
            champion.getPassive().Execute();
        }
    }

    private void deathByNPC(
            List<Minion> waveIAmIn,
            String dmgString) {
        String deathString = "%s | %s has died!".formatted(dmgString, minionName);
        System.out.println(deathString);
        this._isAlive = false;
        waveIAmIn.remove(this);
    }

    @Override
    public String name() {
        return minionName;
    }

    @Override
    public boolean isAlive() {
        return _isAlive;
    }

    public MinionType getMinionType() {
        return this.minionType;
    }

    @Override
    public String toString() {
        return "%s HP:%d | Gold Value: %d".formatted(minionName, stats.GetCurrentHealth(), goldValue);
    }
}