/**
 * Clase DubleData usada para tests
 */
class DoubleData {
    /** Valor */
    private final double value;
    /** Media */
    private double average;

    /**
     * Constructor de DoubleData
     * @param value valor
     * @param average media
     */
    public DoubleData(double value, double average) {
        this.value = value;
        this.average = average;
    }

    /**
     * Getter de value
     * @return el valor
     */
    public double getValue() { return value; }

    /**
     * Getter de average
     * @return la media
     */
    public double getAverage() { return average; }

    /**
     * Setter de average
     * @param avg media a configurar
     */
    public void setAverage(double avg) { this.average = avg; }

    /**
     * Metodo toString de DoubleData
     * @return String con información relevante
     */
    @Override
    public String toString() {
        return String.format("%.1f (avg=%.3f)", value, average);
    }
}