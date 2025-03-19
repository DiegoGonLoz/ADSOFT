package tests;

import changes.*;
import commits.*;

import java.util.Arrays;
import java.util.List;

import static tests.ChangeTester.createChanges;

/**
 * Pruebas para la clase Commit.
 * Autor: Diego González y Diego Lesma
 */
public class CommitTester {
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
        List<Commit> commits2 = Arrays.asList(changeCommit1, changeCommit2, mergeCommit1);
        MergeCommit mergeCommit2;

        testFunctionality(changeCommit1);
        testFunctionality(changeCommit2);
        testFunctionality(mergeCommit1);

        changeCommit1.setDefaultUserName("defaultUser2");
        mergeCommit2 = new MergeCommit(null, null, commits2);

        testFunctionality(mergeCommit2);
    }

    /**
     * Metodo para probar distintas funciones de la clase Commit.
     *
     * @param commit Objeto commit que se prueba
     */
    public static void testFunctionality(Commit commit){
        System.out.println(commit);
        System.out.println(commit.changes().size());
        System.out.println(commit.totalLinesMoved());
    }
}
