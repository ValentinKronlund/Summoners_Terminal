package summonersTerminal.gameHelpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import summonersTerminal.Champion;
import summonersTerminal.Minion;
import summonersTerminal.Nexus;
import summonersTerminal.Target;

public class MinionTargetingSystem {

    public static Target targetPriority(List<Minion> wave, Champion champion, Nexus nexus) {
        int index;
        Random random = new Random();
        List<Target> firstPriorityPool = new ArrayList<>();
        List<Target> secondPriorityPool = new ArrayList<>();
        List<Target> thirdPriorityPool = new ArrayList<>();

        if (wave != null && !wave.isEmpty()) {
            for (Minion minion : wave) {
                if (minion.isAlive()) {
                    switch (minion.getMinionType()) {
                        case MELEE -> {
                            firstPriorityPool.add(minion);
                        }
                        case CASTER -> {
                            secondPriorityPool.add(minion);
                        }
                        case CANON -> {
                            thirdPriorityPool.add(minion);
                        }
                    }
                }
            }
        }

        if (!firstPriorityPool.isEmpty()) {
            index = random.nextInt(0, firstPriorityPool.size());
            return firstPriorityPool.get(index);
        } else if (!secondPriorityPool.isEmpty()) {
            index = random.nextInt(0, secondPriorityPool.size());
            return secondPriorityPool.get(index);
        } else if (!thirdPriorityPool.isEmpty()) {
            index = random.nextInt(0, thirdPriorityPool.size());
            return thirdPriorityPool.get(index);
        } else if (champion.isAlive() && !champion.inBase()) {
            return champion;
        } else {
            return nexus;
        }
    }
}
