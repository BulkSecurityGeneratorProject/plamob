package be.civadis.plamob.service;

import be.civadis.plamob.domain.Ressource;
import be.civadis.plamob.repository.RessourceRepository;
import be.civadis.plamob.service.dto.RessourceDTO;
import be.civadis.plamob.service.mapper.RessourceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Ressource.
 */
@Service
@Transactional
public class RessourceService {

    private final Logger log = LoggerFactory.getLogger(RessourceService.class);

    private final RessourceRepository ressourceRepository;

    private final RessourceMapper ressourceMapper;

    public RessourceService(RessourceRepository ressourceRepository, RessourceMapper ressourceMapper) {
        this.ressourceRepository = ressourceRepository;
        this.ressourceMapper = ressourceMapper;
    }

    /**
     * Save a ressource.
     *
     * @param ressourceDTO the entity to save
     * @return the persisted entity
     */
    public RessourceDTO save(RessourceDTO ressourceDTO) {
        log.debug("Request to save Ressource : {}", ressourceDTO);
        Ressource ressource = ressourceMapper.toEntity(ressourceDTO);
        ressource = ressourceRepository.save(ressource);
        return ressourceMapper.toDto(ressource);
    }

    /**
     * Get all the ressources.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<RessourceDTO> findAll() {
        log.debug("Request to get all Ressources");
        return ressourceRepository.findAll().stream()
            .map(ressourceMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one ressource by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public RessourceDTO findOne(Long id) {
        log.debug("Request to get Ressource : {}", id);
        Ressource ressource = ressourceRepository.findOne(id);
        return ressourceMapper.toDto(ressource);
    }

    /**
     * Delete the ressource by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Ressource : {}", id);
        ressourceRepository.delete(id);
    }
}
