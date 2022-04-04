package crossroadsystem.logic;

import crossroadsystem.vehicles.SUV;
import crossroadsystem.vehicles.Semi;
import crossroadsystem.vehicles.SportsCar;
import crossroadsystem.vehicles.Vehicle;

import javafx.scene.paint.Color;

import java.util.Random;

public class CarRandomizer {
    private final Random random = new Random();
    private final char[] compass = {'N', 'S', 'W', 'E'};

    public Vehicle getVehicle() {
        byte dice = (byte)random.nextInt(3);
        char spawnPoint = compass[random.nextInt(compass.length)];
        char movingDirection = compass[getDirection(spawnPoint)];

        Vehicle car = switch(dice) {
            case 1 -> new SUV();
            case 2 -> new SportsCar();
            default -> new Semi();
        };
        car.setSpawnPoint(spawnPoint);
        car.setMovingDirection(movingDirection);

        return car;
    }

    public Color getColor() {
        return Color.rgb(
                random.nextInt(256),
                random.nextInt(256),
                random.nextInt(256)
        );
    }

    private int getDirection(char spawnPoint) {
        int movingDirectionIndex = random.nextInt(compass.length);
        while(spawnPoint == compass[movingDirectionIndex])
            movingDirectionIndex = random.nextInt(compass.length);

        return movingDirectionIndex;
    }
}
