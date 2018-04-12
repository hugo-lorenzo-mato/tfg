package es.enxenio.GGAL1701.model.dominio.capitulo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by hlorenzo on 08/09/2017.
 **/
public interface CapituloRepository extends JpaRepository<Capitulo, Long> {

    @Query(value = "SELECT c" +
        " FROM Capitulo c" +
        " WHERE ( str(c.id) like %:query%" +
        " OR lower(unaccent(c.titulo)) like %:query% )")
        Page<Capitulo> filter(@Param("query") String query, Pageable pageable);
}
