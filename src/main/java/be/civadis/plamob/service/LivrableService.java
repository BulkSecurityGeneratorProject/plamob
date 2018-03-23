package be.civadis.plamob.service;

import be.civadis.plamob.domain.Livrable;
import be.civadis.plamob.repository.LivrableRepository;
import be.civadis.plamob.service.dto.LivrableDTO;
import be.civadis.plamob.service.mapper.LivrableMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Livrable.
 */
@Service
@Transactional
public class LivrableService {

    private final Logger log = LoggerFactory.getLogger(LivrableService.class);

    private final LivrableRepository livrableRepository;

    private final LivrableMapper livrableMapper;

    public LivrableService(LivrableRepository livrableRepository, LivrableMapper livrableMapper) {
        this.livrableRepository = livrableRepository;
        this.livrableMapper = livrableMapper;
    }

    /**
     * Save a livrable.
     *
     * @param livrableDTO the entity to save
     * @return the persisted entity
     */
    public LivrableDTO save(LivrableDTO livrableDTO) {
        log.debug("Request to save Livrable : {}", livrableDTO);
        Livrable livrable = livrableMapper.toEntity(livrableDTO);
        livrable = livrableRepository.save(livrable);
        return livrableMapper.toDto(livrable);
    }

    /**
     * Get all the livrables.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<LivrableDTO> findAll() {
        log.debug("Request to get all Livrables");
        return livrableRepository.findAll().stream()
            .map(livrableMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one livrable by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public LivrableDTO findOne(Long id) {
        log.debug("Request to get Livrable : {}", id);
        Livrable livrable = livrableRepository.findOne(id);
        return livrableMapper.toDto(livrable);
    }

    /**
     * Delete the livrable by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Livrable : {}", id);
        livrableRepository.delete(id);
    }
}
