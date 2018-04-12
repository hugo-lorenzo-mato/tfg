package es.enxenio.GGAL1701.controller.publica;

import com.fasterxml.jackson.annotation.JsonView;
import es.enxenio.GGAL1701.controller.custom.util.CiudadFilter;
import es.enxenio.GGAL1701.controller.custom.util.PageableFilter;
import es.enxenio.GGAL1701.controller.util.JsonViews;
import es.enxenio.GGAL1701.model.dominio.lugar.ciudad.Ciudad;
import es.enxenio.GGAL1701.model.dominio.lugar.ciudad.CiudadService;
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
 * Created by crodriguez on 11/05/2017.
 */
@RestController
@RequestMapping(ConstantesRest.URL_PUBLIC + "/ciudad")
public class CiudadController {

    @Inject
    private CiudadService ciudadService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Ciudad get(@PathVariable("id") Long id) {
        return ciudadService.get(id);
    }

    @JsonView(JsonViews.List.class)
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ResponseEntity<Page<Ciudad>> findAll(CiudadFilter filter) {
        if (filter != null) {
            return new ResponseEntity<>(ciudadService.filter(filter), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
