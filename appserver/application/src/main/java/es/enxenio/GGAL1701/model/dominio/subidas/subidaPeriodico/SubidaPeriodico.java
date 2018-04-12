package es.enxenio.GGAL1701.model.dominio.subidas.subidaPeriodico;

import com.fasterxml.jackson.annotation.JsonBackReference;
import es.enxenio.GGAL1701.model.dominio.periodico.Periodico;
import es.enxenio.GGAL1701.model.dominio.subidas.Subida;

import javax.persistence.*;

/**
 * Created by hlorenzo on 31/08/2017.
 **/

@Entity
@Table(name = "subida_Periodico")
public class SubidaPeriodico extends Subida {

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Periodico_id")
    private Periodico periodico;


    public SubidaPeriodico() {
    }

    public Periodico getPeriodico() {
        return periodico;
    }

    public void setPeriodico(Periodico periodico) {
        this.periodico = periodico;
    }
}
