package logic;

public interface IMovementBehaviour {

    public int moveUp(int y, int maxY);
    public int moveDown(int y, int maxY);
    public int moveLeft(int x, int maxX);
    public int moveRight(int x, int maxX);

}
