package summonersTerminal.champion.Passives;

import summonersTerminal.champion.Champion;

public class Dummy extends Passive {
    public Dummy(final String pName, final Champion pChampionRef,
            final boolean pIsActive) {
        super(pName, pChampionRef, pIsActive);
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