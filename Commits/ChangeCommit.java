package Commits;

import java.util.List;
import java.util.Date;

public class ChangeCommit extends Commit {
    private List<Change> changes;
    public ChangeCommit(String userName, Date date, String description) {
        super(userName, date, description);
    }

    @Override
    public String printCommit() {
        return "";
    }

    @Override
    public int totalLinesMoved() {
        return 0;
    }
}
