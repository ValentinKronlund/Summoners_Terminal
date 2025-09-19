package summonersTerminal.champion.Passives;

import summonersTerminal.champion.Champion;
import summonersTerminal.champion.Passives.Base.Passive;

public class Dummy extends Passive {
    public Dummy(final String pName, final ePassiveType pPassiveType, final Champion pChampionRef,
            final boolean pIsActive) {
        super(pName, pPassiveType, pChampionRef, pIsActive);
    }

    @Override
    public void Init() {
        System.out.println("This is dummy Init!");
    }

    @Override
    public boolean Tick() {
        System.out.println("This is dummy passive!");
        return false;
    }
}