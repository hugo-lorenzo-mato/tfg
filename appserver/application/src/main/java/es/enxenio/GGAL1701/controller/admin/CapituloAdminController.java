package es.enxenio.GGAL1701.controller.admin;

import es.enxenio.GGAL1701.controller.util.HeaderUtil;
import es.enxenio.GGAL1701.model.dominio.capitulo.CapituloService;
import es.enxenio.GGAL1701.model.dominio.capitulo.Capitulo;
import es.enxenio.GGAL1701.model.dominio.capitulo.CapituloService;
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
 * Created by hlorenzo on 03/09/2017.
 */

@RestController
@PreAuthorize(ConstantesRest.HAS_ROLE_ANHADIR)
@RequestMapping(ConstantesRest.URL_ADMIN + "/capitulo")
public class CapituloAdminController {



    @Inject
    private CapituloService capituloService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Capitulo> saveOrUpdate(@Valid @RequestBody Capitulo capitulo) {
        try {
            if (capitulo.getId() == null) {
                return new ResponseEntity<>(capituloService.save(capitulo),
                    HeaderUtil.createAlert("admin.capitulo.create.success"), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(capituloService.update(capitulo),
                    HeaderUtil.createAlert("admin.capitulo.edit.success"), HttpStatus.OK);
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
            capituloService.delete(id);
        } catch (InstanceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HeaderUtil.createAlert("admin.capitulo.delete.success"), HttpStatus.OK);
    }


}
