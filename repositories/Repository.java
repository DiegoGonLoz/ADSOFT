package repositories;

import branches.Branch;
import commits.*;
import changes.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un repositorio con ramas, usuarios y estrategias de fusión.
 *
 * @author Diego González y Diego Lesma
 */
public class Repository {
    private final String name;
    private Strategy defaultStrategy;
    private final List<Branch> branches;
    private Branch activeBranch;
    private final List<User> users;

    /**
     * Constructor de la clase Repository.
     *
     * @param name            Nombre del repositorio.
     * @param defaultStrategy Estrategia de fusión por defecto.
     * @param admin           Usuario administrador del repositorio.
     */
    public Repository(String name, Strategy defaultStrategy, User admin) {
        this.name = name;
        this.branches = new ArrayList<Branch>();
        this.users = new ArrayList<User>();
        this.defaultStrategy = defaultStrategy;

        this.branches.add(new Branch("main"));
        this.activeBranch = this.branches.getFirst();

        this.users.add(admin);
    }

    /**
     * Establece la estrategia de fusión por defecto.
     *
     * @param strategy Estrategia a establecer.
     */
    public void setStrategy(Strategy strategy) {
        this.defaultStrategy = strategy;
    }

    /**
     * Crea una nueva rama con el nombre especificado.
     *
     * @param name Nombre de la nueva rama.
     */
    public void newBranch(String name){
        for(Branch b: branches){
            if(b.getName().equals(name)){
                System.out.println("Branch already exists");
                return;
            }
        }

        this.branches.add(new Branch(name));
    }

    /**
     * Crea una nueva rama a partir de una rama existente.
     *
     * @param name   Nombre de la nueva rama.
     * @param branch Rama existente de la cual se creará la nueva rama.
     */
    public void newBranch(String name, Branch branch){
        for(Branch b: branches){
            if(b.getName().equals(name)){
                System.out.println("Branch already exists");
                return;
            }
        }
        this.branches.add(new Branch(name, branch));
    }

    /**
     * Obtiene una rama por su nombre.
     *
     * @param name Nombre de la rama a buscar.
     * @return La rama encontrada o null si no existe.
     */
    public Branch getBranch(String name){
        for(Branch branch: branches){
            if(branch.getName().equals(name)){
                return branch;
            }
        }

        System.out.println("Branch '"+name+"' does not exist\n");

        return null;
    }

    /**
     * Cambia la rama activa del repositorio.
     *
     * @param name Nombre de la rama a activar.
     */
    public void changeActiveBranch(String name){
        for(Branch branch: branches){
            if(branch.getName().equals(name)){
                activeBranch = branch;
                return;
            }
        }

        System.out.println("Not a valid branch name\n");
    }

    /**
     * Añade un usuario al repositorio.
     *
     * @param user Usuario a añadir.
     */
    public void addUser(User user){
        this.users.add(user);
    }

    /**
     * Fusiona una rama de origen en una rama de destino utilizando una estrategia específica.
     *
     * @param origin   Nombre de la rama de origen.
     * @param target   Nombre de la rama de destino.
     * @param strategy Estrategia de fusión a utilizar.
     * @return Lista de conflictos detectados durante la fusión.
     */
    public List<String> mergeBranch(String origin, String target, Strategy strategy){
        Branch originBranch = getBranch(origin);
        Branch targetBranch = getBranch(target);
        List<Commit> originCommits = originBranch.getCommits();
        List<Commit> targetCommits = targetBranch.getCommits();
        List<Commit> originCommitsToMerge = new ArrayList<>();
        List<Commit> targetCommitsAfterCommon = new ArrayList<>();
        List<Commit> finalCommits;
        boolean foundLastCommonCommit = false;
        List<String> conflicts;
        MergeCommit mergeCommit;


        Commit lastCommonCommit = getLastCommonCommit(originCommits, targetCommits);

        for (Commit commit : originCommits) {
            if (foundLastCommonCommit) {
                originCommitsToMerge.add(commit);
            }
            if (commit.getId() == lastCommonCommit.getId()) {
                foundLastCommonCommit = true;
            }
        }

        foundLastCommonCommit = false;
        for (Commit commit : targetCommits) {
            if (foundLastCommonCommit) {
                targetCommitsAfterCommon.add(commit);
            }
            if (commit.getId() == lastCommonCommit.getId()) {
                foundLastCommonCommit = true;
            }
        }

        conflicts = detectConflicts(originCommitsToMerge, targetCommitsAfterCommon);

        if (!conflicts.isEmpty() && (strategy == null || strategy == Strategy.NONE)) {
            return conflicts;
        }

        finalCommits = resolveConflicts(originCommitsToMerge, targetCommitsAfterCommon, strategy != null ? strategy : defaultStrategy);


        mergeCommit = new MergeCommit(null, null, finalCommits);
        mergeCommit.setDefaultDescription("Merge branches " + origin + " into " + target);

        targetBranch.addCommit(mergeCommit);

        return conflicts;
    }

    /**
     * Obtiene el último commit común entre dos listas de commits.
     *
     * @param originCommits Lista de commits de la rama de origen.
     * @param targetCommits Lista de commits de la rama de destino.
     * @return El último commit común.
     * @throws IllegalStateException Si no se encuentra un commit común.
     */
    private static Commit getLastCommonCommit(List<Commit> originCommits, List<Commit> targetCommits) {
        Commit lastCommonCommit = null;
        for (Commit originCommit : originCommits) {
            for (Commit targetCommit : targetCommits) {
                if (originCommit.getId() == targetCommit.getId()) {
                    lastCommonCommit = originCommit;
                }
            }
        }

        if (lastCommonCommit == null) {
            throw new IllegalStateException("Las ramas no tienen commits en común.");
        }
        return lastCommonCommit;
    }

    /**
     * Detecta conflictos entre dos listas de commits.
     *
     * @param originCommits Lista de commits de la rama de origen.
     * @param targetCommits Lista de commits de la rama de destino.
     * @return Lista de conflictos detectados.
     */
    private List<String> detectConflicts(List<Commit> originCommits, List<Commit> targetCommits) {
        List<String> conflicts = new ArrayList<>();

        for (Commit originCommit : originCommits) {
            for (Commit targetCommit : targetCommits) {
                for (Change originChange : originCommit.changes()) {
                    for (Change targetChange : targetCommit.changes()) {
                        if (originChange.getFilePath().equals(targetChange.getFilePath())) {
                            conflicts.add("Conflict on '" + originChange.getFilePath() + "'");
                        }
                    }
                }
            }
        }
        return conflicts;
    }

    /**
     * Resuelve conflictos entre dos listas de commits utilizando una estrategia específica.
     *
     * @param originCommits Lista de commits de la rama de origen.
     * @param targetCommits Lista de commits de la rama de destino.
     * @param strategy      Estrategia de resolución de conflictos.
     * @return Lista de commits finales después de resolver los conflictos.
     */
    private List<Commit> resolveConflicts(List<Commit> originCommits, List<Commit> targetCommits, Strategy strategy) {
        List<Commit> finalCommits = new ArrayList<>();
        for (Commit originCommit : originCommits) {
            for (Commit targetCommit : targetCommits) {
                List<Change> originChanges = originCommit.changes();
                List<Change> targetChanges = targetCommit.changes();

                for (Change originChange : originChanges) {
                    for (Change targetChange : targetChanges) {
                        if (originChange.getFilePath().equals(targetChange.getFilePath())) {
                            switch (strategy) {
                                case ORIGIN:
                                    finalCommits.add(originCommit);
                                    break;
                                case DESTINY:
                                    finalCommits.add(targetCommit);
                                    break;
                                case NONE:
                                    throw new IllegalStateException("Conflicto no resuelto en el archivo: " + originChange.getFilePath());
                            }
                        }
                    }
                }
            }
        }
        return finalCommits;
    }


    /**
     * Representación en cadena del repositorio.
     *
     * @return Cadena que describe el repositorio y sus ramas.
     */
    @Override
    public String toString() {
        String output = "Repository: " + this.name +
                "\nBranches:";

        for (Branch branch : branches) {
            output += "\n- " + branch.getName();

            if(branch.equals(this.activeBranch)){
                output += " (active)";
            }
        }

        output += "\n"+this.activeBranch.toString();

        return output;
    }
}
