package summonersTerminal.champion.Passives;

import summonersTerminal.champion.Champion;

public abstract class Passive
{
    protected boolean mIsActive;
    protected final Champion mChampion;

    private String mDescription;
    private final String mName;

    public Passive(final String pName, final Champion pChampionRef,
                   final boolean pIsActive)
    {
        mName = pName;
        mIsActive = pIsActive;
        mChampion = pChampionRef;
    }

    public final void Init()
    {
        OnInit();
    }

    public final boolean Tick()
    {
        if (mIsActive)
        {
            return OnTick();
        }

        return false;
    }

    public final boolean Execute(Object... pArgs)
    {
        if (mIsActive)
        {
            return OnExecute(pArgs);
        }

        return false;
    }

    protected void OnInit()
    {
    }

    protected boolean OnTick()
    {
        return true;
    }

    protected boolean OnExecute(Object... pArgs)
    {
        return true;
    }

    public void SetDescription(final String pDescription)
    {
        mDescription = pDescription;
    }

    public String GetName()
    {
        return mName;
    }

    public String GetDescription()
    {
        return mDescription;
    }
}