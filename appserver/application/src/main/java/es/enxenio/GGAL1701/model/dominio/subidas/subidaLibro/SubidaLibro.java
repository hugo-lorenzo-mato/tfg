package es.enxenio.GGAL1701.model.dominio.subidas.subidaLibro;

import com.fasterxml.jackson.annotation.JsonBackReference;
import es.enxenio.GGAL1701.model.dominio.libro.Libro;
import es.enxenio.GGAL1701.model.dominio.prototexto.Prototexto;
import es.enxenio.GGAL1701.model.dominio.subidas.Subida;

import javax.persistence.*;

/**
 * Created by hlorenzo on 31/08/2017.
 **/

@Entity
@Table(name = "subida_libro")
public class SubidaLibro extends Subida {

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "libro_id")
    private Libro libro;


    public SubidaLibro() {
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }
}
