package summonersTerminal.gameHelpers;

import java.util.List;

import summonersTerminal.Champion;
import summonersTerminal.Item;
import summonersTerminal.Minion;
import summonersTerminal.Nexus;

public class Copy {

    public static void initialCopy() {
        System.out.println("\n🔮 Welcome, to Summoner's Terminal! 🔮");
        System.out.println(
                "\n[ Rules ]: \n"
                        + "------------------------------------------------\n"
                        + "   Your aim is to destroy the enemy nexus 🔻, while protecting your own. 💎\n"
                        + "   To attack a nexus, a champion must first break through the enemies minions.\n\n"
                        + "   Minions spawn in waves at the start of each combat sequence.\n"
                        + "   A combat sequence consists of 5 actions. Each combat action cost 1 action point.\n"
                        + "   Certain actions, such as going to base or purchasing items, cost more action points.\n"
                        + "\n   A minion wave consists of 2 melee minions, and 3 caster minions.\n"
                        + "   Every three waves, a stronger canon minion will be added to the wave.\n"
                        + "\n   Minions award gold when killed, which can be used to purchase items.\n"
                        + "\n   Attempting to buy an item will send you back to base, even if you don't have enough gold!\n"
                        + "------------------------------------------------\n");
        System.out.println("\n🔮 Minions spawning soon! 🔮\n");

        System.out.println("\n👤 Choose your champion: 👤\n\n"
                + "(G)aren\n"
                + "(K)atarina\n"
                + "(V)eigar\n");

    }

    public static void championsSelectedCopy(Champion playerChampion, Champion enemyChampion) {
        System.out.println(
                "\n⚔️ Champions selected! ⚔️\n\n"
                        + "Player Champion 😎" + "\n" + playerChampion.toString() + "\n\n"
                        + "Enemy Champion 😈" + "\n" + enemyChampion.toString() + "\n");

        System.out.println("\n🔮 Minions have spawned! 🔮\n");
    }

    public static void newWaveCopy(int waveNumber) {
        System.out.println("\n👹 New wave incoming! 👹"
                + "\n👹 Wave number: " + waveNumber + " 👹\n");
    }

    public static void waveCopy(List<Minion> minionWave) {
        System.out.println("\n♦️♦️♦️♦️♦️");
        for (Minion minion : minionWave) {
            System.out.println(minion.toString());
        }
        System.out.println("\n♦️♦️♦️♦️♦️");
    }

    public static void baseActionChoiceCopy(int playerActionCount) {
        System.out.println("\n\nAction count: " + playerActionCount + "\n");
        System.out.println(
                "\nWhat would you like to do?\n"
                        + "a: Use your ability!\n"
                        + "m: Melee attack!\n"
                        + "g: Go to base.\n"
                        + "i: View available items.\n"
                        + "p: Purchase an item.\n"
                        + "s: Display your stats.\n"
                        + "e: Display enemy stats.\n"
                        + "w: Display minion wave.\n"
                        + "q: Quit the game\n");
    }

    public static void chooseTarget(List<Minion> minionWave) {
        System.out.println("\nWhat target would you like to attack?\n");
        System.out.println("🗡️ 🗡️ 🗡️ 🗡️ 🗡️");
        for (int i = 0; i < minionWave.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + minionWave.get(i));
        }
        System.out.println("🗡️ 🗡️ 🗡️ 🗡️ 🗡️");

    }

    public static void chooseAbility() {
        System.out.println("\nWhat ability would you like to use?\n");
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
                        + "\n\nType 'q' if you want to quit");
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
}
