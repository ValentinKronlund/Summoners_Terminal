package summonersTerminal.champion.Passives.Base;

import summonersTerminal.Champion;
import summonersTerminal.Stats;

public abstract class Passive
{
    public enum ePassiveType
    {
        STATS,
        REGEN,
        COMBAT,
        NONE
    }

    public final ePassiveType mType;

    protected final String mName;
    protected final Champion mChampion;
    protected String mDescription;
    protected Stats mStatsModifier;
    protected boolean mIsActive;

    public boolean Tick()
    {
        return true;
    }

    public void Init() {}

    public Passive(final String pName, final ePassiveType pPassiveType, final Champion pChampionRef, final boolean pIsActive)
    {
        mStatsModifier = new Stats(0, 0, 0, 0, 0, 0);

        this.mName = pName;
        this.mType = pPassiveType;
        this.mIsActive = pIsActive;
        this.mChampion = pChampionRef;
    }

    public void SetDescription(final String pDescription)
    {
        this.mDescription = pDescription;
    }
}