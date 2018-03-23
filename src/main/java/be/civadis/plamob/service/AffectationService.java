package be.civadis.plamob.service;

import be.civadis.plamob.domain.Affectation;
import be.civadis.plamob.repository.AffectationRepository;
import be.civadis.plamob.service.dto.AffectationDTO;
import be.civadis.plamob.service.mapper.AffectationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Affectation.
 */
@Service
@Transactional
public class AffectationService {

    private final Logger log = LoggerFactory.getLogger(AffectationService.class);

    private final AffectationRepository affectationRepository;

    private final AffectationMapper affectationMapper;

    public AffectationService(AffectationRepository affectationRepository, AffectationMapper affectationMapper) {
        this.affectationRepository = affectationRepository;
        this.affectationMapper = affectationMapper;
    }

    /**
     * Save a affectation.
     *
     * @param affectationDTO the entity to save
     * @return the persisted entity
     */
    public AffectationDTO save(AffectationDTO affectationDTO) {
        log.debug("Request to save Affectation : {}", affectationDTO);
        Affectation affectation = affectationMapper.toEntity(affectationDTO);
        affectation = affectationRepository.save(affectation);
        return affectationMapper.toDto(affectation);
    }

    /**
     * Get all the affectations.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<AffectationDTO> findAll() {
        log.debug("Request to get all Affectations");
        return affectationRepository.findAll().stream()
            .map(affectationMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one affectation by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public AffectationDTO findOne(Long id) {
        log.debug("Request to get Affectation : {}", id);
        Affectation affectation = affectationRepository.findOne(id);
        return affectationMapper.toDto(affectation);
    }

    /**
     * Delete the affectation by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Affectation : {}", id);
        affectationRepository.delete(id);
    }
}
