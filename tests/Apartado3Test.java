import workflows.StateGraph;

public class Apartado3Test {
    public static void main(String[] args) {
        StateGraph<StringData> sg = buildWorkflow(Apartado1Test.buildWorkflow());

        /*NumericData input = new NumericData(2,3);
        System.out.println("input = " + input);
        NumericData output = sg.run(input, true);
        System.out.println("result = " + output);*/
    }

    public static StateGraph<StringData> buildWorkflow(StateGraph<NumericData> wfNumeric) {
        StateGraph<StringData> sg = new StateGraph<>("replicate", "Replicates a given word");

        sg.addwfNode("calculate", wfNumeric)
                .withInjector((StringData sd) -> sd.toNumericData())
                .withExtractor((NumericData nd, StringData sd) -> sd.setTimes(nd.get("result")));
        sg.addNode("replicate", sd -> sd.replicate());
        sg.addEdge("calculate", "replicate")
                .addConditionalEdge("replicate", "replicate", sd -> sd.times() > 0);

        sg.setInitial("calculate");

        return sg;
    }
}
