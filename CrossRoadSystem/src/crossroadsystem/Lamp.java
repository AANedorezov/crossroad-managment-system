package crossroadsystem;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Lamp extends Rectangle {

    public Lamp(int x, int y) {
        setX(x);
        setY(y);
        setWidth(100);
        setHeight(20);
        setStroke(Color.BLACK);
        setStrokeWidth(1);
        turnOff();
    }

    public void turnGreen() {
        setFill(Color.GREEN);
    }

    public void turnRed() {
        setFill(Color.RED);
    }

    public void turnYellow() {
        setFill(Color.YELLOW);
    }

    public void turnOff() {
        setFill(Color.BLACK);
    }

    public void paintMe(Group group) {
        group.getChildren().add(this);
    }

    public void turn() {
        var newHeight = getWidth();
        setWidth(getHeight());
        setHeight(newHeight);
    }
}
