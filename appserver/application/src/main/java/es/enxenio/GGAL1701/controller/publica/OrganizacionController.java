package es.enxenio.GGAL1701.controller.publica;

import com.fasterxml.jackson.annotation.JsonView;
import es.enxenio.GGAL1701.controller.custom.util.PageableFilter;
import es.enxenio.GGAL1701.controller.util.JsonViews;
import es.enxenio.GGAL1701.model.dominio.organizacion.Organizacion;
import es.enxenio.GGAL1701.model.dominio.organizacion.OrganizacionService;
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
 * Created by hlorenzo on 05/09/2017.
 */
@RestController
@RequestMapping(ConstantesRest.URL_PUBLIC + "/organizacion")
public class OrganizacionController {

    @Inject
    private OrganizacionService organizacionService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Organizacion get(@PathVariable("id") Long id) {
        return organizacionService.get(id);
    }

    @JsonView(JsonViews.List.class)
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ResponseEntity<Page<Organizacion>> findAll(PageableFilter filter) {
        if (filter != null) {
            return new ResponseEntity<>(organizacionService.filter(filter), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
