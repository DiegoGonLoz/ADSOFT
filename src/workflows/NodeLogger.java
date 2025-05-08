package workflows;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Clase NodeLogger
 * @param <T> objeto asociado
 * @author Diego Gonzalez
 */
public class NodeLogger<T> extends NodeDecorator<T> {
    /** fichero asociado */
    private final String file;

    /**
     * Constructor de NodeLogger
     * @param node nodo tipo interfaz
     * @param file fichero asociado
     */
    public NodeLogger(NodeInterface<T> node, String file) {
        super(node);
        this.file = file;
    }

    /**
     * Metodo run
     * @param input input del metodo run
     */
    @Override
    public void run(T input) {
        super.run(input);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

            writer.write(timestamp + " - Node " + getName() + " executed, with output: " + input);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }

    /**
     * Metodo toString de NodeLogger
     * @return String con la información del nodo
     */
    @Override
    public String toString() {
        return super.toString() + " [logged]";
    }
}