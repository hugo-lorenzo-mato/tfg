package es.enxenio.GGAL1701.model.dominio.periodico;

import com.fasterxml.jackson.annotation.JsonBackReference;
import es.enxenio.GGAL1701.model.dominio.agente.Agente;
import es.enxenio.GGAL1701.model.dominio.edicion.Edicion;
import es.enxenio.GGAL1701.model.dominio.funciones.funcionAgente.FuncionAgente;
import es.enxenio.GGAL1701.model.generic.GenericEntity;

import javax.persistence.*;

/**
 * Created by hlorenzo on 07/09/2017.
 **/
@Entity
@Table(name = "periodico_agente")
public class PeriodicoAgente extends GenericEntity{


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "agente_id")
    private Agente agente;

    // Aqui LAZY porque se encarga la entidad periodico
    @JsonBackReference(value="periodicoAgente")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "periodico_id")
    private Periodico periodico;

    //EAGER porque necesito guardar la clave
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "funcion_agente_id")
    private FuncionAgente funcionAgente;

    public PeriodicoAgente() {
    }

    public Agente getAgente() {
        return agente;
    }

    public void setAgente(Agente agente) {
        this.agente = agente;
    }

    public Periodico getPeriodico() {
        return periodico;
    }

    public void setPeriodico(Periodico periodico) {
        this.periodico = periodico;
    }

    public FuncionAgente getFuncionAgente() {
        return funcionAgente;
    }

    public void setFuncionAgente(FuncionAgente funcionAgente) {
        this.funcionAgente = funcionAgente;
    }

}
