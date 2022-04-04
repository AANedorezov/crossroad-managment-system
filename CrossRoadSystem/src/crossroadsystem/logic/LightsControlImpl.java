package crossroadsystem.logic;

import crossroadsystem.Lamp;

public class LightsControlImpl implements ILightsController{
    private Lamp lampNorth;
    private Lamp lampSouth;
    private Lamp lampWest;
    private Lamp lampEast;

    public LightsControlImpl(Lamp lampNorth, Lamp lampSouth,
                             Lamp lampWest, Lamp lampEast) {
        this.lampNorth = lampNorth;
        this.lampSouth = lampSouth;
        this.lampWest = lampWest;
        this.lampEast = lampEast;
    }

    public void vRoadSetYellow() {
        lampNorth.turnYellow();
        lampSouth.turnYellow();
    }

    public void hRoadSetYellow() {
        lampWest.turnYellow();
        lampEast.turnYellow();
    }

    @Override
    public void vRoadGo() {
        hLampsSetRed();
        vLampsSetGreen();
    }

    @Override
    public void hRoadGo() {
        vLampsSetRed();
        hLampsSetGreen();
    }

    @Override
    public void stopWork() {
        lampNorth.turnOff();
        lampSouth.turnOff();
        lampWest.turnOff();
        lampEast.turnOff();
    }

    private void vLampsSetRed() {
        lampNorth.turnRed();
        lampSouth.turnRed();
    }

    private void vLampsSetGreen() {
        lampNorth.turnGreen();
        lampSouth.turnGreen();
    }

    private void hLampsSetRed() {
        lampWest.turnRed();
        lampEast.turnRed();
    }

    private void hLampsSetGreen() {
        lampWest.turnGreen();
        lampEast.turnGreen();
    }
}
