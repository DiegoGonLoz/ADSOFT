import workflows.StreamingStateGraph;

import java.util.List;

/**
 * Clase test para el apartado 4
 * @author Diego Lesma
 */
public class Apartado4Test {
    /**
     * Metodo main a ejecutar
     * @param args vacío
     */
    public static void main(String[] args) {
        testAverageWorkflow();
        testTextStreamWorkflow();
    }

    /**
     * Test con DoubleData
     */
    public static void testAverageWorkflow() {
        StreamingStateGraph<DoubleData> sg = buildAverageWorkflow();

        System.out.println(sg);
        List.of(1.0, 5.0, 2.0, 4.0).forEach(d -> {
            DoubleData wfInput = new DoubleData(d, 0);
            System.out.println("Workflow input = " + wfInput);
            sg.run(wfInput, true);
        });
        System.out.println("History=" + sg.history());
    }

    /**
     * Test con TextData
     */
    public static void testTextStreamWorkflow() {
        StreamingStateGraph<TextData> sg = new StreamingStateGraph<>("text-stream", "Text stream processing");

        sg.addNode("process", dataList -> {
            TextData current = dataList.get(dataList.size() - 1);
            String allTexts = dataList.stream()
                    .map(TextData::getText)
                    .reduce("", (a, b) -> a + "|" + b);
            current.append(allTexts);
        });

        sg.setInitial("process");

        System.out.println("\n--- TEST TEXT STREAM WORKFLOW ---");
        List.of("first", "second", "third").forEach(text -> {
            TextData input = new TextData(text);
            System.out.println("Workflow input = " + input);
            sg.run(input, true);
            System.out.println("Current state = " + input);
        });
        System.out.println("Final history = " + sg.history());
    }

    /**
     * Metodo auxiliar para test anteriores
     * @return grafo inicializado para los tests
     */
    private static StreamingStateGraph<DoubleData> buildAverageWorkflow() {
        StreamingStateGraph<DoubleData> sg = new StreamingStateGraph<>("average", "Calculates the average of incoming data");

        sg.addNode("average", dataList -> {
            double sum = dataList.stream().mapToDouble(DoubleData::getValue).sum();
            double avg = sum / dataList.size();
            dataList.get(dataList.size() - 1).setAverage(avg);
        });

        sg.setInitial("average");
        return sg;
    }
}
