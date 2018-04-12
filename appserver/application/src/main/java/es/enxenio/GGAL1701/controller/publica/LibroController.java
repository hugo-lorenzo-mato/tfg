package es.enxenio.GGAL1701.controller.publica;

import com.fasterxml.jackson.annotation.JsonView;
import es.enxenio.GGAL1701.controller.custom.util.LibroFilter;
import es.enxenio.GGAL1701.controller.util.JsonViews;
import es.enxenio.GGAL1701.model.dominio.libro.Libro;
import es.enxenio.GGAL1701.model.dominio.libro.LibroService;
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
 * Created by hlorenzo on 08/08/2017.
 **/
@RestController
@RequestMapping(ConstantesRest.URL_PUBLIC + "/libro")
public class LibroController {

    @Inject
    private LibroService libroService;


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Libro get(@PathVariable("id") Long id) {
        return libroService.get(id);
    }

    @JsonView(JsonViews.List.class)
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ResponseEntity<Page<Libro>> findAll(LibroFilter filter) {
        if (filter != null) {
            return new ResponseEntity<>(libroService.filter(filter), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
