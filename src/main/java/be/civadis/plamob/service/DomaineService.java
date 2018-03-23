package be.civadis.plamob.service;

import be.civadis.plamob.domain.Domaine;
import be.civadis.plamob.repository.DomaineRepository;
import be.civadis.plamob.service.dto.DomaineDTO;
import be.civadis.plamob.service.mapper.DomaineMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Domaine.
 */
@Service
@Transactional
public class DomaineService {

    private final Logger log = LoggerFactory.getLogger(DomaineService.class);

    private final DomaineRepository domaineRepository;

    private final DomaineMapper domaineMapper;

    public DomaineService(DomaineRepository domaineRepository, DomaineMapper domaineMapper) {
        this.domaineRepository = domaineRepository;
        this.domaineMapper = domaineMapper;
    }

    /**
     * Save a domaine.
     *
     * @param domaineDTO the entity to save
     * @return the persisted entity
     */
    public DomaineDTO save(DomaineDTO domaineDTO) {
        log.debug("Request to save Domaine : {}", domaineDTO);
        Domaine domaine = domaineMapper.toEntity(domaineDTO);
        domaine = domaineRepository.save(domaine);
        return domaineMapper.toDto(domaine);
    }

    /**
     * Get all the domaines.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<DomaineDTO> findAll() {
        log.debug("Request to get all Domaines");
        return domaineRepository.findAll().stream()
            .map(domaineMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one domaine by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public DomaineDTO findOne(Long id) {
        log.debug("Request to get Domaine : {}", id);
        Domaine domaine = domaineRepository.findOne(id);
        return domaineMapper.toDto(domaine);
    }

    /**
     * Delete the domaine by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Domaine : {}", id);
        domaineRepository.delete(id);
    }
}
