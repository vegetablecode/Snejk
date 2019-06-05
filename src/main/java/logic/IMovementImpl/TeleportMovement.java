package logic.IMovementImpl;

import logic.IMovementBehaviour;

public class TeleportMovement implements IMovementBehaviour {

    @Override
    public int moveUp(int y, int maxY) {
        y--;
        if (y < 0) {
            y = maxY - 1;
        }
        return y;
    }

    @Override
    public int moveDown(int y, int maxY) {
        y++;
        if (y >= maxY) {
            y = 0;
        }
        return y;
    }

    @Override
    public int moveLeft(int x, int maxX) {
        x--;
        if (x < 0) {
            x = maxX - 1;
        }
        return x;
    }

    @Override
    public int moveRight(int x, int maxX) {
        x++;
        if (x >= maxX) {
            x = 0;
        }
        return x;
    }

}
