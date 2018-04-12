package es.enxenio.GGAL1701.model.dominio.organizacion;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by hlorenzo on 05/09/2017.
 */
public interface OrganizacionRepository extends JpaRepository<Organizacion, Long> {

    @Query(value = "SELECT o" +
        " FROM Organizacion o" +
        " WHERE ( str(o.id) like %:query%" +
        " OR lower(unaccent(o.nombre)) like %:query% )")
        Page<Organizacion> filter(@Param("query") String query, Pageable pageable);
}
