package com.jpongsick.game.Entities;


import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.jpongsick.game.Config;

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

        this.label = new Label(nickname.toUpperCase() + ": " + score.getPoints(), Config.skin);
    }

    /*------------------------------GETTERS------------------------------*/

    public Platform getPlatform(){
        return this.platform;
    }

    public Score getScore(){
        return this.score;
    }

    public String getNickname() {
        return this.nickname;
    }

    public Label getLabel() {
        return label;
    }
    /*------------------------------SETTERS------------------------------*/

    public void setPlatform(Platform platform){
        this.platform = platform;
    }

    public void setScore(Score score){
        this.score = score;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    /*------------------------------PUBLIC-------------------------------*/

    public static Player createPlayer(Platform platform, Score score, String nickname){
        return new Player(platform, score, nickname);
    }

    public void updateLabel() {
        this.label.setText(nickname.toUpperCase() + ": " + score.getPoints());
    }

    public void resetScore() {
        score.setPoints(0);
    }

}
