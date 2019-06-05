package objects;

import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Snake {

    private Segment head;
    private Segment tail;

    private String headColor;

    private ArrayList<Segment> segments = new ArrayList<>();

    public Snake(int initLength, Board board, String headColor) {
        this.headColor = headColor;
        int segmentPosX = board.getBoardWidth() / 2;
        int segmentPosY = board.getBoardHeight() / 2;

        head = new Segment(segmentPosX, segmentPosY, null, board);
        segments.add(head);

        head.setFill(Color.web(headColor));


        tail = head;

        for (int i=1; i<initLength; i++) {
            Segment segment = new Segment(segmentPosX + i, segmentPosY, tail, board);
            segments.add(segment);
            tail = segment;
        }
    }

    public int getDrection() {
        return head.direction;
    }

    public void setDirection(int direction) {
        head.direction = direction;
    }

    Segment getHead() {
        return head;
    }

    Segment getTail() {
        return tail;
    }

    public void setHead(Segment head) {
        this.head = head;
    }

    void setTail(Segment tail) {
        this.tail = tail;
    }

    public ArrayList<Segment> getSegments() {
        return segments;
    }

    public void setSegments(ArrayList<Segment> segments) {
        this.segments = segments;
    }
}
