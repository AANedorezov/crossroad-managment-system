package crossroadsystem.logic;

import crossroadsystem.vehicles.Vehicle;

import java.util.Collection;

public class CollisionImpl implements ICollision {

    private Collection<Vehicle> cars;

    public CollisionImpl(Collection<Vehicle> cars) {
        this.cars = cars;
    }

    @Override
    public boolean collisionH(int x, int y, Vehicle v) {
        for(var car : cars) {
            if(y == car.getY()) { // If cars in the same line
                if(!car.equals(v)) { // Not same vehicle
                    if(x < car.getX() + car.getHeight() &&  // Left side is left of other car right
                            x + v.getHeight() > car.getX() // Right side is right
                    ) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean collisionV(int x, int y, Vehicle v) {
        for(var car : cars) {
            if (x == car.getX()) { // If cars in the same line
                if (!car.equals(v)) { // Not same vehicle
                    if (y < car.getY() + car.getWidth() &&  // Left side is right of other car right
                            y + v.getWidth() > car.getY() // Right side is left of other car left
                    ) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
