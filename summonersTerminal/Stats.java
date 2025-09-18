package summonersTerminal;

public class Stats
{
    private int mMaxHealth = 0;
    private int mMaxMana = 0;
    private int mMaxArmor = 0;
    private int mMaxResistance = 0;
    private int mMaxAttackPower = 0;
    private int mMaxAbilityPower = 0;

    private int mCurrentHealth = 0;
    private int mCurrentMana = 0;
    private int mCurrentArmor = 0;
    private int mCurrentResistance = 0;
    private int mCurrentAttackPower = 0;
    private int mCurrentAbilityPower = 0;

    public static final Stats ZERO = new Stats(0, 0, 0, 0, 0, 0);

    public Stats()
    {
        RestoreToMax();
    }

    public Stats(final int pHealth, final int pMana, final int pArmor, final int pResistance, final int pAttackPower, final int pAbilityPower)
    {
        this.mMaxHealth = pHealth;
        this.mMaxMana = pMana;
        this.mMaxArmor = pArmor;
        this.mMaxResistance = pResistance;
        this.mMaxAttackPower = pAttackPower;
        this.mMaxAbilityPower = pAbilityPower;

        RestoreToMax();
    }

    public void RestoreToMax()
    {
        this.mCurrentHealth = this.mMaxHealth;
        this.mCurrentMana = this.mMaxMana;
        this.mCurrentArmor = this.mMaxArmor;
        this.mCurrentResistance = this.mMaxResistance;
        this.mCurrentAttackPower = this.mMaxAttackPower;
        this.mCurrentAbilityPower = this.mMaxAbilityPower;
    }

    @Override
    public String toString()
    {
        return "HP:%d/%d | MANA:%d/%d | ARM:%d | RES:%d | ATK:%d | ABP:%d".formatted(
                mCurrentHealth, mMaxHealth,
                mCurrentMana, mMaxMana,
                mCurrentArmor,
                mCurrentResistance,
                mCurrentAttackPower,
                mCurrentAbilityPower
        );
    }

    @Override
    public boolean equals(Object pObject)
    {
        if (this == pObject)
            return true;

        if (!(pObject instanceof Stats stats))
            return false;

        return mCurrentHealth == stats.mCurrentHealth &&
                mCurrentMana == stats.mCurrentMana &&
                mCurrentArmor == stats.mCurrentArmor &&
                mCurrentResistance == stats.mCurrentResistance &&
                mCurrentAttackPower == stats.mCurrentAttackPower &&
                mCurrentAbilityPower == stats.mCurrentAbilityPower &&

                mMaxHealth == stats.mMaxHealth &&
                mMaxMana == stats.mMaxMana &&
                mMaxArmor == stats.mMaxArmor &&
                mMaxResistance == stats.mMaxResistance &&
                mMaxAttackPower == stats.mMaxAttackPower &&
                mMaxAbilityPower == stats.mMaxAbilityPower;
    }

    public void AddCurrentStats(final Stats pCurrentStatsModifier)
    {
        this.mCurrentHealth += pCurrentStatsModifier.mCurrentHealth;
        this.mCurrentMana += pCurrentStatsModifier.mCurrentMana;
        this.mCurrentArmor += pCurrentStatsModifier.mCurrentArmor;
        this.mCurrentResistance += pCurrentStatsModifier.mCurrentResistance;
        this.mCurrentAttackPower += pCurrentStatsModifier.mCurrentAttackPower;
        this.mCurrentAbilityPower += pCurrentStatsModifier.mCurrentAbilityPower;
    }

    public void AddMaxStats(final Stats pMaxStatsModifier)
    {
        this.mMaxHealth += pMaxStatsModifier.mMaxHealth;
        this.mMaxMana += pMaxStatsModifier.mMaxMana;
        this.mMaxArmor += pMaxStatsModifier.mMaxArmor;
        this.mMaxResistance += pMaxStatsModifier.mMaxResistance;
        this.mMaxAttackPower += pMaxStatsModifier.mMaxAttackPower;
        this.mMaxAbilityPower += pMaxStatsModifier.mMaxAbilityPower;
    }

    public void AddCurrentHealth(final int pHealth)
    {
        mCurrentHealth += pHealth;
    }

    public void AddCurrentMana(final int pMana)
    {
        mCurrentMana += pMana;
    }

    public void AddCurrentArmor(final int pArmor)
    {
        mCurrentArmor += pArmor;
    }

    public void AddCurrentResistance(final int pResistance)
    {
        mCurrentResistance += pResistance;
    }

    public void AddCurrentAttackPower(final int pAttackPower)
    {
        mCurrentAttackPower += pAttackPower;
    }

    public void AddCurrentAbilityPower(final int pAbilityPower)
    {
        mCurrentAbilityPower += pAbilityPower;
    }

    public void MinusCurrentHealth(final int pHealth)
    {
        mCurrentHealth -= pHealth;
    }

    public void MinusCurrentMana(final int pMana)
    {
        mCurrentMana -= pMana;
    }

    public void MinusCurrentArmor(final int pArmor)
    {
        mCurrentArmor -= pArmor;
    }

    public void MinusCurrentResistance(final int pResistance)
    {
        mCurrentResistance -= pResistance;
    }

    public void MinusCurrentAttackPower(final int pAttackPower)
    {
        mCurrentAttackPower -= pAttackPower;
    }

    public void MinusCurrentAbilityPower(final int pAbilityPower)
    {
        mCurrentAbilityPower -= pAbilityPower;
    }

    public void MinusCurrentStats(final Stats pCurrentStatsModifier)
    {
        this.mCurrentHealth -= pCurrentStatsModifier.mCurrentHealth;
        this.mCurrentMana -= pCurrentStatsModifier.mCurrentMana;
        this.mCurrentArmor -= pCurrentStatsModifier.mCurrentArmor;
        this.mCurrentResistance -= pCurrentStatsModifier.mCurrentResistance;
        this.mCurrentAttackPower -= pCurrentStatsModifier.mCurrentAttackPower;
        this.mCurrentAbilityPower -= pCurrentStatsModifier.mCurrentAbilityPower;
    }

    public void MinusMaxStats(final Stats pMaxStatsModifier)
    {
        this.mMaxHealth -= pMaxStatsModifier.mMaxHealth;
        this.mMaxMana -= pMaxStatsModifier.mMaxMana;
        this.mMaxArmor -= pMaxStatsModifier.mMaxArmor;
        this.mMaxResistance -= pMaxStatsModifier.mMaxResistance;
        this.mMaxAttackPower -= pMaxStatsModifier.mMaxAttackPower;
        this.mMaxAbilityPower -= pMaxStatsModifier.mMaxAbilityPower;
    }

    public void SetMaxStats(final Stats pMaxStats)
    {
        this.mMaxHealth = pMaxStats.mMaxHealth;
        this.mMaxMana = pMaxStats.mMaxMana;
        this.mMaxArmor = pMaxStats.mMaxArmor;
        this.mMaxResistance = pMaxStats.mMaxResistance;
        this.mMaxAttackPower = pMaxStats.mMaxAttackPower;
        this.mMaxAbilityPower = pMaxStats.mMaxAbilityPower;
    }

    public void SetCurrentStats(final Stats pCurrentStats)
    {
        this.mCurrentHealth = pCurrentStats.mCurrentHealth;
        this.mCurrentMana = pCurrentStats.mCurrentMana;
        this.mCurrentArmor = pCurrentStats.mCurrentArmor;
        this.mCurrentResistance = pCurrentStats.mCurrentResistance;
        this.mCurrentAttackPower = pCurrentStats.mCurrentAttackPower;
        this.mCurrentAbilityPower = pCurrentStats.mCurrentAbilityPower;
    }

    public void SetMaxHealth(final int pMaxHealth)
    {
        mMaxHealth = pMaxHealth;
    }

    public void SetMaxMana(final int pMaxMana)
    {
        mMaxMana = pMaxMana;
    }

    public void SetMaxArmor(final int pMaxArmor)
    {
        mMaxArmor = pMaxArmor;
    }

    public void SetMaxResistance(final int pMaxResistance)
    {
        mMaxResistance = pMaxResistance;
    }

    public void SetMaxAttackPower(final int pMaxAttackPower)
    {
        mMaxAttackPower = pMaxAttackPower;
    }

    public void SetMaxAbilityPower(final int pMaxAbilityPower)
    {
        mMaxAbilityPower = pMaxAbilityPower;
    }

    public void SetCurrentHealth(final int pCurrentHealth)
    {
        mCurrentHealth = pCurrentHealth;
    }

    public void SetCurrentMana(final int pCurrentMana)
    {
        mCurrentMana = pCurrentMana;
    }

    public void SetCurrentArmor(final int pCurrentArmor)
    {
        mCurrentArmor = pCurrentArmor;
    }

    public void SetCurrentResistance(final int pCurrentResistance)
    {
        mCurrentResistance = pCurrentResistance;
    }

    public void SetCurrentAttackPower(final int pCurrentAttackPower)
    {
        mCurrentAttackPower = pCurrentAttackPower;
    }

    public void SetCurrentAbilityPower(final int pCurrentAbilityPower)
    {
        mCurrentAbilityPower = pCurrentAbilityPower;
    }

    public int GetCurrentHealth()
    {
        return mCurrentHealth;
    }

    public int GetCurrentMana()
    {
        return mCurrentMana;
    }

    public int GetCurrentArmor()
    {
        return mCurrentArmor;
    }

    public int GetCurrentResistance()
    {
        return mCurrentResistance;
    }

    public int GetCurrentAttackPower()
    {
        return mCurrentAttackPower;
    }

    public int GetCurrentAbilityPower()
    {
        return mCurrentAbilityPower;
    }

    public int GetMaxHealth()
    {
        return mMaxHealth;
    }

    public int GetMaxMana()
    {
        return mMaxMana;
    }

    public int GetMaxArmor()
    {
        return mMaxArmor;
    }

    public int GetMaxResistance()
    {
        return mMaxResistance;
    }

    public int GetMaxAttackPower()
    {
        return mMaxAttackPower;
    }

    public int GetMaxAbilityPower()
    {
        return mMaxAbilityPower;
    }
}