package crossroadsystem.logic;

import javafx.event.ActionEvent;
import javafx.stage.WindowEvent;

public interface IStartStopListener {
    public void start(ActionEvent e);
    public void stop(ActionEvent e);
    public void exit(WindowEvent e);
}
