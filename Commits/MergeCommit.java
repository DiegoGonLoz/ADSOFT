package Commits;

import java.util.List;
import java.util.Date;

public class MergeCommit extends Commit{
    private List<ChangeCommit> changeCommits;
    private List<MergeCommit> mergeCommits;
    public MergeCommit(String userName, Date date, String description) {
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
