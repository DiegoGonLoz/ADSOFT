package repositories;

import branches.Branch;
import commits.*;
import changes.*;

import java.util.ArrayList;
import java.util.List;

public class Repository {
    private final String name;
    private Strategy defaultStrategy;
    private final List<Branch> branches;
    private Branch activeBranch;
    private final List<User> users;

    public Repository(String name, Strategy defaultStrategy, User admin) {
        this.name = name;
        this.branches = new ArrayList<Branch>();
        this.users = new ArrayList<User>();
        this.defaultStrategy = defaultStrategy;

        this.branches.add(new Branch("main"));
        this.activeBranch = this.branches.getFirst();

        this.users.add(admin);
    }

    public void setStrategy(Strategy strategy) {
        this.defaultStrategy = strategy;
    }

    public void newBranch(String name){
        for(Branch b: branches){
            if(b.getName().equals(name)){
                System.out.println("Branch already exists");
                return;
            }
        }

        this.branches.add(new Branch(name));
    }

    public void newBranch(String name, Branch branch){
        for(Branch b: branches){
            if(b.getName().equals(name)){
                System.out.println("Branch already exists");
                return;
            }
        }
        this.branches.add(new Branch(name, branch));
    }

    public Branch getBranch(String name){
        for(Branch branch: branches){
            if(branch.getName().equals(name)){
                return branch;
            }
        }

        System.out.println("Branch '"+name+"' does not exist\n");

        return null;
    }

    public void changeActiveBranch(String name){
        for(Branch branch: branches){
            if(branch.getName().equals(name)){
                activeBranch = branch;
                return;
            }
        }

        System.out.println("Not a valid branch name\n");
    }

    public void addUser(User user){
        this.users.add(user);
    }

    public List<String> mergeBranch(String origin, String target, Strategy strategy){
        Branch originBranch = getBranch(origin);
        Branch targetBranch = getBranch(target);
        List<Commit> originCommits = originBranch.getCommits();
        List<Commit> targetCommits = targetBranch.getCommits();
        List<Commit> originCommitsToMerge = new ArrayList<>();
        List<Commit> targetCommitsAfterCommon = new ArrayList<>();
        boolean foundLastCommonCommit = false;
        List<String> conflicts;
        MergeCommit mergeCommit;


        Commit lastCommonCommit = getLastCommonCommit(originCommits, targetCommits);

        for (Commit commit : originCommits) {
            if (foundLastCommonCommit) {
                originCommitsToMerge.add(commit);
            }
            if (commit.getId() == lastCommonCommit.getId()) {
                foundLastCommonCommit = true;
            }
        }

        foundLastCommonCommit = false;
        for (Commit commit : targetCommits) {
            if (foundLastCommonCommit) {
                targetCommitsAfterCommon.add(commit);
            }
            if (commit.getId() == lastCommonCommit.getId()) {
                foundLastCommonCommit = true;
            }
        }

        conflicts = detectConflicts(originCommitsToMerge, targetCommitsAfterCommon);

        if (!conflicts.isEmpty() && (strategy == null || strategy == Strategy.NONE)) {
            return conflicts;
        }

        resolveConflicts(originCommitsToMerge, targetCommitsAfterCommon, strategy != null ? strategy : defaultStrategy);


        mergeCommit = new MergeCommit(null, null, originCommitsToMerge);
        mergeCommit.setDefaultDescription("Merge branches " + origin + " into " + target);

        targetBranch.addCommit(mergeCommit);

        return conflicts;
    }

    private static Commit getLastCommonCommit(List<Commit> originCommits, List<Commit> targetCommits) {
        Commit lastCommonCommit = null;
        for (Commit originCommit : originCommits) {
            for (Commit targetCommit : targetCommits) {
                if (originCommit.getId() == targetCommit.getId()) {
                    lastCommonCommit = originCommit;
                    break;
                }
            }
            if (lastCommonCommit != null) {
                break;
            }
        }

        if (lastCommonCommit == null) {
            throw new IllegalStateException("Las ramas no tienen commits en común.");
        }
        return lastCommonCommit;
    }

    private List<String> detectConflicts(List<Commit> originCommits, List<Commit> targetCommits) {
        List<String> conflicts = new ArrayList<>();

        for (Commit originCommit : originCommits) {
            for (Commit targetCommit : targetCommits) {
                for (Change originChange : originCommit.changes()) {
                    for (Change targetChange : targetCommit.changes()) {
                        if (originChange.getFilePath().equals(targetChange.getFilePath())) {
                            conflicts.add("Conflict on '" + originChange.getFilePath() + "'");
                        }
                    }
                }
            }
        }
        return conflicts;
    }

    private void resolveConflicts(List<Commit> originCommits, List<Commit> targetCommits, Strategy strategy) {
        for (Commit originCommit : originCommits) {
            for (Commit targetCommit : targetCommits) {
                List<Change> originChanges = originCommit.changes();
                List<Change> targetChanges = targetCommit.changes();

                for (Change originChange : originChanges) {
                    for (Change targetChange : targetChanges) {
                        if (originChange.getFilePath().equals(targetChange.getFilePath())) {
                            switch (strategy) {
                                case ORIGIN:
                                    break;
                                case DESTINY:
                                    originChanges.add(targetChange);
                                    originChanges.remove(originChange);
                                    break;
                                case NONE:
                                    throw new IllegalStateException("Conflicto no resuelto en el archivo: " + originChange.getFilePath());
                            }
                        }
                    }
                }
            }
        }
    }



    @Override
    public String toString() {
        String output = "Repository: " + this.name +
                "\nBranches:";

        for (Branch branch : branches) {
            output += "\n- " + branch.getName();

            if(branch.equals(this.activeBranch)){
                output += " (active)";
            }
        }

        output += "\n"+this.activeBranch.toString();

        return output;
    }
}
