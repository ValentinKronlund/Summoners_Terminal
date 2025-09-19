package summonersTerminal.champion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import summonersTerminal.Item;
import summonersTerminal.Nexus;
import summonersTerminal.Stats;
import summonersTerminal.Target;
import summonersTerminal.champion.Passives.Base.Passive;
import summonersTerminal.champion.abilities.Ability;
import summonersTerminal.gameHelpers.Damage;
import summonersTerminal.gameHelpers.Validation;
import summonersTerminal.minion.Minion;

public final class Champion implements Target {

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
            ChampionID championId) {
        this.championName = championName;
        this.championId = championId;
        this._stats = championId.base;
        this._abilityKit = new ArrayList<>(championId.abilityKit);
    }

    public void levelUp() {
        this._level++;
        this._stats.AddMaxStats(this.championId.growthPerLevel);
    }

    private Stats baseAtCurrentLevel() {
        Stats baseStats = championId.base;

        for (int i = 1; i < _level; i++) {
            baseStats.AddMaxStats(championId.growthPerLevel);
        }

        baseStats.RestoreToMax();
        return baseStats;
    }

    // Actions below 👇🏽 ----------
    public boolean equip(Item item) {
        if (!Validation.validateEquipItem(item, _items, _gold)) {
            return false;
        }

        this._gold -= item.cost();
        _items.add(item);
        System.out.println("\n%s purchased %s \n".formatted(championName, item.toString()));

        _stats.AddMaxStats(item.stats());

        return goToBase();
    }

    public boolean unequip(Item item) {
        if (!_items.remove(item))
            return false;

        int sellAmount = (int) Math.round(item.cost() * 0.6);
        System.out.println("\nSold item for: " + sellAmount);
        _gold += sellAmount;

        stats().MinusMaxStats(item.stats());

        return true;
    }

    public boolean attack(Minion target, List<Minion> minionWave) {
        return target.takeDamage(this._stats.GetCurrentAttackPower(), 0, minionWave, this);
    }

    public boolean attackChampion(Champion champion) {
        return champion.takeDamage(this._stats.GetCurrentAttackPower(), 0);
    }

    public boolean attackNexus(Nexus nexus) {
        return nexus.takeDamage(this._stats.GetCurrentAttackPower(), 0);

    }

    public boolean useAbility(int abilityIndex, Minion target, List<Minion> minionWave) {
        if (!Validation.validateAbilityOption(abilityIndex, _abilityKit)) {
            return false;
        }
        Ability ability = _abilityKit.get(abilityIndex);
        return ability.cast(this, target, minionWave);
    }

    public boolean goToBase() {
        this._inBase = true;
        System.out.println("\n%s have gone to the base -- HP and Mana reset!✨\n".formatted(championName));
        this._stats.RestoreToMax();
        this._inBase = false;
        return true;
    }

    public boolean onDeath() {
        String deathString = "%s has been slain! 😵".formatted(championName);
        System.out.println(deathString);
        this._isAlive = false;
        this._inBase = true;
        return true;
    }

    public boolean respawn() {
        this._isAlive = true;
        this._inBase = false;
        this._stats.RestoreToMax();
        System.out.println("\n%s have respawned! 🩵\n".formatted(championName));
        return true;
    }

    public boolean takeDamage(int physicalDamage, int spellDamage, List<Minion> wave, Target target) {
        try {
            int damageTaken = Damage.damageAfterReduction(physicalDamage, spellDamage, this._stats.GetCurrentArmor(),
                    this._stats.GetCurrentResistance());

            this._stats.MinusCurrentHealth(damageTaken);

            String dmgString = "%s has taken %d damage! | HP: %d".formatted(championName, damageTaken,
                    this._stats.GetCurrentHealth());
            System.out.println(dmgString);

            if (this._stats.GetCurrentHealth() <= 0) {
                onDeath();
            }

            return true;
        } catch (Exception e) {
            System.out.println("Some spooky shit happened when champion tried to take damage 👻");
            return false;
        }
    }

    public void setPassive(Passive pPassive) {
        _passive = pPassive;
    }

    public Passive getPassive() {
        return _passive;
    }

    // ----- GETTERS
    @Override
    public String name() {
        return championName;
    }

    public Stats stats() {
        return _stats;
    }

    public int level() {
        return _level;
    }

    public int gold() {
        return _gold;
    }

    public boolean isAlive() {
        return _isAlive;
    }

    public boolean inBase() {
        return _inBase;
    }

    public List<Item> items() {
        return _items;
    }

    public List<Ability> abilities() {
        return Collections.unmodifiableList(_abilityKit);
    }

    // ----- Setters
    public void walkFromBase() {
        this._inBase = false;
    }

    public void useMana(int manaCost) {
        this._stats.SetCurrentMana(this._stats.GetCurrentMana() - manaCost);
    }

    public void addGold(int amount) {
        this._gold += amount;
    }

    @Override
    public String toString() {
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