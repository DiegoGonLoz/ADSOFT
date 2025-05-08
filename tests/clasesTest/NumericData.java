package clasesTest;

import java.util.LinkedHashMap;

/**
 * Clase clasesTest.NumericData
 * @author Profesores
 */
public class NumericData extends LinkedHashMap<String, Integer> {
    /**
     * Constructor de clasesTest.NumericData
     * @param op1 operando 1
     * @param op2 operando 2
     */
    public NumericData(int op1, int op2) {
        put("op1", op1);
        put("op2", op2);
        put("result", 0);
    }
}
