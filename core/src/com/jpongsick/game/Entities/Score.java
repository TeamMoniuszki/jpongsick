package com.jpongsick.game.Entities;

public class Score {
    private int points;

    public Score() {
        points = 0;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points){
        this.points = points;
    }

    public void addPoints() {
        points++;
    }

    public void addPoints(int points) {
        this.points += points;
    }
}
