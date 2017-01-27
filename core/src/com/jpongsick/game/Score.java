package com.jpongsick.game;

/**
 * Created by Szymon on 17.01.2017.
 */
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
