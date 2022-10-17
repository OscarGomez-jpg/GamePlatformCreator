package model;

public class Player {
    private String nickname;
    private String name;
    private double score;
    private int lives;

    public Player(String nickname, String name) {
        this.nickname = nickname;
        this.name = name;
        this.score = 10;
        this.lives = 5;
    }

    public String getName() {
        return name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

}