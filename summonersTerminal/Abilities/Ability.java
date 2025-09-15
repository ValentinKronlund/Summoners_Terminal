package summonersTerminal.Abilities;

import summonersTerminal.Minion;
import summonersTerminal.Stats;

import java.util.List;

public class Ability
{
    public String mName;
    public String mDescription;
    public float mPhysicalDamageRatio;
    public float mMagicDamageRatio;
    public int mBaseDamage;
    public int mManaCost;

    public boolean ActivateAbility(Stats pChampionStats, List<Minion> pTargets)
    {
        System.out.println("This is base method, please use override!");
        return false;
    }
}