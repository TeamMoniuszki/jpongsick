package com.jpongsick.game.Entities;


public class Player {
    private Platform platform;
    private Score score;
    private String nickname;

    /*------------------------------CONSTRUCTORS-------------------------*/

    public Player(Platform platform, Score score, String nickname){
        this.platform = platform;
        this.score = score;
        this.nickname = nickname;
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

    /*------------------------------PUBLIC-------------------------------*/

    public static Player createPlayer(Platform platform, Score score, String nickname){
        return new Player(platform, score, nickname);
    }

}
