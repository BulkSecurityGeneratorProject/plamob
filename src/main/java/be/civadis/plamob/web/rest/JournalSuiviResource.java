package be.civadis.plamob.web.rest;

import com.codahale.metrics.annotation.Timed;
import be.civadis.plamob.service.JournalSuiviService;
import be.civadis.plamob.web.rest.errors.BadRequestAlertException;
import be.civadis.plamob.web.rest.util.HeaderUtil;
import be.civadis.plamob.service.dto.JournalSuiviDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing JournalSuivi.
 */
@RestController
@RequestMapping("/api")
public class JournalSuiviResource {

    private final Logger log = LoggerFactory.getLogger(JournalSuiviResource.class);

    private static final String ENTITY_NAME = "journalSuivi";

    private final JournalSuiviService journalSuiviService;

    public JournalSuiviResource(JournalSuiviService journalSuiviService) {
        this.journalSuiviService = journalSuiviService;
    }

    /**
     * POST  /journal-suivis : Create a new journalSuivi.
     *
     * @param journalSuiviDTO the journalSuiviDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new journalSuiviDTO, or with status 400 (Bad Request) if the journalSuivi has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/journal-suivis")
    @Timed
    public ResponseEntity<JournalSuiviDTO> createJournalSuivi(@RequestBody JournalSuiviDTO journalSuiviDTO) throws URISyntaxException {
        log.debug("REST request to save JournalSuivi : {}", journalSuiviDTO);
        if (journalSuiviDTO.getId() != null) {
            throw new BadRequestAlertException("A new journalSuivi cannot already have an ID", ENTITY_NAME, "idexists");
        }
        JournalSuiviDTO result = journalSuiviService.save(journalSuiviDTO);
        return ResponseEntity.created(new URI("/api/journal-suivis/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /journal-suivis : Updates an existing journalSuivi.
     *
     * @param journalSuiviDTO the journalSuiviDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated journalSuiviDTO,
     * or with status 400 (Bad Request) if the journalSuiviDTO is not valid,
     * or with status 500 (Internal Server Error) if the journalSuiviDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/journal-suivis")
    @Timed
    public ResponseEntity<JournalSuiviDTO> updateJournalSuivi(@RequestBody JournalSuiviDTO journalSuiviDTO) throws URISyntaxException {
        log.debug("REST request to update JournalSuivi : {}", journalSuiviDTO);
        if (journalSuiviDTO.getId() == null) {
            return createJournalSuivi(journalSuiviDTO);
        }
        JournalSuiviDTO result = journalSuiviService.save(journalSuiviDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, journalSuiviDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /journal-suivis : get all the journalSuivis.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of journalSuivis in body
     */
    @GetMapping("/journal-suivis")
    @Timed
    public List<JournalSuiviDTO> getAllJournalSuivis() {
        log.debug("REST request to get all JournalSuivis");
        return journalSuiviService.findAll();
        }

    /**
     * GET  /journal-suivis/:id : get the "id" journalSuivi.
     *
     * @param id the id of the journalSuiviDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the journalSuiviDTO, or with status 404 (Not Found)
     */
    @GetMapping("/journal-suivis/{id}")
    @Timed
    public ResponseEntity<JournalSuiviDTO> getJournalSuivi(@PathVariable Long id) {
        log.debug("REST request to get JournalSuivi : {}", id);
        JournalSuiviDTO journalSuiviDTO = journalSuiviService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(journalSuiviDTO));
    }

    /**
     * DELETE  /journal-suivis/:id : delete the "id" journalSuivi.
     *
     * @param id the id of the journalSuiviDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/journal-suivis/{id}")
    @Timed
    public ResponseEntity<Void> deleteJournalSuivi(@PathVariable Long id) {
        log.debug("REST request to delete JournalSuivi : {}", id);
        journalSuiviService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
