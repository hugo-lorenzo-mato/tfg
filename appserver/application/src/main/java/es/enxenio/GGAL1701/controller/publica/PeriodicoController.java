package es.enxenio.GGAL1701.controller.publica;

import com.fasterxml.jackson.annotation.JsonView;
import es.enxenio.GGAL1701.controller.custom.util.PeriodicoFilter;
import es.enxenio.GGAL1701.controller.util.JsonViews;
import es.enxenio.GGAL1701.model.dominio.periodico.Periodico;
import es.enxenio.GGAL1701.model.dominio.periodico.PeriodicoService;
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
 * Created by hlorenzo on 10/08/2017.
 **/
@RestController
@RequestMapping(ConstantesRest.URL_PUBLIC + "/periodico")
public class PeriodicoController {

    @Inject
    private PeriodicoService periodicoService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Periodico get(@PathVariable("id") Long id) {
        return periodicoService.get(id);
    }

    @JsonView(JsonViews.List.class)
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ResponseEntity<Page<Periodico>> findAll(PeriodicoFilter filter) {
        if (filter != null) {
            return new ResponseEntity<>(periodicoService.filter(filter), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
