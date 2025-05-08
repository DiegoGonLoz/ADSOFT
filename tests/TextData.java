import java.util.HashMap;
import java.util.Map;

public class TextData {
    private final Map<String, String> data;
    private String currentOperation;

    public TextData(String initialText) {
        this.data = new HashMap<>();
        this.data.put("text", initialText);
        this.data.put("result", initialText);  // Inicializamos result con el texto de entrada
        this.currentOperation = "none";
    }

    // Versión corregida - opera sobre el result actual
    public void toUpperCase() {
        String currentResult = data.get("result");
        data.put("result", currentResult.toUpperCase());
        currentOperation = "uppercase";
    }

    // Versión corregida - opera sobre el result actual
    public void reverse() {
        String currentResult = data.get("result");
        data.put("result", new StringBuilder(currentResult).reverse().toString());
        currentOperation = "reverse";
    }

    // Versión corregida - opera sobre el result actual
    public void append(String suffix) {
        String currentResult = data.get("result");
        data.put("result", currentResult + suffix);
        currentOperation = "append";
    }

    public String getText() {
        return data.get("text");
    }

    public String getResult() {
        return data.get("result");
    }

    public String getCurrentOperation() {
        return currentOperation;
    }

    public NumericData toNumericData() {
        return new NumericData(data.get("text").length(), 0);
    }

    @Override
    public String toString() {
        return "TextData{text='" + data.get("text") + "', result='" + data.get("result") +
                "', operation=" + currentOperation + "}";
    }
}
