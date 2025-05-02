import java.util.LinkedHashMap;

public class NumericData extends LinkedHashMap<String, Integer> {
    public NumericData(int op1, int op2) {
        put("op1", op1);
        put("op2", op2);
        put("result", 0);
    }
}
