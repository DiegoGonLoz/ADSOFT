package tests;

import branches.*;
import changes.AddChange;
import changes.Change;
import commits.ChangeCommit;
import commits.Commit;
import repositories.*;

import java.util.List;

public class RepositoryTester {
    public static void main (String[] args) {
        User U1 = new User("test1");
        Repository repository = new Repository("Repository", Strategy.ORIGIN, U1);
        User U2 = new User("test2");
        User U3 = new User("test3");
        Change change = new AddChange(3, "c/:", "This is the content");
        List<Change> changes = List.of(change);
        Commit commit1 = new ChangeCommit("Pepe", "no comment", changes);

        Commit commit2 = new ChangeCommit("Pepe", "Decorator interface", changes);
        Commit commit3 = new ChangeCommit("Pepe", "Merging previous commits", changes);
        Commit commit4 = new ChangeCommit("Pepe", "Solving the issue", changes);

        repository.addUser(U2);
        repository.addUser(U3);

        repository.newBranch("main");

        repository.newBranch("main");

        repository.changeActiveBranch("main");

        repository.getBranch("main").addCommit(commit1);
        repository.getBranch("main").addCommit(commit2);
        repository.getBranch("main").addCommit(commit3);

        System.out.println(repository);

        repository.newBranch("Solving Issue", repository.getBranch("main"));

        repository.getBranch("branch");
        repository.getBranch(null);

        repository.getBranch("Solving Issue").addCommit(commit4);

        repository.changeActiveBranch("Solving Issue");

        System.out.println(repository);

        repository.mergeBranch("Solving Issue","main", Strategy.ORIGIN);

        System.out.println(repository);
    }
}
