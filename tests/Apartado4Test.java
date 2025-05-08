import workflows.StreamingStateGraph;
import java.util.List;

public class Apartado4Test {
    public static void main(String[] args) {
        // Tus tests originales
        testAverageWorkflow();
        testMaxWorkflow();

        // Nuevos tests con TextData
        testTextStreamWorkflow();
        testTextAccumulatorWorkflow();
    }

    // Tus métodos originales (sin cambios)
    public static void testAverageWorkflow() {
        StreamingStateGraph<DoubleData> sg = new StreamingStateGraph<>("average", "Calculate running average");

        sg.addNode("calc", data -> {
            double sum = sg.history().stream()
                    .mapToDouble(DoubleData::getValue)
                    .sum();
            double avg = sum / sg.history().size();
            data.setAverage(avg);
        });

        sg.setInitial("calc");
        sg.setFinal("calc");

        System.out.println("--- TEST AVERAGE WORKFLOW ---");
        List.of(1.0, 5.0, 2.0, 4.0).forEach(d -> {
            DoubleData input = new DoubleData(d, 0);
            System.out.println("Workflow input = " + input);
            sg.run(input, true);
            System.out.println("Current history = " + sg.history());
        });
        System.out.println("Final history = " + sg.history());
    }

    public static void testMaxWorkflow() {
        StreamingStateGraph<DoubleData> sg = new StreamingStateGraph<>("max-tracker", "Track maximum value");

        sg.addNode("max", data -> {
            double currentMax = sg.history().stream()
                    .mapToDouble(DoubleData::getValue)
                    .max()
                    .orElse(data.getValue());
            data.setAverage(currentMax);
        });

        sg.setInitial("max");
        sg.setFinal("max");

        System.out.println("\n--- TEST MAX WORKFLOW ---");
        List.of(3.0, 7.0, 2.0, 5.0).forEach(d -> {
            DoubleData input = new DoubleData(d, 0);
            System.out.println("Workflow input = " + input);
            sg.run(input, true);
            System.out.println("Current max = " + sg.history().getLast());
        });
    }

    // Nuevos tests con TextData
    public static void testTextStreamWorkflow() {
        StreamingStateGraph<TextData> sg = new StreamingStateGraph<>("text-stream", "Text stream processing");

        sg.addNode("process", data -> {
            String allTexts = sg.history().stream()
                    .map(TextData::getText)
                    .reduce("", (a,b) -> a + "|" + b);
            data.append(allTexts);
        });

        sg.setInitial("process");
        sg.setFinal("process");

        System.out.println("\n--- TEST TEXT STREAM WORKFLOW ---");
        List.of("first", "second", "third").forEach(text -> {
            TextData input = new TextData(text);
            System.out.println("Workflow input = " + input);
            sg.run(input, true);
            System.out.println("Current state = " + input);
        });
        System.out.println("Final history = " + sg.history());
    }

    public static void testTextAccumulatorWorkflow() {
        StreamingStateGraph<TextData> sg = new StreamingStateGraph<>("text-accumulator", "Text accumulator");

        sg.addNode("accumulate", data -> {
            int totalLength = sg.history().stream()
                    .mapToInt(td -> td.getText().length())
                    .sum();
            data.append("_totalLen" + totalLength);
        });

        sg.setInitial("accumulate");
        sg.setFinal("accumulate");

        System.out.println("\n--- TEST TEXT ACCUMULATOR WORKFLOW ---");
        List.of("one", "two", "three").forEach(text -> {
            TextData input = new TextData(text);
            System.out.println("Workflow input = " + input);
            sg.run(input, true);
            System.out.println("Current accumulation = " + input);
        });
        System.out.println("Final history = " + sg.history());
    }
}
