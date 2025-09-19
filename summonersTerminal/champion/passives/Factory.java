package summonersTerminal.champion.Passives;

import summonersTerminal.champion.Champion;
import summonersTerminal.champion.Passives.Base.Passive;

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
                return new TheGiant("The Giant", Passive.ePassiveType.STATS, pChampionRef, true);
            case INFINITE_POWER:
                return new InfinitePower("Infinite Power!", Passive.ePassiveType.COMBAT, pChampionRef, true);
            case UNDYING:
                return new Undying(ePassive.UNDYING.toString(), Passive.ePassiveType.NONE, pChampionRef, true);
            default:
                return new Dummy("Dummy, I do nothing", Passive.ePassiveType.NONE, pChampionRef, false);
        }
    }
}