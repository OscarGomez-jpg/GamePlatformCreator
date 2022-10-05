package model;
import java.util.Random;

public class Loot {
    private Random random;
    private String name;
    private String URL;
    private double points;
    private int posX;
    private int posY;

    public Loot(String name, String URL, double points) {
        this.random = new Random();
        this.name = name;
        this.URL = URL;
        this.points = points;
        this.posX = random.nextInt(1280 + 1);
        this.posY = random.nextInt(720 + 1);
    }

    public String getName() {
        return name;
    }

    public String getURL() {
        return URL;
    }

    public double getPoints() {
        return points;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setURL(String uRL) {
        URL = uRL;
    }
}
