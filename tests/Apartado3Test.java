import workflows.StateGraph;

public class Apartado3Test {
    public static void main(String[] args) {
        // Tus tests originales
        testReplicateWorkflow();
        testNestedTextWorkflow();

        // Nuevos tests con TextData
        testTextTransformationWorkflow();
        testTextStatisticsWorkflow();
    }

    // Tus métodos originales (sin cambios)
    public static void testReplicateWorkflow() {
        StateGraph<NumericData> mathWorkflow = new StateGraph<>("math", "Simple math operations");
        mathWorkflow.addNode("sum", (NumericData no) -> no.put("result", no.get("op1") + no.get("op2")))
                .setInitial("sum");

        mathWorkflow.setFinal("sum");

        StateGraph<StringData> sg = new StateGraph<>("replicate", "Replicate word");

        sg.addWfNode("calculate", mathWorkflow)
                .withInjector((StringData sd) -> new NumericData(sd.times(), 0))
                .withExtractor((NumericData nd, StringData sd) -> sd.setTimes(nd.get("result")));

        sg.addNode("replicate", sd -> sd.replicate());
        sg.addEdge("calculate", "replicate")
                .addConditionalEdge("replicate", "replicate", sd -> sd.times() > 0);

        sg.setInitial("calculate");

        StringData input = new StringData("jamon", 4);
        System.out.println("--- TEST REPLICATE WORKFLOW ---");
        System.out.println("input = " + input);
        StringData output = sg.run(input, true);
        System.out.println("result = " + output);
    }

    public static void testNestedTextWorkflow() {
        StateGraph<NumericData> lengthWorkflow = new StateGraph<>("length", "Calculate length");
        lengthWorkflow.addNode("length", (NumericData no) -> no.put("result", no.get("op1")))
                .setInitial("length");

        lengthWorkflow.setFinal("length");

        StateGraph<TextData> sg = new StateGraph<>("text-nested", "Nested text processing");

        sg.addWfNode("getLength", lengthWorkflow)
                .withInjector((TextData td) -> td.toNumericData())
                .withExtractor((NumericData nd, TextData td) -> td.append("_len" + nd.get("result")));

        sg.addNode("transform", (TextData td) -> td.toUpperCase());
        sg.addEdge("getLength", "transform");

        sg.setInitial("getLength");
        sg.setFinal("transform");

        System.out.println("\n--- TEST NESTED TEXT WORKFLOW ---");
        TextData input = new TextData("example");
        System.out.println("input = " + input);
        TextData output = sg.run(input, true);
        System.out.println("result = " + output);
    }

    // Nuevos tests con TextData
    public static void testTextTransformationWorkflow() {
        StateGraph<TextData> sg = new StateGraph<>("text-transform", "Text transformation pipeline");

        sg.addNode("upper", (TextData td) -> td.toUpperCase())
                .addNode("reverse", (TextData td) -> td.reverse())
                .addNode("append", (TextData td) -> td.append("_END"));

        sg.addEdge("upper", "reverse")
                .addEdge("reverse", "append");

        sg.setInitial("upper");
        sg.setFinal("append");

        TextData input = new TextData("hello");
        System.out.println("\n--- TEST TEXT TRANSFORMATION WORKFLOW ---");
        System.out.println("input = " + input);
        TextData output = sg.run(input, true);
        System.out.println("result = " + output);
    }

    public static void testTextStatisticsWorkflow() {
        StateGraph<NumericData> statsWorkflow = new StateGraph<>("stats", "Text statistics");
        statsWorkflow.addNode("length", (NumericData no) -> no.put("result", no.get("op1")))
                .addNode("square", (NumericData no) -> no.put("result", no.get("result") * no.get("result")))
                .addEdge("length", "square")
                .setInitial("length");

        statsWorkflow.setFinal("square");

        StateGraph<TextData> sg = new StateGraph<>("text-stats", "Text statistics workflow");

        sg.addWfNode("calculate", statsWorkflow)
                .withInjector((TextData td) -> td.toNumericData())
                .withExtractor((NumericData nd, TextData td) -> td.append("_sq" + nd.get("result")));

        sg.addNode("log", (TextData td) -> System.out.println("Processed: " + td))
                .addEdge("calculate", "log")
                .setInitial("calculate");

        sg.setFinal("log");

        TextData input = new TextData("test");
        System.out.println("\n--- TEST TEXT STATISTICS WORKFLOW ---");
        System.out.println("input = " + input);
        TextData output = sg.run(input, true);
        System.out.println("result = " + output);
    }
}