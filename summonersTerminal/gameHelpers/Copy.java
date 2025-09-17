package summonersTerminal.gameHelpers;

import java.util.List;

import summonersTerminal.Champion;
import summonersTerminal.Item;
import summonersTerminal.Minion;
import summonersTerminal.Nexus;
import summonersTerminal.champion.abilities.Ability;

public class Copy {

    public static void initialCopy() {
        System.out.println("\n🔮 Welcome, to Summoner's Terminal! 🔮");
        System.out.println(
                "\n[ Rules ]: \n"
                        + "------------------------------------------------\n"
                        + "   Your aim is to destroy the enemy nexus 🔻, while protecting your own. 💠\n"
                        + "   To attack a nexus, a champion must first break through the enemies minions.\n\n"
                        + "   Minions spawn in waves at the start of each combat sequence.\n"
                        + "   A combat sequence consists of 5 actions. Each combat action cost 1 action point.\n"
                        + "   Certain actions, such as going to base or purchasing items, cost more action points.\n"
                        + "\n   A minion wave consists of 2 melee minions, and 3 caster minions.\n"
                        + "   Every three waves, a stronger canon minion will be added to the wave.\n"
                        + "\n   Minions award gold when killed, which can be used to purchase items.\n"
                        + "\n   Attempting to buy an item will send you back to base, even if you don't have enough gold!\n"
                        + "------------------------------------------------\n");
        System.out.println("🔮 Minions spawning soon! 🔮\n");

        System.out.println("👤 Choose your champion: 👤\n\n"
                + "(G)aren\n"
                + "(K)atarina\n"
                + "(V)eigar");

    }

    public static void championsSelectedCopy(Champion playerChampion, Champion enemyChampion) {
        System.out.println(
                "\n⚔️ Champions selected! ⚔️\n\n"
                        + "Player Champion 😎" + playerChampion.toString() + "\n\n"
                        + "Enemy Champion 😈" + enemyChampion.toString() + "\n");

        System.out.println("🔮 Minions have spawned! 🔮");
    }

    public static void newWaveCopy(int waveNumber) {
        System.out.println("\n👹 New wave incoming! 👹"
                + "\n👹 Wave number: " + waveNumber + " 👹\n");
    }

    public static void enemyWaveCopy(List<Minion> enemyMinionWave) {
        System.out.println("♦️ ♦️ ♦️ ♦️ ♦️");
        for (Minion minion : enemyMinionWave) {
            System.out.println(minion.toString());
        }
        System.out.println("♦️ ♦️ ♦️ ♦️ ♦️");
    }

    public static void nexusCopy(Nexus nexus) {
        System.out.println(nexus.toString());
    }

    public static void allyWaveCopy(List<Minion> allyMinionWave) {
        System.out.println("🔷🔷🔷🔷🔷");
        for (Minion minion : allyMinionWave) {
            System.out.println(minion.toString());
        }
        System.out.println("🔷🔷🔷🔷🔷");
    }

    public static void baseActionChoiceCopy(int playerActionCount) {
        System.out.println("\nAction count: " + playerActionCount + "\n");
        System.out.println(
                "What would you like to do?\n"
                        + "a: 🔮 Use your ability!\n"
                        + "m: 🗡️  Melee attack!\n"
                        + "b: 🏰 Go to base.\n"
                        + "i: 🧐 View available items.\n"
                        + "p: 💰 Purchase an item.\n"
                        + "q: 😇 Display ally minion wave.\n"
                        + "w: 😈 Display enemy minion wave.\n"
                        + "e: 🏆 Display enemy stats.\n"
                        + "r: 💠 Display ally nexus.\n"
                        + "t: ♦️  Display ally nexus.\n"
                        + "x: ❌ Quit the game");
    }

    public static void chooseTarget(List<Minion> minionWave) {
        System.out.println("\nWhat target would you like to attack?\n");
        System.out.println("🗡️ 🗡️ 🗡️ 🗡️ 🗡️");
        for (int i = 0; i < minionWave.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + minionWave.get(i));
        }
        System.out.println("🗡️ 🗡️ 🗡️ 🗡️ 🗡️");

    }

    public static void chooseAbility(List<Ability> rawAbilities) {
        System.out.println("\nWhat ability would you like to use?\n");
        System.out.println("🔮 🔮 🔮 🔮 🔮");
        for (int i = 1; i <= rawAbilities.size(); i++) {
            System.out.println("[" + i + "] " + rawAbilities.get(i - 1).name());
        }
        System.out.println("🔮 🔮 🔮 🔮 🔮");

    }

    public static void attackNexusCopy(Nexus nexus) {
        System.out.println("\n" + nexus.toString() + " | has been attacked!");
    }

    public static void viewItemsCopy() {
        System.out.println("\n\n[1] " + Item.THORN_MAIL.toString() +
                "\n[2] " + Item.ROD_OF_AGES.toString() +
                "\n[3] " + Item.INFINITY_EDGE.toString() +
                "\n[4] " + Item.RABADONS_DEATHCAP.toString() +
                "\n");
    }

    public static void quitCopy() {
        System.out.println(
                "\nYou are about to quit the game 😵"
                        + "\nAre you sure you want to leave and lose 25 LP?"
                        + "\nYou'll be stuck in elo hell!"
                        + "\n\nType 'x' if you want to quit");
    }

    public static void victoryCopy() {
        System.out.println(
                "\n\n🔻 The Enemy Nexus has been destroyed! 🔻\n\n");

        System.out.println(
                "\n\n"
                        + "\n      💎💎💎💎💎"
                        + "\n    💎💎💎💎💎💎💎"
                        + "\n  💎💎💎💎💎💎💎💎💎"
                        + "\n 💎💎💎        💎💎💎"
                        + "\n💎💎🏆 VICTORY! 🏆💎💎"
                        + "\n 💎💎💎        💎💎💎"
                        + "\n  💎💎💎💎💎💎💎💎💎"
                        + "\n    💎💎💎💎💎💎💎"
                        + "\n      💎💎💎💎💎"
                        + "\n\n");

    }

    public static void defeatCopy() {
        System.out.println(
                "\n\n🔻 The Enemy Nexus has been destroyed! 🔻\n\n");

        System.out.println(
                "\n\n"
                        + "\n      🔻🔻🔻🔻🔻"
                        + "\n    🔻🔻🔻🔻🔻🔻🔻"
                        + "\n  🔻🔻🔻🔻🔻🔻🔻🔻🔻"
                        + "\n 🔻🔻🔻        🔻🔻🔻"
                        + "\n🔻🔻🔺  DEFEAT! 🔺🔻🔻"
                        + "\n 🔻🔻🔻        🔻🔻🔻"
                        + "\n  🔻🔻🔻🔻🔻🔻🔻🔻🔻"
                        + "\n    🔻🔻🔻🔻🔻🔻🔻"
                        + "\n      🔻🔻🔻🔻🔻"
                        + "\n\n");
    }
}
