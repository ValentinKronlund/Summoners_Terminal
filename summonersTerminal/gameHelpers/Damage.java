package summonersTerminal.gameHelpers;

public class Damage {

    // I trust the internet with this equation -- Bismillah 🙏🏽
    public static double damageAfterArmor(double dmg, double armor) {
        double cap = 0.60, k = 28.0; // saturates at 60%
        double dr = cap * armor / (armor + k);
        return dmg * (1.0 - dr);
    }
}
