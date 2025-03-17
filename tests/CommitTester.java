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
        List<Commit> commits1 = Arrays.asList(changeCommit1, changeCommit2);
        MergeCommit mergeCommit1 = new MergeCommit(null, null, commits1);

        System.out.println(changeCommit1);
        System.out.println(changeCommit2);
        System.out.println(mergeCommit1);

        List<Commit> commits2 = Arrays.asList(changeCommit1, changeCommit2, mergeCommit1);
        MergeCommit mergeCommit2 = new MergeCommit("Pepe", null, commits2);

        System.out.println(mergeCommit2);
    }
}
