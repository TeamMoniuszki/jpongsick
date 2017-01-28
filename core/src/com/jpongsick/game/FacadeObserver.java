package com.jpongsick.game;


import com.badlogic.gdx.utils.Array;
import com.jpongsick.game.Entities.Ball;
import com.jpongsick.game.Entities.Player;
import com.jpongsick.game.Entities.PlayerManager;

public abstract class FacadeObserver {
    private static boolean isInitialized = false;
    private static JPongSick game;
    private static Array<Player> players;

    public enum Event {
        NEW_GAME, LEFT_PLAYER_SCORED, RIGHT_PLAYER_SCORED
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
                restartGame();
                break;
            }
            case LEFT_PLAYER_SCORED: {
                updatePoints(players.get(0));
                break;
            }
            case RIGHT_PLAYER_SCORED: {
                updatePoints(players.get(1));
                break;
            }
            default: {

                break;
            }
        }

    }

    private static void restartGame() {
        game.getGameScreen().getBall().restart();
        players.get(0).getPlatform().restart();
        players.get(1).getPlatform().restart();
        game.getGameScreen().pause();
    }

    private static void updatePoints(Player player) {
        player.getScore().addPoints();
        player.updateLabel();
        restartGame();
    }
}
