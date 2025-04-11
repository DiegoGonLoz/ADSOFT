package proyectos;

import myExceptions.PorcentajeInvalido;
import myExceptions.PresupuestoMenorIgualCero;
import proponentes.Fundacion;

/**
 * Clase que representa a un proyecto propuesto por una fundación
 *
 * @author Diego Lesma
 */
public class ProyectoFundacion extends ProyectoParticipativo {
    /**Presupuesto estimado*/
    private double presupuestoEstimado;
    /**Porcentaje asumido por la fundacion*/
    private double porcentajeAsumeFundacion;

    /**
     * Constructor de la clase ProyectoFundacion
     * @param titulo título del proyecto
     * @param descripcion descripcion del proyecto
     * @param proponente proponente del proyecto
     * @param presupuestoEstimado presupuesto estimado del proyecto
     * @param porcentajeAsumeFundacion porcentaje que asume la fundacion
     * @throws PorcentajeInvalido porcentaje a asumir invalido
     * @throws PresupuestoMenorIgualCero presupuesto a proponer invalido
     */
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

    /**
     * Metodo toString de la clase ProyectoFundacion
     * @return String con la información del proyecto
     */
    @Override
    public String toString() {
        return super.toString() + ". " +
                "Presupuesto: " + presupuestoEstimado + ". " +
                "PorcentajeAsume: " + porcentajeAsumeFundacion + " /proyecto de fundación/";
    }
}
