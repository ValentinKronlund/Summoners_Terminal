package summonersTerminal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import summonersTerminal.champion.abilities.Ability;
import summonersTerminal.gameHelpers.Damage;

public final class Champion {

    String championName;
    ChampionID championId;
    private Stats stats;
    private int level = 1;
    private final int startGold = 500;
    private int gold = startGold;
    private boolean isDead = false;
    private boolean inBase = false;

    private final List<Ability> abilityKit;
    private final List<Item> items = new ArrayList<>();

    public Champion(
            String championName,
            ChampionID championId) {
        this.championName = championName;
        this.championId = championId;
        this.stats = championId.base;
        this.abilityKit = new ArrayList<>(championId.abilityKit);
    }

    public void levelUp() {
        this.level++;
        recalcAllStats();
    }

    private Stats baseAtCurrentLevel() {
        Stats st = championId.base;
        for (int i = 1; i < level; i++) {
            st = st.plus(championId.growthPerLevel);
        }
        return st;
    }

    private void recalcAllStats() {
        Stats itemBonus = Stats.ZERO;
        for (Item item : items) {
            itemBonus = itemBonus.plus(item.stats());
        }

        Stats newStats = baseAtCurrentLevel().plus(itemBonus);

        this.stats = new Stats(
                newStats.health(),
                newStats.mana(),
                newStats.armor(),
                newStats.resistance(),
                newStats.attackPower(),
                newStats.abilityPower());
    }

    // Actions below 👇🏽 ----------
    public boolean equip(Item item) {
        if (items.size() >= 6) {
            System.out.println("\nYou don't have any more available item slots!");
            return false;
        }
        if (this.gold < item.cost()) {
            System.out.println("\nNot enough gold for that item!");
            return false;
        }

        this.gold -= item.cost();
        items.add(item);
        System.out.println("\nYou purchased " + item.toString() + "\n");
        return goToBase();
    }

    public boolean unequip(Item item) {
        if (!items.remove(item))
            return false;

        int sellAmount = (int) Math.round(item.cost() * 0.6);
        System.out.println("\nSold item for: " + sellAmount);
        gold += sellAmount;
        recalcAllStats();
        return true;
    }

    public boolean attack(Minion target, List<Minion> minionWave) {
        return target.takeDamage(this.stats.attackPower(), minionWave, this);
    }

    public boolean attackNexus(Nexus nexus) {
        return nexus.takeDamage(this.stats.attackPower());
    }

    public boolean useAbility(int abilityIndex, Minion target, List<Minion> minionWave) {
        if (abilityIndex < 0 || abilityIndex >= abilityKit.size()) {
            System.out.println("Invalid ability index: " + abilityIndex + 1);
            return false;
        }
        Ability ability = abilityKit.get(abilityIndex);
        return ability.cast(this, target, minionWave);
    }

    public boolean goToBase() {
        this.inBase = true;
        System.out.println("\nYou have gone to the base -- HP and Mana reset!✨\n");
        recalcAllStats();
        this.inBase = false;
        return true;
    }

    public boolean onDeath() {
        System.out.println("You have been slain! 😵");
        this.isDead = true;
        this.inBase = true;
        return true;
    }

    public boolean respawn() {
        this.isDead = false;
        this.inBase = false;
        recalcAllStats();
        System.out.println("\nYou have respawned! 🩵\n");
        return true;
    }

    public boolean takeDamage(int damageAmount) {
        int damageTaken = (int) Math.round(Damage.damageAfterArmor(damageAmount, stats.armor()));
        try {
            this.stats = stats.minus(new Stats(damageTaken, 0, 0, 0, 0, 0));
            System.out.println("You have taken " + damageTaken + " damage!" + " | HP: " + this.stats.health());

            if (this.stats.health() <= 0) {
                onDeath();
            }

            return true;
        } catch (Exception e) {
            System.out.println("Some spooky shit happened when champion tried to take damage 👻");
            return false;
        }
    }

    // ----- GETTERS
    public Stats getStats() {
        return stats;
    }

    public int getLevel() {
        return level;
    }

    public int getGold() {
        return gold;
    }

    public boolean getIsDead() {
        return isDead;
    }

    public boolean getInBase() {
        return inBase;
    }

    public List<Item> getItems() {
        return items;
    }

    public List<Ability> showAbilities() {
        return Collections.unmodifiableList(abilityKit);
    }

    // ----- Setters
    public void walkFromBase() {
        this.inBase = false;
    }

    public void useMana(int manaCost) {
        this.stats = stats.minus(new Stats(0, manaCost, 0, 0, 0, 0));
    }

    public void addGold(int amount) {
        this.gold += amount;
    }

    @Override
    public String toString() {
        String abilityNames = abilityKit.stream().map(Ability::name).reduce((a, b) -> a + "\n" + b)
                .orElse("(no abilities)");
        String itemsString = items.isEmpty()
                ? "(none)"
                : items.stream()
                        .map(Item::toString)
                        .collect(Collectors.joining("\n"));

        return "\n[%s] - (%s | Lv.%d)\n%s\n%s\nGold: %d\nItems: %s".formatted(championName, championId.displayName,
                level, stats,
                abilityNames, gold, itemsString);
    }

}
