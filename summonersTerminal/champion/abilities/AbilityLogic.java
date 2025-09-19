package summonersTerminal.champion.abilities;

import java.util.List;
import summonersTerminal.champion.Champion;
import summonersTerminal.minion.Minion;

public interface AbilityLogic {
    boolean cast(Champion self, Minion target, List<Minion> wave);
}
