package repositories;

import branches.Branch;

public class Repository {
    private final String name;
    private Strategy defaultStrategy;
    private List<Branch> branches;
    private Branch activeBranch;
    private List<User> users;

    public void newBranch(String name){

    }

    public Branch getBranch(String name){

    }

    public void changeActiveBranch(Branch branch){

    }

    public void mergeBranch(
            String origin,
            String target,
            Strategy strategy){

    }

    @Override
    public String toString() {

    }
}
