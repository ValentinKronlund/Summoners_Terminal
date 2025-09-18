package summonersTerminal.champion.Passives;

import summonersTerminal.Champion;
import summonersTerminal.Stats;
import summonersTerminal.champion.Passives.Base.Passive;

public class TheRock extends Passive
{
    private final int mHealthIncrease = 1000;

    public TheRock(final String pName, final ePassiveType pPassiveType, final Champion pChampionRef, final boolean pIsActive)
    {
        super(pName, pPassiveType, pChampionRef, pIsActive);
    }

    @Override
    public void Init()
    {
        SetDescription("\"" + mName + "\"" + "! Increase health amount by " + mHealthIncrease + ".");

        Stats stats = new Stats(mHealthIncrease, 0, 0, 0, 0, 0);
        mStatsModifier = mStatsModifier.plus(stats);

        mChampion.increaseStats(mStatsModifier);
    }
}