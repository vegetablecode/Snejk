package logic;

import objects.Segment;
import objects.Snake;

import java.util.List;

public interface IGameOverBehaviour {
    public boolean isGameOver(List<Segment> segments, Snake snake);
}
