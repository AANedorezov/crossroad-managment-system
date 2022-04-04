package crossroadsystem.tasks;

import crossroadsystem.ui.Crossroad;
import crossroadsystem.logic.CarRandomizer;
import crossroadsystem.logic.ISpawnPointCoordinatesCalc;
import crossroadsystem.logic.SpawnPointCoordinatesCalcImp;
import crossroadsystem.vehicles.Vehicle;

import javafx.application.Platform;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

public class CarSpawner implements Runnable {

    private Crossroad crossroad;
    private Collection<Vehicle> newCars = Collections.synchronizedCollection(new LinkedList<>());
    private final CarRandomizer random = new CarRandomizer();
    private final ISpawnPointCoordinatesCalc coordinatesCalc;
    private boolean running = true;

    public CarSpawner(Crossroad crossroad) {
        this.crossroad = crossroad;
        coordinatesCalc = new SpawnPointCoordinatesCalcImp(crossroad.getCars());
    }

    @Override
    public void run() {
        while(running == true) {
            try {
                if (!newCars.isEmpty()) {
                    for (var car : newCars)
                        spawnCar(car);
                    newCars.clear();
                }

                var car = random.getVehicle();
                car.setFill(random.getColor());
                newCars.add(car);
                Thread.sleep(3000);

            } catch(InterruptedException e) {
                System.out.println("Spawning has been stopped.");
                running = false;
            }
        }
    }

    private void spawnCar(Vehicle vehicle) {
        Platform.runLater(() -> drawCar(vehicle));
        switch(vehicle.getSpawnPoint()) {
            case 'N' -> coordinatesCalc.spawnNorth(vehicle);
            case 'S' -> coordinatesCalc.spawnSouth(vehicle);
            case 'W' -> coordinatesCalc.spawnWest(vehicle);
            case 'E' -> coordinatesCalc.spawnEast(vehicle);
        }
    }

    private void drawCar(Vehicle car) {
        crossroad.addCar(car);
    }
}
