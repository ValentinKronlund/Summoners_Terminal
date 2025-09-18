package summonersTerminal.champion.Passives;

import summonersTerminal.Champion;
import summonersTerminal.champion.Passives.Base.Passive;

public class InfinitePower extends Passive
{
    private int mIncreasePowerPerStack = 1;
    private int mIncreasedPowerStack = 0;

    public InfinitePower(final String pName, final Passive.ePassiveType pPassiveType, final Champion pChampionRef, final boolean pIsActive)
    {
        super(pName, pPassiveType, pChampionRef, pIsActive);
    }

    @Override
    public void Init()
    {
        SetDescription("\"" + GetName() + "\"" + "! Attack Power and Ability Power increase by " + mIncreasePowerPerStack + " for every takedown.");
    }

    @Override
    public boolean Execute()
    {
        mChampion.stats().MinusMaxAttackPower(mIncreasedPowerStack);
        mChampion.stats().MinusMaxAbilityPower(mIncreasedPowerStack);
        mChampion.stats().MinusCurrentAttackPower(mIncreasedPowerStack);
        mChampion.stats().MinusCurrentAbilityPower(mIncreasedPowerStack);

        mIncreasedPowerStack += mIncreasePowerPerStack;

        mChampion.stats().AddCurrentAttackPower(mIncreasedPowerStack);
        mChampion.stats().AddCurrentAbilityPower(mIncreasedPowerStack);
        mChampion.stats().AddMaxAttackPower(mIncreasedPowerStack);
        mChampion.stats().AddMaxAbilityPower(mIncreasedPowerStack);

        return true;
    }
}