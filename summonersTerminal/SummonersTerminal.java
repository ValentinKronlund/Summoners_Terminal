package summonersTerminal;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import summonersTerminal.Champions.Garen;
import summonersTerminal.gameHelpers.Action;
import summonersTerminal.gameHelpers.Copy;
import summonersTerminal.gameHelpers.Helpers;

public class SummonersTerminal {
    Scanner scanner = new Scanner(System.in);
    Helpers helper = new Helpers();

    private boolean winConditionMet = false;
    private Champion playerChampion;
    private Champion enemyChampion;
    private Nexus nexus = new Nexus("Enemy Nexus");
    private List<Minion> minionWave = new ArrayList<>();
    private int waveNumber = 1;

    public void PlayGame() {
        InitiateGame();
        GameLoop();
    }

    private void InitiateGame() {
        Copy.initialCopy();

        while (playerChampion == null) {
            try {
                String championRequest = helper.askLine(scanner, "");

                switch (championRequest) {
                    case "Garen", "garen", "G", "g": {
                        //this.playerChampion = new Champion("Garen", ChampionClass.BRAWLER);
                        this.playerChampion = new Garen();
                        break;
                    }
                    case "Katarina", "katarina", "K", "k": {
                        this.playerChampion = new Champion("Katarina", ChampionClass.ASSASSIN);
                        break;
                    }
                    case "Veigar", "veigar", "V", "v": {
                        this.playerChampion = new Champion("Veigar", ChampionClass.MAGE);
                        break;
                    }
                    default: {
                        System.out.println("\n There is no champion named: " + championRequest);
                        throw new IllegalArgumentException();
                    }
                }
            } catch (IllegalArgumentException err) {
                System.out.println("Try again! ");
                continue;
            }
        }

        this.enemyChampion = new Champion("Lux", ChampionClass.MAGE);

        Copy.championsSelectedCopy(playerChampion, enemyChampion);
    }

    private void GameLoop() {
        while (!winConditionMet) {
            if (playerChampion.getIsDead() == true) {
                playerChampion.respawn();
            }

            Copy.newWaveCopy(waveNumber);
            Action.generateMinionWave(minionWave, waveNumber);
            Copy.waveCopy(minionWave);

            int playerActionCount = 0;
            playerChampion.walkFromBase();

            boolean shouldEndGame = Action.mainActionOptions(nexus, playerChampion, enemyChampion, minionWave,
                    playerActionCount);

            if (shouldEndGame == true) {
                if (nexus.isDestroyed == true) {
                    endGame(true);
                }
                endGame(false);

            }

            waveNumber++;

        }
    }

    public void endGame(boolean nexusDestroyed) {
        if (nexusDestroyed) {
            Copy.victoryCopy();
        }
        this.winConditionMet = true;
    }

}
