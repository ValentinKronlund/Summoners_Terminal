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
        this.mMaxHealth = 100;
        this.mMaxMana = 100;
        this.mMaxArmor = 30;
        this.mMaxResistance = 30;
        this.mMaxAttackPower = 1;
        this.mMaxAbilityPower = 0;

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

    public Stats plus(Stats statsModifier)
    {
        return new Stats(
                this.mCurrentHealth + statsModifier.mCurrentHealth,
                this.mCurrentMana + statsModifier.mCurrentMana,
                this.mCurrentArmor + statsModifier.mCurrentArmor,
                this.mCurrentResistance + statsModifier.mCurrentResistance,
                this.mCurrentAttackPower + statsModifier.mCurrentAttackPower,
                this.mCurrentAbilityPower + statsModifier.mCurrentAbilityPower
        );
    }

    public Stats minus(Stats statsModifier)
    {
        return new Stats(
                this.mCurrentHealth - statsModifier.mCurrentHealth,
                this.mCurrentMana - statsModifier.mCurrentMana,
                this.mCurrentArmor - statsModifier.mCurrentArmor,
                this.mCurrentResistance - statsModifier.mCurrentResistance,
                this.mCurrentAttackPower - statsModifier.mCurrentAttackPower,
                this.mCurrentAbilityPower - statsModifier.mCurrentAbilityPower
        );
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

    public int GetHealth()
    {
        return mCurrentHealth;
    }

    public int GetMana()
    {
        return mCurrentMana;
    }

    public int GetArmor()
    {
        return mCurrentArmor;
    }

    public int GetResistance()
    {
        return mCurrentResistance;
    }

    public int GetAttackPower()
    {
        return mCurrentAttackPower;
    }

    public int GetAbilityPower()
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