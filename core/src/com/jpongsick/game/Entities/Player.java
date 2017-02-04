package com.jpongsick.game.Entities;


import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.jpongsick.game.Input;
import com.jpongsick.game.Util.UIManager;

public class Player {
    private Platform platform;
    private Score score;
    private String nickname;
    private Label label;
    private AI.Difficulty aiType;

    private int movement;

    /*------------------------------CONSTRUCTORS-------------------------*/

    public Player(Platform platform, Score score, String nickname, AI.Difficulty difficulty){
        this.platform = platform;
        this.score = score;
        this.nickname = nickname;
        this.movement = 0;
        this.label = new Label(nickname.toUpperCase() + ": " + score.getPoints(), UIManager.skin);
        this.aiType = difficulty;

    }

    public static Player createPlayer(Platform platform, Score score, String nickname, AI.Difficulty difficulty){
        return new Player(platform, score, nickname, difficulty);
    }

    /*------------------------------GETTERS, SETTERS------------------------------*/

    public Platform getPlatform(){
        return this.platform;
    }

    public void setPlatform(Platform platform){
        this.platform = platform;
    }

    public Score getScore(){
        return this.score;
    }

    public void setScore(Score score){
        this.score = score;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public AI.Difficulty getAiType() {
        return aiType;
    }

    public void setAiType(AI.Difficulty aiType) {
        this.aiType = aiType;
    }

    public int getMovement() {
        return movement;
    }

    public void setMovement(int movement) {
        this.movement = movement;
    }

    /*------------------------------PUBLIC-------------------------------*/


    public void updateLabel() {
        this.label.setText(nickname.toUpperCase() + ": " + score.getPoints());
    }

    public void restart() {
        platform.resetPos();
        score.setPoints(0);
    }

    public void updateMovement(){
        switch(aiType){
            case PLAYER:{
                movement = Input.getMovement(this);
                break;
            }
            default: {
                movement = AI.getMovement(this);
            }
        }
    }

}
