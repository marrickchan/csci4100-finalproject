package net.uoit.mcjb.csci4100_finalproject;

/**
 * Created by user on 20/11/2016.
 */

public class Score {
    // Todo: add rank number once db integrated
    private String name;
    private long score;

    public Score(String name, long score) {
        this.name = name;
        this.score = score;
    }

    public long getScore() {
        return score;
    }

    public String getName() {
        return name;
    }
}
