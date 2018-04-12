package es.enxenio.GGAL1701.model.dominio.capitulo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import es.enxenio.GGAL1701.model.dominio.agente.Agente;
import es.enxenio.GGAL1701.model.dominio.funciones.funcionAgente.FuncionAgente;
import es.enxenio.GGAL1701.model.generic.GenericEntity;

import javax.persistence.*;

/**
 * Created by hlorenzo on 08/09/2017.
 **/
@Entity
@Table(name = "capitulo_agente")
public class CapituloAgente extends GenericEntity{


    @JsonBackReference(value="capituloAgente")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "capitulo_id")
    private Capitulo capitulo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "agente_id")
    private Agente agente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "funcion_agente_id")
    private FuncionAgente funcionAgente;

    public CapituloAgente() {
    }

    public Capitulo getCapitulo() {
        return capitulo;
    }

    public void setCapitulo(Capitulo capitulo) {
        this.capitulo = capitulo;
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
