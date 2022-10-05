package model;
import java.util.Random;

public class Enemy {
    private Random random;
    private String name;
    private typeEnemy type;
    private double pointsGiven;
    private double pointsTaken;
    private int posX;
    private int posY;

    public Enemy(String name, int type, double pointsGiven, double pointsTaken) {
        this.random = new Random();
        this.name = name;
        this.pointsGiven = pointsGiven;
        this.pointsTaken = pointsTaken;
        this.posX = random.nextInt(1280);
        this.posY = random.nextInt(720);
        setType(type);
    }

    public String getName() {
        return name;
    }

    public typeEnemy getType() {
        return type;
    }

    public double getPointsGiven() {
        return pointsGiven;
    }

    public double getPointsTaken() {
        return pointsTaken;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setType(int selection) {
        switch (selection) {
            case 1:
                this.type = typeEnemy.Ogre;
            case 2:
                this.type = typeEnemy.Abstract;
            case 3:
                this.type = typeEnemy.Magical;
            case 4:
                this.type = typeEnemy.Boss;
        }
    }

    public void setPointsGiven(double pointsGiven) {
        this.pointsGiven = pointsGiven;
    }

    public void setPointsTaken(double pointsTaken) {
        this.pointsTaken = pointsTaken;
    }
}
