package es.enxenio.GGAL1701.controller.admin;

import es.enxenio.GGAL1701.controller.util.HeaderUtil;
import es.enxenio.GGAL1701.model.dominio.organizacion.Organizacion;
import es.enxenio.GGAL1701.model.dominio.organizacion.OrganizacionService;
import es.enxenio.GGAL1701.model.dominio.organizacion.Organizacion;
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
 * Created by hlorenzo on 05/09/2017.
 */

@RestController
@PreAuthorize(ConstantesRest.HAS_ROLE_ANHADIR)
@RequestMapping(ConstantesRest.URL_ADMIN + "/organizacion")
public class OrganizacionAdminController {



    @Inject
    private OrganizacionService organizacionService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Organizacion> saveOrUpdate(@Valid @RequestBody Organizacion organizacion) {
        try {
            if (organizacion.getId() == null) {
                return new ResponseEntity<>(organizacionService.save(organizacion),
                    HeaderUtil.createAlert("admin.organizacion.create.success"), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(organizacionService.update(organizacion),
                    HeaderUtil.createAlert("admin.organizacion.edit.success"), HttpStatus.OK);
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
            organizacionService.delete(id);
        } catch (InstanceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HeaderUtil.createAlert("admin.organizacion.delete.success"), HttpStatus.OK);
    }


}
