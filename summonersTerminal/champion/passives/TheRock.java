package summonersTerminal.champion.passives;

public class TheRock extends Passive
{
    public TheRock(final String pName,final ePassiveType pPassiveType, final boolean pIsActive)
    {
        super(pName, pPassiveType, pIsActive);
    }

    public boolean Tick()
    {
        return true;
    }
}
