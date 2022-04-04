package crossroadsystem.logic;

import crossroadsystem.tasks.CarMover;
import crossroadsystem.tasks.CarSpawner;
import crossroadsystem.tasks.TrafficLightsController;
import crossroadsystem.ui.Crossroad;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.stage.WindowEvent;

public class StartStopListenerImpl implements IStartStopListener{
    private Crossroad crossroad;

    private Thread mover = null;
    private Thread spawner = null;
    private Thread lights = null;

    private boolean running = false;

    public StartStopListenerImpl(Crossroad crossroad) {
        this.crossroad = crossroad;
    }

    @Override
    public void start(ActionEvent e) {
        if(!running) {
            spawner = new Thread(new CarSpawner(crossroad));
            mover = new Thread(new CarMover(crossroad));
            lights = new Thread(new TrafficLightsController(
                    crossroad.lampNorth, crossroad.lampSouth,
                    crossroad.lampWest, crossroad.lampEast));

            spawner.start();
            mover.start();
            lights.start();

            running = true;
            System.out.println("Starting the simulation...");
        }
        else
            System.out.println("Simulation already running.");
    }

    @Override
    public void stop(ActionEvent e) {
        if(running) {
            spawner.interrupt();
            mover.interrupt();
            lights.interrupt();

            running = false;
            System.out.println("Pausing the simulation...");
        }
        else
            System.out.println("Nothing to stop.");
    }

    @Override
    public void exit(WindowEvent e) {
        if(mover != null && spawner != null && lights != null) {
            mover.interrupt();
            spawner.interrupt();
            lights.interrupt();
        }
        Platform.exit();
        System.exit(0);
    }
}
