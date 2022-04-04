package crossroadsystem.logic;

import crossroadsystem.vehicles.Vehicle;

public interface ICollision {
    public boolean collisionH(int x, int y, Vehicle v);
    public boolean collisionV(int x, int y, Vehicle v);
}
