package es.enxenio.GGAL1701.controller.custom.util;

import es.enxenio.GGAL1701.controller.custom.util.PageableFilter;
import es.enxenio.GGAL1701.model.dominio.prototexto.TipoPrototexto;
/**
 * Created by hlorenzo on 03/08/2017.
 */
public class PrototextoFilter extends PageableFilter {


    private TipoPrototexto tipo;
    private Boolean revisado;
    private Boolean concluido;

    public TipoPrototexto getTipo() {
        return tipo;
    }

    public void setTipo(TipoPrototexto tipo) {
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
