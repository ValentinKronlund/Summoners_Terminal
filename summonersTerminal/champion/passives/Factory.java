package summonersTerminal.champion.Passives;

import summonersTerminal.Champion;
import summonersTerminal.champion.Passives.Base.Passive;

public class Factory
{
    public enum ePassive
    {
        THE_GIANT,
        INFINITE_POWER,
        Mundo_Does_What_He_Pleased,
        Dummy
    }

    public Passive Create(final ePassive pPassive, final Champion pChampionRef)
    {
        switch (pPassive)
        {
            case THE_GIANT:
                return new TheGiant("The Giant", Passive.ePassiveType.STATS, pChampionRef, true);
            case INFINITE_POWER:
                return new Dummy("Infinite Power!", Passive.ePassiveType.NONE, pChampionRef, false);
            case Mundo_Does_What_He_Pleased:
                return new MundoDoesWhatHePleased("Mundo Does What He Pleased", Passive.ePassiveType.NONE, pChampionRef, false);
            default:
                return new Dummy("Dummy, I do nothing", Passive.ePassiveType.NONE, pChampionRef, false);
        }
    }
}