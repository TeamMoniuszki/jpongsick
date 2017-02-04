package com.jpongsick.game;


import com.jpongsick.game.Entities.AI;
import com.jpongsick.game.Entities.Player;
import com.jpongsick.game.Entities.PlayerManager;
import com.jpongsick.game.Util.Announcer;
import com.jpongsick.game.Util.State;

import static com.jpongsick.game.Entities.AI.Difficulty.HARD;
import static com.jpongsick.game.Entities.AI.Difficulty.PLAYER;
import static com.jpongsick.game.Entities.AI.Difficulty.SHOWOFF;

public abstract class Logic {
    private static boolean isInitialized = false;
    private static JPongSick game;
    public static boolean isAIGame;
    public enum Event {
        NEW_GAME,
        LEFT_PLATFORM_HIT,
        RIGHT_PLATFORM_HIT,
        LEFT_PLAYER_SCORED,
        RIGHT_PLAYER_SCORED,
        GAME_RESUMED,
        EXITED_TO_MAIN_MENU,
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

            case LEFT_PLATFORM_HIT: {
                AI.handleEvent(e);
                break;
            }

            case RIGHT_PLATFORM_HIT: {
                AI.handleEvent(e);
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
        setPlayersAiType();
        game.getGameScreen().getBall().resetPos();
        Physics.ghostBall.resetPos();
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
            Physics.resetPhysics();
        }
        Announcer.showLabel();
    }

    private static void resumeGame() {
        game.setState(State.PLAYING);
        Announcer.hideLabel();
    }

    private static void setPlayersAiType(){
        if(!game.getMainMenuScreen().getAiCheckbox().isChecked()){
            game.getGameScreen().getPlayer1().setAiType(PLAYER);
            game.getGameScreen().getPlayer2().setAiType(PLAYER);
        }
        else {
            switch ((AI.Difficulty)game.getMainMenuScreen().getDifficultySelection().getSelected()){
                case SHOWOFF:{
                    game.getGameScreen().getPlayer1().setAiType(HARD);
                    game.getGameScreen().getPlayer2().setAiType(HARD);
                    break;
                }
                default: {
                    game.getGameScreen().getPlayer1().setAiType(PLAYER);
                    game.getGameScreen().getPlayer2().setAiType((AI.Difficulty)game.getMainMenuScreen().getDifficultySelection().getSelected());
                    break;
                }
            }
        }

    }

    private static void exitToMenu() {
        game.getGameScreen().getPlayer1().setAiType(HARD);
        game.getGameScreen().getPlayer2().setAiType(HARD);
        Announcer.hideLabel();
        game.getGameScreen().hide();
        game.getMainMenuScreen().show();
        game.setState(State.MENU);
        game.setScreen(game.getMainMenuScreen());
    }
}
