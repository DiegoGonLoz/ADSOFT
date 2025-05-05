public class StringData {
    private String word;
    private String result;
    private int times;

    public StringData(String word, int times) {
        this.word = word;
        this.times = times;
        this.result = "";
    }

    public NumericData toNumericData() {
        return new NumericData(this.times, 0); // op1 = times, op2 = 0 (como en el ejemplo)
    }

    public void replicate() {
        if (times > 0) {
            result += word;
            times--;
        }
    }

    // Getters and setters
    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int times() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    @Override
    public String toString() {
        return "word: " + word + ", times: " + times + ", result: " + result;
    }
}