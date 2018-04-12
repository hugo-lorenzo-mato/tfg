package es.enxenio.GGAL1701.controller.admin;


import es.enxenio.GGAL1701.controller.util.HeaderUtil;
import es.enxenio.GGAL1701.model.dominio.funciones.funcionOrganizacion.FuncionOrganizacion;
import es.enxenio.GGAL1701.model.dominio.funciones.funcionOrganizacion.FuncionOrganizacionService;
import es.enxenio.GGAL1701.model.dominio.funciones.funcionOrganizacion.FuncionOrganizacionService;
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
@RequestMapping(ConstantesRest.URL_ADMIN + "/funcionOrganizacion")
@PreAuthorize(ConstantesRest.HAS_ROLE_ANHADIR)
public class FuncionOrganizacionAdminController {

    @Inject
    private FuncionOrganizacionService funcionOrganizacionService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<FuncionOrganizacion> saveOrUpdate(@Valid @RequestBody FuncionOrganizacion funcionOrganizacion) {
        try {
            if (funcionOrganizacion.getId() == null) {
                return new ResponseEntity<>(funcionOrganizacionService.save(funcionOrganizacion),
                    HeaderUtil.createAlert("admin.funcionOrganizacion.create.success"), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(funcionOrganizacionService.update(funcionOrganizacion),
                    HeaderUtil.createAlert("admin.funcionOrganizacion.edit.success"), HttpStatus.OK);
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
            funcionOrganizacionService.delete(id);
        } catch (InstanceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HeaderUtil.createAlert("admin.funcionOrganizacion.delete.success"), HttpStatus.OK);
    }

}
