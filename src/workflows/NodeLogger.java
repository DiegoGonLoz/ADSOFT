package workflows;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NodeLogger<T> extends NodeDecorator<T> {
    private final String file;

    public NodeLogger(NodeInterface<T> node, String file) {
        super(node);
        this.file = file;
    }

    @Override
    public void run(T input) {
        super.run(input);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss"));
            writer.write("[" + timestamp + "] Node " + getName() + " executed, with output: " + input);

            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return super.toString() + " [logged]";
    }
}