package objects;

import javafx.scene.shape.Rectangle;

import static consts.GameConsts.FOOD_COLOR;

class Food extends Rectangle {

    private int posX, posY;

    Food(int posX, int posY, int blockSize) {
        super(blockSize, blockSize);

        this.posX = posX;
        this.posY = posY;

        setTranslateX(posX * blockSize);
        setTranslateY(posY * blockSize);

        setFill(FOOD_COLOR);
    }

    int getPosX() {
        return posX;
    }

    int getPosY() {
        return posY;
    }
}
