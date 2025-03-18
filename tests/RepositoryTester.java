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
        Change change1 = new AddChange(3, "c/:", "This is the content");
        Change change2 = new AddChange(3, "c/:a", "This is the content");
        Change change3 = new AddChange(3, "c/:b", "This is the content");
        Change change4 = new AddChange(3, "c/:c", "This is the content");
        List<Change> changes1 = List.of(change1);
        List<Change> changes2 = List.of(change2);
        List<Change> changes3 = List.of(change3);
        List<Change> changes4 = List.of(change4);

        Commit commit1 = new ChangeCommit("Pepe", "no comment", changes1);

        Commit commit2 = new ChangeCommit("Pepe", "Decorator interface", changes2);
        Commit commit3 = new ChangeCommit("Pepe", "Merging previous commits", changes3);
        Commit commit4 = new ChangeCommit("Pepe", "Solving the issue", changes4);

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

        repository.mergeBranch("Solving Issue","main", Strategy.DESTINY);

        repository.changeActiveBranch("main");

        System.out.println(repository);

        repository.changeActiveBranch("Solving Issue");

        System.out.println(repository);
    }
}
