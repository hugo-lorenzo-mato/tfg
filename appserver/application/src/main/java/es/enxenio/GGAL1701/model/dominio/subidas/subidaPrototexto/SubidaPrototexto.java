package es.enxenio.GGAL1701.model.dominio.subidas.subidaPrototexto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import es.enxenio.GGAL1701.model.dominio.prototexto.Prototexto;
import es.enxenio.GGAL1701.model.dominio.subidas.Subida;

import javax.persistence.*;

/**
 * Created by hlorenzo on 31/08/2017.
 **/

@Entity
@Table(name = "subida_prototexto")
public class SubidaPrototexto extends Subida {

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prototexto_id")
    private Prototexto prototexto;


    public SubidaPrototexto() {
    }

    public Prototexto getPrototexto() {
        return prototexto;
    }

    public void setPrototexto(Prototexto prototexto) {
        this.prototexto = prototexto;
    }
}
