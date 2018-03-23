package be.civadis.plamob.web.rest;

import com.codahale.metrics.annotation.Timed;
import be.civadis.plamob.service.AffectationService;
import be.civadis.plamob.web.rest.errors.BadRequestAlertException;
import be.civadis.plamob.web.rest.util.HeaderUtil;
import be.civadis.plamob.service.dto.AffectationDTO;
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
 * REST controller for managing Affectation.
 */
@RestController
@RequestMapping("/api")
public class AffectationResource {

    private final Logger log = LoggerFactory.getLogger(AffectationResource.class);

    private static final String ENTITY_NAME = "affectation";

    private final AffectationService affectationService;

    public AffectationResource(AffectationService affectationService) {
        this.affectationService = affectationService;
    }

    /**
     * POST  /affectations : Create a new affectation.
     *
     * @param affectationDTO the affectationDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new affectationDTO, or with status 400 (Bad Request) if the affectation has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/affectations")
    @Timed
    public ResponseEntity<AffectationDTO> createAffectation(@RequestBody AffectationDTO affectationDTO) throws URISyntaxException {
        log.debug("REST request to save Affectation : {}", affectationDTO);
        if (affectationDTO.getId() != null) {
            throw new BadRequestAlertException("A new affectation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AffectationDTO result = affectationService.save(affectationDTO);
        return ResponseEntity.created(new URI("/api/affectations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /affectations : Updates an existing affectation.
     *
     * @param affectationDTO the affectationDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated affectationDTO,
     * or with status 400 (Bad Request) if the affectationDTO is not valid,
     * or with status 500 (Internal Server Error) if the affectationDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/affectations")
    @Timed
    public ResponseEntity<AffectationDTO> updateAffectation(@RequestBody AffectationDTO affectationDTO) throws URISyntaxException {
        log.debug("REST request to update Affectation : {}", affectationDTO);
        if (affectationDTO.getId() == null) {
            return createAffectation(affectationDTO);
        }
        AffectationDTO result = affectationService.save(affectationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, affectationDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /affectations : get all the affectations.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of affectations in body
     */
    @GetMapping("/affectations")
    @Timed
    public List<AffectationDTO> getAllAffectations() {
        log.debug("REST request to get all Affectations");
        return affectationService.findAll();
        }

    /**
     * GET  /affectations/:id : get the "id" affectation.
     *
     * @param id the id of the affectationDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the affectationDTO, or with status 404 (Not Found)
     */
    @GetMapping("/affectations/{id}")
    @Timed
    public ResponseEntity<AffectationDTO> getAffectation(@PathVariable Long id) {
        log.debug("REST request to get Affectation : {}", id);
        AffectationDTO affectationDTO = affectationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(affectationDTO));
    }

    /**
     * DELETE  /affectations/:id : delete the "id" affectation.
     *
     * @param id the id of the affectationDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/affectations/{id}")
    @Timed
    public ResponseEntity<Void> deleteAffectation(@PathVariable Long id) {
        log.debug("REST request to delete Affectation : {}", id);
        affectationService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
