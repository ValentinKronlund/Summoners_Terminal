package summonersTerminal.champion.abilities;

import java.util.List;
import summonersTerminal.champion.Champion;
import summonersTerminal.minion.Minion;

public interface Ability {
    String name();

    int manaCost();

    boolean cast(Champion self, Minion target, List<Minion> wave);
}