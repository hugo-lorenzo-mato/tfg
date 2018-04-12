package es.enxenio.GGAL1701.model.dominio.edicion;

import com.fasterxml.jackson.annotation.JsonBackReference;
import es.enxenio.GGAL1701.model.dominio.agente.Agente;
import es.enxenio.GGAL1701.model.dominio.funciones.funcionAgente.FuncionAgente;
import es.enxenio.GGAL1701.model.generic.GenericEntity;

import javax.persistence.*;

/**
 * Created by hlorenzo on 06/09/2017.
 **/
@Entity
@Table(name = "edicion_agente")
public class EdicionAgente extends GenericEntity{


    @JsonBackReference(value="edicionAgente")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "edicion_id")
    private Edicion edicion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "agente_id")
    private Agente agente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "funcion_agente_id")
    private FuncionAgente funcionAgente;

    public EdicionAgente() {
    }

    public Edicion getEdicion() {
        return edicion;
    }

    public void setEdicion(Edicion edicion) {
        this.edicion = edicion;
    }

    public Agente getAgente() {
        return agente;
    }

    public void setAgente(Agente agente) {
        this.agente = agente;
    }

    public FuncionAgente getFuncionAgente() {
        return funcionAgente;
    }

    public void setFuncionAgente(FuncionAgente funcionAgente) {
        this.funcionAgente = funcionAgente;
    }
}
