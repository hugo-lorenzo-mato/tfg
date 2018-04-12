package es.enxenio.GGAL1701.controller.publica;

import com.fasterxml.jackson.annotation.JsonView;
import es.enxenio.GGAL1701.controller.custom.util.PrototextoFilter;
import es.enxenio.GGAL1701.controller.util.JsonViews;
import es.enxenio.GGAL1701.model.dominio.prototexto.Prototexto;
import es.enxenio.GGAL1701.model.dominio.prototexto.PrototextoService;
import es.enxenio.GGAL1701.util.ConstantesRest;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

/**
 * Created by hlorenzo on 05/08/2017.
 */
@RestController
@RequestMapping(ConstantesRest.URL_PUBLIC + "/prototexto")
public class PrototextoController {

    @Inject
    private PrototextoService prototextoService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Prototexto get(@PathVariable("id") Long id) {
        return prototextoService.get(id);
    }

    @JsonView(JsonViews.List.class)
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ResponseEntity<Page<Prototexto>> findAll(PrototextoFilter filter) {
        if (filter != null) {
            return new ResponseEntity<>(prototextoService.filter(filter), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
