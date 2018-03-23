package be.civadis.plamob.service;

import be.civadis.plamob.domain.Profil;
import be.civadis.plamob.repository.ProfilRepository;
import be.civadis.plamob.service.dto.ProfilDTO;
import be.civadis.plamob.service.mapper.ProfilMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Profil.
 */
@Service
@Transactional
public class ProfilService {

    private final Logger log = LoggerFactory.getLogger(ProfilService.class);

    private final ProfilRepository profilRepository;

    private final ProfilMapper profilMapper;

    public ProfilService(ProfilRepository profilRepository, ProfilMapper profilMapper) {
        this.profilRepository = profilRepository;
        this.profilMapper = profilMapper;
    }

    /**
     * Save a profil.
     *
     * @param profilDTO the entity to save
     * @return the persisted entity
     */
    public ProfilDTO save(ProfilDTO profilDTO) {
        log.debug("Request to save Profil : {}", profilDTO);
        Profil profil = profilMapper.toEntity(profilDTO);
        profil = profilRepository.save(profil);
        return profilMapper.toDto(profil);
    }

    /**
     * Get all the profils.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<ProfilDTO> findAll() {
        log.debug("Request to get all Profils");
        return profilRepository.findAll().stream()
            .map(profilMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one profil by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public ProfilDTO findOne(Long id) {
        log.debug("Request to get Profil : {}", id);
        Profil profil = profilRepository.findOne(id);
        return profilMapper.toDto(profil);
    }

    /**
     * Delete the profil by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Profil : {}", id);
        profilRepository.delete(id);
    }
}
