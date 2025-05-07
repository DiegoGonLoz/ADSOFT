import java.util.HashMap;
import java.util.Map;

public class TextData {
    private Map<String, String> data;
    private String currentOperation;

    public TextData(String initialText) {
        this.data = new HashMap<>();
        this.data.put("text", initialText);
        this.data.put("result", "");
        this.currentOperation = "none";
    }

    public void toUpperCase() {
        String text = data.get("text");
        data.put("result", text.toUpperCase());
        currentOperation = "uppercase";
    }

    public void reverse() {
        String text = data.get("text");
        data.put("result", new StringBuilder(text).reverse().toString());
        currentOperation = "reverse";
    }

    public void append(String suffix) {
        String text = data.get("text");
        data.put("result", text + suffix);
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
