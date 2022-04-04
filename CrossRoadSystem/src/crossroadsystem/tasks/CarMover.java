package crossroadsystem.tasks;

import crossroadsystem.ui.Crossroad;
import crossroadsystem.Lamp;
import crossroadsystem.logic.IMover;
import crossroadsystem.logic.MoverImpl;
import crossroadsystem.vehicles.Vehicle;

import javafx.application.Platform;
import javafx.scene.paint.Color;

public class CarMover implements Runnable{
    private Crossroad crossroad;
    private boolean running = true;
    private IMover mover;

    public CarMover(Crossroad crossroad) {
        this.crossroad = crossroad;
        mover = new MoverImpl(crossroad.cars);
    }


    @Override
    public void run() {
        while(running == true) {
            try {
                move();
                Thread.sleep(100);
            } catch(InterruptedException e) {
                System.out.println("Moving has been stopped.");
                running = false;
            }
        }

    }

    private void move() {
        for(var car : crossroad.cars) {
            if (mover.isEndOfTheRoad(car))
                despawn(car);

            if (mover.isMoveToOppositeSide(car) == false) {
                if (mover.isReachTheTurn(car) == true) {
                    car.turn();
                    car.setOppositeDirection();
                }
            }

            if (isMoveNotAllowed(car) == true) // Light is Red or Yellow and car reach the turn
                car.stopMove(); // Set speed to 0
            else {
                if (car.getSpeed() == 0)
                    car.startMove(); // Recover speed
            }

            step(car);
        }
    }

    private void step(Vehicle v) {
        switch(v.getSpawnPoint()) {
            case 'N' -> mover.stepN2S(v);
            case 'S' -> mover.stepS2N(v);
            case 'W' -> mover.stepW2E(v);
            case 'E' -> mover.stepE2W(v);
        }
    }

    private void despawn(Vehicle car) {
        if(!crossroad.cars.isEmpty())
            crossroad.cars.remove(car); // Remove from existing cars

        if(crossroad.getChildren().contains(car))
            Platform.runLater(() -> crossroad.getChildren().remove(car)); // Remove from the screen

        System.out.println("Despawned: " + car);
    }

    private boolean isMoveNotAllowed(Vehicle car) {
        char direction = car.getSpawnPoint();

        // Return false if light is red and car is reach the traffic light;
        return switch(direction) {
            case 'N' -> isStopLight(crossroad.lampNorth) && isReachTheCrossroad(car);
            case 'S' -> isStopLight(crossroad.lampSouth) && isReachTheCrossroad(car);
            case 'W' -> isStopLight(crossroad.lampWest) && isReachTheCrossroad(car);
            case 'E' -> isStopLight(crossroad.lampEast) && isReachTheCrossroad(car);
            default -> false;
        };

    }

    private boolean isStopLight(Lamp lamp) {
        return isRed(lamp) || isYellow(lamp);
    }

    private boolean isRed(Lamp lamp) {
        return lamp.getFill().equals(Color.RED);
    }

    private boolean isYellow(Lamp lamp) {
        return lamp.getFill().equals(Color.YELLOW);
    }


    private boolean isReachTheCrossroad(Vehicle car) {
        var nCarHead = car.getY() + car.getHeight();
        var nStopLine = crossroad.lampNorth.getY();
        var sCarHead = car.getY();
        var sStopLine = crossroad.lampSouth.getY();
        var wCarHead = car.getX() + car.getWidth();
        var wStopLine = crossroad.lampWest.getX();
        var eCarHead = car.getX();
        var eStopLine = crossroad.lampEast.getX();


        char direction = car.getSpawnPoint();
        return switch(direction) {
            case 'N' -> nCarHead >= nStopLine &&
                    nCarHead <= nStopLine + crossroad.lampNorth.getHeight();

            case 'S' -> sCarHead >= sStopLine &&
                    sCarHead <= sStopLine + crossroad.lampSouth.getHeight();

            case 'W' -> wCarHead >= wStopLine &&
                    wCarHead <= wStopLine + crossroad.lampWest.getWidth();

            case 'E' -> eCarHead >= eStopLine &&
                    eCarHead <= eStopLine + crossroad.lampEast.getWidth();
            default -> false;
        };
    }
}
