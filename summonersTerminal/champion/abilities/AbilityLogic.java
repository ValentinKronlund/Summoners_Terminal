package summonersTerminal.champion.abilities;

import java.util.List;
import summonersTerminal.Champion;
import summonersTerminal.Minion;

public interface AbilityLogic {
    boolean cast(Champion self, Minion target, List<Minion> wave);
}
