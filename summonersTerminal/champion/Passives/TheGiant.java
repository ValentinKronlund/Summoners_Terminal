package summonersTerminal.champion.Passives;

import summonersTerminal.champion.Champion;

public class TheGiant extends Passive {
    private final int mHealthIncrease = 200;

    public TheGiant(final String pName, final Champion pChampionRef,
            final boolean pIsActive) {
        super(pName, pChampionRef, pIsActive);
    }

    @Override
    protected void OnInit() {
        SetDescription("\"" + GetName() + "\"" + "! Increase health amount by " + mHealthIncrease + ".");
        mChampion.stats().addHealth(mHealthIncrease);
    }
}