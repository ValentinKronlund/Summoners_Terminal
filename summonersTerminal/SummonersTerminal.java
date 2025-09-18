package summonersTerminal;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import summonersTerminal.champion.Passives.Factory;
import summonersTerminal.gameHelpers.Action;
import summonersTerminal.gameHelpers.Copy;
import summonersTerminal.gameHelpers.Helpers;

public class SummonersTerminal
{
    Scanner scanner = new Scanner(System.in);
    Helpers helper = new Helpers();

    private boolean winConditionMet = false;
    private Champion playerChampion;
    private Champion enemyChampion;
    private Nexus enemyNexus = new Nexus("♦️ Enemy Nexus ♦️");
    private Nexus allyNexus = new Nexus("💠 Ally Nexus 💠");
    private List<Minion> enemyMinionWave = new ArrayList<>();
    private List<Minion> allyMinionWave = new ArrayList<>();
    private int waveNumber = 1;

    public void PlayGame()
    {
        InitiateGame();
        GameLoop();
    }

    private void InitiateGame()
    {
        String playerName = helper.askLine(scanner, "\nWhat is your gamer tag? ");
        Copy.initialCopy();

        this.playerChampion = Action.chooseChampion(playerName);
        this.enemyChampion = ChampionID.VEIGAR.create("Enemy Veigar");

        { //NOTE(Nat): TO-DO make a Choose Passive state
            Factory passiveFactory = new Factory();
            this.playerChampion.setPassive(passiveFactory.Create(Factory.ePassive.THE_GIANT, this.playerChampion));
            this.playerChampion.getPassive().Init();

            System.out.println("\nSelected passive: " + this.playerChampion.getPassive().GetDescription());
        }

        Copy.championsSelectedCopy(playerChampion, enemyChampion);
    }

    private void GameLoop()
    {
        while (!winConditionMet)
        {
            if (playerChampion.isAlive() == false)
            {
                playerChampion.respawn();
            }

            if (enemyChampion.isAlive() == false)
            {
                enemyChampion.respawn();
            }

            Copy.newWaveCopy(waveNumber);
            Action.generateMinionWave(enemyMinionWave, waveNumber, true);
            Action.generateMinionWave(allyMinionWave, waveNumber, false);
            Copy.enemyWaveCopy(enemyMinionWave);
            Copy.allyWaveCopy(allyMinionWave);

            int playerActionCount = 0;
            playerChampion.walkFromBase();
            enemyChampion.walkFromBase();
            boolean shouldEndGame = Action.mainActionsLoop(enemyNexus, allyNexus, playerChampion, enemyChampion,
                    enemyMinionWave,
                    allyMinionWave,
                    playerActionCount);

            if (shouldEndGame == true)
            {
                if (enemyNexus.isAlive() == false)
                {
                    endGame(true, false);
                }
                endGame(false, true);
            }

            playerChampion.getPassive().Tick();
            waveNumber++;
        }
    }

    public void endGame(boolean enemyNexusDestroyed, boolean allyNexusDestroyed)
    {
        if (enemyNexusDestroyed)
        {
            Copy.victoryCopy();
        } else
        {
            Copy.defeatCopy();
        }
        this.winConditionMet = true;
    }

}
