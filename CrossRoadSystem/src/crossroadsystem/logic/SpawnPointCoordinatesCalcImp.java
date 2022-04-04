package crossroadsystem.logic;

import crossroadsystem.vehicles.Vehicle;
import java.util.Collection;

public class SpawnPointCoordinatesCalcImp implements ISpawnPointCoordinatesCalc {

    private Collection<Vehicle> cars;
    private ICollision c;

    private final int CROSSROAD_WIDTH = 400;
    private final int CROSSROAD_HEIGHT = 800;

    public SpawnPointCoordinatesCalcImp(Collection cars) {
        this.cars = cars;
        c = new CollisionImpl(cars);
    }

    @Override
    public void spawnNorth(Vehicle vehicle) {
        for(int y = 0; y < CROSSROAD_WIDTH; y += 20) {
            for (int x = 210; x < CROSSROAD_WIDTH; x += 50) {
                vehicle.setX(x);
                vehicle.setY(y);
                if(c.collisionV(x, y, vehicle) == false) {
                    System.out.println("Spawned: " + vehicle);
                    return;
                }
            }
        }
    }

    @Override
    public void spawnSouth(Vehicle vehicle) {
        for(int y = CROSSROAD_HEIGHT - (int)vehicle.getWidth(); y > CROSSROAD_WIDTH; y -= 20) {
            for (int x = 410; x < CROSSROAD_WIDTH + 200; x += 50) {
                vehicle.setX(x);
                vehicle.setY(y);
                if(c.collisionV(x, y, vehicle) == false) {
                    System.out.println("Spawned: " + vehicle);
                    return;
                }
            }
        }
    }

    @Override
    public void spawnWest(Vehicle vehicle) {
        vehicle.turn();
        for(int x = 0; x < CROSSROAD_WIDTH; x -= 20) {
            for (int y = 410; y < CROSSROAD_WIDTH + 200; y += 50) {
                vehicle.setX(x);
                vehicle.setY(y);
                if(c.collisionH(x, y, vehicle) == false) {
                    System.out.println("Spawned: " + vehicle);
                    return;
                }
            }
        }
    }

    @Override
    public void spawnEast(Vehicle vehicle) {
        vehicle.turn();
        for(int x = CROSSROAD_HEIGHT - (int)vehicle.getWidth(); x > CROSSROAD_WIDTH; x += 20) {
            for (int y = 210; y < CROSSROAD_WIDTH; y += 50) {
                vehicle.setX(x);
                vehicle.setY(y);
                if(c.collisionH(x, y, vehicle) == false) {
                    System.out.println("Spawned: " + vehicle);
                    return;
                }
            }
        }
    }
}
