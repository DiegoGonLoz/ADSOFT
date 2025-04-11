package proyectos;

import myExceptions.PorcentajeInvalido;
import myExceptions.PresupuestoMenorIgualCero;
import proponentes.Fundacion;

public class ProyectoFundacion extends ProyectoParticipativo {
    private double presupuestoEstimado;
    private double porcentajeAsumeFundacion;

    public ProyectoFundacion(String titulo, String descripcion, Fundacion proponente,
                             double presupuestoEstimado, double porcentajeAsumeFundacion)
    throws PorcentajeInvalido, PresupuestoMenorIgualCero {

        super(titulo, descripcion, proponente);

        if (presupuestoEstimado <= 0) {
            throw new PresupuestoMenorIgualCero("Error en el parámetro PresupuestoEstimado " +
                    "en el constructor ProyectoFundacion", presupuestoEstimado);
        }

        if (porcentajeAsumeFundacion < 1 || porcentajeAsumeFundacion > 100) {
            throw new PorcentajeInvalido("Error en el parámetro porcentajeAsumeFundacion " +
                    "en el constructor ProyectoFundacion", porcentajeAsumeFundacion);
        }

        this.presupuestoEstimado = presupuestoEstimado;
        this.porcentajeAsumeFundacion = porcentajeAsumeFundacion;
    }

    @Override
    public String toString() {
        return super.toString() + ". " +
                "Presupuesto: " + presupuestoEstimado + ". " +
                "PorcentajeAsume: " + porcentajeAsumeFundacion + " /proyecto de fundación/";
    }
}
