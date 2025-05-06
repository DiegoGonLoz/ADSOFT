import workflows.StateGraph;

public class Apartado3Test {
    public static void main(String[] args) {
        StateGraph<StringData> sg = buildWorkflow(Apartado1Test.buildWorkflow());

        StringData input = new StringData("jamon",4);
        System.out.println("input = " + input);
        StringData output = sg.run(input, true);
        System.out.println("result = " + output);
    }

    public static StateGraph<StringData> buildWorkflow(StateGraph<NumericData> wfNumeric) {
        StateGraph<StringData> sg = new StateGraph<StringData>("replicate", "Replicates a given word");

        sg.addWfNode("calculate", wfNumeric)
                .withInjector((StringData sd) -> sd.toNumericData())
                .withExtractor((NumericData nd, StringData sd) -> sd.setTimes(nd.get("result")));
        sg.addNode("replicate", sd -> sd.replicate());
        sg.addEdge("calculate", "replicate")
                .addConditionalEdge("replicate", "replicate", sd -> sd.times() > 0);

        sg.setInitial("calculate");

        return sg;
    }
}
