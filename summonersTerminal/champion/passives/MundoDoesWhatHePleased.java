package summonersTerminal.champion.Passives;

import summonersTerminal.Champion;
import summonersTerminal.champion.Passives.Base.Passive;

public class MundoDoesWhatHePleased extends Passive
{
    private int mHealthRegenPerTurn = 1000;

    public MundoDoesWhatHePleased(final String pName, final Passive.ePassiveType pPassiveType, final Champion pChampionRef, final boolean pIsActive)
    {
        super(pName, pPassiveType, pChampionRef, pIsActive);
    }

    @Override
    public void Init()
    {
        SetDescription("\"" + GetName() + "\"" + "! Regen health by " + mHealthRegenPerTurn + " every turn.");
    }

    @Override
    public boolean Tick()
    {
        if (mType == ePassiveType.REGEN)
        {
            return true;
        }

        return false;
    }
}