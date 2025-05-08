import workflows.*;

public class Apartado5Test {
    public static void main(String[] args) {
        testProfilerWorkflow();   // Test original con NumericData
        testTextProfilerWorkflow(); // Test adicional con TextData
    }

    // Test original con NumericData (salida como pide el enunciado)
    public static void testProfilerWorkflow() {
        StateGraph<NumericData> g = new StateGraph<>("loop-down", "Get a number, and decrease if positive");
        StateGraphLogger<NumericData> lg = new StateGraphLogger<>(g, "traces.txt");
        StateGraphProfiler<NumericData> sg = new StateGraphProfiler<>(lg);

        sg.addNode("decrease", (NumericData no) -> no.put("op1", no.get("op1")-1 ))
                .addConditionalEdge("decrease", "decrease", (NumericData no) -> no.get("op1") > 0)
                .setInitial("decrease");

        NumericData input = new NumericData(3, 0);
        System.out.println(sg + "\ninput = " + input);
        NumericData output = sg.run(input, true);
        System.out.println("result = " + output);
        System.out.println("history = "+ ((StateGraphProfiler<NumericData>)sg).history());
    }

    // Test adicional con TextData
    public static void testTextProfilerWorkflow() {
        StateGraph<TextData> g = new StateGraph<>("text-process", "Text processing with profiling");
        StateGraphLogger<TextData> lg = new StateGraphLogger<>(g, "text_traces.txt");
        StateGraphProfiler<TextData> sg = new StateGraphProfiler<>(lg);

        sg.addNode("upper", (TextData td) -> td.toUpperCase())
                .addNode("reverse", (TextData td) -> td.reverse())
                .addEdge("upper", "reverse")
                .setInitial("upper");

        sg.setFinal("reverse");

        TextData input = new TextData("hello");
        System.out.println("\n--- TEST TEXT PROFILER WORKFLOW ---");
        System.out.println(sg + "\ninput = " + input);
        TextData output = sg.run(input, true);
        System.out.println("result = " + output);
        System.out.println("Profiling history = " + ((StateGraphProfiler<TextData>)sg).history());
    }
}