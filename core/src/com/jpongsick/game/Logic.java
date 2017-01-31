package com.jpongsick.game;


import com.jpongsick.game.Entities.Player;
import com.jpongsick.game.Entities.PlayerManager;
import com.jpongsick.game.Util.Announcer;
import com.jpongsick.game.Util.State;

public abstract class Logic {
    private static boolean isInitialized = false;
    private static JPongSick game;
    public static boolean isAIGame;
    public enum Event {
        NEW_GAME,
        LEFT_PLAYER_SCORED,
        RIGHT_PLAYER_SCORED,
        EXITED_TO_MAIN_MENU,
        GAME_RESUMED,
    }

    public static void initialize(JPongSick g) {
        if(isInitialized) return;
        game = g;

        isInitialized = true;
    }

    public static void handle(Logic.Event e) {
        if(!isInitialized) return;
        switch (e) {
            case NEW_GAME: {
                newGame();
                resumeGame();
                break;
            }
            case LEFT_PLAYER_SCORED: {
                playerScored(PlayerManager.getPlayers().get(0));
                break;
            }
            case RIGHT_PLAYER_SCORED: {
                playerScored(PlayerManager.getPlayers().get(1));
                break;
            }
            case GAME_RESUMED: {
                resumeGame();
                break;
            }
            case EXITED_TO_MAIN_MENU: {
                exitToMenu();
                break;
            }
        }
    }

    private static void newGame() {
        game.getGameScreen().getBall().resetPos();
        PlayerManager.restartPlayers();
        game.getMainMenuScreen().hide();
        game.getGameScreen().show();
        game.setScreen(game.getGameScreen());
    }

    private static void playerScored(Player player) {
        player.getScore().addPoints();
        player.updateLabel();

        if (player.getScore().getPoints() >= Config.maxGoals) {
            game.setState(State.GAME_OVER);
            Announcer.setText(player.getNickname().toUpperCase() + " WON\nESC TO EXIT TO MAIN MENU");
        } else {
            game.setState(State.ROUND_OVER);
            Announcer.setText(player.getNickname().toUpperCase() + " SCORED, PRESS SPACE TO CONTINUE");
            game.getGameScreen().getBall().resetPos();
            PlayerManager.getPlayers().get(0).getPlatform().resetPos();
            PlayerManager.getPlayers().get(1).getPlatform().resetPos();
        }
        Announcer.showLabel();
    }

    private static void resumeGame() {
        game.setState(State.PLAYING);
        Announcer.hideLabel();
    }

    private static void exitToMenu() {
        Announcer.hideLabel();
        game.getGameScreen().hide();
        game.getMainMenuScreen().show();
        game.setState(State.MENU);
        game.setScreen(game.getMainMenuScreen());
    }
}
