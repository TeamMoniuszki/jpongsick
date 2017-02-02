package com.jpongsick.game.Entities;


import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.jpongsick.game.Util.UIManager;

public class Player {
    private Platform platform;
    private Score score;
    private String nickname;
    private Label label;

    /*------------------------------CONSTRUCTORS-------------------------*/

    public Player(Platform platform, Score score, String nickname){
        this.platform = platform;
        this.score = score;
        this.nickname = nickname;

        this.label = new Label(nickname.toUpperCase() + ": " + score.getPoints(), UIManager.skin);
    }

    public static Player createPlayer(Platform platform, Score score, String nickname){
        return new Player(platform, score, nickname);
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


    /*------------------------------PUBLIC-------------------------------*/


    public void updateLabel() {
        this.label.setText(nickname.toUpperCase() + ": " + score.getPoints());
    }

    public void restart() {
        platform.resetPos();
        score.setPoints(0);
    }

}
