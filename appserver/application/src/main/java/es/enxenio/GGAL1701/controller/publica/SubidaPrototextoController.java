package es.enxenio.GGAL1701.controller.publica;

import com.fasterxml.jackson.annotation.JsonView;
import es.enxenio.GGAL1701.controller.custom.util.PageableFilter;
import es.enxenio.GGAL1701.controller.util.JsonViews;
import es.enxenio.GGAL1701.model.dominio.subidas.subidaPrototexto.SubidaPrototexto;
import es.enxenio.GGAL1701.model.dominio.subidas.subidaPrototexto.SubidaPrototextoService;
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
 * Created by hlorenzo on 31/08/2017.
 **/
@RestController
@RequestMapping(ConstantesRest.URL_PUBLIC + "/subida")
public class SubidaPrototextoController {

    @Inject
    private SubidaPrototextoService subidaPrototextoService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public SubidaPrototexto get(@PathVariable("id") Long id) {
        return subidaPrototextoService.get(id);
    }

    @JsonView(JsonViews.List.class)
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ResponseEntity<Page<SubidaPrototexto>> findAll(PageableFilter filter) {
        if (filter != null) {
            return new ResponseEntity<>(subidaPrototextoService.filter(filter), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
