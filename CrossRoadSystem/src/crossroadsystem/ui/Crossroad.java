package crossroadsystem.ui;

import crossroadsystem.Lamp;
import crossroadsystem.vehicles.Vehicle;

import javafx.scene.Group;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;

public class Crossroad extends Group {
    private final CrossroadRenderer renderer = new CrossroadRenderer(this);
    public Collection<Vehicle> cars = Collections.synchronizedCollection(new CopyOnWriteArrayList<>());

    private final int CROSSROAD_WEIGHT = 400;
    private final int CROSSROAD_HEIGHT = 800;

    public Lamp lampNorth;
    public Lamp lampSouth;
    public Lamp lampWest;
    public Lamp lampEast;

    public Crossroad() { // Initialize all core components of the crossroad
        renderer.render();
        drawLamps();
    }

    public void addCar(Vehicle vehicle) {
        cars.add(vehicle);
        vehicle.paintMe(this);
    }

    public Collection<Vehicle> getCars() {
        return cars;
    }

    private void drawLamps() {
        var roadB4turn = CROSSROAD_WEIGHT / 2;
        int lampHeight = 20;
        int lampWeight = 100;

        lampNorth = new Lamp(CROSSROAD_WEIGHT - lampWeight - 2, roadB4turn - lampHeight);
        lampNorth.paintMe(this);

        lampSouth = new Lamp(CROSSROAD_HEIGHT - CROSSROAD_WEIGHT + 2, CROSSROAD_HEIGHT - roadB4turn);
        lampSouth.paintMe(this);

        lampWest = new Lamp(roadB4turn - lampHeight, CROSSROAD_WEIGHT + 2);
        lampWest.turn();
        lampWest.paintMe(this);

        lampEast = new Lamp(CROSSROAD_HEIGHT - roadB4turn, roadB4turn + roadB4turn / 2 - 2);
        lampEast.turn();
        lampEast.paintMe(this);
    }
}
