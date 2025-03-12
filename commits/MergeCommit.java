package commits;

import java.util.List;
import java.util.Date;

public class MergeCommit extends Commit{
    private List<Commit> commits;

    public MergeCommit(List<Commit> commits) {
        this.commits = commits;
    }


    @Override
    public String toString() {
        return "";
    }



    @Override
    public int totalLinesMoved() {
        return 0;
    }
}
