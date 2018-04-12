package es.enxenio.GGAL1701.model.dominio.edicion;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
/**
 * Created by hlorenzo on 03/09/2017.
 **/
public interface EdicionRepository  extends JpaRepository<Edicion, Long> {

    @Query(value = "SELECT e" +
        " FROM Edicion e" +
        " WHERE ( str(e.id) like %:query%" +
        " OR lower(unaccent(e.titulo)) like %:query% )")
        Page<Edicion> filter(@Param("query") String query, Pageable pageable);
}
