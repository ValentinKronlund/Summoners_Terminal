package summonersTerminal;

import java.util.List;

public final class Nexus implements Target {
    private final String _name;
    private boolean _isAlive = false;
    private Stats stats;

    public Nexus(String name) {
        this._name = name;
        this._isAlive = true;
        this.stats = new Stats(
                500,
                0,
                0,
                0,
                0,
                0);
    }

    @Override
    public String name() {
        return _name;
    }


    @Override
    public boolean isAlive() {
        return _isAlive;
    }

    public void onDeath() {
        this._isAlive = false;
    }

    @Override
    public boolean takeDamage(int physicalDamage, int spellDamage, List<Minion> wave, Target target) {
        try {
            this.stats = this.stats.minus(new Stats(physicalDamage, 0, 0, 0, 0, 0));

            System.out.println(this._name + " has taken " + physicalDamage + " damage!" + " | HP: "
                    + this.stats.GetHealth());

            if (this.stats.GetHealth() <= 0) {
                onDeath();
                return true;
            }

            return true;
        } catch (Exception e) {
            System.out.println("Some spooky shit happened when minion tried to take damage 👻");
            return false;
        }
    }

    @Override
    public String toString() {
        return "[%s] - HP:%d".formatted(_name, stats.GetHealth());
    }
}