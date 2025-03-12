package repositories;

import branches.Branch;

import java.util.ArrayList;
import java.util.List;

public class Repository {
    private final String name;
    private Strategy defaultStrategy;
    private List<Branch> branches;
    private Branch activeBranch;
    private List<User> users;

    public Repository(String name, Strategy defaultStrategy, User admin) {
        this.name = name;
        this.branches = new ArrayList<Branch>();
        this.users = new ArrayList<User>();
        this.defaultStrategy = defaultStrategy;

        this.branches.add(new Branch("main"));
        this.activeBranch = this.branches.getFirst();

        this.users.add(admin);
    }

    public void newBranch(String name){

    }

    public void newBranch(Branch branch){

    }

    public Branch getBranch(String name){

    }

    public void changeActiveBranch(Branch branch){

    }

    public void mergeBranch(String origin, String target, Strategy strategy){

    }


    @Override
    public String toString() {

    }
}
