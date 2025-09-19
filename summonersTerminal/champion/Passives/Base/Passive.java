package summonersTerminal.champion.Passives.Base;

import summonersTerminal.Stats;
import summonersTerminal.champion.Champion;

public abstract class Passive {
    public enum ePassiveType {
        STATS,
        REGEN,
        COMBAT,
        NONE
    }

    public final ePassiveType mType;

    private final String mName;
    protected final Champion mChampion;
    protected Stats mStatsModifier;
    protected boolean mIsActive;

    private String mDescription;

    public void Init() {
    }

    public boolean Tick() {
        return true;
    }

    public boolean Execute() {
        return true;
    }

    public Passive(final String pName, final ePassiveType pPassiveType, final Champion pChampionRef,
            final boolean pIsActive) {
        mStatsModifier = new Stats(0, 0, 0, 0, 0, 0);

        this.mName = pName;
        this.mType = pPassiveType;
        this.mIsActive = pIsActive;
        this.mChampion = pChampionRef;
    }

    public void SetDescription(final String pDescription) {
        this.mDescription = pDescription;
    }

    public String GetName() {
        return this.mName;
    }

    public String GetDescription() {
        return this.mDescription;
    }
}