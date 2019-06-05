package logic.IGameOverImpl;

import logic.IGameOverBehaviour;
import objects.Segment;
import objects.Snake;

import java.util.List;

public class WallsGameOver implements IGameOverBehaviour {

    @Override
    public boolean isGameOver(List<Segment> segments, Snake snake) {
        // collision with self
        for (Segment segment : segments) {
            if (segment != snake.getHead()) {
                if (segment.getPositionX() == snake.getHead().getPositionX() && segment.getPositionY() == snake.getHead().getPositionY()) {
                    return true;
                }
            }
        }

        // collision with walls
        Segment head = snake.getHead();
        return (head.getPositionX() >= head.getMaxX()) || (head.getPositionX() < 0) ||
                (head.getPositionY() >= head.getMaxY()) || (head.getPositionY() < 0);

    }

}
