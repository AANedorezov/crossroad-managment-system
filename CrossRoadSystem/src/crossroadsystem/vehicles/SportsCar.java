package crossroadsystem.vehicles;

import javafx.scene.Group;
import javafx.scene.paint.Color;

public class SportsCar extends Vehicle {

    private final int SPEED = 20;

    public SportsCar(int x, int y) {
        super(x, y);
        setDefaultValues();
    }

    public SportsCar() {
        super();
        setDefaultValues();
    }

    private void setDefaultValues() {
        setWidth(15);
        setHeight(20);
        setSpeed(SPEED);
        setFill(Color.DARKORANGE);
    }

    @Override
    public void paintMe(Group group) {
        group.getChildren().add(this);
    }

    @Override
    public void startMove() {
        setSpeed(SPEED);
    }
}
