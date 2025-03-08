package Commits;

public class ChangeCommit {
    private String type;
    private String file;
    private int linesMoved;

    public ChangeCommit(String type, String file, int linesMoved) {
        this.type = type;
        this.file = file;
        this.linesMoved = linesMoved;
    }
}
