package commits;

import changes.Change;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Clase que representa un commit de fusión (merge) en un sistema de control de versiones.
 * @author Diego González y Diego Lesma
 */
public class MergeCommit extends Commit {
    private final List<Commit> commits;

    /**
     * Constructor para crear un commit de fusión.
     * @param userName Nombre del usuario que realiza el commit.
     * @param description Descripción del commit.
     * @param commits Lista de commits que se fusionan.
     */
    public MergeCommit(String userName, String description, List<Commit> commits) {
        super(userName, description);
        this.commits = commits;
    }

    /**
     * Representación en cadena del commit de fusión.
     * @return Cadena que describe el commit de fusión.
     */
    @Override
    public String toString() {
        String aux = "";
        for (Commit commit : commits) {
            aux = aux + commit.getCode() + " on " + commit.date.toString() + "\n";
        }
        return "{\ncommit " + String.format("%05d", this.id) + this.code +
                "\nAuthor: " + this.userName +
                "\nDate: " + this.date +
                "\nDescription: " + Arrays.toString(this.description.split(",")) +
                "\n" + aux +
                "\n}\n";
    }

    /**
     * Obtiene la lista de cambios del commit de fusión.
     * @return Lista de cambios.
     */
    @Override
    public List<Change> changes() {
        List<Change> changes = new ArrayList<Change>();
        for (Commit commit : commits) {
            changes.addAll(commit.changes());
        }
        return changes;
    }

    /**
     * Obtiene el número total de líneas afectadas por los cambios del commit de fusión.
     * @return Número total de líneas afectadas.
     */
    @Override
    public int totalLinesMoved() {
        int totalLines = 0;
        for (Commit commit : commits) {
            totalLines = totalLines + commit.totalLinesMoved();
        }
        return totalLines;
    }
}
