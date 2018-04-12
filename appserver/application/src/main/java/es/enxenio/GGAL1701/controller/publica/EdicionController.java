package es.enxenio.GGAL1701.controller.publica;

import com.fasterxml.jackson.annotation.JsonView;
import es.enxenio.GGAL1701.controller.custom.util.PageableFilter;
import es.enxenio.GGAL1701.controller.custom.util.PrototextoFilter;
import es.enxenio.GGAL1701.controller.util.JsonViews;
import es.enxenio.GGAL1701.model.dominio.edicion.Edicion;
import es.enxenio.GGAL1701.model.dominio.edicion.EdicionService;
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
 * Created by hlorenzo on 03/09/2017.
 */
@RestController
@RequestMapping(ConstantesRest.URL_PUBLIC + "/edicion")
public class EdicionController {

    @Inject
    private EdicionService edicionService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Edicion get(@PathVariable("id") Long id) {
        return edicionService.get(id);
    }

    @JsonView(JsonViews.List.class)
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ResponseEntity<Page<Edicion>> findAll(PageableFilter filter) {
        if (filter != null) {
            return new ResponseEntity<>(edicionService.filter(filter), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
