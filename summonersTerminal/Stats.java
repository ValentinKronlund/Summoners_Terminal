package summonersTerminal;

public class Stats {
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

    public Stats() {
        restore();
    }

    public Stats(final int pHealth, final int pMana, final int pArmor, final int pResistance, final int pAttackPower,
            final int pAbilityPower) {
        this.mMaxHealth = pHealth;
        this.mMaxMana = pMana;
        this.mMaxArmor = pArmor;
        this.mMaxResistance = pResistance;
        this.mMaxAttackPower = pAttackPower;
        this.mMaxAbilityPower = pAbilityPower;

        restore();
    }

    public void restore() {
        this.mCurrentHealth = this.mMaxHealth;
        this.mCurrentMana = this.mMaxMana;
        this.mCurrentArmor = this.mMaxArmor;
        this.mCurrentResistance = this.mMaxResistance;
        this.mCurrentAttackPower = this.mMaxAttackPower;
        this.mCurrentAbilityPower = this.mMaxAbilityPower;
    }

    @Override
    public String toString() {
        return "HP:%d/%d | MANA:%d/%d | ARM:%d | RES:%d | ATK:%d | ABP:%d".formatted(
                mCurrentHealth, mMaxHealth,
                mCurrentMana, mMaxMana,
                mCurrentArmor,
                mCurrentResistance,
                mCurrentAttackPower,
                mCurrentAbilityPower);
    }

    @Override
    public boolean equals(Object pObject) {
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

    public void setStats(final Stats pStats)
    {
        setMaxStats(pStats);
        setCurrentStats(pStats);
    }

    public void addStats(final Stats pStatsModifier)
    {
        addMaxStats(pStatsModifier);
        addCurrentStats(pStatsModifier);
    }

    public void addHealth(final int pHealth)
    {
        addMaxHealth(pHealth);
        addCurrentHealth(pHealth);
    }

    public void addMana(final int pMana)
    {
        addMaxMana(pMana);
        addCurrentMana(pMana);
    }

    public void addArmor(final int pArmor)
    {
        addMaxArmor(pArmor);
        addCurrentArmor(pArmor);
    }

    public void addResistance(final int pResistance)
    {
        addMaxResistance(pResistance);
        addCurrentResistance(pResistance);
    }

    public void addAttackPower(final int pAttackPower)
    {
        addMaxAttackPower(pAttackPower);
        addCurrentAttackPower(pAttackPower);
    }

    public void addAbilityPower(final int pAbilityPower)
    {
        addMaxAbilityPower(pAbilityPower);
        addCurrentAbilityPower(pAbilityPower);
    }

    public void minusStats(final Stats pStatsModifier)
    {
        minusMaxStats(pStatsModifier);
    }

    public void minusHealth(final int pHealth)
    {
        minusMaxHealth(pHealth);
        minusCurrentHealth(pHealth);
    }

    public void minusMana(final int pMana)
    {
        minusMaxMana(pMana);
        minusCurrentMana(pMana);
    }

    public void minusArmor(final int pArmor)
    {
        minusMaxArmor(pArmor);
        minusCurrentArmor(pArmor);
    }

    public void minusResistance(final int pResistance)
    {
        minusMaxResistance(pResistance);
        minusCurrentResistance(pResistance);
    }

    public void minusAttackPower(final int pAttackPower)
    {
        minusMaxAttackPower(pAttackPower);
        minusCurrentAttackPower(pAttackPower);
    }

    public void minusAbilityPower(final int pAbilityPower)
    {
        minusMaxAbilityPower(pAbilityPower);
        minusCurrentAbilityPower(pAbilityPower);
    }

    public void addCurrentStats(final Stats pCurrentStatsModifier) {
        addCurrentHealth(pCurrentStatsModifier.mCurrentHealth);
        addCurrentMana(pCurrentStatsModifier.mCurrentMana);
        addCurrentArmor(pCurrentStatsModifier.mCurrentArmor);
        addCurrentResistance(pCurrentStatsModifier.mCurrentResistance);
        addCurrentAttackPower(pCurrentStatsModifier.mCurrentAttackPower);
        addCurrentAbilityPower(pCurrentStatsModifier.mCurrentAbilityPower);
    }

    public void addMaxStats(final Stats pMaxStatsModifier) {
        addMaxHealth(pMaxStatsModifier.mCurrentHealth);
        addMaxMana(pMaxStatsModifier.mCurrentMana);
        addMaxArmor(pMaxStatsModifier.mMaxArmor);
        addMaxResistance(pMaxStatsModifier.mMaxResistance);
        addMaxAttackPower(pMaxStatsModifier.mMaxAttackPower);
        addMaxAbilityPower(pMaxStatsModifier.mMaxAbilityPower);
    }

    public void addCurrentHealth(final int pHealth) {
        mCurrentHealth += pHealth;

        if (mCurrentHealth > mMaxHealth) {
            mCurrentHealth = mMaxHealth;
        }
    }

    public void addCurrentMana(final int pMana) {
        mCurrentMana += pMana;

        if (mCurrentMana > mMaxMana) {
            mCurrentMana = mMaxMana;
        }
    }

    public void addCurrentArmor(final int pArmor) {
        mCurrentArmor += pArmor;

        if (mCurrentArmor > mMaxArmor) {
            mCurrentArmor = mMaxArmor;
        }
    }

    public void addCurrentResistance(final int pResistance) {
        mCurrentResistance += pResistance;

        if (mCurrentResistance > mMaxResistance) {
            mCurrentResistance = mMaxResistance;
        }
    }

    public void addCurrentAttackPower(final int pAttackPower) {
        mCurrentAttackPower += pAttackPower;

        if (mCurrentAttackPower > mMaxAttackPower) {
            mCurrentAttackPower = mMaxAttackPower;
        }
    }

    public void addCurrentAbilityPower(final int pAbilityPower) {
        mCurrentAbilityPower += pAbilityPower;

        if (mCurrentAbilityPower > mMaxAbilityPower) {
            mCurrentAbilityPower = mMaxAbilityPower;
        }
    }

    public void addMaxHealth(final int pHealth) {
        mMaxHealth += pHealth;
    }

    public void addMaxMana(final int pMana) {
        mMaxMana += pMana;
    }

    public void addMaxArmor(final int pArmor) {
        mMaxArmor += pArmor;
    }

    public void addMaxResistance(final int pResistance) {
        mMaxResistance += pResistance;
    }

    public void addMaxAttackPower(final int pAttackPower) {
        mMaxAttackPower += pAttackPower;
    }

    public void addMaxAbilityPower(final int pAbilityPower) {
        mMaxAbilityPower += pAbilityPower;
    }

    public void minusCurrentHealth(final int pHealth) {
        mCurrentHealth -= pHealth;
    }

    public void minusCurrentMana(final int pMana) {
        mCurrentMana -= pMana;
    }

    public void minusCurrentArmor(final int pArmor) {
        mCurrentArmor -= pArmor;
    }

    public void minusCurrentResistance(final int pResistance) {
        mCurrentResistance -= pResistance;
    }

    public void minusCurrentAttackPower(final int pAttackPower) {
        mCurrentAttackPower -= pAttackPower;
    }

    public void minusCurrentAbilityPower(final int pAbilityPower) {
        mCurrentAbilityPower -= pAbilityPower;
    }

    public void minusMaxHealth(final int pHealth) {
        mMaxHealth -= pHealth;
        mCurrentHealth -= pHealth;
    }

    public void minusMaxMana(final int pMana) {
        mMaxMana -= pMana;
        mCurrentMana -= pMana;
    }

    public void minusMaxArmor(final int pArmor) {
        mMaxArmor -= pArmor;
        mCurrentArmor -= pArmor;
    }

    public void minusMaxResistance(final int pResistance) {
        mMaxResistance -= pResistance;
        mCurrentResistance -= pResistance;
    }

    public void minusMaxAttackPower(final int pAttackPower) {
        mMaxAttackPower -= pAttackPower;
        mCurrentAttackPower -= pAttackPower;
    }

    public void minusMaxAbilityPower(final int pAbilityPower) {
        mMaxAbilityPower -= pAbilityPower;
        mCurrentAttackPower -= pAbilityPower;
    }

    public void minusCurrentStats(final Stats pCurrentStatsModifier) {
        this.mCurrentHealth -= pCurrentStatsModifier.mCurrentHealth;
        this.mCurrentMana -= pCurrentStatsModifier.mCurrentMana;
        this.mCurrentArmor -= pCurrentStatsModifier.mCurrentArmor;
        this.mCurrentResistance -= pCurrentStatsModifier.mCurrentResistance;
        this.mCurrentAttackPower -= pCurrentStatsModifier.mCurrentAttackPower;
        this.mCurrentAbilityPower -= pCurrentStatsModifier.mCurrentAbilityPower;
    }

    public void minusMaxStats(final Stats pMaxStatsModifier) {
        this.mMaxHealth -= pMaxStatsModifier.mMaxHealth;
        this.mMaxMana -= pMaxStatsModifier.mMaxMana;
        this.mMaxArmor -= pMaxStatsModifier.mMaxArmor;
        this.mMaxResistance -= pMaxStatsModifier.mMaxResistance;
        this.mMaxAttackPower -= pMaxStatsModifier.mMaxAttackPower;
        this.mMaxAbilityPower -= pMaxStatsModifier.mMaxAbilityPower;

        minusCurrentStats(pMaxStatsModifier);
    }

    public void setMaxStats(final Stats pMaxStats) {
        setMaxHealth(pMaxStats.mMaxHealth);
        setMaxMana(pMaxStats.mMaxMana);
        setMaxArmor(pMaxStats.mMaxArmor);
        setMaxResistance(pMaxStats.mMaxResistance);
        setMaxAttackPower(pMaxStats.mMaxAttackPower);
        setMaxAbilityPower(pMaxStats.mMaxAbilityPower);
    }

    public void setCurrentStats(final Stats pCurrentStats) {
        setCurrentHealth(pCurrentStats.mCurrentHealth);
        setCurrentMana(pCurrentStats.mCurrentMana);
        setCurrentArmor(pCurrentStats.mCurrentArmor);
        setCurrentResistance(pCurrentStats.mCurrentResistance);
        setCurrentAttackPower(pCurrentStats.mCurrentAttackPower);
        setCurrentAbilityPower(pCurrentStats.mCurrentAbilityPower);
    }

    public void setMaxHealth(final int pMaxHealth) {
        this.mMaxHealth = pMaxHealth;
    }

    public void setMaxMana(final int pMaxMana) {
        mMaxMana = pMaxMana;
    }

    public void setMaxArmor(final int pMaxArmor) {
        mMaxArmor = pMaxArmor;
    }

    public void setMaxResistance(final int pMaxResistance) {
        mMaxResistance = pMaxResistance;
    }

    public void setMaxAttackPower(final int pMaxAttackPower) {
        mMaxAttackPower = pMaxAttackPower;
    }

    public void setMaxAbilityPower(final int pMaxAbilityPower) {
        mMaxAbilityPower = pMaxAbilityPower;
    }

    public void setCurrentHealth(final int pCurrentHealth) {
        mCurrentHealth = pCurrentHealth;
    }

    public void setCurrentMana(final int pCurrentMana) {
        mCurrentMana = pCurrentMana;
    }

    public void setCurrentArmor(final int pCurrentArmor) {
        mCurrentArmor = pCurrentArmor;
    }

    public void setCurrentResistance(final int pCurrentResistance) {
        mCurrentResistance = pCurrentResistance;
    }

    public void setCurrentAttackPower(final int pCurrentAttackPower) {
        mCurrentAttackPower = pCurrentAttackPower;
    }

    public void setCurrentAbilityPower(final int pCurrentAbilityPower) {
        mCurrentAbilityPower = pCurrentAbilityPower;
    }

    public int getCurrentHealth() {
        return mCurrentHealth;
    }

    public int getCurrentMana() {
        return mCurrentMana;
    }

    public int getCurrentArmor() {
        return mCurrentArmor;
    }

    public int getCurrentResistance() {
        return mCurrentResistance;
    }

    public int getCurrentAttackPower() {
        return mCurrentAttackPower;
    }

    public int getCurrentAbilityPower() {
        return mCurrentAbilityPower;
    }

    public int getMaxHealth() {
        return mMaxHealth;
    }

    public int getMaxMana() {
        return mMaxMana;
    }

    public int getMaxArmor() {
        return mMaxArmor;
    }

    public int getMaxResistance() {
        return mMaxResistance;
    }

    public int getMaxAttackPower() {
        return mMaxAttackPower;
    }

    public int getMaxAbilityPower() {
        return mMaxAbilityPower;
    }
}