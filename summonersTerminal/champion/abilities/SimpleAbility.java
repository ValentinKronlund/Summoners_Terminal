package summonersTerminal.champion.abilities;

import java.util.List;
import summonersTerminal.Stats;
import summonersTerminal.champion.Champion;
import summonersTerminal.minion.Minion;

public final class SimpleAbility implements Ability {
    private final String name;
    // private final String description; <--- Add this later :)
    private final int manaCost;
    private final AbilityLogic logic;

    public SimpleAbility(
            String name,
            int manaCost,
            AbilityLogic logic) {
        this.name = name;
        this.manaCost = manaCost;
        this.logic = logic;
    }

    @Override
    public String name() {
        return "%s - Mana cost: %s".formatted(name, manaCost);
    }

    @Override
    public int manaCost() {
        return manaCost;
    }

    @Override
    public boolean cast(
            Champion self,
            Minion target,
            List<Minion> wave) {
        Stats ownStats = self.stats();

        if (ownStats.GetCurrentMana() < manaCost) {
            System.out.printf("\nNot enough mana to cast %s (%d/%d)%n", name, ownStats.GetCurrentMana(), manaCost);
            return false;
        }

        self.useMana(manaCost);
        return logic.cast(self, target, wave);
    }
}
