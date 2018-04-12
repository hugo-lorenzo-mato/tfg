package es.enxenio.GGAL1701.controller.custom.util;

import es.enxenio.GGAL1701.model.dominio.libro.TipoLibro;


/**
 * Created by hlorenzo on 04/08/2017.
 */
public class LibroFilter extends PageableFilter{

    private TipoLibro tipo;
    private Boolean revisado;
    private Boolean concluido;

    public TipoLibro getTipo() {
        return tipo;
    }

    public void setTipo(TipoLibro tipo) {
        this.tipo = tipo;
    }

    public Boolean getRevisado() {
        return revisado;
    }

    public void setRevisado(Boolean revisado) {
        this.revisado = revisado;
    }

    public Boolean getConcluido() {
        return concluido;
    }

    public void setConcluido(Boolean concluido) {
        this.concluido = concluido;
    }
}
