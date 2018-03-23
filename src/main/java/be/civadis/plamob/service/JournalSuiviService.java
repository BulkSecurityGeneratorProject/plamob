package be.civadis.plamob.service;

import be.civadis.plamob.domain.JournalSuivi;
import be.civadis.plamob.repository.JournalSuiviRepository;
import be.civadis.plamob.service.dto.JournalSuiviDTO;
import be.civadis.plamob.service.mapper.JournalSuiviMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing JournalSuivi.
 */
@Service
@Transactional
public class JournalSuiviService {

    private final Logger log = LoggerFactory.getLogger(JournalSuiviService.class);

    private final JournalSuiviRepository journalSuiviRepository;

    private final JournalSuiviMapper journalSuiviMapper;

    public JournalSuiviService(JournalSuiviRepository journalSuiviRepository, JournalSuiviMapper journalSuiviMapper) {
        this.journalSuiviRepository = journalSuiviRepository;
        this.journalSuiviMapper = journalSuiviMapper;
    }

    /**
     * Save a journalSuivi.
     *
     * @param journalSuiviDTO the entity to save
     * @return the persisted entity
     */
    public JournalSuiviDTO save(JournalSuiviDTO journalSuiviDTO) {
        log.debug("Request to save JournalSuivi : {}", journalSuiviDTO);
        JournalSuivi journalSuivi = journalSuiviMapper.toEntity(journalSuiviDTO);
        journalSuivi = journalSuiviRepository.save(journalSuivi);
        return journalSuiviMapper.toDto(journalSuivi);
    }

    /**
     * Get all the journalSuivis.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<JournalSuiviDTO> findAll() {
        log.debug("Request to get all JournalSuivis");
        return journalSuiviRepository.findAll().stream()
            .map(journalSuiviMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one journalSuivi by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public JournalSuiviDTO findOne(Long id) {
        log.debug("Request to get JournalSuivi : {}", id);
        JournalSuivi journalSuivi = journalSuiviRepository.findOne(id);
        return journalSuiviMapper.toDto(journalSuivi);
    }

    /**
     * Delete the journalSuivi by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete JournalSuivi : {}", id);
        journalSuiviRepository.delete(id);
    }
}
