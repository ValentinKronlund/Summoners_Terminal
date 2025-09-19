package summonersTerminal;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import summonersTerminal.gameHelpers.Action;
import summonersTerminal.gameHelpers.Copy;
import summonersTerminal.gameHelpers.Helpers;
import summonersTerminal.gameHelpers.Screen;

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
        String playerName = helper.askLine(scanner, "What is your gamer tag?");
        Screen.get().addIntroMessage().draw();

        String prompt = "Enter champion name (or initial)";
        while (playerChampion == null) {
            try {
                String championRequest = helper.askLine(scanner, prompt);

                switch (championRequest) {
                    case "Garen", "garen", "G", "g": {
                        this.playerChampion = ChampionID.GAREN.create(playerName);
                        break;
                    }
                    case "Katarina", "katarina", "K", "k": {
                        this.playerChampion = ChampionID.KATARINA.create(playerName);
                        break;
                    }
                    case "Veigar", "veigar", "V", "v": {
                        this.playerChampion = ChampionID.VEIGAR.create(playerName);
                        break;
                    }
                    default: {
                        prompt = "That champion does not exist! (Enter name or initial)";
                        throw new IllegalArgumentException();
                    }
                }
            } catch (IllegalArgumentException err) {
                continue;
            }
        }

        this.enemyChampion = ChampionID.VEIGAR.create("Enemy Veigar");

        Screen.get()
            .addMainWindow()
            .addMainMessage(Copy.championsSelectedCopy(playerChampion, enemyChampion))
            .addStatusWindow()
            .addStatusMessage("Press <Enter> to continue...")
            .draw();
        helper.askChar(scanner, "");
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
