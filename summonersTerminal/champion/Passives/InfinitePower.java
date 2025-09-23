package summonersTerminal.champion.Passives;

import summonersTerminal.champion.Champion;
import summonersTerminal.minion.Minion;

public class InfinitePower extends Passive {
    private final int mIncreasePowerPerStack = 5;
    private int mStack = 0;

    public InfinitePower(final String pName, final Passive.ePassiveType pPassiveType, final Champion pChampionRef,
            final boolean pIsActive) {
        super(pName, pPassiveType, pChampionRef, pIsActive);
    }

    @Override
    protected void OnInit() {
        SetDescription("\"" + GetName() + "\"" + "! Attack Power and Ability Power increase by "
                + mIncreasePowerPerStack + " for every takedown.");
        mStack = 0;
    }

    @Override
    protected boolean OnExecute(Object... pObjects) {

        if (pObjects.length > 0 && pObjects[0] instanceof Minion) {
            mStack++;
            mChampion.stats().addAttackPower(mIncreasePowerPerStack);
            mChampion.stats().addAbilityPower(mIncreasePowerPerStack);
            return true;
        }

        return false;
    }
}