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

    public Enemy(String name, String type, double pointsGiven, double pointsTaken) {
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

    public void setType(String name) {
        name = name.toLowerCase();
        switch (name) {
            case "ogro":
                this.type = typeEnemy.Ogre;
            case "abstracto":
                this.type = typeEnemy.Abstract;
            case "magico":
                this.type = typeEnemy.Magical;
            case "jefe":
                this.type = typeEnemy.Boss;
        }
    }

    public void setPointsGiven(double pointsGiven) {
        this.pointsGiven = pointsGiven;
    }

    public void setPointsTaken(double pointsTaken) {
        this.pointsTaken = pointsTaken;
    }

    public String toString() {
        return "Enemy: " + name + "\n" +
                "   Tipo=" + type + "\n" +
                "   Puntos que da=" + pointsGiven + "\n" +
                "   Puntos que quita=" + pointsTaken + "\n" +
                "   Posicion X=" + posX + "\n" +
                "   posicion Y=" + posY + "\n";
    }

}
