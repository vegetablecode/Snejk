package logic.IGameOverImpl;

import logic.IGameOverBehaviour;
import objects.Segment;
import objects.Snake;

import java.util.List;

public class NoWallsGameOver implements IGameOverBehaviour {

    @Override
    public boolean isGameOver(List<Segment> segments, Snake snake) {
        for (Segment segment : segments) {
            if (segment != snake.getHead()) {
                if (segment.getPositionX() == snake.getHead().getPositionX() && segment.getPositionY() == snake.getHead().getPositionY()) {
                    return true;
                }
            }
        }
        return false;
    }
}
