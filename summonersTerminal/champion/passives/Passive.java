package summonersTerminal.champion.passives;

public abstract class Passive
{
    public enum ePassiveType
    {
        STATS,
        REGEN,
        COMBAT,
        NONE
    }

    protected String mName;
    protected ePassiveType mType;
    protected boolean mIsActive;

    public Passive(final String pName, final ePassiveType pPassiveType, final boolean pIsActive)
    {
        this.mName = pName;
        this.mType = pPassiveType;
        this.mIsActive = pIsActive;
    }

    public abstract boolean Tick();
}