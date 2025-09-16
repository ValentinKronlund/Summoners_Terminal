package summonersTerminal;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
        String playerName = helper.askLine(scanner, "\nWhat is your gamer tag? ");
        Copy.initialCopy();

        this.playerChampion = Action.chooseChampion(playerName);
        this.enemyChampion = ChampionID.VEIGAR.create("Enemy Veigar");

        Copy.championsSelectedCopy(playerChampion, enemyChampion);
    }

    private void GameLoop() {
        while (!winConditionMet) {
            if (playerChampion.isDead() == true) {
                playerChampion.respawn();
            }

            Copy.newWaveCopy(waveNumber);
            Action.generateMinionWave(minionWave, waveNumber);
            Copy.waveCopy(minionWave);

            int playerActionCount = 0;
            playerChampion.walkFromBase();
            boolean shouldEndGame = Action.mainActionsLoop(nexus, playerChampion, enemyChampion, minionWave,
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
