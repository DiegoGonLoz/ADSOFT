import workflows.StateGraph;

public class Apartado3Test {
    public static void main(String[] args) {
        testReplicateWorkflow();
        testNestedTextWorkflow();
    }

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
}