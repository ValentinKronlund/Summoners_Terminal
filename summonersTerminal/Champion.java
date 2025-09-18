package summonersTerminal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import summonersTerminal.champion.abilities.Ability;
import summonersTerminal.champion.passives.Passive;
import summonersTerminal.gameHelpers.Damage;
import summonersTerminal.gameHelpers.Validation;

public final class Champion implements Target
{
    public void setPassive(Passive pPassive)
    {
        _passive = pPassive;
    }

    public void increaseStats(final Stats pStats)
    {
        _stats = _stats.plus(pStats);
    }

    public Passive getPassive()
    {
        return _passive;
    }

    private final String championName;
    ChampionID championId;
    private Stats _stats;
    private int _level = 1;
    private final int _startGold = 500;
    private int _gold = _startGold;
    private boolean _isAlive = true;
    private boolean _inBase = false;

    private final List<Ability> _abilityKit;
    private final List<Item> _items = new ArrayList<>();
    private Passive _passive;

    public Champion(
            String championName,
            ChampionID championId)
    {
        this.championName = championName;
        this.championId = championId;
        this._stats = championId.base;
        this._abilityKit = new ArrayList<>(championId.abilityKit);
    }

    public void levelUp()
    {
        this._level++;
        recalcAllStats();
    }

    private Stats baseAtCurrentLevel()
    {
        Stats st = championId.base;
        for (int i = 1; i < _level; i++)
        {
            st = st.plus(championId.growthPerLevel);
        }
        return st;
    }

    private void recalcAllStats()
    {
        Stats itemBonus = Stats.ZERO;
        for (Item item : _items)
        {
            itemBonus = itemBonus.plus(item.stats());
        }

        Stats newStats = baseAtCurrentLevel().plus(itemBonus);

        this._stats = new Stats(
                newStats.GetHealth(),
                newStats.GetMana(),
                newStats.GetArmor(),
                newStats.GetResistance(),
                newStats.GetAttackPower(),
                newStats.GetAbilityPower());
    }

    // Actions below 👇🏽 ----------
    public boolean equip(Item item)
    {
        if (!Validation.validateEquipItem(item, _items, _gold))
        {
            return false;
        }

        this._gold -= item.cost();
        _items.add(item);
        System.out.println("\nYou purchased " + item.toString() + "\n");
        return goToBase();
    }

    public boolean unequip(Item item)
    {
        if (!_items.remove(item))
            return false;

        int sellAmount = (int) Math.round(item.cost() * 0.6);
        System.out.println("\nSold item for: " + sellAmount);
        _gold += sellAmount;
        recalcAllStats();
        return true;
    }

    public boolean attack(Minion target, List<Minion> minionWave)
    {
        return target.takeDamage(this._stats.GetAttackPower(), 0, minionWave, this);
    }

    public boolean attackNexus(Nexus nexus)
    {
        return nexus.takeDamage(this._stats.GetAttackPower(), 0);
    }

    public boolean useAbility(int abilityIndex, Minion target, List<Minion> minionWave)
    {
        if (!Validation.validateAbilityOption(abilityIndex, _abilityKit))
        {
            return false;
        }
        Ability ability = _abilityKit.get(abilityIndex);
        return ability.cast(this, target, minionWave);
    }

    public boolean goToBase()
    {
        this._inBase = true;
        System.out.println("\nYou have gone to the base -- HP and Mana reset!✨\n");
        recalcAllStats();
        this._inBase = false;
        return true;
    }

    public boolean onDeath()
    {
        String deathString = "%s has been slain! 😵".formatted(championName);
        System.out.println(deathString);
        this._isAlive = false;
        this._inBase = true;
        return true;
    }

    public boolean respawn()
    {
        this._isAlive = true;
        this._inBase = false;
        recalcAllStats();
        System.out.println("\nYou have respawned! 🩵\n");
        return true;
    }

    public boolean takeDamage(int physicalDamage, int spellDamage, List<Minion> wave, Target target)
    {
        try
        {
            int damageTaken = Damage.damageAfterReduction(physicalDamage, spellDamage, this._stats.GetArmor(),
                    this._stats.GetResistance());

            this._stats = _stats.minus(new Stats(damageTaken, 0, 0, 0, 0, 0));
            String dmgString = "%s has taken %d damage! | HP: %d".formatted(championName, damageTaken,
                    this._stats.GetHealth());
            System.out.println(dmgString);

            if (this._stats.GetHealth() <= 0)
            {
                onDeath();
            }

            return true;
        } catch (Exception e)
        {
            System.out.println("Some spooky shit happened when champion tried to take damage 👻");
            return false;
        }
    }

    // ----- GETTERS
    @Override
    public String name()
    {
        return championName;
    }

    public Stats stats()
    {
            return _stats;
        }

        public int level ()
        {
            return _level;
        }

        public int gold ()
        {
            return _gold;
        }

        public boolean isAlive () {
        return _isAlive;
        }

        public boolean inBase ()
        {
            return _inBase;
        }

        public List<Item> items ()
        {
            return _items;
        }

        public List<Ability> abilities ()
        {
            return Collections.unmodifiableList(_abilityKit);
        }

        // ----- Setters
        public void walkFromBase ()
        {
            this._inBase = false;
        }

        public void useMana ( int manaCost)
        {
            this._stats = _stats.minus(new Stats(0, manaCost, 0, 0, 0, 0));
        }

        public void addGold ( int amount)
        {
            this._gold += amount;
        }

        @Override
        public String toString ()
        {
            String abilityNames = _abilityKit.isEmpty()
                    ? "(no abilities)"
                    : _abilityKit.stream().map(Ability::name).collect(Collectors.joining("\n"));

            String itemsString = _items.isEmpty()
                    ? "(none)"
                    : _items.stream()
                    .map(Item::toString)
                    .collect(Collectors.joining("\n"));

            return "\n[%s] - (%s | Lv.%d)\n[Stats]: %s\n[Abilities]: %s\n[Gold]: %d\n[Items]: %s".formatted(championName,
                    championId.displayName,
                    _level, _stats,
                    abilityNames, _gold, itemsString);
        }
    }