package summonersTerminal;

import java.util.List;
import summonersTerminal.champion.abilities.Ability;
import summonersTerminal.champion.abilities.AbilityKits;

public enum ChampionID {
        GAREN(
                        "Garen",
                        new Stats(700, 200, 25, 30, 45, 0),
                        new Stats(70, 20, 4, 3, 5, 0),
                        new Ability[] { AbilityKits.garenJudgment() }),
        KATARINA(
                        "Katarina",
                        new Stats(500, 300, 10, 20, 60, 10),
                        new Stats(50, 30, 3, 2, 6, 2),
                        new Ability[] { AbilityKits.katarinaBouncingBlade() }),

        VEIGAR(
                        "Veigar",
                        new Stats(450, 500, 10, 30, 20, 50),
                        new Stats(45, 40, 2, 3, 3, 7),
                        new Ability[] { AbilityKits.veigarBalefulStrike() });

        public final String displayName;
        public final Stats base, growthPerLevel;
        public final List<Ability> abilityKit;

        ChampionID(String name, Stats base, Stats growthPerLevel, Ability[] kit) {
                this.displayName = name;
                this.base = base;
                this.growthPerLevel = growthPerLevel;
                this.abilityKit = List.of(kit);
        }

        public Champion create(String champName) {
                return new Champion(champName, this);
        }
}