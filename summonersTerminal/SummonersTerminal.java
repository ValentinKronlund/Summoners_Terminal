package summonersTerminal;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import summonersTerminal.champion.Champion;
import summonersTerminal.champion.ChampionID;
import summonersTerminal.champion.Passives.Factory;
import summonersTerminal.gameHelpers.Action;
import summonersTerminal.gameHelpers.Copy;
import summonersTerminal.gameHelpers.Helpers;
import summonersTerminal.minion.Minion;

public class SummonersTerminal {
    private static SummonersTerminal instance;

    private Scanner scanner = new Scanner(System.in);
    private Helpers helper = new Helpers();
    private boolean winConditionMet = false;
    private Champion playerChampion;
    private Champion enemyChampion;
    private Nexus enemyNexus = new Nexus("♦️ Enemy Nexus ♦️");
    private Nexus allyNexus = new Nexus("💠 Ally Nexus 💠");
    private List<Minion> enemyMinionWave = new ArrayList<>();
    private List<Minion> allyMinionWave = new ArrayList<>();
    private int waveNumber = 1;

    private SummonersTerminal() {

    }

    public static SummonersTerminal getInstance() {
        if (instance == null) {
            instance = new SummonersTerminal();
        }

        return instance;
    }

    public void PlayGame() {
        InitiateGame();
        GameLoop();
    }

    private void InitiateGame() {
        String playerName = helper.askLine(scanner, "\n\n 🏷️  Before we begin -- What is your gamer tag? ");
        Copy.initialCopy();

        playerChampion = Action.chooseChampion(playerName);
        enemyChampion = ChampionID.VEIGAR.create("Enemy Veigar");

        { // TODO: NOTE(Nat): make a Choose Passive state
            Random random = new Random();
            int playerIndex = random.nextInt(0, Factory.ePassive.DUMMY.ordinal());
            int enemyIndex = random.nextInt(0, Factory.ePassive.DUMMY.ordinal());

            Factory passiveFactory = new Factory();
            playerChampion.setPassive(passiveFactory.Create(Factory.ePassive.values()[playerIndex], playerChampion));
            playerChampion.getPassive().Init();

            enemyChampion.setPassive(passiveFactory.Create(Factory.ePassive.values()[enemyIndex], enemyChampion));
            enemyChampion.getPassive().Init();

            Copy.selectedPassive(playerChampion.name(), playerChampion.getPassive().GetDescription());
            Copy.selectedPassive(enemyChampion.name(), enemyChampion.getPassive().GetDescription());
        }

        Copy.championsSelectedCopy(playerChampion, enemyChampion);
    }

    private void GameLoop() {
        while (!winConditionMet) {
            if (playerChampion.isAlive() == false) {
                playerChampion.respawn();
            }

            if (enemyChampion.isAlive() == false) {
                enemyChampion.respawn();
            }

            Copy.newWaveCopy(waveNumber);

            Action.generateMinionWave(enemyMinionWave, waveNumber, true);
            Action.generateMinionWave(allyMinionWave, waveNumber, false);
            Copy.enemyWaveCopy(enemyMinionWave);
            Copy.allyWaveCopy(allyMinionWave);

            int actionCount = 5;
            playerChampion.walkFromBase();
            enemyChampion.walkFromBase();

            boolean shouldEndGame = Action.mainActionsLoop(allyNexus, enemyNexus, playerChampion, enemyChampion,
                    allyMinionWave,
                    enemyMinionWave,
                    actionCount);

            if (shouldEndGame == true) {
                if (!enemyNexus.isAlive()) {
                    EndGame(true, false);
                    return;
                } else {
                    EndGame(false, true);
                }
            }

            waveNumber++;

            if (winConditionMet) { // <--- Fallback, should never run
                break;
            }
        }
    }

    private void EndGame(boolean enemyNexusDestroyed, boolean allyNexusDestroyed) {
        if (enemyNexusDestroyed) {
            Copy.victoryCopy();
        } else {
            Copy.defeatCopy();
        }
        this.winConditionMet = true;
    }

}
