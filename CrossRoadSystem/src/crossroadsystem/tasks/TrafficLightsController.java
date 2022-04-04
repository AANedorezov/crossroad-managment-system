package crossroadsystem.tasks;

import crossroadsystem.Lamp;
import crossroadsystem.logic.ILightsController;
import crossroadsystem.logic.LightsControlImpl;

public class TrafficLightsController implements Runnable{

    private ILightsController lightsControl;
    private boolean running = true;
    private final int TIME = 5000;

    public TrafficLightsController(Lamp lampNorth, Lamp lampSouth,
                                   Lamp lampWest, Lamp lampEast) {
        lightsControl = new LightsControlImpl(lampNorth, lampSouth, lampWest, lampEast);
    }

    @Override
    public void run() {
        while(running == true) {
            try {
                // Vertical road cycle
                lightsControl.hRoadSetYellow(); // Before setting green
                Thread.sleep(TIME / 5);
                lightsControl.vRoadGo();
                Thread.sleep(TIME);
                lightsControl.hRoadSetYellow(); // After red
                Thread.sleep(TIME / 2);

                // Horizontal road cycle
                lightsControl.vRoadSetYellow(); // Before setting green
                Thread.sleep(TIME / 5);
                lightsControl.hRoadGo();
                Thread.sleep(TIME);
                lightsControl.vRoadSetYellow(); // After red
                Thread.sleep(TIME / 2);
            } catch (InterruptedException e) {
                System.out.println("Traffic lights has been stopped.");
                lightsControl.stopWork();
                running = false;
            }
        }
    }
}
