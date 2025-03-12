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
        return  "{\nuserName="+this.userName+
                "\nid="+this.id+
                "\ndate="+this.date+
                "\ndescription="+ Arrays.toString(this.description.split(",")) +
                "\nchanges="+this.changes.toString()+
                "\n}\n";
    }

    @Override
    public int totalLinesMoved() {
        return 0;
    }
}
