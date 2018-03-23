package be.civadis.plamob.web.rest;

import com.codahale.metrics.annotation.Timed;
import be.civadis.plamob.service.LivrableService;
import be.civadis.plamob.web.rest.errors.BadRequestAlertException;
import be.civadis.plamob.web.rest.util.HeaderUtil;
import be.civadis.plamob.service.dto.LivrableDTO;
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
 * REST controller for managing Livrable.
 */
@RestController
@RequestMapping("/api")
public class LivrableResource {

    private final Logger log = LoggerFactory.getLogger(LivrableResource.class);

    private static final String ENTITY_NAME = "livrable";

    private final LivrableService livrableService;

    public LivrableResource(LivrableService livrableService) {
        this.livrableService = livrableService;
    }

    /**
     * POST  /livrables : Create a new livrable.
     *
     * @param livrableDTO the livrableDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new livrableDTO, or with status 400 (Bad Request) if the livrable has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/livrables")
    @Timed
    public ResponseEntity<LivrableDTO> createLivrable(@RequestBody LivrableDTO livrableDTO) throws URISyntaxException {
        log.debug("REST request to save Livrable : {}", livrableDTO);
        if (livrableDTO.getId() != null) {
            throw new BadRequestAlertException("A new livrable cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LivrableDTO result = livrableService.save(livrableDTO);
        return ResponseEntity.created(new URI("/api/livrables/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /livrables : Updates an existing livrable.
     *
     * @param livrableDTO the livrableDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated livrableDTO,
     * or with status 400 (Bad Request) if the livrableDTO is not valid,
     * or with status 500 (Internal Server Error) if the livrableDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/livrables")
    @Timed
    public ResponseEntity<LivrableDTO> updateLivrable(@RequestBody LivrableDTO livrableDTO) throws URISyntaxException {
        log.debug("REST request to update Livrable : {}", livrableDTO);
        if (livrableDTO.getId() == null) {
            return createLivrable(livrableDTO);
        }
        LivrableDTO result = livrableService.save(livrableDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, livrableDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /livrables : get all the livrables.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of livrables in body
     */
    @GetMapping("/livrables")
    @Timed
    public List<LivrableDTO> getAllLivrables() {
        log.debug("REST request to get all Livrables");
        return livrableService.findAll();
        }

    /**
     * GET  /livrables/:id : get the "id" livrable.
     *
     * @param id the id of the livrableDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the livrableDTO, or with status 404 (Not Found)
     */
    @GetMapping("/livrables/{id}")
    @Timed
    public ResponseEntity<LivrableDTO> getLivrable(@PathVariable Long id) {
        log.debug("REST request to get Livrable : {}", id);
        LivrableDTO livrableDTO = livrableService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(livrableDTO));
    }

    /**
     * DELETE  /livrables/:id : delete the "id" livrable.
     *
     * @param id the id of the livrableDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/livrables/{id}")
    @Timed
    public ResponseEntity<Void> deleteLivrable(@PathVariable Long id) {
        log.debug("REST request to delete Livrable : {}", id);
        livrableService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
