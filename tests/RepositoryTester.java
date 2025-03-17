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
        Commit commit1 = new ChangeCommit("Pepe", "Description", changes);

        repository.addUser(U2);
        repository.addUser(U3);

        repository.newBranch("branch1");
        repository.newBranch("branch2", repository.getBranch("branch1"));

        repository.getBranch("branch2");
        repository.getBranch(null);

        repository.newBranch("branch1");

        repository.changeActiveBranch("branch1");

        repository.getBranch("branch1").addCommit(commit1);

        System.out.println(repository);

        //repository.mergeBranch("branch1","branch2", Strategy.ORIGIN);

        //System.out.println(repository);
    }
}
