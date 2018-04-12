package es.enxenio.GGAL1701.controller.admin;

import es.enxenio.GGAL1701.controller.util.HeaderUtil;
import es.enxenio.GGAL1701.model.dominio.produccionActiva.ProduccionActiva;
import es.enxenio.GGAL1701.model.dominio.produccionActiva.ProduccionActivaService;
import es.enxenio.GGAL1701.util.ConstantesRest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import javax.validation.Valid;

/**
 * Created by hlorenzo on 10/08/2017.
 **/
@RestController
@PreAuthorize(ConstantesRest.HAS_ROLE_ANHADIR)
@RequestMapping(ConstantesRest.URL_ADMIN + "/produccionActiva")
public class ProduccionActivaAdminController {

    @Inject
    private ProduccionActivaService produccionActivaService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<ProduccionActiva> saveOrUpdate(@Valid @RequestBody ProduccionActiva produccionActiva) {
        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("id") Long id) {
        return null;
    }

}
