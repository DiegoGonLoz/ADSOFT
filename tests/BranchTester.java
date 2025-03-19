package tests;

import branches.*;
import changes.*;
import commits.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Pruebas para la clase Branch.
 * @author Diego González y Diego Lesma
 */
public class BranchTester {
    /**
     * Constructor por defecto de la clase BranchTester.
     * Crea una instancia de BranchTester para realizar pruebas.
     */
    public BranchTester() {
        // Constructor por defecto
    }
    /**
     * Metodo principal para ejecutar las pruebas de la clase Branch.
     *
     * @param args Argumentos de la línea de comandos (no se utilizan).
     */
    public static void main (String[] args) {
        Branch branch1 = new Branch("branch1");
        List<Commit> commits = generateCommits();

        branch1.addCommit(commits.get(0));
        branch1.addCommit(commits.get(1));

        System.out.println(branch1);

        Branch branch2 = new Branch("branch2", branch1);

        System.out.println(branch2);

        branch2.addCommit(commits.getFirst());

        System.out.println(branch2);
    }

    /**
     * Metodo para generar commits para las pruebas de la clase Branch.
     */
    public static List<Commit> generateCommits(){
        Change change = new AddChange(3, "c/:", "This is the content");
        List<Change> changes = List.of(change);
        Commit commit1 = new ChangeCommit("Pepe", "Description", changes);
        List<Commit> commits = List.of(commit1);
        Commit commit2 = new MergeCommit("Alberto", "Description", commits);
        return List.of(commit1, commit2);
    }
}
