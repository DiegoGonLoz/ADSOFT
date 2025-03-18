package branches;

import commits.Commit;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa una rama en un sistema de control de versiones.
 * Autor: Diego González y Diego Lesma
 */
public class Branch {
    private final String name;
    private final String fromBranch;
    private final List<Commit> commits;

    /**
     * Constructor para crear una nueva rama sin rama base.
     * @param name Nombre de la rama.
     */
    public Branch(String name) {
        this.name = name;
        this.commits = new ArrayList<Commit>();
        this.fromBranch = null;
    }

    /**
     * Constructor para crear una nueva rama a partir de otra rama existente.
     * @param name Nombre de la nueva rama.
     * @param branch Rama base desde la cual se crea la nueva rama.
     */
    public Branch(String name, Branch branch) {
        this.name = name;
        this.commits = new ArrayList<>(branch.getCommits());
        this.fromBranch = branch.getName();
    }

    /**
     * Añade un commit a la rama.
     * @param c Commit a añadir.
     */
    public void addCommit(Commit c) {
        this.commits.add(c);
    }

    /**
     * Obtiene la lista de commits de la rama.
     * @return Lista de commits.
     */
    public List<Commit> getCommits() {
        return this.commits;
    }

    /**
     * Obtiene el nombre de la rama.
     * @return Nombre de la rama.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Representación en cadena de la rama.
     * @return Cadena que describe la rama y sus commits.
     */
    @Override
    public String toString() {
        String history = "";
        String from = "";
        for (Commit c : this.commits) {
            history += String.format("%05d", c.getId()) + " - "
                    + c.getDescription().substring(0, Math.min(c.getDescription().length(), 30)) +
                    " at " + c.getDate() + "\n";
        }

        if (this.fromBranch != null) {
            from = " (from " + this.fromBranch + ")";
        }

        return "Branch: " + this.name + from +
                "\n" + this.commits.size() + " commits:" +
                "\n" + history;
    }
}
