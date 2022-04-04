package crossroadsystem.ui;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class CrossroadRenderer {
    private Group crossroad;
    private final int CROSSROAD_WEIGHT = 400;
    private final int CROSSROAD_HEIGHT = 800;

    public CrossroadRenderer(Group group) {
        this.crossroad = group;
    }

    public void render() {
        initializeCrossroad();
    }

    private void initializeCrossroad() {
        drawBackground();
        drawRoads();
        drawLanes();
        drawLines();
    }

    private void drawBackground() {
        crossroad.getChildren().add(new Rectangle(CROSSROAD_HEIGHT,CROSSROAD_HEIGHT, Color.DARKGREEN));
    }

    private void drawRoads() {
        Rectangle vRoad = new Rectangle(CROSSROAD_WEIGHT, CROSSROAD_HEIGHT, Color.GREY);
        vRoad.setX(CROSSROAD_WEIGHT / 2);
        crossroad.getChildren().add(vRoad);

        Rectangle hRoad = new Rectangle(CROSSROAD_HEIGHT, CROSSROAD_WEIGHT, Color.GREY);
        hRoad.setY(CROSSROAD_WEIGHT / 2);
        crossroad.getChildren().add(hRoad);
    }

    private void drawLanes() {
        int roadMid = CROSSROAD_HEIGHT / 2;
        int roadB4turn = CROSSROAD_WEIGHT / 2;

        // Lines for vertical road lanes
        drawLane(roadMid, 0, roadMid, roadB4turn); // Top
        drawLane(roadMid, CROSSROAD_HEIGHT - roadB4turn, roadMid, CROSSROAD_HEIGHT); // Bottom

        // Lines for horizontal road lanes
        drawLane(0, roadMid, roadB4turn, roadMid); // Left
        drawLane(CROSSROAD_HEIGHT - roadB4turn, roadMid, CROSSROAD_HEIGHT, roadMid); // Right
    }

    private void drawLane(int starX, int startY, int endX, int endY) {
        Line line = new Line(starX, startY, endX, endY);
        line.setStrokeWidth(3);
        line.setStroke(Color.WHITE);
        crossroad.getChildren().add(line);
    }

    private void drawLines() {
        int roadB4turn = CROSSROAD_WEIGHT / 2;

        // Vertical roads lines
        drawVLines(0, roadB4turn); // Top
        drawVLines(CROSSROAD_HEIGHT - roadB4turn, CROSSROAD_HEIGHT); // Bottom

        // Horizontal road lines
        drawHLines(0, roadB4turn); // Left
        drawHLines(CROSSROAD_HEIGHT - roadB4turn, CROSSROAD_HEIGHT); // Right
    }

    private void drawVLines(int vStartCoordinates, int vEndCoordinates) {
        int vRoadStart = CROSSROAD_WEIGHT / 2;
        int roadStep = 50;
        int lineStep = 10;
        int lineWidth = 2;

        for(int x = vRoadStart + roadStep; x < vRoadStart * 3; x += roadStep) {
            if(x == vRoadStart * 2) // Skip one line that divide road directions
                continue;
            for(int y = vStartCoordinates; y < vEndCoordinates; y += lineStep * 2) {
                var line = new Rectangle(lineWidth, lineStep);
                line.setX(x);
                line.setY(y);
                line.setFill(Color.WHITE);
                crossroad.getChildren().add(line);
            }
        }
    }

    public void drawHLines(int hStartCoordinates, int hEndCoordinates) {
        int hRoadStart = CROSSROAD_WEIGHT / 2;
        int roadStep = 50;
        int lineStep = 10;
        int lineWidth = 2;

        for(int y = hRoadStart + roadStep; y < hRoadStart * 3; y += roadStep) {
            if(y == hRoadStart * 2) // Skip one line that divide road directions
                continue;
            for(int x = hStartCoordinates; x < hEndCoordinates; x += lineStep * 2) {
                var line = new Rectangle(lineStep, lineWidth);
                line.setX(x);
                line.setY(y);
                line.setFill(Color.WHITE);
                crossroad.getChildren().add(line);
            }
        }
    }
}
