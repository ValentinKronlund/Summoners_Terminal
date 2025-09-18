package summonersTerminal.champion.abilities;

import java.util.List;
import summonersTerminal.Champion;
import summonersTerminal.Minion;

public interface Ability {
    String name();

    int manaCost();

    boolean cast(Champion self, Minion target, List<Minion> wave);
}