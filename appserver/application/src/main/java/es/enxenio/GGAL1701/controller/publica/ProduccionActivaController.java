package es.enxenio.GGAL1701.controller.publica;

import com.fasterxml.jackson.annotation.JsonView;
import es.enxenio.GGAL1701.controller.custom.util.ProduccionActivaFilter;
import es.enxenio.GGAL1701.controller.publica.custom.BusquedaAvanzadaFilter;
import es.enxenio.GGAL1701.controller.util.JsonViews;
import es.enxenio.GGAL1701.model.dominio.produccionActiva.ProduccionActiva;
import es.enxenio.GGAL1701.model.dominio.produccionActiva.ProduccionActivaService;
import es.enxenio.GGAL1701.util.ConstantesRest;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

/**
 * Created by hlorenzo on 10/08/2017.
 **/
@RestController
@RequestMapping(ConstantesRest.URL_PUBLIC + "/produccionActiva")
public class ProduccionActivaController {

    @Inject
    private ProduccionActivaService produccionActivaService;

    @JsonView(JsonViews.List.class)
    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    public ResponseEntity<Page<ProduccionActiva>> findAll(@RequestBody BusquedaAvanzadaFilter filter) {
        if (filter != null) {
            return new ResponseEntity<>(produccionActivaService.filter(filter), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
