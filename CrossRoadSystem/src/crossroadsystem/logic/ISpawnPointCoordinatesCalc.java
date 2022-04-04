package crossroadsystem.logic;

import crossroadsystem.vehicles.Vehicle;

public interface ISpawnPointCoordinatesCalc {
    public void spawnNorth(Vehicle vehicle);
    public void spawnSouth(Vehicle vehicle);
    public void spawnWest(Vehicle vehicle);
    public void spawnEast(Vehicle vehicle);
}
