package summonersTerminal;

public enum MinionType {
        MELEE(
                        "Melee",
                        new Stats(
                                        90,
                                        0,
                                        10,
                                        5,
                                        17,
                                        0),
                        new Stats(
                                        20,
                                        0,
                                        4,
                                        0,
                                        3,
                                        0),
                        150),
        CASTER(
                        "Caster",
                        new Stats(
                                        70,
                                        0,
                                        5,
                                        5,
                                        13,
                                        0),
                        new Stats(
                                        20,
                                        0,
                                        0,
                                        0,
                                        4,
                                        0),
                        100),
        CANON(
                        "Canon",
                        new Stats(
                                        220,
                                        0,
                                        25,
                                        10,
                                        25,
                                        0),
                        new Stats(
                                        40,
                                        0,
                                        5,
                                        0,
                                        8,
                                        0),
                        250);

        private final Stats base, growthPerCycle;
        private final String nameType;
        private final int goldValue;

        MinionType(String nameType, Stats base, Stats growthPerCycle, int goldValue) {
                this.nameType = nameType;
                this.base = base;
                this.growthPerCycle = growthPerCycle;
                this.goldValue = goldValue;
        }

        public String nameType() {
                return nameType;
        }

        public Stats base() {
                return base;
        }

        public Stats growthPerCycle() {
                return growthPerCycle;
        }

        public int goldValue() {
                return goldValue;
        }

}
