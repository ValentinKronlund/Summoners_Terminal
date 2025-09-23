package summonersTerminal.champion.Passives;

import summonersTerminal.champion.Champion;

public class Dummy extends Passive {
    public Dummy(final String pName, final ePassiveType pPassiveType, final Champion pChampionRef,
            final boolean pIsActive) {
        super(pName, pPassiveType, pChampionRef, pIsActive);
    }

    @Override
    protected void OnInit() {
        System.out.println("This is dummy Init!");
    }

    @Override
    protected boolean OnTick() {
        System.out.println("This is dummy passive!");
        return false;
    }
}