package be.civadis.plamob.service;

import be.civadis.plamob.domain.ResponsabiliteAffectation;
import be.civadis.plamob.repository.ResponsabiliteAffectationRepository;
import be.civadis.plamob.service.dto.ResponsabiliteAffectationDTO;
import be.civadis.plamob.service.mapper.ResponsabiliteAffectationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing ResponsabiliteAffectation.
 */
@Service
@Transactional
public class ResponsabiliteAffectationService {

    private final Logger log = LoggerFactory.getLogger(ResponsabiliteAffectationService.class);

    private final ResponsabiliteAffectationRepository responsabiliteAffectationRepository;

    private final ResponsabiliteAffectationMapper responsabiliteAffectationMapper;

    public ResponsabiliteAffectationService(ResponsabiliteAffectationRepository responsabiliteAffectationRepository, ResponsabiliteAffectationMapper responsabiliteAffectationMapper) {
        this.responsabiliteAffectationRepository = responsabiliteAffectationRepository;
        this.responsabiliteAffectationMapper = responsabiliteAffectationMapper;
    }

    /**
     * Save a responsabiliteAffectation.
     *
     * @param responsabiliteAffectationDTO the entity to save
     * @return the persisted entity
     */
    public ResponsabiliteAffectationDTO save(ResponsabiliteAffectationDTO responsabiliteAffectationDTO) {
        log.debug("Request to save ResponsabiliteAffectation : {}", responsabiliteAffectationDTO);
        ResponsabiliteAffectation responsabiliteAffectation = responsabiliteAffectationMapper.toEntity(responsabiliteAffectationDTO);
        responsabiliteAffectation = responsabiliteAffectationRepository.save(responsabiliteAffectation);
        return responsabiliteAffectationMapper.toDto(responsabiliteAffectation);
    }

    /**
     * Get all the responsabiliteAffectations.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<ResponsabiliteAffectationDTO> findAll() {
        log.debug("Request to get all ResponsabiliteAffectations");
        return responsabiliteAffectationRepository.findAll().stream()
            .map(responsabiliteAffectationMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one responsabiliteAffectation by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public ResponsabiliteAffectationDTO findOne(Long id) {
        log.debug("Request to get ResponsabiliteAffectation : {}", id);
        ResponsabiliteAffectation responsabiliteAffectation = responsabiliteAffectationRepository.findOne(id);
        return responsabiliteAffectationMapper.toDto(responsabiliteAffectation);
    }

    /**
     * Delete the responsabiliteAffectation by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete ResponsabiliteAffectation : {}", id);
        responsabiliteAffectationRepository.delete(id);
    }
}
