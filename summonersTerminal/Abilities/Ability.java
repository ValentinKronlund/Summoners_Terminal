package summonersTerminal.Abilities;

import summonersTerminal.Champion;
import summonersTerminal.Minion;
import summonersTerminal.Stats;

import java.util.List;

public class Ability
{
    public String mName;
    public String mDescription;
    public float mPhysicalDamageRatio = 0.0f;
    public float mMagicDamageRatio = 0.0f;
    public float mBaseDamage = 0.0f;
    public int mManaCost = 99999;

    Ability(final String pName)
    {
        this.mName = pName;
    }

    public boolean ActivateAbility(Champion pChampion, List<Minion> pTargets)
    {
        final Stats stats = pChampion.getStats();
        final float damage = mBaseDamage + stats.attackPower() * mPhysicalDamageRatio + stats.abilityPower() * mMagicDamageRatio;

        for (int i = 0; i < pTargets.size(); i++)
        {
            pTargets.get(i).takeDamage((int) damage, pTargets, pChampion);
        }

        return true;
    }
}