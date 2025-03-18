package tests;

import branches.*;
import changes.*;
import commits.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Pruebas para la clase Branch.
 * Autor: Diego González y Diego Lesma
 */
public class BranchTester {
    /**
     * Metodo principal para ejecutar las pruebas de la clase Branch.
     *
     * @param args Argumentos de la línea de comandos (no se utilizan).
     */
    public static void main (String[] args) {
        Branch branch1 = new Branch("branch1");
        Change change = new AddChange(3, "c/:", "This is the content");
        List<Change> changes = List.of(change);
        Commit commit1 = new ChangeCommit("Pepe", "Description", changes);
        List<Commit> commits = List.of(commit1);
        Commit commit2 = new MergeCommit("Alberto", "Description", commits);
        //add commits
        branch1.addCommit(commit1);
        branch1.addCommit(commit2);

        System.out.println(branch1);

        Branch branch2 = new Branch("branch2", branch1);

        System.out.println(branch2);


        branch2.addCommit(commit1);

        System.out.println(branch2);
    }
}
