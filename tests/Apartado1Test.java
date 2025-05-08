import workflows.StateGraph;

public class Apartado1Test {
    public static void main(String[] args) {
        StateGraph<NumericData> sg = buildWorkflow();

        System.out.println(sg);

        NumericData input = new NumericData(2,3);
        System.out.println("input = " + input);
        NumericData output = sg.run(input, true);
        System.out.println("result = " + output);
    }

    public static StateGraph<NumericData> buildWorkflow(){
        StateGraph<NumericData> sg = new StateGraph<>("math2", "Add tow numbers, and then square");

        sg.addNode("sum", (NumericData no) -> no.put("result", no.get("op1")+no.get("op2")))
                .addNode("square", (NumericData no) -> no.put("result", no.get("result")*no.get("result")));

        sg.addEdge("sum", "square");

        sg.setInitial("sum");
        sg.setFinal("square");

        return sg;
    }
}
