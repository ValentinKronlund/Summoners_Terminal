package summonersTerminal.champion.Passives;

import summonersTerminal.Champion;
import summonersTerminal.champion.Passives.Base.Passive;

public class InfinitePower extends Passive
{
    private  int mIncreaseAbilityPowerPerStack = 1;
    private int mIncreasedAbilityPowerStack = 0;

    public InfinitePower(final String pName, final Passive.ePassiveType pPassiveType, final Champion pChampionRef, final boolean pIsActive)
    {
        super(pName, pPassiveType, pChampionRef, pIsActive);
    }

    @Override
    public void Init()
    {
        SetDescription("\"" + GetName() + "\"" + "! Ability increase by " + mIncreaseAbilityPowerPerStack + " for every takedown.");
    }
}