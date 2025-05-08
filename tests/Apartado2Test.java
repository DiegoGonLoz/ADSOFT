import workflows.StateGraph;

public class Apartado2Test {
    public static void main(String[] args) {
        testConditionalWorkflow();
        testTextConditionalWorkflow();
    }

    public static void testConditionalWorkflow() {
        StateGraph<NumericData> sg = new StateGraph<>("math1", "Add and conditional square");

        sg.addNode("sum", (NumericData no) -> no.put("result", no.get("op1") + no.get("op2")))
                .addNode("square", (NumericData no) -> no.put("result", no.get("result") * no.get("result")));

        sg.addConditionalEdge("sum", "square", no -> no.get("result") % 2 == 0);
        sg.setInitial("sum");
        sg.setFinal("square");

        NumericData input1 = new NumericData(2, 3);
        System.out.println("--- TEST CONDITIONAL MATH WORKFLOW ---");
        System.out.println("input = " + input1);
        NumericData output1 = sg.run(input1, true);
        System.out.println("result = " + output1);

        NumericData input2 = new NumericData(2, 2);
        System.out.println("\ninput = " + input2);
        NumericData output2 = sg.run(input2, true);
        System.out.println("result = " + output2);
    }

    public static void testTextConditionalWorkflow() {
        StateGraph<TextData> sg = new StateGraph<>("text-cond", "Conditional text processing");

        sg.addNode("process", (TextData td) -> {
            if(td.getText().length() > 5) {
                td.toUpperCase();
            } else {
                td.reverse();
            }
        });

        sg.addNode("append", (TextData td) -> td.append("_processed"));

        sg.addConditionalEdge("process", "append", td -> td.getCurrentOperation().equals("uppercase"))
                .addConditionalEdge("process", "append", td -> !td.getCurrentOperation().equals("uppercase"));

        sg.setInitial("process");
        sg.setFinal("append");

        System.out.println("\n--- TEST CONDITIONAL TEXT WORKFLOW ---");

        TextData input1 = new TextData("hello");
        System.out.println("input = " + input1);
        TextData output1 = sg.run(input1, true);
        System.out.println("result = " + output1);

        TextData input2 = new TextData("hello world");
        System.out.println("\ninput = " + input2);
        TextData output2 = sg.run(input2, true);
        System.out.println("result = " + output2);
    }
}