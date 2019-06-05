package logic.IMovementImpl;

import logic.IMovementBehaviour;

public class StandardMovement implements IMovementBehaviour {

    @Override
    public int moveUp(int y, int maxY) {
        y--;
        return y;
    }

    @Override
    public int moveDown(int y, int maxY) {
        y++;
        return y;
    }

    @Override
    public int moveLeft(int x, int maxX) {
        x--;
        return x;
    }

    @Override
    public int moveRight(int x, int maxX) {
        x++;
        return x;
    }

}
