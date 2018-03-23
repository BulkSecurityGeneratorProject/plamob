package be.civadis.plamob.repository;

import be.civadis.plamob.domain.Livrable;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Livrable entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LivrableRepository extends JpaRepository<Livrable, Long> {

}
