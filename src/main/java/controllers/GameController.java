package controllers;

import objects.Board;
import objects.Segment;
import objects.Snake;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

import static consts.GameConsts.SCREEN_SIZE;

public class GameController implements Initializable {
    private int initLength = 5;
    private long then = System.nanoTime();
    private boolean changed = false;
    private int nextUpdate;
    private boolean hasNextUpdate = false;
    private Board board;
    private boolean paused = false;

    private MainController mainController;

    @FXML
    private GridPane gridPane;

    @FXML
    public void onFocus() {
        gridPane.requestFocus();
    }

    void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            // setup the board
            int boardSize = mainController.getBoardSize();
            int fpsCount = mainController.getFpsCount();
            String headColor = mainController.getHeadColor();
            int blockSize = SCREEN_SIZE/boardSize;
            board = new Board(boardSize, boardSize, blockSize, mainController);

            gridPane.requestFocus();
            board.addSnake(new Snake(initLength, board, headColor));

            // game loop
            AnimationTimer timer = new AnimationTimer() {
                @Override
                public void handle(long now) {
                    if (now - then > 1000000000 / fpsCount) { // update 4 times / second
                        board.update();
                        then = now;

                        changed = false;
                        if(hasNextUpdate) {
                            setDirection(board.getSnake(), nextUpdate);
                            hasNextUpdate = false;
                        }

                        // if game is paused
                        if(paused) {
                            // show the alert
                            stop();
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setHeaderText("Game is paused");
                            alert.setContentText("Click ok to return!");
                            Platform.runLater(alert::showAndWait);

                            // return to the game
                            alert.setOnHidden(e -> {
                                paused = false;
                                gridPane.requestFocus();
                                start();
                            });
                        }

                        // if snake is dead
                        if(board.isGameOver()) {
                            // show the alert
                            stop();
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setHeaderText("Game over");
                            alert.setContentText("Your score is: " + board.getScore());
                            Platform.runLater(alert::showAndWait);

                            // start a new game
                            alert.setOnHidden(e -> {
                                gridPane.getChildren().clear();
                                board = new Board(boardSize, boardSize, blockSize, mainController);
                                board.addSnake(new Snake(initLength, board, headColor));
                                board.resetScore();
                                mainController.setScoreValue(0);
                                gridPane.getChildren().add(board);
                                start();
                            });
                        }

                        //mainController.setScoreValue(board.getScore());
                    }
                }
            };

            timer.start();


            gridPane.getChildren().add(board);

            gridPane.setOnKeyPressed(e -> {
                switch (e.getCode()) {
                    case W:
                        if (board.getSnake().getDrection() != Segment.DOWN) {
                            setDirection(board.getSnake(), Segment.UP);
                        }
                        break;
                    case S:
                        if (board.getSnake().getDrection() != Segment.UP) {
                            setDirection(board.getSnake(), Segment.DOWN);
                        }
                        break;
                    case D:
                        if (board.getSnake().getDrection() != Segment.LEFT) {
                            setDirection(board.getSnake(), Segment.RIGHT);
                        }
                        break;
                    case A:
                        if (board.getSnake().getDrection() != Segment.RIGHT) {
                            setDirection(board.getSnake(), Segment.LEFT);
                        }
                        break;
                }
            });
        });

    }

    private void setDirection(Snake snake, int direction) {
        if(!changed) {
            snake.setDirection(direction);
            changed = true;
        } else {
            hasNextUpdate = true;
            nextUpdate = direction;
        }
    }

    void pause() {
        paused = true;
    }
}
