package summonersTerminal.gameHelpers;

public class Damage {

    // I trust the internet with this equation -- Bismillah 🙏🏽
    private static double damageAfterArmor(double dmg, double armor) {
        double cap = 0.60, k = 28.0; // saturates at 60%
        double dr = cap * armor / (armor + k);
        return dmg * (1.0 - dr);
    }

    // I trust the internet with this equation -- Bismillah 🙏🏽
    private static double damageAfterResistance(double dmg, double resistance) {
        double cap = 0.65, k = 16.0; // saturates at 65%
        double dr = cap * resistance / (resistance + k);
        return dmg * (1.0 - dr);
    }

    public static int damageAfterReduction(double physical, double spell, double armor, double resistance) {
        return (int) Math.round(damageAfterArmor(physical, armor) + damageAfterResistance(spell, resistance));
    }
}
