package summonersTerminal.champion.passives;

import summonersTerminal.Champion;

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
        SetDescription("\"" + mName + "\"" + "! Regen health by " + mHealthRegenPerTurn + " every turn.");
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