package tests;

import changes.*;
import commits.*;

import java.util.Arrays;
import java.util.List;

import static tests.ChangeTester.createChanges;


public class CommitTester {
    public static void main (String[] args) {

        ChangeCommit changeCommit1 = new ChangeCommit("Pepe", "First Commit", createChanges());
        ChangeCommit changeCommit2 = new ChangeCommit("Fernando", "Second Commit", createChanges());
        List<Commit> commits = Arrays.asList(changeCommit1, changeCommit2);
        MergeCommit mergeCommit = new MergeCommit(commits);

        mergeCommit.toString();
    }
}
