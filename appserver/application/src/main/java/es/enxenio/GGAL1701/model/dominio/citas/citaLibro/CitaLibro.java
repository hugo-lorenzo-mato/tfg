package es.enxenio.GGAL1701.model.dominio.citas.citaLibro;

import com.fasterxml.jackson.annotation.JsonBackReference;
import es.enxenio.GGAL1701.model.dominio.citas.Cita;
import es.enxenio.GGAL1701.model.dominio.libro.Libro;

import javax.persistence.*;

/**
 * Created by hlorenzo on 01/09/2017.
 **/
@Entity
@Table(name = "cita_libro")
public class CitaLibro extends Cita{

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "libro_id")
    private Libro libro;

    public CitaLibro() {
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }
}
