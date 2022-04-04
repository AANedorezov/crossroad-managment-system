package crossroadsystem.logic;

import crossroadsystem.vehicles.Vehicle;

import java.util.Collection;

public class MoverImpl implements IMover{
    private final int CROSSROAD_WIDTH = 400;
    private final int CROSSROAD_HEIGHT = 800;
    private final int LANE_HEIGHT = 50;

    private ICollision collision;

    public MoverImpl(Collection cars) {
        collision = new CollisionImpl(cars);
    }

    @Override
    public void stepW2E(Vehicle car) {
        if(!collision.collisionH((int) car.getX() + car.getSpeed(),
                (int) car.getY(),
                car)
        ) { // If there is no collision
            car.setX(car.getX() + car.getSpeed());
        }
        else { // Car ahead
            if(car.getY() > 410 && // Not rightmost lane
                    !collision.collisionH((int)car.getX(), (int)car.getY() - LANE_HEIGHT, car))
                car.setY(car.getY() - LANE_HEIGHT);

            else if(car.getY() < 410 + 3 * LANE_HEIGHT && // Leftmost lane
                    !collision.collisionH((int)car.getX(), (int)car.getY() + LANE_HEIGHT, car))
                car.setY(car.getY() + LANE_HEIGHT);
        }
    }

    @Override
    public void stepE2W(Vehicle car) {
        if(!collision.collisionH((int) car.getX() - car.getSpeed(),
                (int) car.getY(),
                car)
        ) { // If there is no collision
            if (car.getX() - car.getWidth() <= 0) { // If end of the road
                if(!collision.collisionH(0, (int)car.getY(), car))
                    car.setX(CROSSROAD_HEIGHT - car.getWidth());

            }
            car.setX(car.getX() - car.getSpeed());
        }
        else { // Car ahead
            if(car.getY() > 210 && // Not leftmost lane
                    !collision.collisionH((int)car.getX(), (int)car.getY() - LANE_HEIGHT, car))
                car.setY(car.getY() - LANE_HEIGHT);

            else if(car.getY() < 210 + 3 * LANE_HEIGHT && // Leftmost lane
                    !collision.collisionH((int)car.getX(), (int)car.getY() + LANE_HEIGHT, car))
                car.setY(car.getY() + LANE_HEIGHT);
        }
    }

    @Override
    public void stepN2S(Vehicle car) {
        if(!collision.collisionV((int) car.getX(),
                (int) car.getY() + car.getSpeed(),
                car)
        ) { // If there is no collision
            if (car.getY() + car.getWidth() >= CROSSROAD_HEIGHT) { // If end of the road
                if(!collision.collisionV(210, (int)car.getY(), car))
                    car.setY(0);

            }
            car.setY(car.getY() + car.getSpeed());
        }
        else { // Car ahead
            if(car.getX() > 210 && // Not leftmost lane
                    !collision.collisionV((int)car.getY(), (int)car.getX() - LANE_HEIGHT, car))
                car.setX(car.getX() - LANE_HEIGHT);

            else if(car.getX() < 210 + 3 * LANE_HEIGHT && // Leftmost lane
                    !collision.collisionV((int)car.getY(), (int)car.getX() + LANE_HEIGHT, car))
                car.setX(car.getX() + LANE_HEIGHT);
        }
    }

    @Override
    public void stepS2N(Vehicle car) {
        if(!collision.collisionV((int) car.getX(),
                (int) car.getY() - car.getSpeed(),
                car)
        ) { // If there is no collision
            if (car.getY() - car.getWidth() <= 0) { // If end of the road
                if(!collision.collisionV(410, (int)car.getY(), car))
                    car.setY(CROSSROAD_HEIGHT);

            }
            car.setY(car.getY() - car.getSpeed());
        }
        else { // Car ahead
            if(car.getX() > 410 && // Not leftmost lane
                    !collision.collisionV((int)car.getY(), (int)car.getX() + LANE_HEIGHT, car))
                car.setX(car.getX() - LANE_HEIGHT);

            else if(car.getX() < CROSSROAD_WIDTH + 200 && // Leftmost lane
                    !collision.collisionV((int)car.getY(), (int)car.getX() - LANE_HEIGHT, car))
                car.setX(car.getX() + LANE_HEIGHT);
        }
    }

    @Override
    public boolean isReachTheTurn(Vehicle car) {
        return switch(car.getMovingDirection()) {
            case 'N' -> car.getX() >= 410 && car.getX() < 560;
            case 'S' -> car.getX() >= 210 && car.getX() < 360;
            case 'W' -> car.getY() >= 210 && car.getY() < 360;
            case 'E' -> car.getY() >= 410 && car.getY() < 560;
            default -> false;
        };
    }

    @Override
    public boolean isMoveToOppositeSide(Vehicle car) {
        return switch(car.getSpawnPoint()) {
            case 'N' -> car.getMovingDirection() == 'S';
            case 'S' -> car.getMovingDirection() == 'N';
            case 'W' -> car.getMovingDirection() == 'E';
            case 'E' -> car.getMovingDirection() == 'W';
            default -> true;
        };
    }

    @Override
    public boolean isEndOfTheRoad(Vehicle car) {
        return switch(car.getSpawnPoint()) {
            case 'N' -> car.getY() + car.getWidth() >= CROSSROAD_HEIGHT;
            case 'S' -> car.getY() - car.getWidth() <= 0;
            case 'W' -> car.getX() + car.getWidth() >= CROSSROAD_HEIGHT;
            case 'E' -> car.getX() - car.getWidth() <= 0;
            default -> true;
        };
    }

}
