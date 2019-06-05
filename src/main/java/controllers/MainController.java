package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

import java.io.IOException;

public class MainController {

    private GameController gameController;

    private int fpsCount;
    private int boardSize;
    private String headColor;
    private boolean wallsOn;

    @FXML
    private Pane mainPane;

    @FXML
    private GridPane gamePane;

    @FXML
    private Label scoreNumber;

    @FXML
    private Label welcomeLabel;

    @FXML
    private TextField fpsCountField;

    @FXML
    private TextField boardSizeField;

    @FXML
    private CheckBox wallsOnCheckbox;

    @FXML
    private ColorPicker colorPicker;

    @FXML
    public void headColorPicked() {
        headColor = colorPicker.getValue().toString();
    }

    @FXML
    public void initialize() {

        // set default value of FPS count
        fpsCount = 8;
        fpsCountField.setText(String.valueOf(fpsCount));

        // set default value of board size
        boardSize = 25;
        boardSizeField.setText(String.valueOf(boardSize));

        // score label format
        scoreNumber.setFont(Font.font("Verdana", 20));

        // set welcome label
        welcomeLabel.setFont(Font.font("Verdana", 30));
        welcomeLabel.setText("Click New Game to play!");

        // set default head color
        headColor = "DE7440";

        // default walls are off
        wallsOnCheckbox.setSelected(false);
    }

    @FXML
    public void startGame() {
        if(validateSettings()) {
            // load game controller
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/GameScreen.fxml"));
            GridPane gridPane = null;
            try {
                gridPane = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            gameController = loader.getController();
            gameController.setMainController(this);

            setScreen(gridPane);
        }
    }

    @FXML
    public void pauseGame() {
        if (gameController != null)
            gameController.pause();
    }

    private boolean validateSettings() {

        // validate FPS count
        if (fpsCountField.getText().isEmpty()) {
            showMessage("FPS Value cannot be empty!");
            return false;
        } else {
            try {
                fpsCount = Integer.parseInt(fpsCountField.getText());

            } catch (NumberFormatException e) {
                showMessage("FPS count should be a type of integer!");
                return false;
            }

            if ((fpsCount < 5) || (fpsCount > 100)) {
                showMessage("FPS count should be between 5 and 100!");
                return false;
            }
        }

        // validate board size count
        if (boardSizeField.getText().isEmpty()) {
            showMessage("Board Size cannot be empty!");
            return false;
        } else {
            try {
                boardSize = Integer.parseInt(boardSizeField.getText());

            } catch (NumberFormatException e) {
                showMessage("Board Size should be a type of integer!");
                return false;
            }

            if ((boardSize < 10) || (boardSize > 110)) {
                showMessage("Board Size should be between 10 and 110!");
                return false;
            }

            // fix board size
            while(550%boardSize != 0) {
                boardSize++;
            }
            boardSizeField.setText(Integer.toString(boardSize));
        }

        // turn on / off walls
        wallsOn = wallsOnCheckbox.isSelected();

        return true;
    }

    private void setScreen(GridPane pane) {
        gamePane.getChildren().clear();
        gamePane.add(pane, 0, 0);
    }

    private void showMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning Message");
        alert.setHeaderText("Warning message");
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void setScoreValue(int score) {
        scoreNumber.setText(Integer.toString(score));
    }

    int getScoreValue() {
        return Integer.parseInt(scoreNumber.getText());
    }

    int getFpsCount() {
        return fpsCount;
    }

    int getBoardSize() {
        return boardSize;
    }

    String getHeadColor() {
        return headColor;
    }

    public boolean areWallsOn() {
        return wallsOn;
    }
}
