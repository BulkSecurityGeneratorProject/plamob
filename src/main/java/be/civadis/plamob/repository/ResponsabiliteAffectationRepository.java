package be.civadis.plamob.repository;

import be.civadis.plamob.domain.ResponsabiliteAffectation;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the ResponsabiliteAffectation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ResponsabiliteAffectationRepository extends JpaRepository<ResponsabiliteAffectation, Long> {

}
