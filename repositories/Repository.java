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

    public void mergeBranch(String origin, String target, Strategy strategy){

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

        output += this.activeBranch.toString();

        return output;
    }
}
