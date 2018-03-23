package be.civadis.plamob.web.rest;

import com.codahale.metrics.annotation.Timed;
import be.civadis.plamob.service.RessourceService;
import be.civadis.plamob.web.rest.errors.BadRequestAlertException;
import be.civadis.plamob.web.rest.util.HeaderUtil;
import be.civadis.plamob.service.dto.RessourceDTO;
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
 * REST controller for managing Ressource.
 */
@RestController
@RequestMapping("/api")
public class RessourceResource {

    private final Logger log = LoggerFactory.getLogger(RessourceResource.class);

    private static final String ENTITY_NAME = "ressource";

    private final RessourceService ressourceService;

    public RessourceResource(RessourceService ressourceService) {
        this.ressourceService = ressourceService;
    }

    /**
     * POST  /ressources : Create a new ressource.
     *
     * @param ressourceDTO the ressourceDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new ressourceDTO, or with status 400 (Bad Request) if the ressource has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ressources")
    @Timed
    public ResponseEntity<RessourceDTO> createRessource(@RequestBody RessourceDTO ressourceDTO) throws URISyntaxException {
        log.debug("REST request to save Ressource : {}", ressourceDTO);
        if (ressourceDTO.getId() != null) {
            throw new BadRequestAlertException("A new ressource cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RessourceDTO result = ressourceService.save(ressourceDTO);
        return ResponseEntity.created(new URI("/api/ressources/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ressources : Updates an existing ressource.
     *
     * @param ressourceDTO the ressourceDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated ressourceDTO,
     * or with status 400 (Bad Request) if the ressourceDTO is not valid,
     * or with status 500 (Internal Server Error) if the ressourceDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ressources")
    @Timed
    public ResponseEntity<RessourceDTO> updateRessource(@RequestBody RessourceDTO ressourceDTO) throws URISyntaxException {
        log.debug("REST request to update Ressource : {}", ressourceDTO);
        if (ressourceDTO.getId() == null) {
            return createRessource(ressourceDTO);
        }
        RessourceDTO result = ressourceService.save(ressourceDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, ressourceDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ressources : get all the ressources.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of ressources in body
     */
    @GetMapping("/ressources")
    @Timed
    public List<RessourceDTO> getAllRessources() {
        log.debug("REST request to get all Ressources");
        return ressourceService.findAll();
        }

    /**
     * GET  /ressources/:id : get the "id" ressource.
     *
     * @param id the id of the ressourceDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the ressourceDTO, or with status 404 (Not Found)
     */
    @GetMapping("/ressources/{id}")
    @Timed
    public ResponseEntity<RessourceDTO> getRessource(@PathVariable Long id) {
        log.debug("REST request to get Ressource : {}", id);
        RessourceDTO ressourceDTO = ressourceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(ressourceDTO));
    }

    /**
     * DELETE  /ressources/:id : delete the "id" ressource.
     *
     * @param id the id of the ressourceDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ressources/{id}")
    @Timed
    public ResponseEntity<Void> deleteRessource(@PathVariable Long id) {
        log.debug("REST request to delete Ressource : {}", id);
        ressourceService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
