package helpers;

public class Record implements Comparable<Record> {

    private String name;
    private int score;

    public Record(String name, int score) {
        this.name = name;
        this.score = score;
    }

    String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public int compareTo(Record o) {
        Integer a = this.getScore();
        Integer b = o.getScore();
        return a.compareTo(b);
    }
}
