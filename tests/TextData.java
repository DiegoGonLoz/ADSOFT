import java.util.HashMap;
import java.util.Map;

/**
 * Clase TextData
 * @author Diego Lesma
 */
public class TextData {
    /** Mapa con los datos */
    private final Map<String, String> data;
    /** Operación actual */
    private String currentOperation;

    /**
     * Constructor de TextData
     * @param initialText texto inicial
     */
    public TextData(String initialText) {
        this.data = new HashMap<>();
        this.data.put("text", initialText);
        this.data.put("result", initialText);
        this.currentOperation = "none";
    }

    /**
     * Metodo para convertir en mayúsculas
     */
    public void toUpperCase() {
        String currentResult = data.get("result");
        data.put("result", currentResult.toUpperCase());
        currentOperation = "uppercase";
    }

    /**
     * Metodo para revertir el texto
     */
    public void reverse() {
        String currentResult = data.get("result");
        data.put("result", new StringBuilder(currentResult).reverse().toString());
        currentOperation = "reverse";
    }

    /**
     * Metodo append
     * @param suffix sufijo
     */
    public void append(String suffix) {
        String currentResult = data.get("result");
        data.put("result", currentResult + suffix);
        currentOperation = "append";
    }

    /**
     * Getter del texto
     * @return texto
     */
    public String getText() {
        return data.get("text");
    }

    /**
     * Getter del result
     * @return resultado
     */
    public String getResult() {
        return data.get("result");
    }

    /**
     * Getter de la operación actual
     * @return operación actual
     */
    public String getCurrentOperation() {
        return currentOperation;
    }

    /**
     * Metodo constructor preconfigurado
     * @return numericData inicializado
     */
    public NumericData toNumericData() {
        return new NumericData(data.get("text").length(), 0);
    }

    /**
     * Metodo toString con la información relevante
     * @return String
     */
    @Override
    public String toString() {
        return "TextData{text='" + data.get("text") + "', result='" + data.get("result") +
                "', operation=" + currentOperation + "}";
    }
}
