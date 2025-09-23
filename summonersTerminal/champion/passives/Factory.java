package summonersTerminal.champion.Passives;

import summonersTerminal.champion.Champion;

public class Factory {
    public enum ePassive {
        THE_GIANT,
        INFINITE_POWER,
        UNDYING,
        DUMMY
    }

    public Passive Create(final ePassive pPassive, final Champion pChampionRef) {
        switch (pPassive) {
            case THE_GIANT:
                return new TheGiant("The Giant", pChampionRef, true);
            case INFINITE_POWER:
                return new InfinitePower("Infinite Power!", pChampionRef, true);
            case UNDYING:
                return new Undying("Undying", pChampionRef, true);
            default:
                return new Dummy("Dummy, I do nothing", pChampionRef, false);
        }
    }
}