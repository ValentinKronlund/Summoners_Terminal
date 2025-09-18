package summonersTerminal;

import java.util.List;

import summonersTerminal.gameHelpers.Damage;
import summonersTerminal.gameHelpers.MinionTargetingSystem;

public final class Minion implements Target {
    String minionName;
    MinionType minionType;
    private Stats stats;
    private final int goldValue;
    private boolean _isAlive;

    public Minion(
            String uniqueIdentifier,
            MinionType minionType) {
        this.minionName = uniqueIdentifier + minionType.nameType();
        this.minionType = minionType;
        this.goldValue = minionType.goldValue();
        this._isAlive = true;

        Stats base = minionType.base();
        this.stats = new Stats(
                base.GetCurrentHealth(),
                base.GetCurrentMana(),
                base.GetCurrentArmor(),
                base.GetCurrentResistance(),
                base.GetCurrentAttackPower(),
                base.GetCurrentAbilityPower());
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
    public boolean takeDamage(int physicalDamage, int spellDamage, List<Minion> waveIAmIn, Target attackingEnemy)
    {
        int damageAmount = Damage.damageAfterReduction(physicalDamage, spellDamage, stats.GetCurrentArmor(), stats.GetCurrentResistance());

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