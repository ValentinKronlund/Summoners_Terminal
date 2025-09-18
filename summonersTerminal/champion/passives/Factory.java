package summonersTerminal.champion.Passives;

import summonersTerminal.Champion;
import summonersTerminal.champion.Passives.Base.Passive;

public class Factory
{
    public enum ePassive
    {
        THE_GIANT,
        INFINITE_POWER,
        Undying,
        Dummy
    }

    public Passive Create(final ePassive pPassive, final Champion pChampionRef)
    {
        switch (pPassive)
        {
            case THE_GIANT:
                return new TheGiant("The Giant", Passive.ePassiveType.STATS, pChampionRef, true);
            case INFINITE_POWER:
                return new InfinitePower("Infinite Power!", Passive.ePassiveType.NONE, pChampionRef, true);
            case Undying:
                return new Undying(ePassive.Undying.toString(), Passive.ePassiveType.NONE, pChampionRef, true);
            default:
                return new Dummy("Dummy, I do nothing", Passive.ePassiveType.NONE, pChampionRef, false);
        }
    }
}