package es.enxenio.GGAL1701.model.dominio.citas.citaPeriodico;

import com.fasterxml.jackson.annotation.JsonBackReference;
import es.enxenio.GGAL1701.model.dominio.citas.Cita;
import es.enxenio.GGAL1701.model.dominio.periodico.Periodico;

import javax.persistence.*;

/**
 * Created by hlorenzo on 01/09/2017.
 **/
@Entity
@Table(name = "cita_periodico")
public class CitaPeriodico extends Cita{

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "periodico_id")
    private Periodico periodico;

    public CitaPeriodico() {
    }

    public Periodico getPeriodico() {
        return periodico;
    }

    public void setPeriodico(Periodico periodico) {
        this.periodico = periodico;
    }
}
