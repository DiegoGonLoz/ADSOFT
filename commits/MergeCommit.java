package commits;

import changes.Change;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Date;

public class MergeCommit extends Commit{
    private final List<Commit> commits;

    public MergeCommit(String userName, String description, List<Commit> commits) {
        super(userName, description);
        this.commits = commits;
    }


    @Override
    public String toString() {
        String aux = "";
        for (Commit commit :  commits){
            aux = aux + commit.getCode() +" on "+commit.date.toString()+"\n";
        }
        return  "{\ncommit "+this.code+
                "\nAuthor: "+this.userName+
                "\nDate: "+this.date+
                "\nDescription: "+ Arrays.toString(this.description.split(",")) +
                "\n"+ aux +
                "\n}\n";
    }
    @Override
    public List<Change> changes(){
        List<Change> changes = new ArrayList<Change>();
        for (Commit commit : commits){
            changes.addAll(commit.changes());
        }
        return changes;
    }

    @Override
    public int totalLinesMoved() {
        int totalLines = 0;
        for (Commit commit :  commits){
            totalLines = totalLines + commit.totalLinesMoved();
        }
        return totalLines;
    }
}
