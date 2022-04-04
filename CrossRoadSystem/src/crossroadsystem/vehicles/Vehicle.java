package crossroadsystem.vehicles;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class Vehicle extends Rectangle {

    private int speed = 0;
    private char spawnPoint;
    private char spawnOrigin = ' ';
    private char movingDirection;

    public Vehicle(int x, int y) {
        setX(x);
        setY(y);
        setBorder();
    }

    public Vehicle() {
        setX(0);
        setY(0);
        setBorder();
    }

    public abstract void paintMe(Group group);
    public abstract void startMove();

    public void turn() {
        var newHeight = getWidth();
        setWidth(getHeight());
        setHeight(newHeight);
    }

    public void setSpawnPoint(char spawnPoint) {
        if(spawnOrigin == ' ')
            spawnOrigin = spawnPoint;

        this.spawnPoint = spawnPoint;
    }

    public char getSpawnPoint() {
        return spawnPoint;
    }

    public void setMovingDirection(char movingDirection) {
        this.movingDirection = movingDirection;
    }

    public char getMovingDirection() {
        return movingDirection;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int newSpeed) {
        speed = newSpeed;
    }

    public void stopMove() {
        setSpeed(0);
    }

    public void setOppositeDirection() { // set spawn point to opposite side of moving direction
        switch(movingDirection) {
            case 'N' -> setSpawnPoint('S');
            case 'S' -> setSpawnPoint('N');
            case 'W' -> setSpawnPoint('E');
            case 'E' -> setSpawnPoint('W');
        }
    }

    @Override
    public String toString() {
        var name = getClass().getName().split("[.]");
        return  name[name.length - 1] + // get name of vehicle
                " on X: " + getX() +
                ", Y: " + getY() +
                " Point: " + spawnOrigin +
                " Moving to: " + getMovingDirection();
    }

    private void setBorder() {
        setStroke(Color.BLACK);
        setStrokeWidth(1);
    }
}
