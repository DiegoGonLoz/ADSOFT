package clasesTest;

/**
 * Clase clasesTest.StringData
 * @author Diego Lesma
 */
public class StringData {
    /** Palabra */
    private String word;
    /** Resultado */
    private String result;
    /** Times */
    private int times;

    /**
     * Constructor de clasesTest.StringData
     * @param word palabra
     * @param times times
     */
    public StringData(String word, int times) {
        this.word = word;
        this.times = times;
        this.result = "";
    }

    /**
     * Constructor preconfigurado
     * @return objeto ya configurado
     */
    public NumericData toNumericData() {
        return new NumericData(this.times, 0); // op1 = times, op2 = 0 (como en el ejemplo)
    }

    /**
     * Metodo para replicar
     */
    public void replicate() {
        if (times > 0) {
            result += word;
            times--;
        }
    }

    /**
     * Getter de word
     * @return la palabra
     */
    public String getWord() {
        return word;
    }

    /**
     * Setter de word
     * @param word palabra a configurar
     */
    public void setWord(String word) {
        this.word = word;
    }

    /**
     * Getter de result
     * @return resultado
     */
    public String getResult() {
        return result;
    }

    /**
     * Setter de result
     * @param result resultado a configurar
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * getter de times
     * @return times
     */
    public int times() {
        return times;
    }

    /**
     * Setter de times
     * @param times times
     */
    public void setTimes(int times) {
        this.times = times;
    }

    /**
     * Metodo toString de clasesTest.StringData
     * @return String con información relevante
     */
    @Override
    public String toString() {
        return "word: " + word + ", times: " + times + ", result: " + result;
    }
}