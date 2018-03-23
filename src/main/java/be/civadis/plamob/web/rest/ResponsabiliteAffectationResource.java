package be.civadis.plamob.web.rest;

import com.codahale.metrics.annotation.Timed;
import be.civadis.plamob.service.ResponsabiliteAffectationService;
import be.civadis.plamob.web.rest.errors.BadRequestAlertException;
import be.civadis.plamob.web.rest.util.HeaderUtil;
import be.civadis.plamob.service.dto.ResponsabiliteAffectationDTO;
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
 * REST controller for managing ResponsabiliteAffectation.
 */
@RestController
@RequestMapping("/api")
public class ResponsabiliteAffectationResource {

    private final Logger log = LoggerFactory.getLogger(ResponsabiliteAffectationResource.class);

    private static final String ENTITY_NAME = "responsabiliteAffectation";

    private final ResponsabiliteAffectationService responsabiliteAffectationService;

    public ResponsabiliteAffectationResource(ResponsabiliteAffectationService responsabiliteAffectationService) {
        this.responsabiliteAffectationService = responsabiliteAffectationService;
    }

    /**
     * POST  /responsabilite-affectations : Create a new responsabiliteAffectation.
     *
     * @param responsabiliteAffectationDTO the responsabiliteAffectationDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new responsabiliteAffectationDTO, or with status 400 (Bad Request) if the responsabiliteAffectation has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/responsabilite-affectations")
    @Timed
    public ResponseEntity<ResponsabiliteAffectationDTO> createResponsabiliteAffectation(@RequestBody ResponsabiliteAffectationDTO responsabiliteAffectationDTO) throws URISyntaxException {
        log.debug("REST request to save ResponsabiliteAffectation : {}", responsabiliteAffectationDTO);
        if (responsabiliteAffectationDTO.getId() != null) {
            throw new BadRequestAlertException("A new responsabiliteAffectation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ResponsabiliteAffectationDTO result = responsabiliteAffectationService.save(responsabiliteAffectationDTO);
        return ResponseEntity.created(new URI("/api/responsabilite-affectations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /responsabilite-affectations : Updates an existing responsabiliteAffectation.
     *
     * @param responsabiliteAffectationDTO the responsabiliteAffectationDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated responsabiliteAffectationDTO,
     * or with status 400 (Bad Request) if the responsabiliteAffectationDTO is not valid,
     * or with status 500 (Internal Server Error) if the responsabiliteAffectationDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/responsabilite-affectations")
    @Timed
    public ResponseEntity<ResponsabiliteAffectationDTO> updateResponsabiliteAffectation(@RequestBody ResponsabiliteAffectationDTO responsabiliteAffectationDTO) throws URISyntaxException {
        log.debug("REST request to update ResponsabiliteAffectation : {}", responsabiliteAffectationDTO);
        if (responsabiliteAffectationDTO.getId() == null) {
            return createResponsabiliteAffectation(responsabiliteAffectationDTO);
        }
        ResponsabiliteAffectationDTO result = responsabiliteAffectationService.save(responsabiliteAffectationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, responsabiliteAffectationDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /responsabilite-affectations : get all the responsabiliteAffectations.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of responsabiliteAffectations in body
     */
    @GetMapping("/responsabilite-affectations")
    @Timed
    public List<ResponsabiliteAffectationDTO> getAllResponsabiliteAffectations() {
        log.debug("REST request to get all ResponsabiliteAffectations");
        return responsabiliteAffectationService.findAll();
        }

    /**
     * GET  /responsabilite-affectations/:id : get the "id" responsabiliteAffectation.
     *
     * @param id the id of the responsabiliteAffectationDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the responsabiliteAffectationDTO, or with status 404 (Not Found)
     */
    @GetMapping("/responsabilite-affectations/{id}")
    @Timed
    public ResponseEntity<ResponsabiliteAffectationDTO> getResponsabiliteAffectation(@PathVariable Long id) {
        log.debug("REST request to get ResponsabiliteAffectation : {}", id);
        ResponsabiliteAffectationDTO responsabiliteAffectationDTO = responsabiliteAffectationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(responsabiliteAffectationDTO));
    }

    /**
     * DELETE  /responsabilite-affectations/:id : delete the "id" responsabiliteAffectation.
     *
     * @param id the id of the responsabiliteAffectationDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/responsabilite-affectations/{id}")
    @Timed
    public ResponseEntity<Void> deleteResponsabiliteAffectation(@PathVariable Long id) {
        log.debug("REST request to delete ResponsabiliteAffectation : {}", id);
        responsabiliteAffectationService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
