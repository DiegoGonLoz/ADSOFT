package proyectos;

import proponentes.EnteCiudadano;
import proponentes.Proponente;

import java.util.List;

public class ProyectoFundacion extends ProyectoParticipativo{
    private double presupuestoEstimado;
    private double porcentajeAsumeFundacion;

    public ProyectoFundacion(String titulo, String descripcion, Proponente proponente, List<EnteCiudadano> apoyos, double presupuestoEstimado, double porcentajeAsumeFundacion) {
        super(titulo, descripcion, proponente, apoyos);

        if (presupuestoEstimado <= 0) {
            throw new IllegalArgumentException("El presupuesto estimado debe ser mayor a 0");
        }

        if (porcentajeAsumeFundacion < 1 || porcentajeAsumeFundacion > 100) {
            throw new IllegalArgumentException("El porcentaje debe estar entre 1 y 100");
        }

        this.presupuestoEstimado = presupuestoEstimado;
        this.porcentajeAsumeFundacion = porcentajeAsumeFundacion;
    }


}
