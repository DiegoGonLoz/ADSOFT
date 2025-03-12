package commits;

import changes.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChangeCommit extends Commit {
    private List<Change> changes;

    public ChangeCommit(String userName, String description, List<Change> changes) {
        super(userName, description);
        this.changes = changes;
    }

    @Override
    public String toString() {
        String aux = "";
        for (Change change :  changes){
            aux = aux + change.printForCommit();
        }
        return  "{\ncommit "+this.id+
                "\nAuthor: "+this.userName+
                "\nDate: "+this.date+
                "\nDescription: "+ Arrays.toString(this.description.split(",")) +
                "\n"+ aux +
                "\n}\n";
    }

    @Override
    public List<Change> changes(){
        return changes;
    }

    @Override
    public int totalLinesMoved() {
        int totalLines = 0;
        for (Change change :  changes){
            totalLines = totalLines + change.getNumberOfLines();
        }
        return totalLines;
    }
}
