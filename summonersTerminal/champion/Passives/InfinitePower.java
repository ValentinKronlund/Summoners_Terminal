package summonersTerminal.champion.Passives;

import summonersTerminal.Champion;
import summonersTerminal.champion.Passives.Base.Passive;

public class InfinitePower extends Passive
{
    private final int mIncreasePowerPerStack = 1;
    private int mStack = 0;

    public InfinitePower(final String pName, final Passive.ePassiveType pPassiveType, final Champion pChampionRef, final boolean pIsActive)
    {
        super(pName, pPassiveType, pChampionRef, pIsActive);
    }

    @Override
    public void Init()
    {
        SetDescription("\"" + GetName() + "\"" + "! Attack Power and Ability Power increase by " + mIncreasePowerPerStack + " for every takedown.");
        mStack = 0;
    }

    @Override
    public boolean Execute()
    {
        mChampion.stats().MinusMaxAttackPower(mStack);
        mChampion.stats().MinusMaxAbilityPower(mStack);

        mStack += mIncreasePowerPerStack;

        mChampion.stats().AddMaxAttackPower(mStack);
        mChampion.stats().AddMaxAbilityPower(mStack);

        return true;
    }
}