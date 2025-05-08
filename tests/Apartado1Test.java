import workflows.StateGraph;

/**
 * Clase test para el apartado 1
 * @author Diego Lesma
 */
public class Apartado1Test {
    /**
     * Metodo main a ejecutar
     * @param args vacío
     */
    public static void main(String[] args) {
        testWorkflow();
        testTextWorkflow();
    }

    /**
     * Test aportado en el enunciado
     */
    public static void testWorkflow() {
        StateGraph<NumericData> sg = buildWorkflow();

        System.out.println(sg);

        NumericData input = new NumericData(2,3);
        System.out.println("--- TEST MATH WORKFLOW ---");
        System.out.println("input = " + input);
        NumericData output = sg.run(input, true);
        System.out.println("result = " + output);
    }

    /**
     * Test hecho con una nueva clase
     */
    public static void testTextWorkflow() {
        StateGraph<TextData> sg = new StateGraph<>("text-ops", "Text operations");

        sg.addNode("upper", (TextData td) -> td.toUpperCase())
                .addNode("reverse", (TextData td) -> td.reverse());

        sg.addEdge("upper", "reverse");
        sg.setInitial("upper");
        sg.setFinal("reverse");

        TextData input = new TextData("hello");
        System.out.println("\n--- TEST TEXT WORKFLOW ---");
        System.out.println("input = " + input);
        TextData output = sg.run(input, true);
        System.out.println("result = " + output);
    }

    /**
     * Metodo auxiliar empleado en tests anteriores
     * @return un grafo inicializado para testar
     */
    public static StateGraph<NumericData> buildWorkflow() {
        StateGraph<NumericData> sg = new StateGraph<>("math2", "Add two numbers, and then square");
        sg.addNode("sum", (NumericData no) -> no.put("result", no.get("op1") + no.get("op2")))
                .addNode("square", (NumericData no) -> no.put("result", no.get("result") * no.get("result")));
        sg.addEdge("sum", "square");

        sg.setInitial("sum");
        sg.setFinal("square");

        return sg;
    }
}
