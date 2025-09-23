package summonersTerminal.champion.Passives;

import summonersTerminal.champion.Champion;

public class Undying extends Passive {
    private int mHealthRegenPerTurn = 40;

    public Undying(final String pName, final Passive.ePassiveType pPassiveType, final Champion pChampionRef,
            final boolean pIsActive) {
        super(pName, pPassiveType, pChampionRef, pIsActive);
    }

    @Override
    protected void OnInit() {
        SetDescription("\"" + GetName() + "\"" + ". Regen health by " + mHealthRegenPerTurn + " every turn.");
    }

    @Override
    protected boolean OnTick() {
        mChampion.stats().addCurrentHealth(mHealthRegenPerTurn * mChampion.level());
        return true;
    }
}