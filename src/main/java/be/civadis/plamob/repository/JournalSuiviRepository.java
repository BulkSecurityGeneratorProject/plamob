package be.civadis.plamob.repository;

import be.civadis.plamob.domain.JournalSuivi;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the JournalSuivi entity.
 */
@SuppressWarnings("unused")
@Repository
public interface JournalSuiviRepository extends JpaRepository<JournalSuivi, Long> {

}
