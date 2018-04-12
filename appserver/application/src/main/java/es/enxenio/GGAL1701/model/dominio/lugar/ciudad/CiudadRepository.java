package es.enxenio.GGAL1701.model.dominio.lugar.ciudad;

import es.enxenio.GGAL1701.model.dominio.lugar.pais.Pais;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by crodriguez on 11/05/2017.
 */
public interface CiudadRepository extends JpaRepository<Ciudad, Long> {

    @Query(value = "SELECT c" +
        " FROM Ciudad c" +
        " WHERE ( str(c.id) like %:query%" +
        " OR lower(unaccent(c.nombre)) like %:query% )" +
        " AND (:pais is null OR c.pais = :pais)")
    Page<Ciudad> filter(@Param("query") String query, @Param("pais") Pais pais, Pageable pageable);

}
