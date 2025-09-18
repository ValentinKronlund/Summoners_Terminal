package summonersTerminal.champion.Passives;

import summonersTerminal.Champion;
import summonersTerminal.Stats;
import summonersTerminal.champion.Passives.Base.Passive;

public class TheGiant extends Passive {
    private final int mHealthIncrease = 200;

    public TheGiant(final String pName, final ePassiveType pPassiveType, final Champion pChampionRef,
            final boolean pIsActive) {
        super(pName, pPassiveType, pChampionRef, pIsActive);
    }

    @Override
    public void Init() {
        SetDescription("\"" + GetName() + "\"" + "! Increase health amount by " + mHealthIncrease + ".");

        final Stats stats = new Stats(mHealthIncrease, 0, 0, 0, 0, 0);
        mChampion.stats().AddMaxStats(stats);
    }
}