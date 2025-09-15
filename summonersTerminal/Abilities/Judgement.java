package summonersTerminal.Abilities;

public class Judgement extends Ability
{
    public Judgement()
    {
        super("Judgement");
        mDescription = "This ability is very op!";
        mBaseDamage = 150.0f;
        mPhysicalDamageRatio = 0.50f;
        mMagicDamageRatio = 0.0f;
        mManaCost = 150;
    }
}