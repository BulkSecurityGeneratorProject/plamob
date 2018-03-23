package be.civadis.plamob.service;

import be.civadis.plamob.domain.Mission;
import be.civadis.plamob.repository.MissionRepository;
import be.civadis.plamob.service.dto.MissionDTO;
import be.civadis.plamob.service.mapper.MissionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Mission.
 */
@Service
@Transactional
public class MissionService {

    private final Logger log = LoggerFactory.getLogger(MissionService.class);

    private final MissionRepository missionRepository;

    private final MissionMapper missionMapper;

    public MissionService(MissionRepository missionRepository, MissionMapper missionMapper) {
        this.missionRepository = missionRepository;
        this.missionMapper = missionMapper;
    }

    /**
     * Save a mission.
     *
     * @param missionDTO the entity to save
     * @return the persisted entity
     */
    public MissionDTO save(MissionDTO missionDTO) {
        log.debug("Request to save Mission : {}", missionDTO);
        Mission mission = missionMapper.toEntity(missionDTO);
        mission = missionRepository.save(mission);
        return missionMapper.toDto(mission);
    }

    /**
     * Get all the missions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<MissionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Missions");
        return missionRepository.findAll(pageable)
            .map(missionMapper::toDto);
    }

    /**
     * Get one mission by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public MissionDTO findOne(Long id) {
        log.debug("Request to get Mission : {}", id);
        Mission mission = missionRepository.findOne(id);
        return missionMapper.toDto(mission);
    }

    /**
     * Delete the mission by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Mission : {}", id);
        missionRepository.delete(id);
    }
}
