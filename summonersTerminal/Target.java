package summonersTerminal;

import java.util.List;

import summonersTerminal.champion.Champion;
import summonersTerminal.minion.Minion;

public sealed interface Target permits Minion, Champion, Nexus {
    String name();

    boolean isAlive();

    boolean takeDamage(int physicalDamage, int spellDamage, List<Minion> wave, Target target);

    default boolean takeDamage(int pDamage, int sDamage) {
        return takeDamage(pDamage, sDamage, List.of(), null);
    }
}
