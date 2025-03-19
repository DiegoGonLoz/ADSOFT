package tests;

import branches.*;
import changes.AddChange;
import changes.Change;
import commits.ChangeCommit;
import commits.Commit;
import repositories.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Pruebas para la clase Repository.
 * @author Diego González y Diego Lesma
 */
public class RepositoryTester {
    /**
     * Constructor por defecto de la clase RepositoryTester.
     * Crea una instancia de RepositoryTester para realizar pruebas.
     */
    public RepositoryTester() {
        // Constructor por defecto
    }
    /**
     * Metodo principal para ejecutar las pruebas de la clase Repository.
     *
     * @param args Argumentos de la línea de comandos (no se utilizan).
     */
    public static void main (String[] args) {
        User U1 = new User("test1");
        Repository repository = new Repository("Repository", Strategy.ORIGIN, U1);

        List<Commit> commits = generateCommits();

        testAddUser(repository);

        repository.newBranch("main");

        repository.changeActiveBranch("main");

        repository.getBranch("main").addCommit(commits.get(0));
        repository.getBranch("main").addCommit(commits.get(1));
        repository.getBranch("main").addCommit(commits.get(2));

        System.out.println(repository);

        repository.newBranch("Solving Issue", repository.getBranch("main"));

        testBranchErrors(repository);

        repository.getBranch("Solving Issue").addCommit(commits.get(3));

        repository.changeActiveBranch("Solving Issue");

        System.out.println(repository);

        repository.mergeBranch("Solving Issue","main", Strategy.DESTINY);

        repository.setStrategy(Strategy.DESTINY);

        repository.changeActiveBranch("main");

        System.out.println(repository);

        repository.changeActiveBranch("Solving Issue");

        System.out.println(repository);

        System.out.println(repository.getBranch("main").getCommits().get(3));

        repository.changeActiveBranch("main");

        repository.mergeBranch("Solving Issue","main", Strategy.ADDMERGE);

        System.out.println(repository);

        System.out.println(repository.getBranch("main").getCommits().get(4));
    }

    /**
     * Metodo para generar commits para las pruebas de la clase Repository.
     *
     * @return Lista con los commits para testear
     */
    public static List<Commit> generateCommits(){
        Change change1 = new AddChange(3, "c/:", "This is the content");
        Change change2 = new AddChange(3, "c/:a", "This is the content");
        Change change3 = new AddChange(3, "c/:b", "This is the content");
        Change change4 = new AddChange(3, "c/:c", "This is the content");

        Commit commit1 = new ChangeCommit("Pepe", "no comment", List.of(change1));
        Commit commit2 = new ChangeCommit("Pepe", "Decorator interface", List.of(change2));
        Commit commit3 = new ChangeCommit("Pepe", "Merging previous commits", List.of(change3));
        Commit commit4 = new ChangeCommit("Pepe", "Solving the issue", List.of(change4));

        return List.of(commit1, commit2, commit3, commit4);
    }

    /**
     * Metodo para probar el metodo addUser().
     *
     * @param repository Objeto repositorio
     */
    public static void testAddUser(Repository repository) {
        User U2 = new User("test2");

        repository.addUser(U2);
        repository.addUser(null);
        System.out.println(repository.getUsers());
    }

    /**
     * Metodo para probar que no se puedan hacer tareas prohibidas
     *
     * @param repository Objeto repositorio
     */
    public static void testBranchErrors(Repository repository) {
        repository.newBranch("main");
        repository.getBranch("branch");
        repository.getBranch(null);
    }
}