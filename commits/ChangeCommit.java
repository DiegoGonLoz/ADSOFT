package commits;

import changes.Change;

import java.util.Arrays;
import java.util.List;

/**
 * Clase que representa un commit con cambios específicos.
 * @author Diego González y Diego Lesma
 */
public class ChangeCommit extends Commit {
    private final List<Change> changes;

    /**
     * Constructor para crear un commit con cambios.
     * @param userName Nombre del usuario que realiza el commit.
     * @param description Descripción del commit.
     * @param changes Lista de cambios incluidos en el commit.
     */
    public ChangeCommit(String userName, String description, List<Change> changes) {
        super(userName, description);
        this.changes = changes;
    }

    /**
     * Representación en cadena del commit.
     * @return Cadena que describe el commit y sus cambios.
     */
    @Override
    public String toString() {
        StringBuilder aux = new StringBuilder();

        for (Change change : changes) {
            aux.append(change.printForCommit());
        }
        return "{\ncommit " + String.format("%05d", this.id) + this.code +
                "\nAuthor: " + this.userName +
                "\nDate: " + this.date +
                "\nDescription: " + Arrays.toString(this.description.split("\n")) +
                "\n" + aux +
                "\n}\n";
    }

    /**
     * Obtiene la lista de cambios del commit.
     * @return Lista de cambios.
     */
    @Override
    public List<Change> changes() {
        return changes;
    }

    /**
     * Obtiene el número total de líneas afectadas por los cambios.
     * @return Número total de líneas afectadas.
     */
    @Override
    public int totalLinesMoved() {
        int totalLines = 0;
        for (Change change : changes) {
            totalLines = totalLines + change.getNumberOfLines();
        }
        return totalLines;
    }
}
