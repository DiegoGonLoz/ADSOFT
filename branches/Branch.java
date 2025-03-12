package branches;

import commits.Commit;

import java.util.ArrayList;
import java.util.List;

public class Branch {
    private final String name;
    private List<Commit> commits;

    public Branch(String name){
        this.name = name;
        this.commits = new ArrayList<Commit>();
    }

    public Branch(String name, Branch branch){
        this.name = name + " (from "+branch.name+")";
        this.commits = branch.commits;
    }

    public void addCommit(Commit c){
        this.commits.add(c);
    }

    public List<Commit> getCommits(){
        return this.commits;
    }

    @Override
    public String toString(){

    }
}
