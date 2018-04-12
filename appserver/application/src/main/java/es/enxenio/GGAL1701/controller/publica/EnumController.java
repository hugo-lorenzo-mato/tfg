package es.enxenio.GGAL1701.controller.publica;


import es.enxenio.GGAL1701.model.dominio.libro.TipoLibro;
import es.enxenio.GGAL1701.model.dominio.periodico.TipoPeriodico;
import es.enxenio.GGAL1701.model.dominio.produccionActiva.Assunto;
import es.enxenio.GGAL1701.model.dominio.produccionActiva.Folha;
import es.enxenio.GGAL1701.model.dominio.produccionActiva.Genero;
import es.enxenio.GGAL1701.model.dominio.prototexto.TipoPrototexto;
import es.enxenio.GGAL1701.model.dominio.prototexto.TipoSoporte;
import es.enxenio.GGAL1701.model.dominio.prototexto.TipoSoporteReducido;
import es.enxenio.GGAL1701.model.estatica.estatica.Estatica;
import es.enxenio.GGAL1701.model.usuario.Rol;
import es.enxenio.GGAL1701.util.ConstantesRest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Clase que contiene todas las operaciones de obtención de enumerados.
 * La llamada en el cliente se hace con EnumService.get("nombre-enum");
 * Created by crodriguez on 06/09/2016.
 */
@RestController
@RequestMapping(ConstantesRest.URL_PUBLIC + "/enum")
public class EnumController {


    @RequestMapping(value = "/tipo-prototexto", method = RequestMethod.GET)
    public TipoPrototexto[] getTiposPrototexto() {
        return TipoPrototexto.values();
    }

    @RequestMapping(value = "/genero", method = RequestMethod.GET)
    public Genero[] getTiposGenero() {
        return Genero.values();
    }

    @RequestMapping(value = "/assunto", method = RequestMethod.GET)
    public Assunto[] getTiposAssunto() {
        return Assunto.values();
    }

    @RequestMapping(value = "/folha-prototexto", method = RequestMethod.GET)
    public Folha[] getTiposFolha() {
        return Folha.values();
    }

    @RequestMapping(value = "/tipo-libro", method = RequestMethod.GET)
    public TipoLibro[] getTiposLibro() {
        return TipoLibro.values();
    }

    @RequestMapping(value = "/tipo-periodico", method = RequestMethod.GET)
    public TipoPeriodico[] getTiposPeriodicos() {
        return TipoPeriodico.values();
    }

    @RequestMapping(value = "/tipo-role", method = RequestMethod.GET)
    public Rol[] getTiposRoles() {
        return Rol.values();
    }

    @RequestMapping(value = "/tipo-sampliado", method = RequestMethod.GET)
    public TipoSoporte[] getTipoSoporte() {
        return TipoSoporte.values();
    }

    @RequestMapping(value = "/tipo-sreducido", method = RequestMethod.GET)
    public TipoSoporteReducido[] getTipoSoporteReducido() {
        return TipoSoporteReducido.values();
    }

    // Estáticas

    @RequestMapping(value = "/estaticas-idioma", method = RequestMethod.GET)
    public Estatica.Idioma[] getIdiomasEstaticas() {
        return Estatica.Idioma.values();
    }

}
