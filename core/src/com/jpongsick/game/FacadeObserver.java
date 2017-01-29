package com.jpongsick.game;


import com.badlogic.gdx.utils.Array;
import com.jpongsick.game.Entities.Player;
import com.jpongsick.game.Entities.PlayerManager;
import com.jpongsick.game.Util.Announcer;
import com.jpongsick.game.Util.State;

public abstract class FacadeObserver {
    private static boolean isInitialized = false;
    private static JPongSick game;
    private static Array<Player> players;

    public enum Event {
        NEW_GAME,
        RESTART_ROUND,
        LEFT_PLAYER_SCORED,
        RIGHT_PLAYER_SCORED,
        PAUSE_GAME,
        RESUME_GAME,
        EXIT_TO_MAIN_MENU
    }

    public static void initialize(JPongSick g) {
        if(isInitialized) return;
        game = g;
        players = PlayerManager.getPlayers();

        isInitialized = true;
    }

    public static void notify(Event e) {
        if(!isInitialized) return;
        switch (e) {
            case NEW_GAME: {
                newGame();
                break;
            }
            case RESTART_ROUND: {
                restartRound();
                break;
            }
            case PAUSE_GAME: {
                Announcer.setText("GAME PAUSED, PRESS SPACE TO CONTINUE");
                Announcer.showLabel();
                pauseGame();
                break;
            }
            case RESUME_GAME: {
                resumeGame();
                break;
            }
            case LEFT_PLAYER_SCORED: {
                Announcer.setText(players.get(0).getNickname().toUpperCase() + " SCORED, PRESS SPACE TO CONTINUE");
                updatePoints(players.get(0));
                Announcer.showLabel();
                pauseGame();
                break;
            }
            case RIGHT_PLAYER_SCORED: {
                Announcer.setText(players.get(1).getNickname().toUpperCase() + " SCORED, PRESS SPACE TO CONTINUE");
                updatePoints(players.get(1));
                Announcer.showLabel();
                pauseGame();
                break;
            }
            case EXIT_TO_MAIN_MENU: {
                exitToMainMenu();
                break;
            }
            default: {

                break;
            }
        }

    }

    private static void newGame() {
        restartRound();
        players.get(0).resetScore();
        players.get(1).resetScore();
        players.get(0).updateLabel();
        players.get(1).updateLabel();
    }

    private static void pauseGame() {
        game.setState(State.PAUSE);
    }

    private static void resumeGame() {
        Announcer.hideLabel();
        if(game.getMainMenuScreen().isAiGame()){
            game.setState(State.AI_GAME);
        }
        else {
            game.setState(State.PLAYING);
        }
    }

    private static void restartRound() {
        game.getGameScreen().getBall().restart();
        players.get(0).getPlatform().restart();
        players.get(1).getPlatform().restart();
    }

    private static void playerWon(Player player) {
        game.setState(State.GAME_OVER);
        Announcer.setText(player.getNickname().toUpperCase() + " WON\nPRESS SPACE TO START NEW GAME\nESC TO EXIT TO MAIN MENU");
        Announcer.showLabel();
        pauseGame();
        newGame();
    }

    private static void updatePoints(Player player) {
        player.getScore().addPoints();
        player.updateLabel();

        if (player.getScore().getPoints() >= Config.maxGoals) {
            playerWon(player);
        }
    }

    private static void exitToMainMenu(){
        Announcer.hideLabel();
        game.getGameScreen().hide();
        game.getMainMenuScreen().show();
        game.setState(State.MENU);
        game.setScreen(game.getMainMenuScreen());
    }
}
