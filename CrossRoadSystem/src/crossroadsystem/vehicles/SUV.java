package crossroadsystem.vehicles;

import javafx.scene.Group;
import javafx.scene.paint.Color;

public class SUV extends Vehicle {

    private final int SPEED = 15;

    public SUV(int x, int y) {
        super(x, y);
        setDefaultValues();
    }

    public SUV() {
        super();
        setDefaultValues();
    }

    private void setDefaultValues() {
        setWidth(20);
        setHeight(30);
        setSpeed(8);
        setFill(Color.FIREBRICK);
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
