package summonersTerminal.gameHelpers;

import java.util.List;
import summonersTerminal.Champion;
import summonersTerminal.Item;
import summonersTerminal.Minion;
import summonersTerminal.Nexus;
import summonersTerminal.champion.abilities.Ability;

public final class Copy {
    public static String statusBarCopy() {
        return
            """
            ▄■▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀■▄
            █■                                                               ■█
            ▀■▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄■▀
            """
        ;
    }

    public static String mainWindowCopy() {
        return
            """
            █▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀█
            ▓▒░                                                                  ░▒▓
            ▓▒░                                                                  ░▒▓
            ▓▒░                                                                  ░▒▓
            ▓▒░                                                                  ░▒▓
            ▓▒░                                                                  ░▒▓
            ▓▒░                                                                  ░▒▓
            ▓▒░                                                                  ░▒▓
            ▓▒░                                                                  ░▒▓
            ▓▒░                                                                  ░▒▓
            ▓▒░                                                                  ░▒▓
            ▓▒░                                                                  ░▒▓
            ▓▒░                                                                  ░▒▓
            ▓▒░                                                                  ░▒▓
            ▓▒░                                                                  ░▒▓
            ▓▒░                                                                  ░▒▓
            █▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄█
            """
        ;
    }


    public static String initialCopy() {
        return
            """
            ▒░                   🔮 Welcome, to Summoner's Terminal! 🔮                   ░▒
            ────────────────────────────────────────────────────────────────────────────────
             Your aim is to destroy the enemy nexus 🔻, while protecting your own. 💎       
             Minions spawn in waves at the start of each combat sequence. A combat sequence 
             consists of 5 actions. Each combat action cost 1 action point. Certain actions—
             such as going to base or purchasing items— cost more action points.            
             A minion wave consists of 2 melee minions, and 3 caster minions. Every three   
             waves, a stronger canon minion will be added to the wave. Minions award gold   
             when killed, which can be used to purchase items. Attempting to buy an item    
             will send you back to base, even if you don't have enough gold!                
            ────────────────────────────────────────────────────────────────────────────────
            ▒░                        🔮 Minions spawning soon! 🔮                        ░▒
            ▒░                         👤 Choose your champion 👤                         ░▒
            ▒░                              ▄▄■■■■▀▀▀■■■■▄▄                               ░▒
            ▒░                              █  (G)aren    █                               ░▒
            ▒░                              █  (K)atarina █                               ░▒
            ▒░                              █▄ (V)eigar  ▄█                               ░▒
            ▒░                              ██▄▄       ▄▄██                               ░▒
            """
        ;
    }

    public static String championsSelectedCopy(Champion playerChampion, Champion enemyChampion) {
        return String.format(
            """
            ⚔️ Champions selected! ⚔️
            
            Player Champion 😎
            %s
            
            Enemy Champion 😈
            %s
            """,
            playerChampion.toString(),
            enemyChampion.toString()
        );
    }
//🔮 Minions have spawned! 🔮
    public static String newWaveCopy(int waveNumber) {
        return String.format(
            """
            👹 New wave incoming! 👹"
            👹 Wave number: %d 👹");
            """,
            waveNumber
        );
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
