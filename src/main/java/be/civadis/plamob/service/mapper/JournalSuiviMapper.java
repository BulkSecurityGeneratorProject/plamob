package be.civadis.plamob.service.mapper;

import be.civadis.plamob.domain.*;
import be.civadis.plamob.service.dto.JournalSuiviDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity JournalSuivi and its DTO JournalSuiviDTO.
 */
@Mapper(componentModel = "spring", uses = {MissionMapper.class})
public interface JournalSuiviMapper extends EntityMapper<JournalSuiviDTO, JournalSuivi> {

    @Mapping(source = "mission.id", target = "missionId")
    JournalSuiviDTO toDto(JournalSuivi journalSuivi);

    @Mapping(source = "missionId", target = "mission")
    JournalSuivi toEntity(JournalSuiviDTO journalSuiviDTO);

    default JournalSuivi fromId(Long id) {
        if (id == null) {
            return null;
        }
        JournalSuivi journalSuivi = new JournalSuivi();
        journalSuivi.setId(id);
        return journalSuivi;
    }
}
