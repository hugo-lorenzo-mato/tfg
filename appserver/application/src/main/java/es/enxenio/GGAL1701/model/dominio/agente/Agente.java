package es.enxenio.GGAL1701.model.dominio.agente;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import es.enxenio.GGAL1701.model.dominio.edicion.EdicionAgente;
import es.enxenio.GGAL1701.model.dominio.periodico.PeriodicoAgente;
import es.enxenio.GGAL1701.model.generic.GenericEntity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by hlorenzo on 04/09/2017.
 */
@Entity
@Table(name = "agente")
public class Agente extends GenericEntity{

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellidos")
    private String apellidos;

    /*La relación es unidireccional, por eso no establezco aquí una many to one como tenía*/

    public Agente() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

}
