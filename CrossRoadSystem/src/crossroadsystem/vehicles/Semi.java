package crossroadsystem.vehicles;

import javafx.scene.Group;
import javafx.scene.paint.Color;

public class Semi extends Vehicle {

    private final int SPEED = 12;

    public Semi(int x, int y) {
        super(x, y);
        setDefaultValues();
    }

    public Semi() {
        super();
        setDefaultValues();
    }

    private void setDefaultValues() {
        setWidth(30);
        setHeight(40);
        setSpeed(5);
        setFill(Color.AQUA);
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
