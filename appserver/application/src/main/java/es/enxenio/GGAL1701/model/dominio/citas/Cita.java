package es.enxenio.GGAL1701.model.dominio.citas;

import es.enxenio.GGAL1701.model.generic.GenericEntity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Created by hlorenzo on 01/09/2017.
 **/
@MappedSuperclass
public class Cita extends GenericEntity {

    @Column(name = "referencia_pasivo")
    private String referenciaPasivo;

    @Column(name = "autor_citado")
    private String autorCitado;

    @Column(name = "ano_citado")
    private Integer anoCitado;

    @Column(name = "pagina_ini_citado")
    private Integer paginaIniCitado;

    @Column(name = "pagina_fin_citado")
    private Integer paginaFinCitado;

    public String getReferenciaPasivo() {
        return referenciaPasivo;
    }

    public void setReferenciaPasivo(String referenciaPasivo) {
        this.referenciaPasivo = referenciaPasivo;
    }

    public String getAutorCitado() {
        return autorCitado;
    }

    public void setAutorCitado(String autorCitado) {
        this.autorCitado = autorCitado;
    }

    public Integer getAnoCitado() {
        return anoCitado;
    }

    public void setAnoCitado(Integer anoCitado) {
        this.anoCitado = anoCitado;
    }

    public Integer getPaginaIniCitado() {
        return paginaIniCitado;
    }

    public void setPaginaIniCitado(Integer paginaIniCitado) {
        this.paginaIniCitado = paginaIniCitado;
    }

    public Integer getPaginaFinCitado() {
        return paginaFinCitado;
    }

    public void setPaginaFinCitado(Integer paginaFinCitado) {
        this.paginaFinCitado = paginaFinCitado;
    }
}
