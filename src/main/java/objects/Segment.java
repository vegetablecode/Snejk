package objects;


import javafx.scene.shape.Rectangle;

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
        positionY--;
        if (positionY < 0) {
            positionY = maxY - 1;
        }
    }

    private void moveDown() {
        positionY++;
        if (positionY >= maxY) {
            positionY = 0;
        }
    }

    private void moveLeft() {
        positionX--;
        if (positionX < 0) {
            positionX = maxX - 1;
        }
    }

    private void moveRight() {
        positionX++;
        if (positionX >= maxX) {
            positionX = 0;
        }
    }

    private void updatePosition() {
        setTranslateX(positionX * blockSize);
        setTranslateY(positionY * blockSize);
    }

    int getPositionX() {
        return positionX;
    }


    int getPrevPositionX() {
        return prevPositionX;
    }


    int getPositionY() {
        return positionY;
    }


    int getPrevPositionY() {
        return prevPositionY;
    }

}
