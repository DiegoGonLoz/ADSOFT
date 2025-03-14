package branches;

import commits.Commit;

import java.util.ArrayList;
import java.util.List;

public class Branch {
    private final String name;
    private final String fromBranch;
    private List<Commit> commits;

    public Branch(String name){
        this.name = name;
        this.commits = new ArrayList<Commit>();
        this.fromBranch = null;
    }

    public Branch(String name, Branch branch){
        this.name = name + " (from "+branch.name+")";
        this.commits = branch.commits;
        this.fromBranch = branch.name;
    }

    public void addCommit(Commit c){
        this.commits.add(c);
    }

    public List<Commit> getCommits(){
        return this.commits;
    }

    public String getName(){
        return this.name;
    }

    @Override
    public String toString(){
        String history = "";

        for(Commit c : this.commits){
            history += String.format("%05d", c.getId()) + " - "
                    + c.getDescription().substring(0,Math.min(c.getDescription().length(), 30)) +
                    " at " + c.getDate() +"\n";
        }

        String from = null;

        if (this.fromBranch != null){{
            from = " (from "+this.fromBranch+")";}
        }
        return "Branch: "+this.name+from+
                "\n"+this.commits.size()+"commits:"+
                "\n"+history;

    }
}
