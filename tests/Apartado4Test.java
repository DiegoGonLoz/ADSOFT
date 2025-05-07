/*import workflows.StreamingStateGraph;
import java.util.List;

public class Apartado4Test {
    public static void main(String[] args) {
        testAverageWorkflow();
        testTextStreamWorkflow();
    }

    public static void testAverageWorkflow() {
        StreamingStateGraph<DoubleData> sg = new StreamingStateGraph<>("average", "Calculate average");

        sg.addNode("average", dataList -> {
            double sum = dataList.stream().mapToDouble(DoubleData::getValue).sum();
            double avg = sum / dataList.size();
            dataList.get(dataList.size() - 1).setAverage(avg);
        });

        sg.setInitial("average");

        System.out.println("--- TEST AVERAGE WORKFLOW ---");
        List.of(1.0, 5.0, 2.0, 4.0).forEach(d -> {
            DoubleData wfInput = new DoubleData(d, 0);
            System.out.println("Workflow input = " + wfInput);
            sg.run(wfInput, true);
        });
        System.out.println("History=" + sg.history());
    }

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
            TextData wfInput = new TextData(text);
            System.out.println("Workflow input = " + wfInput);
            sg.run(wfInput, true);
        });
        System.out.println("History=" + sg.history());
    }
}

class DoubleData {
    private double value;
    private double average;

    public DoubleData(double value, double average) {
        this.value = value;
        this.average = average;
    }

    public double getValue() { return value; }
    public double getAverage() { return average; }
    public void setAverage(double avg) { this.average = avg; }

    @Override
    public String toString() {
        return String.format("%.1f (avg=%.3f)", value, average);
    }
}*/