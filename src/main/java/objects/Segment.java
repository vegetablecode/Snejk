package objects;


import javafx.scene.shape.Rectangle;
import logic.IMovementBehaviour;
import logic.IGameOverImpl.NoWallsGameOver;
import logic.IMovementImpl.StandardMovement;
import logic.IMovementImpl.TeleportMovement;

public class Segment extends Rectangle {

    public static final int UP = 0;
    public static final int RIGHT = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;

    private int positionX;
    private int prevPositionX;
    private int positionY;
    private int prevPositionY;

    private int blockSize;

    private Segment previous;

    int direction = LEFT;

    private int maxX, maxY;

    // movement behaviour
    private IMovementBehaviour movementBehaviour;

    Segment(int x, int y, Segment segment, Board board) {
        super(board.getBlockSize(), board.getBlockSize());
        positionX = x;
        positionY = y;

        blockSize = board.getBlockSize();

        setTranslateX(positionX * blockSize);
        setTranslateY(positionY * blockSize);

        previous = segment;

        maxX = board.getBoardWidth();
        maxY = board.getBoardHeight();

        // set the right movement
        if(board.getGameOverBehaviour() instanceof NoWallsGameOver) {
            movementBehaviour = new TeleportMovement();
        }
        else {
            movementBehaviour = new StandardMovement();
        }

    }

    void update() {
        // update logical position
        prevPositionX = positionX;
        prevPositionY = positionY;

        // then it's the head
        if (previous == null) {
            switch (direction) {
                case UP:
                    moveUp();
                    break;
                case RIGHT:
                    moveRight();
                    break;
                case DOWN:
                    moveDown();
                    break;
                case LEFT:
                    moveLeft();
                    break;
            }
        } else {
            positionX = previous.prevPositionX;
            positionY = previous.prevPositionY;
        }

        // update visual position
        updatePosition();
    }

    private void moveUp() {
        positionY = movementBehaviour.moveUp(positionY, maxY);
    }

    private void moveDown() {
        positionY = movementBehaviour.moveDown(positionY, maxY);
    }

    private void moveLeft() {
        positionX  = movementBehaviour.moveLeft(positionX, maxX);
    }

    private void moveRight() {
        positionX  = movementBehaviour.moveRight(positionX, maxX);
    }

    private void updatePosition() {
        if((positionY < maxY) && (positionX < maxX) && (positionY >= 0) && (positionX >= 0)) {
            setTranslateX(positionX * blockSize);
            setTranslateY(positionY * blockSize);
        }
    }

    public int getPositionX() {
        return positionX;
    }


    int getPrevPositionX() {
        return prevPositionX;
    }


    public int getPositionY() {
        return positionY;
    }


    int getPrevPositionY() {
        return prevPositionY;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }
}
