package es.enxenio.GGAL1701.controller.admin;


import es.enxenio.GGAL1701.controller.util.HeaderUtil;
import es.enxenio.GGAL1701.model.dominio.lugar.pais.Pais;
import es.enxenio.GGAL1701.model.dominio.lugar.pais.PaisService;
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
 * Created by crodriguez on 06/06/2017.
 */
@RestController
@RequestMapping(ConstantesRest.URL_ADMIN + "/pais")
@PreAuthorize(ConstantesRest.HAS_ROLE_ANHADIR)
public class PaisAdminController {

    @Inject
    private PaisService paisService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Pais> saveOrUpdate(@Valid @RequestBody Pais pais) {
        try {
            if (pais.getId() == null) {
                return new ResponseEntity<>(paisService.save(pais),
                    HeaderUtil.createAlert("admin.pais.create.success"), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(paisService.update(pais),
                    HeaderUtil.createAlert("admin.pais.edit.success"), HttpStatus.OK);
            }
        } catch (InstanceAlreadyExistsException e) {
            return new ResponseEntity<>(
                HeaderUtil.createFailureAlert("admin.form.error.alreadyexists"), HttpStatus.BAD_REQUEST);
        } catch (InstanceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("id") Long id) {
        try {
            paisService.delete(id);
        } catch (InstanceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HeaderUtil.createAlert("admin.pais.delete.success"), HttpStatus.OK);
    }

}
