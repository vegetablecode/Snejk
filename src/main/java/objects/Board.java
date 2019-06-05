package objects;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;

import static consts.GameConsts.BOARD_BORDER_COLOR;
import static consts.GameConsts.BOARD_BORDER_SIZE;
import static consts.GameConsts.BOARD_COLOR;

public class Board extends Pane {

    // board properties
    private int boardWidth;
    private int boardHeight;
    private int blockSize;

    // game objects
    private Snake snake;
    private Food food;
    private ArrayList<Segment> segments = new ArrayList<>();

    // score
    public int score = 0;

    public Board(int boardWidth, int boardHeight, int blockSize) {
        // set board properties
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.blockSize = blockSize;

        setMinSize(boardWidth * blockSize, boardHeight * blockSize);
        setBackground(new Background(new BackgroundFill(BOARD_COLOR, null, null)));
        setBorder(new Border(new BorderStroke((BOARD_BORDER_COLOR), BorderStrokeStyle.SOLID, null, new BorderWidths(BOARD_BORDER_SIZE))));

        // add food to board
        addFood();
    }

    // updates every block in the field
    public void update() {
        for (Segment segment : segments) {
            segment.update();
        }

        if (isEaten(food)) {
            score += 10;
            addFood();
            addNewBlock();
        }
    }

    private void addNewBlock() {
        Segment segment = new Segment(snake.getTail().getPrevPositionX(), snake.getTail().getPrevPositionY(), snake.getTail(), this);
        snake.setTail(segment);
        addBlock(segment);
    }

    private void addFood() {
        int randomX = (int) (Math.random() * boardWidth);
        int randomY = (int) (Math.random() * boardHeight);

        // remove old food from the board
        getChildren().remove(food);

        // add new food to the board
        Food food = new Food(randomX, randomY, blockSize);
        getChildren().add(food);
        this.food = food;
    }

    public boolean isGameOver() {
        for (Segment segment : segments) {
            if (segment != snake.getHead()) {
                if (segment.getPositionX() == snake.getHead().getPositionX() && segment.getPositionY() == snake.getHead().getPositionY()) {
                    return true;
                }
            }
        }
        return false;
    }

    public void addSnake(Snake snake) {
        this.snake = snake;

        for (Segment segment : snake.getSegments()) {
            addBlock(segment);
        }
    }

    private void addBlock(Segment segment) {
        getChildren().add(segment);
        segments.add(segment);
    }

    private boolean isEaten(Food food) {
        if (food == null)
            return false;
        return food.getPosX() == snake.getHead().getPositionX() && food.getPosY() == snake.getHead().getPositionY();
    }

    int getBoardWidth() {
        return boardWidth;
    }

    int getBoardHeight() {
        return boardHeight;
    }

    int getBlockSize() {
        return blockSize;
    }

    public Snake getSnake() {
        return snake;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }
}
