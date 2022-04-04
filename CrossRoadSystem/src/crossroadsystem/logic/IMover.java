package crossroadsystem.logic;

import crossroadsystem.vehicles.Vehicle;

public interface IMover {
    public void stepW2E(Vehicle v);
    public void stepE2W(Vehicle v);
    public void stepN2S(Vehicle v);
    public void stepS2N(Vehicle v);
    public boolean isReachTheTurn(Vehicle car);
    public boolean isMoveToOppositeSide(Vehicle car);
    public boolean isEndOfTheRoad(Vehicle car);
}
