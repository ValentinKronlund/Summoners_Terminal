package summonersTerminal.Champions;

import summonersTerminal.Abilities.Ability;
import summonersTerminal.Abilities.Judgement;
import summonersTerminal.Champion;
import summonersTerminal.ChampionClass;

public class Garen extends Champion
{
    public Garen()
    {
        super("Garen", ChampionClass.BRAWLER);
        InitAbilities();
    }

    private void InitAbilities()
    {
        mAbilities[0] = new Judgement();
    }
}