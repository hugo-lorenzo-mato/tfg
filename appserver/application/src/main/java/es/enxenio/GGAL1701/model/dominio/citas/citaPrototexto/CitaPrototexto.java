package es.enxenio.GGAL1701.model.dominio.citas.citaPrototexto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import es.enxenio.GGAL1701.model.dominio.citas.Cita;
import es.enxenio.GGAL1701.model.dominio.prototexto.Prototexto;
import javax.persistence.*;

/**
 * Created by hlorenzo on 29/08/2017.
 **/
@Entity
@Table(name = "cita_prototexto")
public class CitaPrototexto extends Cita{

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prototexto_id")
    private Prototexto prototexto;

    public CitaPrototexto() {
    }

    public Prototexto getPrototexto() {
        return prototexto;
    }

    public void setPrototexto(Prototexto prototexto) {
        this.prototexto = prototexto;
    }

}
