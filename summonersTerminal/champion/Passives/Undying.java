package summonersTerminal.champion.Passives;

import summonersTerminal.Champion;
import summonersTerminal.champion.Passives.Base.Passive;

public class Undying extends Passive {
    private int mHealthRegenPerTurn = 40;

    public Undying(final String pName, final Passive.ePassiveType pPassiveType, final Champion pChampionRef,
            final boolean pIsActive) {
        super(pName, pPassiveType, pChampionRef, pIsActive);
    }

    @Override
    public void Init() {
        SetDescription("\"" + GetName() + "\"" + ". Regen health by " + mHealthRegenPerTurn + " every turn.");
    }

    @Override
    public boolean Tick() {
        mChampion.stats().AddCurrentHealth(mHealthRegenPerTurn * mChampion.level());
        return true;
    }
}