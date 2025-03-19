package tests;

import changes.*;
import commits.*;

import java.util.Arrays;
import java.util.List;

import static tests.ChangeTester.createChanges;

/**
 * Pruebas para la clase Commit.
 * @author Diego González y Diego Lesma
 */
public class CommitTester {
    /**
     * Constructor por defecto de la clase CommitTester.
     * Crea una instancia de CommitTester para realizar pruebas.
     */
    public CommitTester() {
        // Constructor por defecto
    }

    /**
     * Metodo principal para ejecutar las pruebas de la clase Commit.
     *
     * @param args Argumentos de la línea de comandos (no se utilizan).
     */
    public static void main (String[] args) {

        ChangeCommit changeCommit1 = new ChangeCommit("Pepe", "First Commit", createChanges());
        ChangeCommit changeCommit2 = new ChangeCommit("Fernando", "Second Commit", createChanges());
        List<Commit> commits1 = Arrays.asList(changeCommit1, changeCommit2);
        MergeCommit mergeCommit1 = new MergeCommit(null, null, commits1);

        System.out.println(changeCommit1);
        System.out.println(changeCommit1.changes().size());
        System.out.println(changeCommit1.totalLinesMoved());

        System.out.println(changeCommit2);
        System.out.println(changeCommit2.changes().size());
        System.out.println(changeCommit2.totalLinesMoved());

        System.out.println(mergeCommit1);
        System.out.println(mergeCommit1.changes().size());
        System.out.println(mergeCommit1.totalLinesMoved());


        List<Commit> commits2 = Arrays.asList(changeCommit1, changeCommit2, mergeCommit1);
        changeCommit1.setDefaultUserName("defaultUser2");
        MergeCommit mergeCommit2 = new MergeCommit(null, null, commits2);

        System.out.println(mergeCommit2);
        System.out.println(mergeCommit2.changes().size());
        System.out.println(mergeCommit2.totalLinesMoved());

    }
}
