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
    public float mBaseDamage;
    public int mManaCost;

    public boolean ActivateAbility(Stats pChampionStats, List<Minion> pTargets)
    {
        final float damage = mBaseDamage + pChampionStats.attackPower() * mPhysicalDamageRatio + pChampionStats.abilityPower() * mMagicDamageRatio;

        for (int i = 0; i < pTargets.size(); i++)
        {
            //pTargets.get(i).takeDamage(damage); //TO-DO: refactor takeDamage method
        }

        return true;
    }
}