package crossroadsystem.ui;

import crossroadsystem.logic.IStartStopListener;
import crossroadsystem.logic.StartStopListenerImpl;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UserInterface {

    private BorderPane bpRoot = new BorderPane();
    private VBox container = new VBox();
    private VBox roadDirections = new VBox();
    private HBox simController = new HBox();
    private Crossroad crossroad = new Crossroad();

    private Button startBtn = new Button("Start");
    private Button stopBtn = new Button("Stop");

    private final Insets PADDING = new Insets(5);
    private final int SPACING = 10;

    private final IStartStopListener startStopListener =
            new StartStopListenerImpl(crossroad);


    public UserInterface(Stage primaryStage) {
        container.setPadding(PADDING);
        simController.setPadding(PADDING);
        simController.setSpacing(SPACING);
        roadDirections.setPadding(PADDING);
        roadDirections.setSpacing(SPACING);

        // Buttons behavior
        startBtn.setOnAction(startStopListener::start);
        stopBtn.setOnAction(startStopListener::stop);
        primaryStage.setOnCloseRequest(startStopListener::exit); // On exit pressed

        // Initializing interface
        simController.getChildren().add(startBtn);
        simController.getChildren().add(stopBtn);

        container.getChildren().add(simController);
        container.getChildren().add(roadDirections);

        bpRoot.setCenter(crossroad);
        bpRoot.setRight(container);

        primaryStage.setTitle("Crossroad");
        primaryStage.setScene(new Scene(bpRoot));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
