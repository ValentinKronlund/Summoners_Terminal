package summonersTerminal.champion.passives;

import summonersTerminal.Champion;

public class Factory
{
    public enum ePassive
    {
        THE_ROCK,
        INFINITE_POWER,
        Mundo_Does_What_He_Pleased,
        DummyPassive
    }

    public Passive Create(final ePassive pPassive, final Champion pChampionRef)
    {
        switch (pPassive)
        {
            case THE_ROCK:
            {
                return new TheRock("The rock", Passive.ePassiveType.STATS, pChampionRef, true);
            }
            case INFINITE_POWER:
                return new Dummy("Infinite Power!", Passive.ePassiveType.NONE, pChampionRef, false);
            case Mundo_Does_What_He_Pleased:
                return new Dummy("Mundo Does What He Pleased", Passive.ePassiveType.NONE, pChampionRef, false);
            default:
            {
                return new Dummy("Dummy, I do nothing", Passive.ePassiveType.NONE, pChampionRef, false);
            }
        }
    }
}